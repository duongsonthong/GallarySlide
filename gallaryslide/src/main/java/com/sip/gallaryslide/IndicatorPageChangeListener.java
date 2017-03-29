package com.sip.gallaryslide;

import android.support.v4.view.ViewPager;

/**
 * Created by duongsonthong on 9/1/16.
 */
public abstract class IndicatorPageChangeListener implements ViewPager.OnPageChangeListener {

    public boolean isShouldMoveIndicate(int visibleWidth,int movePos,int indicatorOldPos){

        if(movePos >( indicatorOldPos +(visibleWidth-1) )){
            return true;
        }
        if(movePos <indicatorOldPos){
            return true;
        }
        return false;
    }

}
