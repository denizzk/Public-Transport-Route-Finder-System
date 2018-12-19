package com.se.ptrfs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.se.ptrfs.R;
import com.se.ptrfs.fragment.TabAddStopFragment;
import com.se.ptrfs.fragment.TabDeleteStopFragment;
import com.se.ptrfs.fragment.TabEditStopFragment;
import com.se.ptrfs.adapter.SectionsPageAdapter;

public class StopActivity extends AppCompatActivity {

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
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
        tabLayout.getTabAt(0).setText("Add Stop");
//        tabLayout.getTabAt(1).setIcon(R.drawable.ic_explore);
        tabLayout.getTabAt(1).setText("Edit Stop");
        tabLayout.getTabAt(2).setText("Delete Stop");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_explore:
                        Intent intent1 = new Intent(StopActivity.this, MainActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_stops:
                        break;

                    case R.id.ic_routes:
                        Intent intent2 = new Intent(StopActivity.this, RouteActivity.class);
                        startActivity(intent2);
                        break;
                }


                return false;
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {

        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabAddStopFragment());
        adapter.addFragment(new TabEditStopFragment());
        adapter.addFragment(new TabDeleteStopFragment());
        viewPager.setAdapter(adapter);
    }
}
