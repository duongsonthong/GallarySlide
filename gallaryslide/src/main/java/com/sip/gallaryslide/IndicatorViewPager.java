package com.sip.gallaryslide;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by duongsonthong on 8/30/16.
 */
public class IndicatorViewPager extends ViewPager implements IndicatorAdapter.IFitemIndicatorClick {
    private String TAG = this.getClass().getSimpleName();
    private ViewPager mMainSlide;
    public ViewPager mSubSilde;
    private int mNumberImageVisible;
    private int mMainOldPos = 0;
    private int mIndicatorOldPos = 0;
    private  IndicatorAdapter indicatorAdapter;
    private boolean mScrollable = true;
    public IndicatorViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setMainSlide(ViewPager viewPager){
        mMainSlide = viewPager;
        mSubSilde = this;
        MainSliderAdapter mainSliderAdapter =(MainSliderAdapter)mMainSlide.getAdapter();
        indicatorAdapter = new IndicatorAdapter(mainSliderAdapter.getUrl(),mNumberImageVisible,this);
        this.setAdapter(indicatorAdapter);
        this.setClipToPadding(false);
        mMainSlide.addOnPageChangeListener(mainPageChangeListener);
        mSubSilde.addOnPageChangeListener(indicatorPageChangeListener);
        mSubSilde.setOffscreenPageLimit(mainSliderAdapter.getUrl().length);

    }

    @Override
    public int getPageMargin() {
        return super.getPageMargin();
    }

    /**
    * Set indicator height and set number of image visible on screen
     * @param numberImageVisible number of image visible on screen
    * */

    public  IndicatorViewPager setImageVisible(int numberImageVisible){
        mNumberImageVisible = numberImageVisible;
        return this;
    }
    IndicatorPageChangeListener indicatorPageChangeListener = new IndicatorPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int maskIndicatorOldPos = mMainOldPos;
            if(mIndicatorOldPos < position){
                //move right
                mMainOldPos+=Math.abs(position-mIndicatorOldPos);
            }else{
                //move left
                mMainOldPos-=Math.abs(position-mIndicatorOldPos);
            }
            moveMainStatic(mMainOldPos);
            indicatorAdapter.updatePos(mSubSilde,mMainOldPos,maskIndicatorOldPos);
            mIndicatorOldPos = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    public final void moveIndicatorStatic(final int moveTo){
        mSubSilde.removeOnPageChangeListener(indicatorPageChangeListener);
        mSubSilde.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE){
                    mSubSilde.addOnPageChangeListener(indicatorPageChangeListener);
                    mSubSilde.removeOnPageChangeListener(this);
                }
            }
        });
        mSubSilde.setCurrentItem(moveTo);
    }
    public final void moveMainStatic(final int moveTo){
        mScrollable = false;
        mMainSlide.clearOnPageChangeListeners();
        mMainSlide.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == SCROLL_STATE_IDLE){
                    mMainSlide.clearOnPageChangeListeners();
                    mMainSlide.addOnPageChangeListener(mainPageChangeListener);
                    mScrollable = true;
                }
            }
        });
        mMainSlide.setCurrentItem(moveTo);
    }
    IndicatorPageChangeListener mainPageChangeListener = new IndicatorPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if( position == 0 && positionOffset == 0.0 && positionOffsetPixels == 0) return;
            if(position == mMainOldPos&&positionOffsetPixels>=600) {
                indicatorAdapter.updatePos(mSubSilde,mMainOldPos+1,mMainOldPos);
            }
            if(position == mMainOldPos && positionOffsetPixels<600){
                indicatorAdapter.updatePos(mSubSilde,mMainOldPos,mMainOldPos+1);
            }
            if(position < mMainOldPos&&positionOffsetPixels<600) {
                indicatorAdapter.updatePos(mSubSilde,mMainOldPos-1,mMainOldPos);
            }
            if(position < mMainOldPos && positionOffsetPixels>=600){
                indicatorAdapter.updatePos(mSubSilde,mMainOldPos,mMainOldPos-1);
            }
        }

        @Override
        public void onPageSelected(int position) {
            Log.d(TAG,"onPageSelected");
            if(mMainOldPos< position){
                if(isShouldMoveIndicate(mNumberImageVisible,position,mIndicatorOldPos))
                    moveIndicatorStatic(++mIndicatorOldPos);
            }else{
                if(isShouldMoveIndicate(mNumberImageVisible,position,mIndicatorOldPos))
                    moveIndicatorStatic(--mIndicatorOldPos);
            }
            mMainOldPos = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public void itemClick(int position) {
        if(position != mMainOldPos){
            moveMainStatic(position);
            indicatorAdapter.updatePos(mSubSilde,position,mMainOldPos);
            mMainOldPos = position;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.mScrollable&&super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mScrollable && super.onInterceptTouchEvent(ev);
    }
}
