/*
 * Copyright (c) 2014.
 *
 * Restfullib is a library to facilitate the creation of an android app (client side) connected with server based on RESTful webservice.
 *
 * This library has been created by xaviml
 * https://github.com/xaviml
 */

package xml.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xml.ui.base.BaseFragment;

public class BaseFragmentAdapter extends FragmentPagerAdapter {

    private BaseFragment[] fragments;

    public BaseFragmentAdapter(FragmentManager fm, Class<BaseFragment>[] classes) {
        super(fm);
        fragments =  new BaseFragment[classes.length];
        for (int i = 0; i < getCount(); i++) {
            try {
                fragments[i] = classes[i].newInstance();
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {}
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position%getCount()];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position%getCount()].getName();
    }

    public void updatePage(int currentItem, IBase.ProgressType progressType) {
        fragments[currentItem].updateData(IBase.BASE_UPDATE, progressType);
    }
}
