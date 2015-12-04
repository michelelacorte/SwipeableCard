package it.michelelacorte.swipeablecard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Michele on 02/12/2015.
 */

/**
 * This is an Adapter for RecyclerView that is implemented for al user!
 * @author Michele Lacorte
 */
public class SwipeableCardAdapter extends RecyclerView.Adapter<SwipeableCardAdapter.CardViewHolder> implements AnimationCard{

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        CardView card;
        ImageView image;
        TextView text;
        TextView subTitle;
        Toolbar toolbar;

        CardViewHolder(View itemView) {
            super(itemView);
            /*
            Set up recycler view custom object.
             */
            card = (CardView)itemView.findViewById(R.id.card);
            image = (ImageView) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.image);
            text = (TextView) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.text);
            subTitle = (TextView) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.subTitle);
            toolbar = (Toolbar) itemView.findViewById(it.michelelacorte.swipeablecard.R.id.toolbar);
        }


    }
    Context context;
    List<OptionView> optionsView;
    int height;
    int width;
    public SwipeableCardAdapter(List<OptionView> optionsView, Context context){
        this.optionsView = optionsView;
        this.context = context;
    }

    /**
     * Animation Card at start layout, please do not modify this.
     * @param card
     * @param toolbar
     */
    @Override
    public void animationCardStart(final CardView card, final Toolbar toolbar){
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                card.animate()
                        .translationY(height - ((int) (toolbar.getHeight() * 1.7)))
                        .setDuration(1)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                card.setVisibility(View.VISIBLE);
                            }
                        }).start();
            }
        }.start();

    }

    /**
     * Animation Card for down animation, please do not modify this.
     * @param card
     * @param toolbar
     */
    @Override
    public void animationCardDown(final CardView card, final Toolbar toolbar, final long duration){
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                card.animate()
                        .translationY(height - ((int)(toolbar.getHeight() * 1.7)))
                        .setDuration(duration).start();
            }
        }.start();
    }

    /**
     * Animation Card for up animation, please do not modify this.
     * @param card
     * @param toolbar
     */
    @Override
    public void animationCardUp(final CardView card, final Toolbar toolbar, final long duration){
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                card.animate()
                        .translationY(height - (card.getHeight() + toolbar.getHeight()))
                        .setDuration(duration).start();
            }
        }.start();
    }


    /**
     * Get number of option view.
     * @return
     */
    @Override
    public int getItemCount() {
        return optionsView.size();
    }

    private Point getScreenSize(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new Point(size.x, size.y);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Inflate layout on recycler view.
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_card, viewGroup, false);
        final CardViewHolder cvh = new CardViewHolder(v);

        //Get screen size.
        width = getScreenSize(context).x;
        height = getScreenSize(context).y;

        //Return view.
        return cvh;

    }

    @Override
    public void onBindViewHolder(final CardViewHolder cardViewHolder, int i) {
        /*
        Set option for all swipeable card on recycler view.
         */
        //Set Text
        final long duration = optionsView.get(i).getDuration();
        cardViewHolder.card.setLayoutParams(new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
        if(optionsView.get(i).isText())
        {
            cardViewHolder.image.setVisibility(View.GONE);
            cardViewHolder.text.setVisibility(View.VISIBLE);
            cardViewHolder.text.setText(optionsView.get(i).getText());
        }
        //Set Sub Title
        if(optionsView.get(i).isSubTitle())
        {
            cardViewHolder.subTitle.setVisibility(View.VISIBLE);
            cardViewHolder.subTitle.setText(optionsView.get(i).getSubTitle());
        }
        //Set Image
        if(optionsView.get(i).isImage())
        {
            cardViewHolder.text.setVisibility(View.GONE);
            cardViewHolder.image.setVisibility(View.VISIBLE);
            cardViewHolder.image.setImageResource(optionsView.get(i).getImage());
        }

        //Set toolbar
        cardViewHolder.toolbar.setTitle(optionsView.get(i).getTitle());

        //Check if has menu item
        if (optionsView.get(i).isMenuItem()) {
            cardViewHolder.toolbar.setOnMenuItemClickListener(optionsView.get(i).getToolbarListener());
            cardViewHolder.toolbar.inflateMenu(optionsView.get(i).getMenuItem());
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cardViewHolder.toolbar.setTitleTextColor(ContextCompat.getColor(context, optionsView.get(i).getColorTitle()));
            cardViewHolder.toolbar.setBackgroundColor(ContextCompat.getColor(context, optionsView.get(i).getColorToolbar()));

        } else {
            cardViewHolder.toolbar.setTitleTextColor(context.getResources().getColor(optionsView.get(i).getColorTitle()));
            cardViewHolder.toolbar.setBackgroundColor(context.getResources().getColor(optionsView.get(i).getColorToolbar()));
        }

        //Start animation at end of creation of swipeable card
         animationCardStart(cardViewHolder.card, cardViewHolder.toolbar);

        //Set Listener for View and Card
        cardViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCardDown(cardViewHolder.card, cardViewHolder.toolbar, duration);
            }
        });

        cardViewHolder.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCardUp(cardViewHolder.card, cardViewHolder.toolbar, duration);
            }
        });
    }



    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

