package it.michelelacorte.swipeablecard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Michele on 29/11/2015.
 */

/**
 * Swipeable Card is a custom view which use CardView to create
 * a custom layout of card.
 * @author Michele Lacorte
 */
public class SwipeableCard extends LinearLayout implements View.OnClickListener, AnimationCard {
    CardView card;
    ImageView image;
    TextView text;
    TextView subTitle;
    Toolbar toolbar;
    int height;
/*
    public SwipeableCard(Context context) {
        super(context);
        this.setClickable(true);
        this.setOnClickListener(this);
    }*/

    /**
     * Public constructor of Swipeable Card.
     * @param context Set application context
     * @param attrs Set attributeSet
     */
    public SwipeableCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        /*
        Inflater custom layout to view.
         */
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rootView = inflater.inflate(R.layout.swipeable_card, this, true);

        /*
        Get Screen Size.
         */
        height = getScreenSize(context);

        /*
        Set up xml object.
         */
        card = (CardView) findViewById(R.id.card);
        image = (ImageView) findViewById(R.id.image);
        text = (TextView) findViewById(R.id.text);
        subTitle = (TextView) findViewById(R.id.subTitle);

        /*
        Check if subTitle is set.
         */
        if(OptionView.getOptionView().isSubTitle())
        {
            subTitle.setVisibility(View.VISIBLE);
            subTitle.setText(OptionView.getOptionView().getSubTitle());
        }
        /*
        Check if Image is set.
         */
        if(OptionView.getOptionView().isImage())
        {
            text.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            image.setImageResource(OptionView.getOptionView().getImage());
        }
        /*
        Check if Text is set.
         */
        if(OptionView.getOptionView().isText())
        {
            image.setVisibility(View.GONE);
            text.setVisibility(View.VISIBLE);
            text.setText(OptionView.getOptionView().getText());
        }
        /*
        Configure the toolbar
         */
        initToolbar(context);

        /*
        Set listener for swipeable card.
         */
        this.setClickable(true);
        this.setOnClickListener(this);

        /*
        Set listener for root view.
         */
        rootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCardDown(card, toolbar, OptionView.getOptionView().getDuration());
            }
        });
    }

    /**
     * Get size of screen.
     * @param context
     * @return
     */
    private int getScreenSize(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    /**
     * Initialize Toolbar.
     * @param context
     */
    private void initToolbar(Context context)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(OptionView.getOptionView().getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(ContextCompat.getColor(context, OptionView.getOptionView().getColorTitle()));
            toolbar.setBackgroundColor(ContextCompat.getColor(context, OptionView.getOptionView().getColorToolbar()));

        } else {
            toolbar.setTitleTextColor(getResources().getColor(OptionView.getOptionView().getColorTitle()));
            toolbar.setBackgroundColor(getResources().getColor(OptionView.getOptionView().getColorToolbar()));
        }
        if (OptionView.getOptionView().isMenuItem()) {
            toolbar.inflateMenu(OptionView.getOptionView().getMenuItem());
            toolbar.setOnMenuItemClickListener(OptionView.getOptionView().getToolbarListener());
        }

        toolbar.setOnClickListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        animationCardStart(card, toolbar);
    }

    /**
     * Animation Card at start layout, please do not modify this.
     * @param card
     * @param toolbar
     */
    @Override
    public void animationCardStart(final CardView card, final Toolbar toolbar){
        new CountDownTimer(300, 1) {
            public void onTick(long millisUntilFinished) {
                card.setTranslationY(height - ((int)(toolbar.getHeight() * 1.7)));
            }

            public void onFinish() {
                card.setTranslationY(height - ((int)(toolbar.getHeight() * 1.7)));
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
     * onClick method for animate swipeable card.
     * @param v View
     */
    @Override
    public void onClick(View v) {
        animationCardUp(card, toolbar, OptionView.getOptionView().getDuration());
    }

}
