package it.michelelacorte.swipeablecard;

import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

/**
 * OptionView class describe all option settable by user, provide a Builder pattern constructor.
 * This class have all type of SwiepableCard.
 * Created by Michele on 29/11/2015.
 */

/**
 *  Option View describes the options that can be set for Swipeable Card,
 *  offers a Builder to make configuration options easier for users.
 *  @author Michele Lacorte
 */
@SuppressWarnings("unused")
public class OptionView {
    private int mColorTitle;
    private int mMenuItem;
    private String mTitle;
    private String mText;
    private String mSubTitle;
    private int mImage;
    private int mToolbarColor;
    private boolean isMenuItem = false;
    private boolean isImage = false;
    private boolean isText = true;
    private boolean isSubTitle = false;
    private boolean isSwipeLeftRight = false;
    private Toolbar.OnMenuItemClickListener mToolbarListener;
    private long mDuration = 500;
    private float mCardRadius = 4f;
    static OptionView optionView = null;
    private OptionViewAdditional optionViewAdditional = null;
    private double latitude;
    private double longitude;
    private String markerTitle;
    private boolean isStreetName = true;
    private boolean TYPE_CARD_NORMAL = false;
    private boolean TYPE_CARD_MAPS = false;
    private boolean multipleMarker = false;
    private boolean singleMarker = false;
    private String[] markerTitleArray;
    private LatLng[] latLngArray;
    private int[] markerIconArray;
    List<LatLng> latLngList = new ArrayList<>();
    List<String> markerTitleList = new ArrayList<>();
    List<Integer> markerIconList = new ArrayList<>();
    private int markerIcon;
    private float mapsZoom = 0;


    /**
     * Public constructor for set option to Swipeable Card (Maps Mode).
     */
    public OptionView(MapsCard mapsCard)
    {
        if(mapsCard.mColorTitle == 0)
        {
            Log.e("ColorTitle", "Impossible to set Color Title to 0, default value BLACK is set! Please Check it");
            mColorTitle = android.R.color.black;
        }else {
            mColorTitle = mapsCard.mColorTitle;
        }
        if(mapsCard.mTitle == null)
        {
            Log.e("Title", "Impossible to set Title to null, default value empty string is set! Please Check it");
            mTitle = "";
        }else {
            mTitle = mapsCard.mTitle;
        }
        if(mapsCard.mToolbarColor == 0)
        {
            Log.e("ToolbarColor", "Impossible to set Toolbar Color to 0, default value transparent is set! Please Check it");
            mToolbarColor = android.R.color.transparent;

        }else {
            mToolbarColor = mapsCard.mToolbarColor;
        }
        mMenuItem = mapsCard.mMenuItem;
        mText = mapsCard.mText;
        mSubTitle = mapsCard.mSubTitle;
        mImage = mapsCard.mImage;
        mToolbarListener = mapsCard.mToolbarListener;
        isText = mapsCard.isText;
        isImage = mapsCard.isImage;
        isMenuItem = mapsCard.isMenuItem;
        isSubTitle = mapsCard.isSubTitle;
        isSwipeLeftRight = mapsCard.isSwipeLeftRight;
        mDuration = mapsCard.mDuration;
        optionViewAdditional = mapsCard.optionViewAdditional;
        mCardRadius = mapsCard.mCardRadius;
        latitude = mapsCard.latitude;
        longitude = mapsCard.longitude;
        latLngArray = mapsCard.latLngArray;
        markerTitleArray = mapsCard.markerTitleArray;
        latLngList = new ArrayList<>(mapsCard.latLngList);
        markerTitleList = new ArrayList<>(mapsCard.markerTitleList);
        markerTitle = mapsCard.markerTitle;
        isStreetName = mapsCard.isStreetName;
        singleMarker = mapsCard.singleMarker;
        multipleMarker = mapsCard.multipleMarker;
        markerIcon = mapsCard.markerIcon;
        markerIconArray = mapsCard.markerIconArray;
        markerIconList = mapsCard.markerIconList;
        mapsZoom = mapsCard.mZoom;
        TYPE_CARD_MAPS = true;
        TYPE_CARD_NORMAL = false;
    }

