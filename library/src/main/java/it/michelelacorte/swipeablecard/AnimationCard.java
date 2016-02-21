package it.michelelacorte.swipeablecard;



/**
 * Animation Card interface, provide method for animation
 * Created by Michele on 02/12/2015.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

/**
 *  AnimationCard is an Interface for set-up animation.
 *  @author Michele Lacorte
 */
public interface AnimationCard {

    /**
     * Animation card start method
     * @param card CardView
     * @param toolbar Toolbar
     */
    void animationCardStart(final CardView card, final Toolbar toolbar);

    /**
     * Animation card down method
     * @param card CardView
     * @param toolbar Toolbar
     * @param duration long
     */
    void animationCardDown(final CardView card, final Toolbar toolbar, long duration);

    /**
     * Animation card up method
     * @param card CardView
     * @param toolbar Toolbar
     * @param duration long
     */
    void animationCardUp(final CardView card, final Toolbar toolbar, long duration);
}
