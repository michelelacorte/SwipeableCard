package it.michelelacorte.swipeablecard;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.Log;
import android.view.View;

import org.jetbrains.annotations.NotNull;

/**
 * OptionViewAdditional provide all custom object settable to SwipeableCard.
 * Have a Builder pattern constructor to set-up object.
 * Created by Michele on 12/12/2015.
 */
public class OptionViewAdditional {
    /**
     * Customizable Icon and Text
     */
    private int mIconBtn1;
    private int mIconBtn2;
    private int mIconBtn3;
    private String mTextBtn1;
    private String mTextBtn2;
    private boolean isIconBtn1 = false;
    private boolean isIconBtn2 = false;
    private boolean isIconBtn3 = false;
    private boolean isTextBtn1 = false;
    private boolean isTextBtn2 = false;
    private int mTextColorBtn1;
    private int mTextColorBtn2;
    private float mTextSizeBtn1;
    private float mTextSizeBtn2;
    private int mFabBackgroundColor;
    private int mFabIcon;
    /*
    Listener
    */
    private View.OnClickListener mOnClickListenerIconBtn1;
    private View.OnClickListener mOnClickListenerIconBtn2;
    private View.OnClickListener mOnClickListenerIconBtn3;
    private View.OnClickListener mOnClickListenerTextBtn1;
    private View.OnClickListener mOnClickListenerTextBtn2;
    private View.OnClickListener mOnClickListenerFab;

    public OptionViewAdditional(@NotNull Builder builder) {
        /**
         * Customizable Icon and Text
         */
        mIconBtn1 = builder.mIconBtn1;
        mIconBtn2 = builder.mIconBtn2;
        mIconBtn3 = builder.mIconBtn3;
        mTextBtn1 = builder.mTextBtn1;
        mTextBtn2 = builder.mTextBtn2;
        isTextBtn1 = builder.isTextBtn1;
        isTextBtn2 = builder.isTextBtn2;
        isIconBtn1 = builder.isIconBtn1;
        isIconBtn2 = builder.isIconBtn2;
        isIconBtn3 = builder.isIconBtn3;
        mFabBackgroundColor = builder.mFabBackgroundColor;
        mFabIcon = builder.mFabIcon;
        /*
        Listener
         */
        if(builder.mOnClickListenerFab == null)
        {
            Log.w("ListenerFab", "Listener is not set!");
        }else
        {
            mOnClickListenerFab = builder.mOnClickListenerFab;
        }
        if(builder.mOnClickListenerIconBtn1 == null)
        {
            Log.w("ListenerIconButton1", "Listener is not set!");
        }else
        {
            mOnClickListenerIconBtn1 = builder.mOnClickListenerIconBtn1;
        }

        if(builder.mOnClickListenerIconBtn2 == null)
        {
            Log.w("ListenerIconButton2", "Listener is not set!");
        }else
        {
            mOnClickListenerIconBtn2 = builder.mOnClickListenerIconBtn2;
        }

        if(builder.mOnClickListenerIconBtn3 == null)
        {
            Log.w("ListenerIconButton3", "Listener is not set!");
        }else
        {
            mOnClickListenerIconBtn3 = builder.mOnClickListenerIconBtn3;
        }
        if(builder.mOnClickListenerTextBtn1 == null)
        {
            Log.w("ListenerTextButton1", "Listener is not set!");
        }else
        {
            mOnClickListenerTextBtn1 = builder.mOnClickListenerTextBtn1;
        }
        if(builder.mOnClickListenerTextBtn2 == null)
        {
            Log.w("ListenerTextButton2", "Listener is not set!");
        }else
        {
            mOnClickListenerTextBtn2 = builder.mOnClickListenerTextBtn2;
        }


        if(builder.mTextSizeBtn1 == 0) {
            Log.e("TextSizeButton1", "Impossible to set Text Size to 0, default value 15sp is set! Please Check it");
            mTextSizeBtn1 = 15f;
        }
        else
        {
            mTextSizeBtn1 = builder.mTextSizeBtn1;
        }
        if(builder.mTextSizeBtn2 == 0) {
            Log.e("TextSizeButton2", "Impossible to set Text Size to 0, default value 15sp is set! Please Check it");
            mTextSizeBtn2 = 15f;
        }
        else
        {
            mTextSizeBtn2 = builder.mTextSizeBtn2;
        }
        if(builder.mTextColorBtn1 == 0)
        {
            Log.e("TextColorButton1", "Impossible to set Text Color Button to 0, default value black is set! Please Check it");
            mTextColorBtn1 = android.R.color.black;

        }else {
            mTextColorBtn1 = builder.mTextColorBtn1;
        }
        if(builder.mTextColorBtn2 == 0)
        {
            Log.e("TextColorButton2", "Impossible to set Text Color Button to 0, default value black is set! Please Check it");
            mTextColorBtn2 = android.R.color.black;

        }else {
            mTextColorBtn2 = builder.mTextColorBtn2;
        }
    }

