package expandablepanel.jorgecastilloprz.com.expandablepanel.ui.activities;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import expandablepanel.jorgecastilloprz.com.expandablepanel.R;
import expandablepanel.jorgecastilloprz.com.expandablepanel.ui.fragments.BasicBehaviorFragment;
import expandablepanel.jorgecastilloprz.com.expandablepanel.ui.fragments.InverseBehaviorFragment;
import expandablepanel.jorgecastilloprz.com.expandablepanel.ui.utils.FragmentTypes;


public class MainActivity extends FragmentActivity implements ListView.OnItemClickListener {

    private String[] sectionTitles;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        sectionTitles = getResources().getStringArray(R.array.fragment_names);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, sectionTitles));
        mDrawerList.setOnItemClickListener(this);

        selectItem(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (item.getItemId() == R.id.actionExit)
            finish();

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {

        Fragment fragment = null;
        Bundle args;
        switch (position) {
            case 0:
                fragment = new BasicBehaviorFragment();
                args = new Bundle();
                args.putInt("type", FragmentTypes.BASIC.ordinal());
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new BasicBehaviorFragment();
                args = new Bundle();
                args.putInt("type", FragmentTypes.INVERSE.ordinal());
                fragment.setArguments(args);
                break;
            case 2:
                fragment = new InverseBehaviorFragment();
                args = new Bundle();
                args.putInt("type", FragmentTypes.BASIC.ordinal());
                fragment.setArguments(args);
                break;
            case 3:
                fragment = new InverseBehaviorFragment();
                args = new Bundle();
                args.putInt("type", FragmentTypes.INVERSE.ordinal());
                fragment.setArguments(args);
                break;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(sectionTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }
}
