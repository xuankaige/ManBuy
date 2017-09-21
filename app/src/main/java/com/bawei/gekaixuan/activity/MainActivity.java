package com.bawei.gekaixuan.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.gekaixuan.R;
import com.bawei.gekaixuan.fragment.ClassFragment;
import com.bawei.gekaixuan.fragment.HomeFragment;
import com.bawei.gekaixuan.fragment.ShoppingcartFragment;
import com.bawei.gekaixuan.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // 工具栏

    // 导航布局
    private TabLayout tabLayout;
    // 视图对象
    private ViewPager viewPager;
    // 自定义类，导航布局的适配器
    private TabAdaper tabAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {

        viewPager = (ViewPager) findViewById(R.id.mypager);
        tabLayout = (TabLayout) findViewById(R.id.mytab);
        tabAdaper = new TabAdaper(getSupportFragmentManager());
        viewPager.setAdapter(tabAdaper);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.nav_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.nav_class);
        tabLayout.getTabAt(2).setIcon(R.drawable.nav_cart);
        tabLayout.getTabAt(3).setIcon(R.drawable.nav_user);
    }

    class TabAdaper extends FragmentPagerAdapter {

        List<Fragment> fragmentList = new ArrayList<>();

        String[] titles = {" 首页", "分类", "购物车", "我的"};

        public TabAdaper(FragmentManager fm) {
            super(fm);
            fragmentList.add(new HomeFragment());
            fragmentList.add(new ClassFragment());
            fragmentList.add(new ShoppingcartFragment());
            fragmentList.add(new UserFragment());

        }

        @Override




        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }


        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}

