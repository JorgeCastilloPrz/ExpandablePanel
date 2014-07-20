package com.jorgecastilloprz.expandablepanel.listeners;

import android.view.MotionEvent;

/**
 * Created by jorge on 19/07/14.
 */
public interface ExpandableListener {

    void onExpandingStarted();
    void onExpandingFinished();
    void onShrinkStarted();
    void onShrinkFinished();
    void onExpandingTouchEvent(MotionEvent motionEvent);
}
