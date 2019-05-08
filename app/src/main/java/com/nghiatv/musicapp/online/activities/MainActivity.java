package com.nghiatv.musicapp.online.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nghiatv.musicapp.R;
import com.nghiatv.musicapp.online.adapters.ViewPagerAdapter;
import com.nghiatv.musicapp.online.fragments.Fragment_TimKiem;
import com.nghiatv.musicapp.online.fragments.OnlineFragment;


public class MainActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        init();
    }

    private void init() {
        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment(new OnlineFragment(), "Trang Chủ");
        mViewPagerAdapter.addFragment(new Fragment_TimKiem(), "Tìm Kiếm");

        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_video);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_search);

    }

    private void initView() {
        mTabLayout = findViewById(R.id.myTablayout);
        mViewPager = findViewById(R.id.myViewPager);
    }
}
