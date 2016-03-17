package it.michelelacorte.swipeablecard;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cooltechworks.creditcarddesign.CreditCardView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * SwipeableCard is a LinearLayout class, the "core" of library
 * Created by Michele on 29/11/2015.
 */

/**
 * Swipeable Card is a custom view which use CardView to create
 * a custom layout of card.
 * @author Michele Lacorte
 */
public class SwipeableCard extends LinearLayout implements View.OnClickListener, AnimationCard, OnMapReadyCallback{

    private int previousFingerPositionY;
    private int previousFingerPositionX;
    private boolean isLocked = false;
    private int pressedy;
    private int viewMariginY;

    private CardView card;
    private ImageView image;
    private TextView text;
    private TextView subTitle;
    private Toolbar toolbar;
    private int height;
    private FloatingActionButton fab;
    private FrameLayout frame;
    private OptionView mOptionView = null;
    private CreditCardView creditCardView;
    private Button newCreditCard;

    /**
     * Private Variable
     */
    private float cardRadiusAttr;
    private long duration;
    private String titleAttr;
    private String subTitleAttr;
    private int colorTitleAttr;
    private int colorToolbarAttr;
    private int imageAttr;
    private String textAttr;
    private boolean isSwipeToDismiss;
    private boolean isAutoAnimation;
    //private View rootView;

    /**
     * Maps Variable
     */
    private GoogleMap mGoogleMap;
    private LatLng mMapLocation;
    private MapView mapView;
    private TextView streetNameView;
    private RelativeLayout relativeMaps;
    private RelativeLayout relativeNormal;
    private RelativeLayout relativeCreditCard;
    private RelativeLayout relativeCreditCardCreation;
    private float mapsZoom;

    /**
     * Customizable Icon and Text
     */
    private ImageView iconBtn1;
    private ImageView iconBtn2;
    private ImageView iconBtn3;
    private TextView textBtn1;
    private TextView textBtn2;

    @SuppressWarnings("all")
    public void init(@NotNull final Context context, @NotNull final OptionView option){
        if(option != null)
        {
        mOptionView = option;
        /*
        Check if Listener was set, clear and re-set it.
         */
        this.setOnTouchListener(null);
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
            mapView = (MapView) findViewById(R.id.mapLite);
            streetNameView = (TextView) findViewById(R.id.streetName);
            relativeNormal = (RelativeLayout) findViewById(R.id.relativeNormal);
            relativeMaps = (RelativeLayout) findViewById(R.id.relativeMaps);
            relativeCreditCard = (RelativeLayout) findViewById(R.id.relativeCreditCard);
            relativeCreditCardCreation = (RelativeLayout) findViewById(R.id.relativeCreditCardCreation);
            newCreditCard = (Button) findViewById(R.id.creditCardButton);
            /**
             * Set-up customizable Icon and Text
             */
            iconBtn1 = (ImageView) findViewById(R.id.iconBtn1);
            iconBtn2 = (ImageView) findViewById(R.id.iconBtn2);
            iconBtn3 = (ImageView) findViewById(R.id.iconBtn3);
            textBtn1 = (TextView) findViewById(R.id.textBtn1);
            textBtn2 = (TextView) findViewById(R.id.textBtn2);
            fab = (FloatingActionButton) findViewById(R.id.fab);
            creditCardView = (CreditCardView) findViewById(R.id.creditCard);

            frame.setOnTouchListener(null);
            card.setRadius(cardRadiusAttr);
            if(!option.isAutoAnimation()) {
                fab.setVisibility(GONE);
            }
            card.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));