    /**
     * Public constructor for set option to Swipeable Card (Normal Mode).
     */
    public OptionView(NormalCard normalCard)
    {
        if(normalCard.mColorTitle == 0)
        {
            Log.e("ColorTitle", "Impossible to set Color Title to 0, default value BLACK is set! Please Check it");
            mColorTitle = android.R.color.black;
        }else {
            mColorTitle = normalCard.mColorTitle;
        }
        if(normalCard.mTitle == null)
        {
            Log.e("Title", "Impossible to set Title to null, default value empty string is set! Please Check it");
            mTitle = "";
        }else {
            mTitle = normalCard.mTitle;
        }
        if(normalCard.mToolbarColor == 0)
        {
            Log.e("ToolbarColor", "Impossible to set Toolbar Color to 0, default value transparent is set! Please Check it");
            mToolbarColor = android.R.color.transparent;

        }else {
            mToolbarColor = normalCard.mToolbarColor;
        }
        mMenuItem = normalCard.mMenuItem;
        mText = normalCard.mText;
        mSubTitle = normalCard.mSubTitle;
        mImage = normalCard.mImage;
        mToolbarListener = normalCard.mToolbarListener;
        isText = normalCard.isText;
        isImage = normalCard.isImage;
        isMenuItem = normalCard.isMenuItem;
        isSubTitle = normalCard.isSubTitle;
        isSwipeLeftRight = normalCard.isSwipeLeftRight;
        mDuration = normalCard.mDuration;
        optionViewAdditional = normalCard.optionViewAdditional;
        mCardRadius = normalCard.mCardRadius;
        TYPE_CARD_NORMAL = true;
        TYPE_CARD_MAPS = false;
    }

    /**
     * Public class Builder for setting up Swipeable Card.
     */
    public static class Builder{

        public NormalCard normalCard()
        {
            return new NormalCard();
        }
        public MapsCard mapsCard()
        {
            return new MapsCard();
        }
    }

    /**
     * Get toolbarListener of Swipeable Card.
     * @return OnMenuItemClickListener item
     */
    public Toolbar.OnMenuItemClickListener getToolbarListener() {
        return mToolbarListener;
    }

    /**
     * Get Toolbar Color of Swipeable Card.
     * @return int of color
     */
    public int getColorToolbar() {
        return mToolbarColor;
    }

    /**
     * Get Sub Title of Swipeable Card.
     * @return String of sub title
     */
    public String getSubTitle() {
        return mSubTitle;
    }

    /**
     * Get Text of Swipeable Card.
     * @return Text string of Swipeable Card
     */
    public String getText() {
        return mText;
    }

    /**
     * Get Image of Swipeable Card.
     * @return Integer image of Swipeable Card
     */
    public int getImage() {
        return mImage;
    }

    /**
     * Get Title of Swipeable Card.
     * @return String of title
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Get Menu Item of Swipeable Card.
     * @return Integer of menu item
     */
    public int getMenuItem()
    {
        return mMenuItem;
    }

    /**
     * Get color of toolbar Title.
     * @return Integer of color title
     */
    public int getColorTitle()
    {
        return mColorTitle;
    }

    /**
     * Set OptionView in case of single swipeable card.
     * @param optionViews A series of OptionView
     */
    public static void setOptionView(@NotNull OptionView optionViews)
    {
        optionView = optionViews;
    }

    /**
     * Get OptionView.
     * @return OptionView type
     */
    @NotNull
    public static OptionView getOptionView()
    {
        return optionView;
    }

    /**
     * Get Animation duration
     * @return long representing the duration
     */
    public long getDuration()
    {
        return mDuration;
    }

    /**
     * Get card radius
     * @return float representing card radius
     */
    public float getCardRadius() {
        return mCardRadius;
    }

    /**
     * Get Optional View
     * @return OptionViewAdditional for custom item
     */
    @NotNull
    public OptionViewAdditional getOptionViewAdditional() {
        return optionViewAdditional;
    }

