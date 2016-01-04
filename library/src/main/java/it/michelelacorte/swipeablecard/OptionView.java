package it.michelelacorte.swipeablecard;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.OptionalDataException;

/**
 * Created by Michele on 29/11/2015.
 */

/**
 *  Option View describes the options that can be set for Swipeable Card,
 *  offers a Builder to make configuration options easier for users.
 *  @author Michele Lacorte
 */
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
    private Toolbar.OnMenuItemClickListener mToolbarListener;
    private long mDuration = 500;
    private float mCardRadius = 4f;
    static OptionView optionView;
    private OptionViewAdditional optionViewAdditional;


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
     * Public constructor for set option to Swipeable Card.
     * @param builder A series of option
     */
    public OptionView(Builder builder)
    {
        if(builder.mColorTitle == 0)
        {
            Log.e("ColorTitle", "Impossible to set Color Title to 0, default value BLACK is set! Please Check it");
            mColorTitle = android.R.color.black;
        }else {
            mColorTitle = builder.mColorTitle;
        }
        if(builder.mTitle == null)
        {
            Log.e("Title", "Impossible to set Title to null, default value empty string is set! Please Check it");
            mTitle = "";
        }else {
            mTitle = builder.mTitle;
        }
        if(builder.mToolbarColor == 0)
        {
            Log.e("ToolbarColor", "Impossible to set Toolbar Color to 0, default value transparent is set! Please Check it");
            mToolbarColor = android.R.color.transparent;

        }else {
            mToolbarColor = builder.mToolbarColor;
        }
        mMenuItem = builder.mMenuItem;
        mText = builder.mText;
        mSubTitle = builder.mSubTitle;
        mImage = builder.mImage;
        mToolbarListener = builder.mToolbarListener;
        isText = builder.isText;
        isImage = builder.isImage;
        isMenuItem = builder.isMenuItem;
        isSubTitle = builder.isSubTitle;
        mDuration = builder.mDuration;
        optionViewAdditional = builder.optionViewAdditional;
        mCardRadius = builder.mCardRadius;
    }

    /**
     * Public class Builder for setting up Swipeable Card.
     */
    public static class Builder{
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
        Toolbar.OnMenuItemClickListener mToolbarListener;
        private long mDuration = 500;
        private float mCardRadius = 4f;
        private OptionViewAdditional optionViewAdditional;


        public Builder setCardRadius(float radius)
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

        public Builder setAdditionalItem(OptionViewAdditional option)
        {
            optionViewAdditional = option;
            return this;
        }

        /**
         * Set animation duration for up and down Swipeable Card
         * @param durationInMillis representing the duration
         */
        public Builder setDuration(long durationInMillis)
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
        public Builder toolbarListener(Toolbar.OnMenuItemClickListener toolbarListener)
        {
            if(toolbarListener == null)
            {
                Log.e("ToolbarListener", "Impossible to set Toolbar Listener to null! Please Check it");
            }
            else {
                mToolbarListener = toolbarListener;
            }
            return this;
        }

        /**
         * Color Title of Swipeable Card, default is BLACK.
         * @param colorTitle A color for Title of Swipeable Card
         */
        public Builder colorTitle(int colorTitle) {

            if(colorTitle == 0)
            {
                Log.e("ColorTitle", "Impossible to set Color Title to 0, default value BLACK is set! Please Check it");
                mColorTitle = android.R.color.black;
            }
            else {
                mColorTitle = colorTitle;
            }
            return this;
        }

        /**
         * Menu Item for set custom menu item to Swipeable Card.
         * @param menuItem An int from R.menu class.
         */
        public Builder menuItem(int menuItem) {

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
        public Builder title(String title) {
            if(title == null)
            {
                Log.e("Title", "Impossible to set Title to null, default value empty string is set! Please Check it");
                mTitle = "";
            }
            else {
                mTitle = title;
            }
            return this;
        }

        /**
         * Set text on content of Swipeable Card.
         * @param text String for content of Swipeable Card.
         */
        public Builder text(String text) {

            if(text == null)
            {
                Log.e("Text", "Impossible to set Text to null! Please Check it");
            }
            else {
                mText = text;
                isImage = false;
                isText = true;
            }
            return this;
        }

        /**
         * Set sub title of Swipeable Card
         * @param subTitle String for sub title of Swipeable Card
         */
        public Builder subTitle(String subTitle) {

            if(subTitle == null)
            {
                Log.e("SubTitle", "Impossible to set Sub Title to null! Please Check it");
            }
            else {
                mSubTitle = subTitle;
                isSubTitle = true;
            }
            return this;
        }

        /**
         * Image for Swipeable Card.
         * @param image Resource integer which describe image
         */
        public Builder image(int image) {

            if(image == 0)
            {
                Log.e("Image", "Impossible to set Image to 0! Please Check it");
            }
            else {
                mImage = image;
                isImage = true;
                isText = false;
            }
            return this;
        }

        /**
         * Toolbar Color of Swipeable Card.
         * @param toolbarColor Resource integer which describe color
         */
        public Builder toolbarColor(int toolbarColor) {

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
        public OptionView build() {
            return new OptionView(this);
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
    public static void setOptionView(OptionView optionViews)
    {
        optionView = optionViews;
    }

    /**
     * Get OptionView.
     * @return OptionView type
     */
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
    public OptionViewAdditional getOptionViewAdditional() {
        return optionViewAdditional;
    }
}