    public static class Builder {
        /**
         * Customizable Icon and Text
         */
        private int mIconBtn1;
        private int mIconBtn2;
        private int mIconBtn3;
        private String mTextBtn1;
        private String mTextBtn2;
        private boolean isIconBtn1 = false;
        private boolean isIconBtn2 = false;
        private boolean isIconBtn3 = false;
        private boolean isTextBtn1 = false;
        private boolean isTextBtn2 = false;
        private int mTextColorBtn1;
        private int mTextColorBtn2;
        private float mTextSizeBtn1;
        private float mTextSizeBtn2;
        /*
        Listener
         */
        private View.OnClickListener mOnClickListenerIconBtn1;
        private View.OnClickListener mOnClickListenerIconBtn2;
        private View.OnClickListener mOnClickListenerIconBtn3;
        private View.OnClickListener mOnClickListenerTextBtn1;
        private View.OnClickListener mOnClickListenerTextBtn2;
        private View.OnClickListener mOnClickListenerFab;
        private int mFabBackgroundColor;
        private int mFabIcon;

        /**
         * Set OnClickListener for Fab Button
         * @param listener OnClickListener
         */
        public Builder setOnClickListenerFab(@NotNull View.OnClickListener listener)
        {
            mOnClickListenerFab = listener;
            return this;
        }

        /**
         * Set Fab Icon
         * @param icon int
         */
        public Builder setFabIcon(@DrawableRes int icon)
        {
            if(icon <= 0)
            {
                Log.e("FabIcon", "Impossible to set Fab Icon Resource lower than 0! Please Check it");
            }
            else {
                mFabIcon = icon;
            }
            return this;
        }


        /**
         * Set Fab Color
         * @param color int
         */
        public Builder setFabColor(@ColorRes int color)
        {
            if(color <= 0)
            {
                Log.e("FabColor", "Impossible to set Fab Color lower than 0! Please Check it");
                mFabBackgroundColor = android.R.color.transparent;
            }
            else {
                mFabBackgroundColor = color;
            }
            return this;
        }

        /**
         * Set OnClickListener for Text Button
         * @param listeners OnClickListener interface
         */
        public Builder setOnClickListenerTextButton(@NotNull View.OnClickListener... listeners)
        {
            if(listeners.length > 2)
            {
                Log.e("ListenerTextButton", "Impossible to set Listener Button value more than 2! Please Check it");
                throw new IllegalArgumentException("Impossible to set Listener Icon Button value more than 2! Please Check it");
            }
            if(listeners.length > 0)
            {
                if(listeners[0] == null) {
                    Log.e("ListenerTextButton", "Impossible to set Listener Text Button to null! Please Check it");
                }
                else{
                    mOnClickListenerTextBtn1 = listeners[0];
                }
            }
            if(listeners.length > 1)
            {
                if(listeners[1] == null) {
                    Log.e("ListenerTextButton", "Impossible to set Listener Text Button to null! Please Check it");
                }
                else{
                    mOnClickListenerTextBtn2 = listeners[1];
                }
            }
            return this;
        }

        /**
         * Set OnClickListener for Icon Button
         * @param listeners OnClickListener interface
         */
        public Builder setOnClickListenerIconButton(@NotNull View.OnClickListener... listeners)
        {
            if(listeners.length > 3)
            {
                Log.e("ListenerIconButton", "Impossible to set Listener Button value more than 3! Please Check it");
                throw new IllegalArgumentException("Impossible to set Listener Icon Button value more than 2! Please Check it");
            }
            if(listeners.length > 0)
            {
                if(listeners[0] == null) {
                    Log.e("ListenerIconButton", "Impossible to set Listener Icon Button to null! Please Check it");
                }
                else{
                    mOnClickListenerIconBtn1 = listeners[0];
                }
            }
            if(listeners.length > 1)
            {
                if(listeners[1] == null) {
                    Log.e("ListenerIconButton", "Impossible to set Listener Icon Button to null! Please Check it");
                }
                else{
                    mOnClickListenerIconBtn2 = listeners[1];
                }
            }
            if(listeners.length > 2)
            {
                if(listeners[2] == null) {
                    Log.e("ListenerIconButton", "Impossible to set Listener Icon Button to null! Please Check it");
                }
                else{
                    mOnClickListenerIconBtn3 = listeners[1];
                }
            }
            return this;
        }

