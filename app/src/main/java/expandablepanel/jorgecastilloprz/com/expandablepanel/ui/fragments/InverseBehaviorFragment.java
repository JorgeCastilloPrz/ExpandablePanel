package expandablepanel.jorgecastilloprz.com.expandablepanel.ui.fragments;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.jorgecastilloprz.expandablepanel.ExpandablePanelView;
import com.jorgecastilloprz.expandablepanel.listeners.ExpandableListener;

import expandablepanel.jorgecastilloprz.com.expandablepanel.R;
import expandablepanel.jorgecastilloprz.com.expandablepanel.ui.utils.FragmentTypes;

/**
 * Created by jorge on 31/07/14.
 */
public class InverseBehaviorFragment extends Fragment implements ExpandableListener{

    private View rootView;

    private View avatar;
    private ExpandablePanelView expandablePanelView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null)
            return rootView;

        int fragmentType = getArguments().getInt("type", FragmentTypes.BASIC.ordinal());

        switch (fragmentType) {
            case 0:
                rootView = inflater.inflate(R.layout.fragment_inverse_behaviour, container, false);
                break;
            case 1:
                rootView = inflater.inflate(R.layout.fragment_inverse_expanded_behaviour, container, false);
                break;
        }

        avatar = rootView.findViewById(R.id.avatar);
        expandablePanelView = (ExpandablePanelView) rootView.findViewById(R.id.expandablePanelView);

        expandablePanelView.attachExpandableListener(this);

        return rootView;
    }

    @Override
    public void onExpandingStarted() {
    }

    @Override
    public void onExpandingFinished() {
        ValueAnimator avatarAnim = ObjectAnimator.ofFloat(avatar, "alpha", 1, 0, 0);
        avatarAnim.setDuration(1000).setInterpolator(new DecelerateInterpolator());

        avatarAnim.start();
    }

    @Override
    public void onShrinkStarted() {
    }

    @Override
    public void onShrinkFinished() {
        ValueAnimator avatarAnim = ObjectAnimator.ofFloat(avatar, "alpha", 0, 1, 1);
        avatarAnim.setDuration(1000).setInterpolator(new DecelerateInterpolator());

        avatarAnim.start();
    }

    @Override
    public void onExpandingTouchEvent(MotionEvent motionEvent) {
    }
}
