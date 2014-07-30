package com.jorgecastilloprz.expandablepanel.controllers;

import android.view.View;

import com.jorgecastilloprz.expandablepanel.controllers.strategy.AbstractAnimationStrategy;
import com.jorgecastilloprz.expandablepanel.controllers.strategy.BasicAnimationStrategy;

/**
 * Created by jorge on 28/07/14.
 */
public class AnimationController {

    private static AnimationController INSTANCE = null;
    private AbstractAnimationStrategy animationStrategy;

    private AnimationController(int displayHeight, View animableView){
        this.animationStrategy = new BasicAnimationStrategy(displayHeight, animableView); //default strat
    }

    private synchronized static void createInstance(int displayHeight, View animableView) {
        if (INSTANCE == null) {
            INSTANCE = new AnimationController(displayHeight, animableView);
        }
    }

    public static AnimationController getInstance(int displayHeight, View animableView) {
        createInstance(displayHeight, animableView);
        return INSTANCE;
    }

    public void setAnimationStrategy(AbstractAnimationStrategy animationStrategy) {
        this.animationStrategy = animationStrategy;
    }

    public void setBounceEnabled(boolean bounceEnabled) {
        animationStrategy.setBounceEnabled(bounceEnabled);
    }

    public void updateAnimableViewHeight(View animableView, int initialAnimableLayoutHeight, int heightDiff) {
        animationStrategy.updateAnimableViewHeight(animableView, initialAnimableLayoutHeight, heightDiff);
    }

    public void playBounceAnimation(final int bounceCount) {
        animationStrategy.playBounceAnimation(bounceCount);
    }

    public void completeAnimationToFullHeight(int completeExpandAnimationSpeed) {
        animationStrategy.completeAnimationToFullHeight(completeExpandAnimationSpeed);
    }

    public void completeAnimationToInitialHeight(int completeShrinkAnimationSpeed, int initialAnimableLayoutHeight) {
        animationStrategy.completeAnimationToInitialHeight(completeShrinkAnimationSpeed, initialAnimableLayoutHeight);
    }
}
