package com.se.ptrfs.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.se.ptrfs.R;
import com.se.ptrfs.fragment.TabFindRouteFragment;
import com.se.ptrfs.fragment.TabNearbyStopFragment;
import com.se.ptrfs.adapter.SectionsPageAdapter;

public class MainActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupPage();
    }

    private void setupPage() {

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_explore);
        tabLayout.getTabAt(0).setText("Find Routes");
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_explore);
        tabLayout.getTabAt(1).setText("Check Nearby Stops");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_explore:

                        break;

                    case R.id.ic_stops:
                        Intent intent1 = new Intent(MainActivity.this, StopActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_routes:
                        Intent intent2 = new Intent(MainActivity.this, RouteActivity.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabFindRouteFragment());
        adapter.addFragment(new TabNearbyStopFragment());
        viewPager.setAdapter(adapter);
    }


}
