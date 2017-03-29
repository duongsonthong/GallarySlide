package com.sip.gallaryslide;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by duongsonthong on 8/31/16.
 */
public class MainSliderAdapter extends PagerAdapter {
    String[] mUrl;
    public MainSliderAdapter(String[] url){
        mUrl = url;
    }
    public String[] getUrl(){
        return  mUrl;
    }
    @Override
    public int getCount() {
        return mUrl.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.main_card_view,container,false);
        CardView cardView = (CardView)v.findViewById(R.id.slider_main_cardview);
        ImageView imageView = (ImageView)v.findViewById(R.id.image_slide);
        ImageLoader.getInstance().displayImage(mUrl[position],imageView);
        container.addView(cardView);
        return v;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }
}
