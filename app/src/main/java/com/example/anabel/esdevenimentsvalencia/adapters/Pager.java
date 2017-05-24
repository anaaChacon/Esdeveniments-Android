package com.example.anabel.esdevenimentsvalencia.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.anabel.esdevenimentsvalencia.activities.DetailActivity;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentArribar;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentInfo;
import com.example.anabel.esdevenimentsvalencia.fragments.FragmentPrincipal;

/**
 * Created by Anabel on 22/05/2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FragmentPrincipal tab1 = new FragmentPrincipal();

                return tab1.newInstance(DetailActivity.idCategoria, DetailActivity.idLugar);
            case 1:
                FragmentArribar tab2 = new FragmentArribar();
                return tab2.newInstance(DetailActivity.idEvento, DetailActivity.nombreLugar, DetailActivity.direccionLugar);
            case 2:
                FragmentInfo tab3 = new FragmentInfo();
                return tab3.newInstance(DetailActivity.idEvento, DetailActivity.nombreLugar, DetailActivity.direccionLugar, DetailActivity.informacion, DetailActivity.imagenLugar);
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
