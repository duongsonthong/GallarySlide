# GallarySlide

![untitled](https://media.giphy.com/media/PL7fCBouXC4Mg/giphy.gif)
# Download

use Gradle:
```javascript
compile 'com.duongsonthong.gallarySlide:gallaryslide:1.0'
```
# How to use?

- first : create main slide 

```xml
<android.support.v4.view.ViewPager
     android:id="@+id/main_slide"
     android:layout_width="match_parent"
     android:layout_height="300dp"/>
```
- second : create indicator slide under main slide

```xml
<com.sip.gallaryslide.IndicatorViewPager
     android:id="@+id/indicator"
     android:paddingLeft="@dimen/card_padding"
     android:paddingRight="@dimen/card_padding"
     android:paddingStart="@dimen/card_padding"
     android:layout_below="@id/main_slide"
     android:layout_width="wrap_content"
     android:layout_height="60dp"/>
```    
- Configurate slideshow on MainActivity
  
  Create a String array which hold links image, for example
  ```java
  String[] links = new String[5];
   links[0] = "http://img.f10.giaitri.vnecdn.net/2017/03/28/Kevin-Costner-1490673411_660x0.jpg";
   links[1] = "http://img.f12.giaitri.vnecdn.net/2017/03/28/kevin-costner-2-1490673023_660x0.jpg";
   links[2] = "http://img.f11.giaitri.vnecdn.net/2017/03/28/kevin-costner-3-1490673024_660x0.jpg";
   links[3] = "http://img.f12.giaitri.vnecdn.net/2017/03/28/kevin-costner-4-1490673024_660x0.jpg";
   links[4] = "http://img.f11.giaitri.vnecdn.net/2017/03/28/kevin-costner-5-1490673024_660x0.jpg";
  ```
  Create MainSliderAdapter with links array above
  ```java
  MainSliderAdapter mainSliderAdapter = new MainSliderAdapter(links);
  ```
  set adapter for main slide
  ```java
   ViewPager mainSlider = (ViewPager)findViewById(R.id.main_slide);
  mainSlider.setAdapter(mainSliderAdapter);
  ```
  set the number of images is displayed on Indicator and then set main slide for indicator
  
  ```java
  IndicatorViewPager indicatorViewPager = (IndicatorViewPager)findViewById(R.id.indicator);
  indicatorViewPager.setImageVisible(3).setMainSlide(mainSlider);
  ```
  
# Demo 
  You can download my project example above
  
  

  
  
  
  
  
  
  
  

