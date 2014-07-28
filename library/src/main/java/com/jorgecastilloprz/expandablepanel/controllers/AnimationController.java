package com.jorgecastilloprz.expandablepanel.controllers;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.jorgecastilloprz.expandablepanel.anim.HeightAnimation;

/**
 * Created by jorge on 28/07/14.
 */
public class AnimationController {

    private static AnimationController INSTANCE = null;
    private int displayHeight;
    private View animableView;
    private boolean bounceEnabled;

    private AnimationController(int displayHeight, View animableView){
        this.displayHeight = displayHeight;
        this.animableView = animableView;
        this.bounceEnabled = true;
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

    public void setBounceEnabled(boolean bounceEnabled) {
        this.bounceEnabled = bounceEnabled;
    }

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

    /**
     * Anima la altura del topLayout hasta que ocupe la pantalla completa, y termina el desplazamiento
     * de las vista de avatar y de mapa hasta que queden alineadas en la parte baja de la pantalla
     */
    public void completeAnimationToFullHeight(int completeExpandAnimationSpeed) {
        HeightAnimation heightAnim = new HeightAnimation(animableView, animableView.getMeasuredHeight(), displayHeight);

        heightAnim.setDuration(completeExpandAnimationSpeed);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        animableView.startAnimation(heightAnim);
    }

    /**
     * Anima la altura del topLayout para devolverla a su tamanyo inicial, y devuelve las vistas de
     * avatar y mapa a su posicion inicial
     */
    public void completeAnimationToInitialHeight(int completeShrinkAnimationSpeed, int initialTopLayoutHeight) {
        HeightAnimation heightAnim = new HeightAnimation(animableView, animableView.getMeasuredHeight(), initialTopLayoutHeight);

        heightAnim.setDuration(completeShrinkAnimationSpeed);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        animableView.startAnimation(heightAnim);
    }
}
