package wuqing.theone.fragment;

import android.media.Image;
import android.media.tv.TvContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import wuqing.theone.R;
import wuqing.theone.wuqing.theone.exlist.Group;

/**
 * Created by 无情 on 2018/1/24.
 */

public class Fragment3 extends Fragment {

    //ViewPager组
    private ViewPager mViewPager;
    private PagerAdapter adapter;
    private List<View> viewPages = new ArrayList<>();
    //下方的小圆点
    private View mView;
    private ImageView mImageView;
    private ViewGroup mGroup;
    private ImageView[] imageViews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment3, container, false);
        }

        initView();
        initPageAdapter();
        initPointer();
        initEvent();


        return mView;
    }

    private void initEvent() {
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new GuidePageChangeListener());
    }

    private void initPointer() {
        imageViews = new ImageView[viewPages.size()];
        for (int i=0;i<imageViews.length;i++){
            mImageView = new ImageView(getActivity());
            mImageView.setLayoutParams(new ViewGroup.LayoutParams(25, 25));
            mImageView.setPadding(20,0,20,0);
            mImageView = imageViews[i];

            if(i == 0){
                mImageView.setBackgroundResource(R.mipmap.page_indicator_focused);

                /**
                 * 在java代码中动态生成ImageView的时候
                 * 要设置其BackgroundResource属性才有效
                 * 设置ImageResource属性无效
                 */
            } else{
                imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
            }
            mGroup.addView(imageViews[i]);
        }


    }

    /**
     * PageAdater 适配器加载每个图片
     */


    private void initPageAdapter() {
        /**
         * 对于这几个想要动态载入的page页面，使用LayoutInflater.inflate()来找到其布局文件，并实例化为View对象
         */
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View page1 = inflater.inflate(R.layout.page1, null);
        View page2 = inflater.inflate(R.layout.page2, null);
        View page3 = inflater.inflate(R.layout.page3, null);
        View page4 = inflater.inflate(R.layout.page4, null);
        View page5 = inflater.inflate(R.layout.page5, null);


        //添加到集合中
        mViewPager.addView(page1);
        mViewPager.addView(page2);
        mViewPager.addView(page3);
        mViewPager.addView(page4);
        mViewPager.addView(page5);

        adapter = new PagerAdapter() {

            //获取当前界面个数
            @Override
            public int getCount() {
                return viewPages.size();
            }

            //判断是否由对象生成界面
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewPages.get(position));
            }

            //返回一个对象，这个对象表明了PagerAdapter适配器选择哪个对象放在当前的ViewPager中
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = viewPages.get(position);
                container.addView(view);
                return view;
            }
        };


    }


    /**
     * 控件实例化
     */
    private void initView() {
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager);
        mGroup = (ViewGroup) mView.findViewById(R.id.viewGroup);
    }

    /**
     * ViewPager的onPageChangeListener监听事件，当ViewPager的page页发生变化的时候调用
     */
    private class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //页面滑动完成后执行
        @Override
        public void onPageSelected(int position) {
            //判断当前是在那个page，就把对应下标的ImageView原点设置为选中状态的图片
            for (int i=0; i<imageViews.length;i++){
                imageViews[position].setBackgroundResource(R.mipmap.page_indicator_focused);
                if ( i!=position){
                    imageViews[i].setBackgroundResource(R.mipmap.page_indicator_unfocused);
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
