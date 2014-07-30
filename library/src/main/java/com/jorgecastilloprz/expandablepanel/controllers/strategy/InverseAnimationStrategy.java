package com.jorgecastilloprz.expandablepanel.controllers.strategy;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.widget.RelativeLayout;

import com.jorgecastilloprz.expandablepanel.anim.HeightAnimation;

/**
 * Created by jorge on 28/07/14.
 */
public class InverseAnimationStrategy extends AbstractAnimationStrategy {

    public InverseAnimationStrategy(int displayHeight, View animableView) {
        super(displayHeight, animableView);
    }

    @Override
    public void playBounceAnimation(final int bounceCount) {

        if (bounceCount == 0 || !bounceEnabled)
            return;

        int heightValueToBounce = (int) (displayHeight * 0.95);
        int singleAnimDuration = 300;

        final HeightAnimation bounceAnimDown = new HeightAnimation(animableView, heightValueToBounce, displayHeight);
        bounceAnimDown.setDuration(singleAnimDuration);
        bounceAnimDown.setInterpolator(new BounceInterpolator());
        bounceAnimDown.setStartOffset(singleAnimDuration);

        HeightAnimation bounceAnimUp = new HeightAnimation(animableView, displayHeight, heightValueToBounce);
        bounceAnimUp.setDuration(singleAnimDuration);
        bounceAnimUp.setInterpolator(new AccelerateDecelerateInterpolator());
        bounceAnimUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animableView.startAnimation(bounceAnimDown);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        animableView.startAnimation(bounceAnimUp);

        animableView.postDelayed(new Runnable() {
            @Override
            public void run() {
                playBounceAnimation(bounceCount-1);
            }
        }, 2000);
    }

    @Override
    public void updateAnimableViewHeight(View animableView, int initialAnimableViewHeight, int heightDiff) {
        RelativeLayout.LayoutParams animableViewParams = (RelativeLayout.LayoutParams) animableView.getLayoutParams();

        if (animableViewParams.height >= initialAnimableViewHeight) {
            animableViewParams.height -= heightDiff;
        }

        animableView.setLayoutParams(animableViewParams);
    }
}
