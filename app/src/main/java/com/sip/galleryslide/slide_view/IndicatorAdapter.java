package com.sip.galleryslide.slide_view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sip.galleryslide.R;

/**
 * Created by duongsonthong on 8/30/16.
 */
public class IndicatorAdapter extends PagerAdapter {
    String TAG = this.getClass().getSimpleName();
    private int mNumberPageVisisble;
    private String[] mUrlArray;
    private boolean mIsInited = false;
    public int getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }


    private int newPosition;
    private IFitemIndicatorClick mIFitemIndicatorClick;
    public  IndicatorAdapter (String[] urlArray,int numberPageVisible,IFitemIndicatorClick iFitemIndicatorClick){
        mUrlArray = urlArray;
        mNumberPageVisisble = numberPageVisible;
        mIFitemIndicatorClick = iFitemIndicatorClick;
    }

    @Override
    public int getCount() {
        return mUrlArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Log.d("--record--","notifica");
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.indicator_card,container,false);
        CardView card = (CardView)v.findViewById(R.id.cardView);
//        card.setMaxCardElevation(3.0f);
        ImageView imageView = (ImageView)v.findViewById(R.id.image);
        LinearLayout mask = (LinearLayout) v.findViewById(R.id.mask);
        ImageLoader.getInstance().displayImage(mUrlArray[position],imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIFitemIndicatorClick.itemClick(position);
               //
            }
        });
        container.addView(card,position);
        if(!mIsInited && position == 0){
            updatePos(container,0,-1);
            mIsInited = true;
        }
        return v;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);

    }

    public void updatePos(ViewGroup viewGroup, int newPosition,int oldPos){

        CardView cardViewLight = (CardView) viewGroup.getChildAt(oldPos);
        if(cardViewLight!=null) {
            final LinearLayout mask1 = (LinearLayout) cardViewLight.getChildAt(1);

            ObjectAnimator objectanimator1 =  ObjectAnimator.ofFloat(cardViewLight,"scaleX",1f).ofFloat(cardViewLight,"scaleY",1f);
            objectanimator1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (mask1 != null)
                        mask1.setBackgroundColor(Color.parseColor("#80F5F9FA"));
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            objectanimator1.start();
        }
        CardView cardViewMask = (CardView) viewGroup.getChildAt(newPosition);
        if(cardViewMask!=null) {
            final  LinearLayout mask = (LinearLayout) cardViewMask.getChildAt(1);
            ObjectAnimator objectanimator =  ObjectAnimator.ofFloat(cardViewMask,"scaleX",1.6f).ofFloat(cardViewMask,"scaleY",1.6f);
            objectanimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    if (mask != null)
                        mask.setBackgroundColor(Color.TRANSPARENT);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            objectanimator.start();
        }
        super.startUpdate(viewGroup);
    }
    @Override
    public float getPageWidth(int position) {
        return (float)1/mNumberPageVisisble;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public interface IFitemIndicatorClick{
        void itemClick(int position);
    }


}
