package com.qifan.shangjia.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by administartor on 2017/8/2.
 */

public class OrderFragmentAdapter extends FragmentStatePagerAdapter {
    String[]title={"全部","待付款","待收货","已完成","已取消"};
//      String[]title={"全部\n(-)","待付款\n(-)","待发货\n(-)","待收货\n(-)","待评价\n(-)"};
    List<Fragment> list;

    public void setList(List<Fragment> list) {
        this.list = list;
    }

    public OrderFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
//        return super.getPageTitle(position);
    }
}