    /**
     * Method to check if Swipe to dismiss is set from user.
     */
    public boolean isSwipeToDismiss()
    {
        return isSwipeLeftRight;
    }
    /**
     * Method to check if Menu Item is set from user.
     */
    public boolean isMenuItem()
    {
        return isMenuItem;
    }
    /**
     * Method to check if Image is set from user.
     */
    public boolean isImage()
    {
        return isImage;
    }
    /**
     * Method to check if Text is set from user.
     */
    public boolean isText()
    {
        return isText;
    }
    /**
     * Method to check if Sub Title is set from user.
     */
    public boolean isSubTitle()
    {
        return isSubTitle;
    }

    /**
     * Get Latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Get boolean to know if Normal Card is set
     * @return boolean TYPE_CARD_NORMAL
     */
    public boolean isTYPE_CARD_NORMAL() {
        return TYPE_CARD_NORMAL;
    }

    /**
     * Get boolean to know if Maps Card is set
     * @return boolean TYPE_CARD_MAPS
     */
    public boolean isTYPE_CARD_MAPS() {
        return TYPE_CARD_MAPS;
    }

    /**
     * Get LatLng array
     * @return LatLng[]
     */
    public LatLng[] getLatLngArray() {
        return latLngArray;
    }

    /**
     * Get marker title
     * @return String markerTitle
     */
    public String getMarkerTitle() {
        return markerTitle;
    }

    /**
     * Get boolean isStreetName to know if street name is set
     * @return boolean isStreetName
     */
    public boolean isStreetName() {
        return isStreetName;
    }

    /**
     * Get boolean isMultipleMarker to know if multiple marker mode is used
     * @return boolean multipleMarker
     */
    public boolean isMultipleMarker() {
        return multipleMarker;
    }

    /**
     * Get boolean isSingleMarker to know if single marker mode is used
     * @return boolean singleMarker
     */
    public boolean isSingleMarker() {
        return singleMarker;
    }

    /**
     * Get marker title array
     * @return String[] markerTitleArray
     */
    public String[] getMarkerTitleArray() {
        return markerTitleArray;
    }

    /**
     * Get LatLng list of marker
     * @return List latlngList
     */
    public List<LatLng> getLatLngList() {
        return latLngList;
    }

    /**
     * Get String list of marker title
     * @return List markerTitleList
     */
    public List<String> getMarkerTitleList() {
        return markerTitleList;
    }

    /**
     * Get int of marker icon
     * @return int markerIcon
     */
    public int getMarkerIcon() {
        return markerIcon;
    }

    /**
     * Get int array of marker icon
     * @return int[] markerIconArray
     */
    public int[] getMarkerIconArray() {
        return markerIconArray;
    }

    /**
     * Get integer list of marker icon
     * @return List markerIconlist
     */
    public List<Integer> getMarkerIconList() {
        return markerIconList;
    }

    /**
     * Get zoom of camera maps
     * @return float mapsZoom
     */
    public float getMapsZoom() {
        return mapsZoom;
    }


    /**
     * Maps Card mode
     */
    @SuppressWarnings("unused")
    public static class MapsCard implements Card{
        int mColorTitle;
        int mMenuItem;
        String mTitle;
        String mText;
        String mSubTitle;
        int mImage;
        int mToolbarColor;
        boolean isMenuItem = false;
        boolean isImage = false;
        boolean isText = true;
        boolean isSubTitle = false;
        boolean isSwipeLeftRight = false;
        Toolbar.OnMenuItemClickListener mToolbarListener;
        long mDuration = 500;
        float mCardRadius = 4f;
        OptionViewAdditional optionViewAdditional;
        float mZoom = 0;
        /*
        Single Marker
         */
        double latitude;
        double longitude;
        String markerTitle;
        int markerIcon;
        boolean isStreetName = true;
        boolean singleMarker = false;
        /*
        Multiple Marker Array
         */
        LatLng[] latLngArray;
        String[] markerTitleArray;
        int[] markerIconArray;
        /*
        Multiple Marker List
         */
        List<LatLng> latLngList = new ArrayList<>();
        List<String> markerTitleList = new ArrayList<>();
        List<Integer> markerIconList = new ArrayList<>();
        boolean multipleMarker = false;

