package com.gmat.lg.paddy.gmat_app_re;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dengpan.pan.common.app.BaseActivity;
import com.gmat.lg.paddy.gmat_app_re.fragment.MakeTestFragment;
import com.gmat.lg.paddy.gmat_app_re.fragment.PersonalFragment;
import com.gmat.lg.paddy.gmat_app_re.fragment.PreCourseFragment;
import com.gmat.lg.paddy.gmat_app_re.fragment.ReadyTestFragment;
import com.gmat.lg.paddy.gmat_app_re.fragment.SmallCourseFragment;
import com.gmat.lg.paddy.gmat_app_re.helper.NavHelper;

import butterknife.BindView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavHelper.OnTabChangedLisener<Integer>, BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;

    private NavHelper<Integer> mNavHelper ;


    @Override
    protected void initWidget() {
        super.initWidget();
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mNavHelper = new NavHelper<>(this,R.id.container,getSupportFragmentManager(),this);
        mNavHelper.add(R.id.navigation_pre_course,new NavHelper.Tab<Integer>(PreCourseFragment.class,R.string.nav_title_pre_test))
                .add(R.id.navigation_test,new NavHelper.Tab<Integer>(MakeTestFragment.class,R.string.nav_title_make_test))
                .add(R.id.navigation_community,new NavHelper.Tab<Integer>(ReadyTestFragment.class,R.string.nav_title_ready_community))
                .add(R.id.navigation_small_course,new NavHelper.Tab<Integer>(SmallCourseFragment.class,R.string.nav_title_small_course))
                .add(R.id.navigation_account,new NavHelper.Tab<Integer>(PersonalFragment.class,R.string.nav_title_person_center));

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Menu menu = bottomNavigationView.getMenu();
        //点一次触发点击
        menu.performIdentifierAction(R.id.navigation_pre_course,0);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else{
            mNavHelper.performClickMenu(id);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * tab 交换得时候
     * @param newTab
     * @param oldTab
     */
    @Override
    public void onTabChanged(NavHelper.Tab<Integer> newTab, NavHelper.Tab<Integer> oldTab) {
        //可以加载title 等等

    }
}