        /**
         * Set Text Size
         * @param size float
         */
        public Builder textSize(float... size)
        {
            if(size.length > 2)
            {
                Log.e("TextSizeButton", "Impossible to set Text Size value more than 2! Please Check it");
                throw new IllegalArgumentException("Impossible to set Text Size value more than 2! Please Check it");
            }
            if(size.length > 0)
            {
                if(size[0] == 0) {
                    Log.e("TextSizeButton", "Impossible to set Text Size to 0, default value 15sp is set! Please Check it");
                    mTextSizeBtn1 = 15f;
                }
                else
                {
                    mTextSizeBtn1 = size[0];
                }
            }
            if(size.length > 1)
            {
                if(size[1] == 0) {
                    Log.e("TextSizeButton", "Impossible to set Text Size to 0, default value 15sp is set! Please Check it");
                    mTextSizeBtn2 = 15f;
                }
                else {
                    mTextSizeBtn2 = size[1];
                }
            }
            return this;
        }

        /**
         * Set Text Color of Button Text
         * @param color int
         */
        public Builder textColorButton(@ColorRes int... color)
        {
            if(color.length > 2)
            {
                Log.e("TextColorButton", "Impossible to set Text Color Button value more than 2! Please Check it");
                throw new IllegalArgumentException("Impossible to set Text Color Button value more than 2! Please Check it");
            }
            if(color.length > 0)
            {
                if(color[0] == 0) {
                    Log.e("TextColorButton", "Impossible to set Text Color Button to 0, default value black is set! Please Check it");
                    mTextColorBtn1 = android.R.color.black;
                }
                else{
                    mTextColorBtn1 = color[0];
                }
            }
            if(color.length > 1)
            {
                if(color[1] == 0) {
                    Log.e("TextColorButton", "Impossible to set Text Color Button to 0, default value black is set! Please Check it");
                    mTextColorBtn2 = android.R.color.black;
                }
                else{
                    mTextColorBtn2 = color[1];
                }
            }
            return this;
        }

        /**
         * Set Icon on Icon Button
         * @param icon int
         */
        public Builder iconButton(@DrawableRes int... icon)
        {
            if(icon.length > 3)
            {
                Log.e("TextColorButton", "Impossible to set Icon Button value more than 3! Please Check it");
                throw new IllegalArgumentException("Impossible to set Icon Button value more than 3! Please Check it");
            }
            if(icon.length > 0)
            {
                if(icon[0] == 0) {
                    Log.e("Icon", "Impossible to set Icon to 0! Please Check it");
                }else{
                    mIconBtn1 = icon[0];
                    isIconBtn1 = true;
                }
            }
            if(icon.length > 1)
            {
                if(icon[1] == 0) {
                    Log.e("Icon", "Impossible to set Icon to 0! Please Check it");
                }else{
                    mIconBtn2 = icon[1];
                    isIconBtn2 = true;
                }
            }

            if(icon.length > 2)
            {
                if(icon[2] == 0) {
                    Log.e("Icon", "Impossible to set Icon to 0! Please Check it");
                }else{
                    mIconBtn3 = icon[2];
                    isIconBtn3 = true;
                }
            }
            return this;
        }

        /**
         * Set Text on Text Button
         * @param text String
         */
        public Builder textButton(@NotNull String... text)
        {
            if(text.length > 2)
            {
                Log.e("TextColorButton", "Impossible to set Text Button value more than 2! Please Check it");
                throw new IllegalArgumentException("Impossible to set Text Button value more than 2! Please Check it");
            }
            if(text.length > 0)
            {
                if(text[0] == null) {
                    Log.e("TextButton", "Impossible to set Text to null! Please Check it");
                }
                else
                {
                    mTextBtn1 = text[0];
                    isTextBtn1 = true;
                }
            }
            if(text.length > 1)
            {
                if(text[1] == null) {
                    Log.e("TextButton", "Impossible to set Text to null! Please Check it");
                }
                else {
                    mTextBtn2 = text[1];
                    isTextBtn2 = true;
                }
            }
            return this;
        }

