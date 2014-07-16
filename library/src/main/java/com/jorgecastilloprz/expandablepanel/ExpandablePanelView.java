package com.jorgecastilloprz.expandablepanel;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.jorgecastilloprz.expandablepanel.anim.HeightAnimation;
import com.jorgecastilloprz.expandablepanel.utils.DisplayUtils;

/**
 * Created by jorge on 16/07/14.
 */
public class ExpandablePanelView extends LinearLayout {

    private int lastY;
    private int displayHeight;
    private boolean expanded;
    private int initialTopLayoutHeight;
    private View topView;
    private View bottomView;

    public ExpandablePanelView(Context context) {
        super(context);
        init();
    }

    public ExpandablePanelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public ExpandablePanelView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        checkChildrenCount();
        displayHeight = DisplayUtils.getDisplayHeight(getContext());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initialTopLayoutHeight = getChildAt(0).getMeasuredHeight();
        topView = getChildAt(0);
        bottomView = getChildAt(1);
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
                break;

            case MotionEvent.ACTION_MOVE:

                int currentY = (int) motionEvent.getY();
                int diff = (currentY - lastY);

                LinearLayout.LayoutParams topLayoutParams = (LinearLayout.LayoutParams) topView.getLayoutParams();

                if (topLayoutParams.height >= initialTopLayoutHeight) {
                    topLayoutParams.height += diff;
                }

                topView.setLayoutParams(topLayoutParams);

                lastY = currentY;
                break;

            case MotionEvent.ACTION_UP:

                if (topView.getHeight() > displayHeight * 3 / 4 && !expanded)
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

        HeightAnimation heightAnim = new HeightAnimation(topView, topView.getHeight(), displayHeight);

        heightAnim.setDuration(200);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        topView.startAnimation(heightAnim);

        expanded = true;
    }

    /**
     * Anima la altura del topLayout para devolverla a su tamanyo inicial, y devuelve las vistas de
     * avatar y mapa a su posicion inicial
     */
    private void completeAnimationToInitialHeight() {
        HeightAnimation heightAnim = new HeightAnimation(topView, topView.getHeight(), initialTopLayoutHeight);

        heightAnim.setDuration(200);
        heightAnim.setInterpolator(new DecelerateInterpolator());
        topView.startAnimation(heightAnim);

        expanded = false;
    }
}
