package it.michelelacorte.swipeablecard;

import android.support.annotation.AnyRes;
import android.support.annotation.ColorRes;
import android.support.v7.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

/**
 * Interface to create a custom SwipeableCard, need to implements this with default method
 * Created by Michele on 13/02/2016.
 */
@SuppressWarnings("unused")
public interface Card {

    /**
     * Public builder to set OptionView class with custom option.
     */
    OptionView build();

    /**
     * Toolbar Color of Swipeable Card.
     * @param toolbarColor Resource integer which describe color
     */
    Card toolbarColor(@ColorRes int toolbarColor);

    /**
     * Swipe to dismiss (left-right) animation for card, default false
     * @param isSwipe boolean
     */
    Card setSwipeToDismiss(boolean isSwipe);

    /**
     * Set sub title of Swipeable Card
     * @param subTitle String for sub title of Swipeable Card
     */
    Card subTitle(@NotNull String subTitle);

    /**
     * Set up your Title to Swipeable Card.
     * @param title String for Swipeable Card Title
     */
    Card title(@NotNull String title);

    /**
     * Menu Item for set custom menu item to Swipeable Card.
     * @param menuItem An int from R.menu class.
     */
    Card menuItem(@AnyRes int menuItem);

    /**
     * Color Title of Swipeable Card, default is BLACK.
     * @param colorTitle A color for Title of Swipeable Card
     */
    Card colorTitle(@ColorRes int colorTitle);

    /**
     * Toolbar Listener for set own listener with own option to menu item.
     * @param toolbarListener An listener thath implements OnMenuItemClickListener(..)
     */
    Card toolbarListener(@NotNull Toolbar.OnMenuItemClickListener toolbarListener);

    /**
     * Set animation duration for up and down Swipeable Card
     * @param durationInMillis representing the duration
     */
    Card setDuration(long durationInMillis);

    /**
     * Set additional item with OptionViewAdditional.Builder() constructor
     * @param option OptionViewAdditional
     */
    Card setAdditionalItem(@NotNull OptionViewAdditional option);

    /**
     * Set radius of SwipeableCard
     * @param radius float rapresent radius
     */
    Card setCardRadius(float radius);

    /**
     * Set auto animation of SwipeableCard
     * @param autoAnimation boolean auto animation, default true
     */
    Card setAutoAnimation(boolean autoAnimation);
}
