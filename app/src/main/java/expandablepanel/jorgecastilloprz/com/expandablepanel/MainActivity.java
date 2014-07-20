package expandablepanel.jorgecastilloprz.com.expandablepanel;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.jorgecastilloprz.expandablepanel.ExpandablePanelView;
import com.jorgecastilloprz.expandablepanel.listeners.ExpandableListener;


public class MainActivity extends ActionBarActivity implements ExpandableListener {

    private View avatar;
    private ExpandablePanelView expandablePanelView;

    private Typeface robotoThin;
    private Typeface robotoLight;

    private TextView userName;
    private TextView userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        robotoLight = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

        userName = (TextView) findViewById(R.id.userName);
        userDetails = (TextView) findViewById(R.id.userDetails);

        userName.setTypeface(robotoThin);
        userDetails.setTypeface(robotoLight);

        avatar = findViewById(R.id.avatar);
        expandablePanelView = (ExpandablePanelView) findViewById(R.id.expandablePanelView);

        expandablePanelView.attachExpandableListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
