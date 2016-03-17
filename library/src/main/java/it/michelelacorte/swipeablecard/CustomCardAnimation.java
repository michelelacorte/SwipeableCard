package it.michelelacorte.swipeablecard;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.WindowManager;

/**
 * CustomCardAnimation provide animation when use Custom Layout of CardView and you have to get
 * bottom up animation
 * Created by Michele on 11/12/2015.
 */

/**
 * Custom Card Animation is a class that allow user to use animation with
 * all card layout
 * @author Michele Lacorte
 */
@SuppressWarnings("unused")
public class CustomCardAnimation {
    private Context mContext;
    private CardView mCardView;
    private int mStartCardPosition;
    private long mDuration = 500;

    /**
     * Public constructor for set-up animation
     * @param context Context
     * @param card CardView reference
     * @param startCardPosition int which rapresent initial card height
     */
    public CustomCardAnimation(Context context, CardView card, int startCardPosition)
    {
        if(card == null)
        {
            throw new IllegalArgumentException("CardView can not be null, please check it!");
        }
        if(context == null)
        {
            throw new IllegalArgumentException("Context can not be null, please check it!");
        }
        if(startCardPosition <= 0)
        {
            throw new IllegalArgumentException("Start Card Position can not be <= 0, please check it!");
        }
        this.mCardView = card;
        this.mContext = context;
        this.mStartCardPosition = startCardPosition;
        animationCustomCardStart();
    }

    /**
     * Public constructor for set-up animation
     * @param context Context
     * @param card CardView
     * @param startCardPosition int for Start Card Position
     * @param duration Duration of animation
     */
    public CustomCardAnimation(Context context, CardView card, int startCardPosition, long duration)
    {
        if(card == null)
        {
            throw new IllegalArgumentException("CardView can not be null, please check it!");
        }
        if(context == null)
        {
            throw new IllegalArgumentException("Context can not be null, please check it!");
        }
        if(startCardPosition <= 0)
        {
            throw new IllegalArgumentException("Start Card Position can not be <= 0, please check it!");
        }
        this.mCardView = card;
        this.mContext = context;
        this.mStartCardPosition = startCardPosition;
        this.mDuration = duration;
        animationCustomCardStart();
    }
    /**
     * Start animation and set card at start position
     * Do not modify this!
     */
    @TargetApi(14)
    private void animationCustomCardStart()
    {
        final int height = getScreenSize(mContext);
        new CountDownTimer(300, 1) {
            public void onTick(long millisUntilFinished) {
                mCardView.setTranslationY(height - ((int)(mStartCardPosition * 1.7)));
            }

            public void onFinish() {
                mCardView.setTranslationY(height - ((int)(mStartCardPosition * 1.7)));
            }
        }.start();
    }

    /**
     * Down animation
     * Do not modify this!
     */
    @TargetApi(14)
    public void animationCustomCardDown()
    {
        final int height = getScreenSize(mContext);
                new CountDownTimer(1, 1) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        mCardView.animate()
                                .translationY(height - ((int) (mStartCardPosition * 1.7)))
                                .setDuration(mDuration).start();
                    }
                }.start();
    }

    /**
     * Up animation
     * Do not modify this!
     */
    @TargetApi(14)
    public void animationCustomCardUp()
    {
        final int height = getScreenSize(mContext);
        new CountDownTimer(1, 1) {
            public void onTick(long millisUntilFinished) {
            }

                    public void onFinish() {
                        mCardView.animate()
                                .translationY(height - (mCardView.getHeight() + mStartCardPosition))
                                .setDuration(mDuration).start();
                    }
                }.start();
    }

    /**
     * Get Size of Screen (Height)
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
}
