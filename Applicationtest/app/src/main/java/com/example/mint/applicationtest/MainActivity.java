package com.example.mint.applicationtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {
    private ViewPager viewPager;
    private RiceSlidePager pager;
    private BottomNavigationView bottomNavigation;


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());


        /*ActionBar ab = getSupportActionBar();

        ab.setDisplayHomeAsUpEnabled(true);*/


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pager = new RiceSlidePager(getSupportFragmentManager(), 4);
        viewPager.setAdapter(pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Menu menu = bottomNavigation.getMenu();
                bottomNavigation.setOnNavigationItemSelectedListener(MainActivity.this);
                switch (position) {
                    case 0:

                        //bottomNavigation.setSelectedItemId(R.id.menu_rice_new);
                        MenuItem menuItem0 = menu.getItem(0);
                        menuItem0.setChecked(true);
                        break;
                    case 1:
                        //bottomNavigation.setSelectedItemId(R.id.menu_rice_);


                        MenuItem menuItem = menu.getItem(1);
                        menuItem.setChecked(true);
                        break;
                    case 2:

                        //bottomNavigation.setSelectedItemId(R.id.menu_rice_middle);

                        MenuItem menuItem2 = menu.getItem(2);
                        menuItem2.setChecked(true);
                        break;
                    case 3:
                        //bottomNavigation.setSelectedItemId(R.id.menu_rice_heavy);

                        MenuItem menuItem3 = menu.getItem(3);
                        menuItem3.setChecked(true);
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //viewPager.addOnPageChangeListener(this);

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
        //Menu menu = bottomNavigation.getMenu();
        //MenuItem menuItem = menu.getItem(1);
        //menuItem.setChecked(true);
//        bottomNavigation.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                int id = item.getItemId();
//                switch (id) {
//                    case R.id.menu_rice_new:
//                        viewPager.setCurrentItem(0);
//                        break;
//                    case R.id.menu_rice_light:
//                        viewPager.setCurrentItem(1);
//                        break;
//                    case R.id.menu_rice_middle:
//                        viewPager.setCurrentItem(2);
//                        break;
//                    case R.id.menu_rice_heavy:
//                        viewPager.setCurrentItem(3);
//                        break;
//
//                }
//
//                return true;
//            }
//        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_rice_new:
                viewPager.setCurrentItem(0);
                break;
            case R.id.menu_rice_light:
                viewPager.setCurrentItem(1);
                break;
            case R.id.menu_rice_middle:
                viewPager.setCurrentItem(2);
                break;
            case R.id.menu_rice_heavy:
                viewPager.setCurrentItem(3);
                break;

        }

        // return true;
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.basket:
                startActivity(new Intent(MainActivity.this, Basket.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
