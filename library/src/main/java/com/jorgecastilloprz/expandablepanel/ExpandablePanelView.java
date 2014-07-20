package com.jorgecastilloprz.expandablepanel;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.jorgecastilloprz.expandablepanel.anim.HeightAnimation;
import com.jorgecastilloprz.expandablepanel.listeners.ExpandableListener;

/**
 * Created by jorge on 16/07/14.
 */
public class ExpandablePanelView extends RelativeLayout {

    private int lastY;
    private int displayHeight;
    private boolean expanded;
    private int initialTopLayoutHeight;
    private View topView;

    private ExpandableListener expandableListener;

    private float completionPercent;
    private int completeExpandAnimationSpeed;
    private int completeShrinkAnimationSpeed; 

    public ExpandablePanelView(Context context) {
        super(context);
        init(null);
    }

    public ExpandablePanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ExpandablePanelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

        //Initial attrs
        if (attrs != null) {

            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandablePanelView);

            completionPercent = a.getFloat(R.styleable.ExpandablePanelView_completionPercent, 0.75f);
            completeExpandAnimationSpeed = a.getInt(R.styleable.ExpandablePanelView_completeExpandAnimationSpeed, 200);
            completeShrinkAnimationSpeed = a.getInt(R.styleable.ExpandablePanelView_completeShrinkAnimationSpeed, 200);

            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (initialTopLayoutHeight == 0 && topView == null)
        {
            checkChildrenCount();

            displayHeight = getMeasuredHeight();

            initialTopLayoutHeight = getChildAt(0).getMeasuredHeight();
            topView = getChildAt(0);
        }
    }

    /**
     * Attachs the listener for expanding/shrinking events
     * @param expandableListener
     */
    public void attachExpandableListener(ExpandableListener expandableListener) {
        this.expandableListener = expandableListener;
    }

    /**
     * Checks if children number is correct and logs an error if it is not
     */
    private void checkChildrenCount() {
        if (getChildCount() != 2)
            Log.e(getResources().getString(R.string.tag), getResources().getString(R.string.wrong_number_children_error));
    }

    public boolean isExpanded() {
        return expanded;
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = (int) motionEvent.getY();

                dispatchGenericMovementStarted();

                break;

            case MotionEvent.ACTION_MOVE:

                int currentY = (int) motionEvent.getY();
                int diff = (currentY - lastY);

                RelativeLayout.LayoutParams topLayoutParams = (RelativeLayout.LayoutParams) topView.getLayoutParams();

                if (topLayoutParams.height >= initialTopLayoutHeight) {
                    topLayoutParams.height += diff;
                }

                topView.setLayoutParams(topLayoutParams);
                lastY = currentY;
                dispatchOnTouchEvent(motionEvent);
                break;

            case MotionEvent.ACTION_UP:

                if (topView.getMeasuredHeight() > displayHeight * completionPercent && !expanded)
                    completeAnimationToFullHeight();
                else
                    completeAnimationToInitialHeight();

                break;
        }
        return true;
    }

    /**
     * Anima la altura del topLayout hasta que ocupe la pantalla completa, y termina el desplazamiento
     * de las vista de avatar y de mapa hasta que queden alineadas en la parte baja de la pantalla
     */
    private void completeAnimationToFullHeight() {
        HeightAnimation heightAnim = new HeightAnimation(topView, topView.getMeasuredHeight(), displayHeight);

        heightAnim.setDuration(completeExpandAnimationSpeed);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        topView.startAnimation(heightAnim);

        expanded = true;
        dispatchGenericMovementFinished();
    }

    /**
     * Anima la altura del topLayout para devolverla a su tamanyo inicial, y devuelve las vistas de
     * avatar y mapa a su posicion inicial
     */
    private void completeAnimationToInitialHeight() {
        HeightAnimation heightAnim = new HeightAnimation(topView, topView.getMeasuredHeight(), initialTopLayoutHeight);

        heightAnim.setDuration(completeShrinkAnimationSpeed);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        topView.startAnimation(heightAnim);

        expanded = false;
        dispatchGenericMovementFinished();
    }

    //Listener actions

    private void dispatchGenericMovementStarted() {
        if (!expanded)
            dispatchExpandingStarted();
        else
            dispatchShrinkingStarted();
    }

    private void dispatchGenericMovementFinished() {
        if (!expanded)
            dispatchShrinkingFinished();
        else
            dispatchExpandingFinished();
    }

    /**
     * This listener is dispatched when the expanding begins by the user
     */
    private void dispatchExpandingStarted() {
        if (expandableListener != null)
            expandableListener.onExpandingStarted();
    }

    /**
     * This listener is dispatched when the expanding finishes
     */
    private void dispatchExpandingFinished() {
        if (expandableListener != null)
            expandableListener.onExpandingFinished();
    }

    /**
     * This listener is dispatched when the shrink (collapse) begins
     */
    private void dispatchShrinkingStarted() {
        if (expandableListener != null)
            expandableListener.onShrinkStarted();
    }

    /**
     * This listener is dispatched when the shrink (collapse) finishes
     */
    private void dispatchShrinkingFinished() {
        if (expandableListener != null)
            expandableListener.onShrinkFinished();
    }

    /**
     * This listener is dispatched meanwhile the expanding movement is being produced
     */
    private void dispatchOnTouchEvent(MotionEvent motionEvent) {
        if (expandableListener != null)
            expandableListener.onExpandingTouchEvent(motionEvent);
    }
}