        /**
         * Constructor build()
         */
        public OptionViewAdditional build() {
            return new OptionViewAdditional(this);
        }
    }

    /**
     * Return true (boolean) if isIconBtn1 is set
     * @return true (boolean) if isIconBtn1 is set
     */
    public boolean isIconBtn1() {
        return isIconBtn1;
    }

    /**
     * Return true (boolean) if isIconBtn2 is set
     * @return true (boolean) if isIconBtn2 is set
     */
    public boolean isIconBtn2() {
        return isIconBtn2;
    }

    /**
     * Return true (boolean) if isIconBtn3 is set
     * @return true (boolean) if isIconBtn3 is set
     */
    public boolean isIconBtn3() {
        return isIconBtn3;
    }

    /**
     * Return true (boolean) if isTextBtn1 is set
     * @return true (boolean) if isTextBtn1 is set
     */
    public boolean isTextBtn1() {
        return isTextBtn1;
    }

    /**
     * Return true (boolean) if isTextBtn1 is set
     * @return true (boolean) if isTextBtn1 is set
     */
    public boolean isTextBtn2() {
        return isTextBtn2;
    }

    /**
     * Return int of Icon Button 1
     * @return int of Icon Button 1
     */
    public int getIconBtn1() {
        return mIconBtn1;
    }

    /**
     * Return int of Icon Button 2
     * @return int of Icon Button 2
     */
    public int getIconBtn2() {
        return mIconBtn2;
    }

    /**
     * Return int of Icon Button 3
     * @return int of Icon Button 3
     */
    public int getIconBtn3() {
        return mIconBtn3;
    }

    /**
     * Return String of Text Button 1
     * @return String of Text Button 1
     */
    public String getTextBtn1() {
        return mTextBtn1;
    }

    /**
     * Return String of Text Button 2
     * @return String of Text Button 2
     */
    public String getTextBtn2() {
        return mTextBtn2;
    }

    /**
     * Return int color of Text Button 1
     * @return int color of Text Button 1
     */
    public int getTextColorBtn1() {
        return mTextColorBtn1;
    }

    /**
     * Return int color of Text Button 2
     * @return int color of Text Button 2
     */
    public int getTextColorBtn2() {
        return mTextColorBtn2;
    }

    /**
     * Return float size of Text Button 1
     * @return float size of Text Button 1
     */
    public float getTextSizeBtn1() {
        return mTextSizeBtn1;
    }

    /**
     * Return float size of Text Button 2
     * @return float size of Text Button 2
     */
    public float getTextSizeBtn2() {
        return mTextSizeBtn2;
    }

    /**
     * Return onClickListener  of Icon Button 1
     * @return onClickListener  of Icon Button 1
     */
    @NotNull
    public View.OnClickListener getOnClickListenerIconBtn1() {
        return mOnClickListenerIconBtn1;
    }

    /**
     * Return onClickListener  of Icon Button 2
     * @return onClickListener  of Icon Button 2
     */
    @NotNull
    public View.OnClickListener getOnClickListenerIconBtn2() {
        return mOnClickListenerIconBtn2;
    }

    /**
     * Return onClickListener  of Text Button 1
     * @return onClickListener  of Text Button 1
     */
    @NotNull
    public View.OnClickListener getOnClickListenerTextBtn1() {
        return mOnClickListenerTextBtn1;
    }

    /**
     * Return onClickListener  of Text Button 2
     * @return onClickListener  of Text Button 2
     */
    @NotNull
    public View.OnClickListener getOnClickListenerTextBtn2() {
        return mOnClickListenerTextBtn2;
    }

    /**
     * Return onClickListener  of Icon Button 1
     * @return onClickListener  of Icon  Button 1
     */
    @NotNull
    public View.OnClickListener getOnClickListenerIconBtn3() {
        return mOnClickListenerIconBtn3;
    }

    /**
     * Get Floating Action Button color
     * @return int color
     */
    public int getFabBackgroundColor() {
        return mFabBackgroundColor;
    }

    /**
     * Get Floating Action Button icon
     * @return int icon
     */
    public int getFabIcon() {
        return mFabIcon;
    }

    /**
     * Get OnClickListener for Floating Action Button
     * @return OnClickListener fab
     */
    @NotNull
    public View.OnClickListener getOnClickListenerFab() {
        return mOnClickListenerFab;
    }


}