        /**
         * Set location of maps
         * @param latLngs List list of LatLng point for marker
         * @param titles List list title of marker
         */
        public MapsCard setLocation(List<LatLng> latLngs, List<String> titles)
        {
            multipleMarker = true;
            singleMarker = false;
            latLngList = new ArrayList<>(latLngs);
            markerTitleList = new ArrayList<>(titles);
            return this;
        }


        /**
         * Set location of maps
         * @param latLngs List list of LatLng point for marker
         * @param titles List list title of marker
         * @param icon List ex. drawable icon
         */
        public MapsCard setLocation(List<LatLng> latLngs, List<String> titles, List<Integer> icon)
        {
            multipleMarker = true;
            singleMarker = false;
            latLngList = latLngs;
            markerTitleList = titles;
            markerIconList = icon;
            return this;
        }

        /**
         * Set location of maps
         * @param latLngs Array of LatLng
         * @param titles Array titles for marker
         */
        public MapsCard setLocation(LatLng[] latLngs, @NotNull String ... titles)
        {
            multipleMarker = true;
            singleMarker = false;
            latLngArray = latLngs;
            markerTitleArray = titles;
            return this;
        }

        /**
         * Set location of maps
         * @param latLngs Array of LatLng
         * @param titles Array titles for marker
         * @param icon Array of icon for makrer
         */
        public MapsCard setLocation(LatLng[] latLngs, @NotNull String[] titles, @DrawableRes int ... icon)
        {
            multipleMarker = true;
            singleMarker = false;
            latLngArray = latLngs;
            markerTitleArray = titles;
            markerIconArray = icon;
            return this;
        }

        /**
         * Set location of maps
         * @param latLngs Array of LatLng
         */
        public MapsCard setLocation(LatLng ... latLngs)
        {
            multipleMarker = true;
            singleMarker = false;
            latLngArray = latLngs;
            return this;
        }

        /**
         * Set location of maps
         * @param latLngs Array of LatLng
         * @param icon Array of icon for makrer
         */
        public MapsCard setLocation(LatLng[] latLngs, @DrawableRes int ... icon)
        {
            multipleMarker = true;
            singleMarker = false;
            latLngArray = latLngs;
            markerIconArray = icon;
            return this;
        }

        /**
         * Set location of maps
         * @param lat double describe latitude
         * @param lon double describe longitude
         */
        public MapsCard setLocation(double lat, double lon)
        {
            singleMarker = true;
            multipleMarker = false;
            latitude = lat;
            longitude = lon;
            return this;
        }

        /**
         * Set location of maps
         * @param lat double describe latitude
         * @param lon double describe longitude
         * @param title string title of marker
         */
        public MapsCard setLocation(double lat, double lon, @NotNull String title)
        {
            singleMarker = true;
            multipleMarker = false;
            latitude = lat;
            longitude = lon;
            markerTitle = title;
            return this;
        }

        /**
         * Set location of maps
         * @param lat double describe latitude
         * @param lon double describe longitude
         * @param icon int rapresent icon of marker
         */
        public MapsCard setLocation(double lat, double lon,  @DrawableRes int icon)
        {
            singleMarker = true;
            multipleMarker = false;
            latitude = lat;
            longitude = lon;
            markerIcon = icon;
            return this;
        }

        /**
         * Set location of maps
         * @param lat double describe latitude
         * @param lon double describe longitude
         * @param title string title of marker
         * @param icon int rapresent icon of marker
         * @return MapsCard
         */
        public MapsCard setLocation(double lat, double lon, @NotNull String title, @DrawableRes int icon)
        {
            singleMarker = true;
            multipleMarker = false;
            latitude = lat;
            longitude = lon;
            markerTitle = title;
            markerIcon = icon;
            return this;
        }

        /**
         * Set zoom for maps, default 10f
         * @param zoom float for zoom level
         */
        public MapsCard setZoom(float zoom)
        {
            mZoom = zoom;
            return this;
        }