            /**
             * Check if Maps Card is set (reset object before)
             */
            streetNameView.setVisibility(GONE);
            mapView.setVisibility(GONE);
            relativeCreditCard.setVisibility(GONE);
            relativeCreditCardCreation.setVisibility(GONE);
            relativeNormal.setVisibility(GONE);
            relativeMaps.setVisibility(GONE);
            if(option.isTypeCardMaps() == true) {
                /**
                 * Multiple marker mode
                 */
                if((option.getLatLngArray() != null && option.getLatLngArray().length > 0) ||
                        (option.getLatLngList() != null && option.getLatLngList().size() > 0)
                        && option.isMultipleMarker() && !option.isSingleMarker())
                    {
                        relativeNormal.setVisibility(GONE);
                        relativeMaps.setVisibility(VISIBLE);
                        mapView.setVisibility(VISIBLE);
                        text.setVisibility(GONE);
                        image.setVisibility(GONE);
                        mapView.onCreate(null);
                        mapView.getMapAsync(this);
                        setMapLocation();
                    }else {
                    /**
                     * Single marker mode
                     */
                        mapsZoom = option.getMapsZoom();
                        relativeNormal.setVisibility(GONE);
                        relativeMaps.setVisibility(VISIBLE);
                        mapView.setVisibility(VISIBLE);
                        text.setVisibility(GONE);
                        image.setVisibility(GONE);
                        mapView.onCreate(null);
                        mapView.getMapAsync(this);
                        setMapLocation(option.getLatitude(), option.getLongitude());
                        if(option.isStreetName() == true)
                        {
                            streetNameView.setText(getStreetNameFromLatLong(option.getLatitude(), option.getLongitude(), context));
                            streetNameView.setVisibility(VISIBLE);
                        }
                    }
                }else if(option.isTypeCardCredit() == true) {
                if(option.isCreateCreditCard()) {
                    /**
                     * Create Card mode
                     */
                    newCreditCard.setVisibility(VISIBLE);
                    relativeCreditCardCreation.setVisibility(VISIBLE);
                    relativeCreditCard.setVisibility(GONE);
                    newCreditCard.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCardCreation.setCreditCardView(creditCardView, option, newCreditCard, relativeCreditCardCreation, relativeCreditCard);
                            Intent intent = new Intent(option.getActivity(), ActivityCardCreation.class);
                            option.getActivity().startActivityForResult(intent, 1);
                        }
                    });
                }else {
                    /**
                     * Setted Credit Card
                     */
                    creditCardView.setCardExpiry(option.getDateYear());
                    creditCardView.setCardNumber(option.getRawCardNumber());
                    creditCardView.setCardHolderName(option.getCardHolderName());
                if(option.getCvv() != null)
                {
                    creditCardView.setCVV(option.getCvv());
                }else {
                    creditCardView.setCVV(option.getIntCvv());
                }
                    creditCardView.showFront();
                    creditCardView.setOnClickListener(new OnClickListener() {
                        boolean back = false;

                        @Override
                        public void onClick(View v) {
                            if (!back) {
                                creditCardView.showBack();
                                back = true;
                            } else {
                                creditCardView.showFront();
                                back = false;
                            }
                        }
                    });
                    relativeCreditCardCreation.setVisibility(GONE);
                    relativeCreditCard.setVisibility(VISIBLE);
                }
            }else {
                relativeCreditCard.setVisibility(GONE);
                relativeCreditCardCreation.setVisibility(GONE);
                relativeNormal.setVisibility(VISIBLE);
                relativeMaps.setVisibility(GONE);
            }

            /**
             * Check if Additional Option is set (reset object before)
             */
            fab.setVisibility(GONE);
            iconBtn1.setVisibility(GONE);
            iconBtn2.setVisibility(GONE);
            iconBtn3.setVisibility(GONE);
            textBtn1.setVisibility(GONE);
            textBtn2.setVisibility(GONE);
            if(option.getOptionViewAdditional() != null) {
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
        Configure the toolbar
         */
        initToolbar(context, option);

        if(option.isAutoAnimation() || isAutoAnimation) {
        /*
        Set listener for swipeable card.
         */
            this.setClickable(true);
            this.setOnClickListener(this);

        /*
        Set listener for root view.
         */
            frame.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    animationCardDown(card, toolbar, duration);
                }
            });
        /*
        Check if Swipe to Dismiss is set.
        */
            if (option.isSwipeToDismiss() || isSwipeToDismiss) {
                frame.setOnTouchListener(new SwipeDismissTouchListenerLeftRight(
                        frame,
                        null,
                        new SwipeDismissTouchListenerLeftRight.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(Object token) {
                                return true;
                            }

                            @Override
                            public void onDismiss(View view, Object token) {
                                setVisibility(View.GONE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
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
        }else{
            this.setClickable(false);
            this.setOnClickListener(null);
            frame.setOnClickListener(null);
        }
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
        inflater.inflate(R.layout.swipeable_card, this, true);
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
            isAutoAnimation = ta.getBoolean(R.styleable.SwipeableCard_sc_autoAnimation, true);
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
        if(option.isAutoAnimation()) {
            toolbar.setOnClickListener(this);
        }else{
            toolbar.setOnClickListener(null);
        }
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
                }else {
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

    /**
     * Update Google Maps marker, with option (icon and text) for marker
     */
    protected void updateMapContents() {
        mGoogleMap.clear();
        if(mOptionView.isMultipleMarker() && !mOptionView.isSingleMarker()) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            if(mOptionView.getLatLngArray() != null && mOptionView.getLatLngArray().length > 0) {
                if (mOptionView.getMarkerTitleArray() != null && mOptionView.getMarkerTitleArray().length > 0
                        && mOptionView.getLatLngArray().length == mOptionView.getMarkerTitleArray().length) {
                    if(mOptionView.getMarkerIconArray() != null && mOptionView.getMarkerIconArray().length > 0
                            && mOptionView.getLatLngArray().length == mOptionView.getMarkerIconArray().length)
                    {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconArray()[i]);
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]).title(mOptionView.getMarkerTitleArray()[i]).icon(icon));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]).title(mOptionView.getMarkerTitleArray()[i]));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }
                } else {
                    if(mOptionView.getMarkerIconArray() != null && mOptionView.getMarkerIconArray().length > 0
                            && mOptionView.getLatLngArray().length == mOptionView.getMarkerIconArray().length)
                    {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconArray()[i]);
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]).title(mOptionView.getMarkerTitleArray()[i]).icon(icon));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngArray().length; i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngArray()[i]));
                            builder.include(mOptionView.getLatLngArray()[i]);
                        }
                    }
                }
            }
            if(mOptionView.getLatLngList() != null && mOptionView.getLatLngList().size() > 0)
            {
                if(mOptionView.getMarkerTitleList() != null && mOptionView.getMarkerTitleList().size() > 0
                        && mOptionView.getMarkerTitleList().size() == mOptionView.getLatLngList().size())
                {
                    if(mOptionView.getMarkerIconList() != null && mOptionView.getMarkerIconList().size() > 0
                            && mOptionView.getMarkerIconList().size() == mOptionView.getLatLngList().size())
                    {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconList().get(i));
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)).title(mOptionView.getMarkerTitleList().get(i)).icon(icon));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)).title(mOptionView.getMarkerTitleList().get(i)));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }
                }else{
                    if(mOptionView.getMarkerIconList() != null && mOptionView.getMarkerIconList().size() > 0
                            && mOptionView.getMarkerIconList().size() == mOptionView.getLatLngList().size())
                    {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIconList().get(i));
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)).icon(icon));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }else {
                        for (int i = 0; i < mOptionView.getLatLngList().size(); i++) {
                            mGoogleMap.addMarker(new MarkerOptions().position(mOptionView.getLatLngList().get(i)));
                            builder.include(mOptionView.getLatLngList().get(i));
                        }
                    }
                }
            }
            LatLngBounds bounds = builder.build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 0);
            mGoogleMap.moveCamera(cameraUpdate);
        }else{
            if(mOptionView.getMarkerTitle() != null) {
                if (mOptionView.getMarkerIcon() != 0) {
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIcon());
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation).title(mOptionView.getMarkerTitle()).icon(icon));
                } else {
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation).title(mOptionView.getMarkerTitle()));
                }
            }else{
                if (mOptionView.getMarkerIcon() != 0) {
                    BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(mOptionView.getMarkerIcon());
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation).icon(icon));
                } else {
                    mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation));
                }
            }
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mMapLocation, mapsZoom);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }

    /**
     * Set map location, check if Google Maps is not null, than update contents (Single marker)
     * @param lat latitude double
     * @param lon longitude double
     */
    public void setMapLocation(double lat, double lon) {
        mMapLocation = new LatLng(lat, lon);
        if (mGoogleMap != null) {
            updateMapContents();
        }
    }

    /**
     * Set map location, check if Google Maps is not null, than update contents (Array and List)
     */
    public void setMapLocation()
    {
        if (mGoogleMap != null) {
            updateMapContents();
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        MapsInitializer.initialize(getContext());
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        if (mMapLocation != null && !mOptionView.isMultipleMarker() && mOptionView.isSingleMarker()) {
            updateMapContents();
        }
        if(mOptionView.getLatLngArray() != null && mOptionView.getLatLngArray().length > 0 && mOptionView.isMultipleMarker() && !mOptionView.isSingleMarker())
        {
            updateMapContents();
        }
        if(mOptionView.getLatLngList() != null && mOptionView.getLatLngList().size() > 0 && mOptionView.isMultipleMarker() && !mOptionView.isSingleMarker())
        {
            updateMapContents();
        }
    }

    /**
     * Get street name from latitude and longitude
     * @param lat latitude double
     * @param lon longitude double
     * @param context Context
     * @return String street name
     */
    public String getStreetNameFromLatLong(double lat, double lon, Context context)
    {
        String streetName = null;
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());

        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lon, 1);

            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for (int j = 0; j < returnedAddress.getMaxAddressLineIndex(); j++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(j)).append("");
                }
                streetName = strReturnedAddress.toString();
            }
        } catch (IOException e) {
            Log.e("SwipeableCard", "Error tryng to retrieve street name from lat long");
        }
        return streetName;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(!mOptionView.isAutoAnimation()) {
            if (isLocked) {
                return false;
            } else {
                final int y = (int) ev.getRawY();
                final int x = (int) ev.getRawX();
                if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    previousFingerPositionX = x;
                    previousFingerPositionY = y;
                } else if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
                    int diffY = y - previousFingerPositionY;
                    int diffX = x - previousFingerPositionX;
                    if (Math.abs(diffX) + 25 < Math.abs(diffY)) {
                        return true;
                    }
                }
                return false;
            }
        }else{
            return false;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!mOptionView.isAutoAnimation()) {
            int currenty = (int) event.getRawY();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) card.getLayoutParams();
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    pressedy = currenty;
                    viewMariginY = layoutParams.topMargin;
                    break;


                case MotionEvent.ACTION_MOVE:
                    int diffy = currenty - pressedy;
                    int marginy = viewMariginY + diffy;
                    layoutParams.topMargin = marginy;
                    if (marginy >= height - (card.getHeight() + toolbar.getHeight()) && marginy <= height - ((int) (toolbar.getHeight() * 1.7))) {
                        ObjectAnimator positionAnimator = ObjectAnimator.ofFloat(card, "y", this.getY(), marginy);
                        positionAnimator.setDuration(0);
                        positionAnimator.start();
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    int diffy2 = currenty - pressedy;
                    int marginy2 = viewMariginY + diffy2;
                    layoutParams.topMargin = marginy2;
                    if (marginy2 >= height - (card.getHeight() + toolbar.getHeight()) && marginy2 <= height - ((int) (toolbar.getHeight() * 1.7))) {
                        ObjectAnimator positionAnimator = ObjectAnimator.ofFloat(card, "y", this.getY(), marginy2);
                        positionAnimator.setDuration(0);
                        positionAnimator.start();
                    }
                    break;
                default:
                    break;
            }

            return true;
        }else
        {
            return false;
        }
    }

}
