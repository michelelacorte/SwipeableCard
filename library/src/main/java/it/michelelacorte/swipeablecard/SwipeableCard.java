package it.michelelacorte.swipeablecard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import org.jetbrains.annotations.NotNull;

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
    FrameLayout frame;

    /**
     * Private Method
     */
    float cardRadiusAttr;
    long duration;
    String titleAttr;
    String subTitleAttr;
    int colorTitleAttr;
    int colorToolbarAttr;
    int imageAttr;
    String textAttr;
    boolean isSwipeToDismiss;
    View rootView;
    boolean isSwipe;


    /**
     * Customizable Icon and Text
     */
    ImageView iconBtn1;
    ImageView iconBtn2;
    ImageView iconBtn3;
    TextView textBtn1;
    TextView textBtn2;

    @SuppressWarnings("all")
    public void init(@NotNull final Context context, @NotNull final OptionView option){
        if(option != null)
        {

        /*
        Check if Listener was set, clear and re-set it.
         */
        this.setOnTouchListener(null);
        isSwipe = false;
        isSwipe = option.isSwipeToDismiss();
        /*
        Clear title and re-set it.
         */
        titleAttr = null;
        titleAttr = option.getTitle();
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
            frame = (FrameLayout) findViewById(R.id.frame);
            /**
             * Set-up customizable Icon and Text
             */
            iconBtn1 = (ImageView) findViewById(R.id.iconBtn1);
            iconBtn2 = (ImageView) findViewById(R.id.iconBtn2);
            iconBtn3 = (ImageView) findViewById(R.id.iconBtn3);
            textBtn1 = (TextView) findViewById(R.id.textBtn1);
            textBtn2 = (TextView) findViewById(R.id.textBtn2);

            card.setRadius(cardRadiusAttr);

            fab = (FloatingActionButton) findViewById(R.id.fab);

            /**
             * Check if Additional Option is set (reset object before)
             */
            fab.setVisibility(GONE);
            iconBtn1.setVisibility(GONE);
            iconBtn2.setVisibility(GONE);
            iconBtn3.setVisibility(GONE);
            textBtn1.setVisibility(GONE);
            textBtn2.setVisibility(GONE);
                if (option.getOptionViewAdditional().getFabIcon() > 0) {
                    fab.setVisibility(View.VISIBLE);
                    if (option.getOptionViewAdditional().getFabIcon() > 0) {
                        fab.setImageResource(option.getOptionViewAdditional().getFabIcon());
                    }
                    if (option.getOptionViewAdditional().getFabBackgroundColor() > 0) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, option.getOptionViewAdditional().getFabBackgroundColor())));
                        } else {
                            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(option.getOptionViewAdditional().getFabBackgroundColor())));
                        }
                    }
                    fab.setOnClickListener(option.getOptionViewAdditional().getOnClickListenerFab());
                }
                /**
                 * Check if Additional Icon Button 1
                 */
                if (option.getOptionViewAdditional().isIconBtn1()) {
                    iconBtn1.setVisibility(View.VISIBLE);
                    iconBtn1.setImageResource(option.getOptionViewAdditional().getIconBtn1());
                    iconBtn1.setOnClickListener(option.getOptionViewAdditional().getOnClickListenerIconBtn1());

                }
                /**
                 * Check if Additional Icon Button 2
                 */
                if (option.getOptionViewAdditional().isIconBtn2()) {
                    iconBtn2.setVisibility(View.VISIBLE);
                    iconBtn2.setImageResource(option.getOptionViewAdditional().getIconBtn2());
                    iconBtn2.setOnClickListener(option.getOptionViewAdditional().getOnClickListenerIconBtn2());
                }
                /**
                 * Check if Additional Icon Button 3
                 */
                if (option.getOptionViewAdditional().isIconBtn3()) {
                    iconBtn3.setVisibility(View.VISIBLE);
                    iconBtn3.setImageResource(option.getOptionViewAdditional().getIconBtn3());
                    iconBtn3.setOnClickListener(option.getOptionViewAdditional().getOnClickListenerIconBtn3());
                }
                /**
                 * Check if Additional Text Button 1
                 */
                if (option.getOptionViewAdditional().isTextBtn1()) {
                    textBtn1.setVisibility(View.VISIBLE);
                    textBtn1.setText(option.getOptionViewAdditional().getTextBtn1());
                    textBtn1.setTextSize(option.getOptionViewAdditional().getTextSizeBtn1());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        textBtn1.setTextColor(ContextCompat.getColor(context, option.getOptionViewAdditional().getTextColorBtn1()));
                    } else {
                        textBtn1.setTextColor(getResources().getColor(option.getOptionViewAdditional().getTextColorBtn1()));
                    }
                    /**
                     * Set OnClickListener Additional Text Button 1
                     */
                    textBtn1.setOnClickListener(option.getOptionViewAdditional().getOnClickListenerTextBtn1());
                }
                /**
                 * Check if Additional Text Button 2
                 */
                if (option.getOptionViewAdditional().isTextBtn2()) {
                    textBtn2.setVisibility(View.VISIBLE);
                    textBtn2.setText(option.getOptionViewAdditional().getTextBtn2());
                    textBtn2.setTextSize(option.getOptionViewAdditional().getTextSizeBtn2());
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        textBtn2.setTextColor(ContextCompat.getColor(context, option.getOptionViewAdditional().getTextColorBtn2()));
                    } else {
                        textBtn2.setTextColor(getResources().getColor(option.getOptionViewAdditional().getTextColorBtn2()));
                    }
                    /**
                     * Set OnClickListener Additional Text Button 2
                     */
                    textBtn2.setOnClickListener(option.getOptionViewAdditional().getOnClickListenerTextBtn2());
                }

        /*
        Check if Text is set.
         */
            if (option.isText()) {
                image.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
                text.setText(option.getText());
            }
            if (textAttr != null) {
                image.setVisibility(View.GONE);
                text.setVisibility(View.VISIBLE);
                text.setText(textAttr);
            }
        /*
        Check if subTitle is set.
         */
            if (option.isSubTitle()) {
                subTitle.setVisibility(View.VISIBLE);
                subTitle.setText(option.getSubTitle());
            }
            if (subTitleAttr != null) {
                subTitle.setVisibility(View.VISIBLE);
                subTitle.setText(subTitleAttr);
            }
        /*
        Check if Image is set.
         */
            if (option.isImage()) {
                text.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                image.setImageResource(option.getImage());
            }
            if (imageAttr != -1) {
                text.setVisibility(View.GONE);
                image.setVisibility(View.VISIBLE);
                image.setImageResource(imageAttr);
            }
        /*
        Check if Swipe to Dismiss is set.
         */
            if (isSwipe || isSwipeToDismiss) {
                this.setOnTouchListener(new SwipeDismissTouchListenerLeftRight(
                        this,
                        null,
                        new SwipeDismissTouchListenerLeftRight.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(Object token) {
                                return true;
                            }

                            @Override
                            public void onDismiss(View view, Object token) {
                                setVisibility(View.GONE);
                                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                                Snackbar.make(SwipeableCard.this, "Deleted!", Snackbar.LENGTH_LONG)
                                        .setAction("Undo", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                setVisibility(View.VISIBLE);
                                            }
                                        })
                                        .show();
                            }
                        }));
            }
        /*
        Configure the toolbar
         */
            initToolbar(context, option);

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
                    animationCardDown(card, toolbar, duration);
                }
            });
            /*
            Start animation
             */
            animationCardStart(card, toolbar);
        }else{
            Log.e("Initialization", "Option View not initialize!");
        }
    }


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
        rootView = inflater.inflate(R.layout.swipeable_card, this, true);
        /*
        Get attribute from XML
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwipeableCard, 0, 0);
        try {
            cardRadiusAttr = ta.getDimension(R.styleable.SwipeableCard_sc_cardRadius, 4);
            duration = (long) ta.getFloat(R.styleable.SwipeableCard_sc_animDuration, 500);
            titleAttr = ta.getString(R.styleable.SwipeableCard_sc_title);
            colorTitleAttr = ta.getInteger(R.styleable.SwipeableCard_sc_titleColor, 0);
            imageAttr = ta.getResourceId(R.styleable.SwipeableCard_sc_image, -1);
            subTitleAttr = ta.getString(R.styleable.SwipeableCard_sc_subTitle);
            colorToolbarAttr = ta.getInteger(R.styleable.SwipeableCard_sc_toolbarColor, 0);
            textAttr = ta.getString(R.styleable.SwipeableCard_sc_text);
            isSwipeToDismiss = ta.getBoolean(R.styleable.SwipeableCard_sc_swipeToDismiss, false);
        } finally {
            ta.recycle();
        }
        init(context, OptionView.getOptionView());
    }

    /**
     * Get size of screen.
     * @param context Context
     * @return height of screen
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
     * @param context Context
     */
    @SuppressWarnings("deprecation")
    private void initToolbar(Context context, OptionView option)
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(titleAttr);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(colorTitleAttr == 0)
            {
                toolbar.setTitleTextColor(ContextCompat.getColor(context, option.getColorTitle()));
            }
            else {
                toolbar.setTitleTextColor(colorTitleAttr);
            }
            if(colorToolbarAttr == 0)
            {
                toolbar.setBackgroundColor((ContextCompat.getColor(context, option.getColorToolbar())));
            }else {

                toolbar.setBackgroundColor(colorToolbarAttr);
            }

        } else {
            if(colorTitleAttr == 0)
            {
                toolbar.setTitleTextColor(getResources().getColor(option.getColorTitle()));
            }
            else {
                toolbar.setTitleTextColor(colorTitleAttr);
            }
            if(colorToolbarAttr == 0) {
                toolbar.setBackgroundColor(getResources().getColor(option.getColorToolbar()));
            }else{
                toolbar.setBackgroundColor(colorToolbarAttr);
            }
        }
        if (option.isMenuItem()) {
            //Reset menù item (avoids duplicate)
            toolbar.getMenu().clear();
            //Set new menù item
            toolbar.inflateMenu(option.getMenuItem());
            toolbar.setOnMenuItemClickListener(option.getToolbarListener());
        }

        toolbar.setOnClickListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    /**
     * Animation Card at start layout, please do not modify this.
     * @param card card view instance
     * @param toolbar toolbar instance
     */
    @Override
    public void animationCardStart(@NotNull final CardView card, @NotNull final Toolbar toolbar){
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
     * @param card card view instance
     * @param toolbar toolbar instance
     */
    @Override
    public void animationCardDown(@NotNull final CardView card, @NotNull final Toolbar toolbar, final long duration){
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
     * @param card card view instance
     * @param toolbar toolbar instance
     */
    @Override
    public void animationCardUp(@NotNull final CardView card, @NotNull final Toolbar toolbar, final long duration){
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
        animationCardUp(card, toolbar, duration);
    }
}
