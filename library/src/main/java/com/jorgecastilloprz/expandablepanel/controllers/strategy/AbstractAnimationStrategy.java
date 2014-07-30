package com.jorgecastilloprz.expandablepanel.controllers.strategy;

import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.jorgecastilloprz.expandablepanel.anim.HeightAnimation;

/**
 * Created by jorge on 28/07/14.
 */
public abstract class AbstractAnimationStrategy {

    protected boolean bounceEnabled = true;
    protected int displayHeight;
    protected View animableView;

    public AbstractAnimationStrategy(int displayHeight, View animableView) {
        this.displayHeight = displayHeight;
        this.animableView = animableView;
    }

    public void setBounceEnabled(boolean bounceEnabled) {
        this.bounceEnabled = bounceEnabled;
    }

    /**
     * Animates animableView until it reaches full screen height
     */
    public void completeAnimationToFullHeight(int completeExpandAnimationSpeed) {
        HeightAnimation heightAnim = new HeightAnimation(animableView, animableView.getMeasuredHeight(), displayHeight);

        heightAnim.setDuration(completeExpandAnimationSpeed);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        animableView.startAnimation(heightAnim);
    }

    /**
     * Animates animableView to get its initial height back
     */
    public void completeAnimationToInitialHeight(int completeShrinkAnimationSpeed, int initialAnimableLayoutHeight) {
        HeightAnimation heightAnim = new HeightAnimation(animableView, animableView.getMeasuredHeight(), initialAnimableLayoutHeight);

        heightAnim.setDuration(completeShrinkAnimationSpeed);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        animableView.startAnimation(heightAnim);
    }

    public abstract void playBounceAnimation(final int bounceCount);
    public abstract void updateAnimableViewHeight(View animableView, int initialAnimableLayoutHeight, int heightDiff);
}
