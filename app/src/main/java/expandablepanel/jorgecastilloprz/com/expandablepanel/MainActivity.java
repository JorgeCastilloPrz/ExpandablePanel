package expandablepanel.jorgecastilloprz.com.expandablepanel;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
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

    }

    @Override
    public void onShrinkStarted() {
    }

    @Override
    public void onShrinkFinished() {

    }

    @Override
    public void onExpandingTouchEvent(MotionEvent motionEvent) {
    }
}
