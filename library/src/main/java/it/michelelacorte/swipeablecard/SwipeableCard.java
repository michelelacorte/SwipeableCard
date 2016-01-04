package it.michelelacorte.swipeablecard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Point;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
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
    FloatingActionButton fab;
    /**
     * Customizable Icon and Text
     */
    ImageView iconBtn1;
    ImageView iconBtn2;
    ImageView iconBtn3;
    TextView textBtn1;
    TextView textBtn2;

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
        /**
         * Set-up customizable Icon and Text
         */
        iconBtn1 = (ImageView) findViewById(R.id.iconBtn1);
        iconBtn2 = (ImageView) findViewById(R.id.iconBtn2);
        iconBtn3 = (ImageView) findViewById(R.id.iconBtn3);
        textBtn1 = (TextView) findViewById(R.id.textBtn1);
        textBtn2 = (TextView) findViewById(R.id.textBtn2);

        card.setRadius(OptionView.getOptionView().getCardRadius());

        fab = (FloatingActionButton) findViewById(R.id.fab);

        /**
         * Check if Additional Option is set
         */
        if(OptionView.getOptionView().getOptionViewAdditional() != null) {
            if(OptionView.getOptionView().getOptionViewAdditional().getFabIcon() > 0)
            {
                fab.setVisibility(View.VISIBLE);
                if(OptionView.getOptionView().getOptionViewAdditional().getFabIcon() > 0) {
                    fab.setImageResource(OptionView.getOptionView().getOptionViewAdditional().getFabIcon());
                }
                if(OptionView.getOptionView().getOptionViewAdditional().getFabBackgroundColor() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, OptionView.getOptionView().getOptionViewAdditional().getFabBackgroundColor())));
                    } else {
                        fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(OptionView.getOptionView().getOptionViewAdditional().getFabBackgroundColor())));
                    }
                }
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerFab() != null) {
                    fab.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerFab());
                }
            }
            /**
             * Check if Additional Icon Button 1
             */
            if (OptionView.getOptionView().getOptionViewAdditional().isIconBtn1()) {
                iconBtn1.setVisibility(View.VISIBLE);
                iconBtn1.setImageResource(OptionView.getOptionView().getOptionViewAdditional().getIconBtn1());
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerIconBtn1() != null) {
                    iconBtn1.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerIconBtn1());
                }
            }
            /**
             * Check if Additional Icon Button 2
             */
            if (OptionView.getOptionView().getOptionViewAdditional().isIconBtn2()) {
                iconBtn2.setVisibility(View.VISIBLE);
                iconBtn2.setImageResource(OptionView.getOptionView().getOptionViewAdditional().getIconBtn2());
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerIconBtn2() != null) {
                    iconBtn2.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerIconBtn2());
                }
            }
            /**
             * Check if Additional Icon Button 3
             */
            if (OptionView.getOptionView().getOptionViewAdditional().isIconBtn3()) {
                iconBtn3.setVisibility(View.VISIBLE);
                iconBtn3.setImageResource(OptionView.getOptionView().getOptionViewAdditional().getIconBtn3());
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerIconBtn3() != null) {
                    iconBtn3.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerIconBtn3());
                }
            }
            /**
             * Check if Additional Text Button 1
             */
            if (OptionView.getOptionView().getOptionViewAdditional().isTextBtn1()) {
                textBtn1.setVisibility(View.VISIBLE);
                textBtn1.setText(OptionView.getOptionView().getOptionViewAdditional().getTextBtn1());
                textBtn1.setTextSize(OptionView.getOptionView().getOptionViewAdditional().getTextSizeBtn1());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textBtn1.setTextColor(ContextCompat.getColor(context, OptionView.getOptionView().getOptionViewAdditional().getTextColorBtn1()));
                } else {
                    textBtn1.setTextColor(getResources().getColor(OptionView.getOptionView().getOptionViewAdditional().getTextColorBtn1()));
                }
                /**
                 * Check if OnClickListener Additional Text Button 1
                 */
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerTextBtn1() != null) {
                    textBtn1.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerTextBtn1());
                }
            }
            /**
             * Check if Additional Text Button 2
             */
            if (OptionView.getOptionView().getOptionViewAdditional().isTextBtn2()) {
                textBtn2.setVisibility(View.VISIBLE);
                textBtn2.setText(OptionView.getOptionView().getOptionViewAdditional().getTextBtn2());
                textBtn2.setTextSize(OptionView.getOptionView().getOptionViewAdditional().getTextSizeBtn2());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textBtn2.setTextColor(ContextCompat.getColor(context, OptionView.getOptionView().getOptionViewAdditional().getTextColorBtn2()));
                } else {
                    textBtn2.setTextColor(getResources().getColor(OptionView.getOptionView().getOptionViewAdditional().getTextColorBtn2()));
                }
                /**
                 * Check if OnClickListener Additional Text Button 2
                 */
                if (OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerTextBtn2() != null) {
                    textBtn2.setOnClickListener(OptionView.getOptionView().getOptionViewAdditional().getOnClickListenerTextBtn2());
                }
            }
        }


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
                fab.setTranslationY((height - ((int) (toolbar.getHeight() * 1.7))) + card.getHeight() - (fab.getHeight() - fab.getHeight() / 4));
            }

            public void onFinish() {
                card.setTranslationY(height - ((int)(toolbar.getHeight() * 1.7)));
                fab.setTranslationY((height - ((int) (toolbar.getHeight() * 1.7))) + card.getHeight() - (fab.getHeight() - fab.getHeight() / 4));
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    fab.animate()
                            .translationY((height - ((int) (toolbar.getHeight() * 1.7))) + card.getHeight() - (fab.getHeight() - fab.getHeight()/4))
                            .setDuration(duration).start();
                }else{
                    fab.animate()
                            .translationY((height - ((int) (toolbar.getHeight() * 1.7))) + card.getHeight() - (fab.getHeight() - fab.getHeight()/3))
                            .setDuration(duration).start();
                }
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    fab.animate()
                            .translationY((height - (card.getHeight() + toolbar.getHeight())) + card.getHeight() - (fab.getHeight() - fab.getHeight() / 4))
                            .setDuration(duration).start();
                }else{
                    fab.animate()
                            .translationY((height - (card.getHeight() + toolbar.getHeight())) + card.getHeight() - (fab.getHeight() - fab.getHeight() / 3))
                            .setDuration(duration).start();
                }
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
