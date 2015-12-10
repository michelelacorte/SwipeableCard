package it.michelelacorte.swipeablecard;



/**
 * Created by Michele on 02/12/2015.
 */

import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;

/**
 *  AnimationCard is an Interface for set-up animation.
 *  @author Michele Lacorte
 */
public interface AnimationCard {

    public abstract void animationCardStart(final CardView card, final Toolbar toolbar);

    public abstract void animationCardDown(final CardView card, final Toolbar toolbar, long duration);

    public abstract void animationCardUp(final CardView card, final Toolbar toolbar, long duration);
}