        /**
         * Show street name, works on a single marker, default false
         * @param isStreetName boolean
         */
        public MapsCard withStreetName(boolean isStreetName)
        {
            this.isStreetName = isStreetName;
            return this;
        }

        /**
         * Swipe to dismiss (left-right) animation for card, default false
         * @param isSwipe boolean
         */
        @Override
        public MapsCard setSwipeToDismiss(boolean isSwipe)
        {
            isSwipeLeftRight = isSwipe;
            return this;
        }

        /**
         * Set radius of SwipeableCard
         * @param radius float rapresent radius
         */
        @Override
        public MapsCard setCardRadius(float radius)
        {
            if(radius <= 0)
            {
                Log.e("CardRadius", "Impossible to set Card Radius lower than 0! Please Check it");
            }
            else {
                mCardRadius = radius;
            }
            return this;
        }

        /**
         * Set additional item with OptionViewAdditional.Builder() constructor
         * @param option OptionViewAdditional
         */
        @Override
        public MapsCard setAdditionalItem(@NotNull OptionViewAdditional option)
        {
            optionViewAdditional = option;
            return this;
        }

        /**
         * Set animation duration for up and down Swipeable Card
         * @param durationInMillis representing the duration
         */
        @Override
        public MapsCard setDuration(long durationInMillis)
        {
            if(durationInMillis <= 0)
            {
                Log.e("Duration", "Impossible to set Duration lower than 0! Please Check it");
            }
            else {
                mDuration = durationInMillis;
            }
            return this;
        }

        /**
         * Toolbar Listener for set own listener with own option to menu item.
         * @param toolbarListener An listener thath implements OnMenuItemClickListener(..)
         */
        @Override
        public MapsCard toolbarListener(@NotNull Toolbar.OnMenuItemClickListener toolbarListener)
        {
            mToolbarListener = toolbarListener;
            return this;
        }

        /**
         * Color Title of Swipeable Card, default is BLACK.
         * @param colorTitle A color for Title of Swipeable Card
         */
        @Override
        public MapsCard colorTitle(@ColorRes int colorTitle) {
            mColorTitle = colorTitle;
            return this;
        }

        /**
         * Menu Item for set custom menu item to Swipeable Card.
         * @param menuItem An int from R.menu class.
         */
        @Override
        public MapsCard menuItem(@AnyRes int menuItem) {

            if(menuItem == 0)
            {
                Log.e("MenuItem", "Impossible to set Menu Item to 0! Please Check it");
            }
            else {
                mMenuItem = menuItem;
                isMenuItem = true;
            }
            return this;
        }

        /**
         * Set up your Title to Swipeable Card.
         * @param title String for Swipeable Card Title
         */
        @Override
        public MapsCard title(@NotNull String title) {
            mTitle = title;
            return this;
        }

        /**
         * Set sub title of Swipeable Card
         * @param subTitle String for sub title of Swipeable Card
         */
        @Override
        public MapsCard subTitle(@NotNull String subTitle) {
            mSubTitle = subTitle;
            isSubTitle = true;
            return this;
        }

        /**
         * Toolbar Color of Swipeable Card.
         * @param toolbarColor Resource integer which describe color
         */
        @Override
        public MapsCard toolbarColor(@ColorRes int toolbarColor) {

            if(toolbarColor == 0)
            {
                Log.e("ToolbarColor", "Impossible to set Toolbar Color to 0, default value transparent is set! Please Check it");
                mToolbarColor = android.R.color.transparent;
            }
            else {
                mToolbarColor = toolbarColor;
            }
            return this;
        }

        /**
         * Public builder to set OptionView class with custom option.
         */
        @Override
        public OptionView build() {
            return new OptionView(this);
        }

    }

    /**
     * Normal Card Mode
     */
    public static class NormalCard implements Card{
        int mColorTitle;
        int mMenuItem;
        String mTitle;
        String mText;
        String mSubTitle;
        int mImage;
        int mToolbarColor;
        boolean isMenuItem = false;
        boolean isImage = false;
        boolean isText = true;
        boolean isSubTitle = false;
        boolean isSwipeLeftRight = false;
        Toolbar.OnMenuItemClickListener mToolbarListener;
        long mDuration = 500;
        float mCardRadius = 4f;
        OptionViewAdditional optionViewAdditional;

