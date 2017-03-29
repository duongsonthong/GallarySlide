package com.sip.galleryslide;

import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.sip.gallaryslide.IndicatorViewPager;
import com.sip.gallaryslide.MainSliderAdapter;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.drawable.loading)
                .showImageOnFail(R.drawable.loading)
                .imageScaleType(ImageScaleType.NONE)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileCount(100)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(options) // default
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);

        setContentView(R.layout.activity_main);
        String[] links = new String[12];
        links[0] = "http://img.f10.giaitri.vnecdn.net/2017/03/28/Kevin-Costner-1490673411_660x0.jpg";
        links[1] = "http://img.f12.giaitri.vnecdn.net/2017/03/28/kevin-costner-2-1490673023_660x0.jpg";
        links[2] = "http://img.f11.giaitri.vnecdn.net/2017/03/28/kevin-costner-3-1490673024_660x0.jpg";
        links[3] = "http://img.f12.giaitri.vnecdn.net/2017/03/28/kevin-costner-4-1490673024_660x0.jpg";
        links[4] = "http://img.f11.giaitri.vnecdn.net/2017/03/28/kevin-costner-5-1490673024_660x0.jpg";
        links[5] = "http://img.f33.dulich.vnecdn.net/2017/03/21/1-3541-1490084648.jpg";
        links[6] = "http://img.f33.dulich.vnecdn.net/2017/03/21/guoliang-village-the-stony-bea-8279-3067-1490090215.jpg";
        links[7] = "http://img.f33.dulich.vnecdn.net/2017/03/21/2-1361-1490084648.jpg";
        links[8] = "http://img.f33.dulich.vnecdn.net/2017/03/21/3-7376-1490084648.jpg";
        links[9] = "http://img.f33.dulich.vnecdn.net/2017/03/21/guoliang-village-the-stony-bea-5317-1221-1490090215.jpg";
        links[10] = "http://img.f33.dulich.vnecdn.net/2017/03/21/4-9257-1490084648.jpg";
        links[11] = "http://img.f33.dulich.vnecdn.net/2017/03/21/6-2396-1490084648.jpg";
        ViewPager mainSlider = (ViewPager)findViewById(R.id.main_slide);
        MainSliderAdapter mainSliderAdapter = new MainSliderAdapter(links);
        mainSlider.setAdapter(mainSliderAdapter);
        IndicatorViewPager indicatorViewPager = (IndicatorViewPager)findViewById(R.id.indicator);
        indicatorViewPager.setImageVisible(4).setMainSlide(mainSlider);
    }
}