        /**
         * Swipe to dismiss (left-right) animation for card, default false
         * @param isSwipe boolean
         */
        @Override
        public NormalCard setSwipeToDismiss(boolean isSwipe)
        {
            isSwipeLeftRight = isSwipe;
            return this;
        }

        /**
         * Set radius of SwipeableCard
         * @param radius float rapresent radius
         */
        @Override
        public NormalCard setCardRadius(float radius)
        {
            if(radius <= 0)
            {
                Log.e("CardRadius", "Impossible to set Card Radius lower than 0! Please Check it");
            }
            else {
                mCardRadius = radius;
            }
            return this;
        }

        /**
         * Set additional item with OptionViewAdditional.Builder() constructor
         * @param option OptionViewAdditional
         */
        @Override
        public NormalCard setAdditionalItem(@NotNull OptionViewAdditional option)
        {
            optionViewAdditional = option;
            return this;
        }

        /**
         * Set animation duration for up and down Swipeable Card
         * @param durationInMillis representing the duration
         */
        @Override
        public NormalCard setDuration(long durationInMillis)
        {
            if(durationInMillis <= 0)
            {
                Log.e("Duration", "Impossible to set Duration lower than 0! Please Check it");
            }
            else {
                mDuration = durationInMillis;
            }
            return this;
        }

        /**
         * Toolbar Listener for set own listener with own option to menu item.
         * @param toolbarListener An listener thath implements OnMenuItemClickListener(..)
         */
        @Override
        public NormalCard toolbarListener(@NotNull Toolbar.OnMenuItemClickListener toolbarListener)
        {
            mToolbarListener = toolbarListener;
            return this;
        }

        /**
         * Color Title of Swipeable Card, default is BLACK.
         * @param colorTitle A color for Title of Swipeable Card
         */
        @Override
        public NormalCard colorTitle(@ColorRes int colorTitle) {
            mColorTitle = colorTitle;
            return this;
        }

        /**
         * Menu Item for set custom menu item to Swipeable Card.
         * @param menuItem An int from R.menu class.
         */
        @Override
        public NormalCard menuItem(@AnyRes int menuItem) {

            if(menuItem == 0)
            {
                Log.e("MenuItem", "Impossible to set Menu Item to 0! Please Check it");
            }
            else {
                mMenuItem = menuItem;
                isMenuItem = true;
            }
            return this;
        }

        /**
         * Set up your Title to Swipeable Card.
         * @param title String for Swipeable Card Title
         */
        @Override
        public NormalCard title(@NotNull String title) {
            mTitle = title;
            return this;
        }

        /**
         * Set text on content of Swipeable Card.
         * @param text String for content of Swipeable Card.
         */
        public NormalCard text(@NotNull String text) {
            mText = text;
            isImage = false;
            isText = true;
            return this;
        }

        /**
         * Set sub title of Swipeable Card
         * @param subTitle String for sub title of Swipeable Card
         */
        @Override
        public NormalCard subTitle(@NotNull String subTitle) {
            mSubTitle = subTitle;
            isSubTitle = true;
            return this;
        }

        /**
         * Image for Swipeable Card.
         * @param image Resource integer which describe image
         */
        public NormalCard image(@DrawableRes int image) {
            mImage = image;
            isImage = true;
            isText = false;
            return this;
        }

        /**
         * Toolbar Color of Swipeable Card.
         * @param toolbarColor Resource integer which describe color
         */
        @Override
        public NormalCard toolbarColor(@ColorRes int toolbarColor) {

            if(toolbarColor == 0)
            {
                Log.e("ToolbarColor", "Impossible to set Toolbar Color to 0, default value transparent is set! Please Check it");
                mToolbarColor = android.R.color.transparent;
            }
            else {
                mToolbarColor = toolbarColor;
            }
            return this;
        }

        /**
         * Public builder to set OptionView class with custom option.
         */
        @Override
        public OptionView build() {
            return new OptionView(this);
        }
    }

}