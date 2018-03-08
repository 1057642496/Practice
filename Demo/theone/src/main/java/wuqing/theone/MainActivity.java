package wuqing.theone;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import wuqing.theone.fragment.Fragment1;
import wuqing.theone.fragment.Fragment2;
import wuqing.theone.fragment.Fragment3;
import wuqing.theone.fragment.Fragment4;
import wuqing.theone.wuqing.theone.exlist.ExpandableListViewAdapter;
import wuqing.theone.wuqing.theone.exlist.Group;
import wuqing.theone.wuqing.theone.exlist.Item;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isExit = false;//点击退出时给定的一个布尔值
    private Toolbar toolbar;//标题栏
    private DrawerLayout mDrawerLayout;//抽屉布局
    private NavigationView navView;//导航视图

    private RadioGroup radioGroup;//底部导航栏的选项组
    private RadioButton button_1;
    private RadioButton button_2;
    private RadioButton button_3;
    private RadioButton button_4;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private Fragment4 fragment4;


    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    List<Fragment> list;
    private FrameLayout frameLayout; //屏幕中间的框架内容



    //这时负责双列表的
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<Item>> iData = null;
    private ArrayList<Item> lData = null;
    private ExpandableListView exlist_lol;
    private ExpandableListViewAdapter exlistAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //设置标题标题栏
        setSupportActionBar(toolbar);
        //设置显示第一个的碎片
        setDefaultFragment();
        //添加左面的侧滑的操作界面空间，使触屏外拉更好用
        setDrawerLeftEdgeSize(MainActivity.this, mDrawerLayout, 0.6f);

        //加载lol列表
        // drawer侧滑开关实现还有toobar
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerToggle.syncState();//初始化状态
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        //----------------------------------------碎片代码开始---------------------------------------------------------------->>


        //----------------------------------------碎片代码结束--------------------------------------------------------->>

    }

    /**
     * 设置列表
     */

    private void initList() {


    }


    /**
     * 将每个部件找到位置实例
     */

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);//下面三行是标题框和侧滑的实例
        navView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        rb_channel = (RadioButton) findViewById(R.id.rb_channel);

        radioGroup = (RadioGroup) findViewById(R.id.rg_tab_bar);
        button_1 = (RadioButton) findViewById(R.id.rb_channel);
        button_2 = (RadioButton) findViewById(R.id.rb_message);
        button_3 = (RadioButton) findViewById(R.id.rb_better);
        button_4 = (RadioButton) findViewById(R.id.rb_setting);
        frameLayout = (FrameLayout) findViewById(R.id.ly_content);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        list = new ArrayList<>();
        list.add(fragment1);
        list.add(fragment2);
        list.add(fragment3);
        list.add(fragment4);

        //设置RadioGroup开始时设置的按钮，设置第一个按钮为默认值
        radioGroup.check(R.id.rb_channel);


        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);


    }


    /**
     * 加载标题栏的控件
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar, menu);
        if (toolbar != null) {//mActionBarToolbar就是android.support.v7.widget.Toolbar
            toolbar.setTitle("提醒");//设置为空，可以自己定义一个居中的控件，当做标题控件使用
        }
        return true;
    }


    /**
     * 隐藏原始toolbar自带的标题，自定义标题内容
     *
     * @param savedInstanceState
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        //Toolbar 必须在onCreate()之后设置标题文本，否则默认标签将覆盖我们的设置

    }


    /**
     * 通过按钮选择下面的底部导航栏  加载碎片
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //我们根据参数的id区别不同按钮
        //不同按钮对应着不同的Fragment对象页面

        FragmentManager fragmentManager = getSupportFragmentManager();
        //使用管理器开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (v.getId()) {
            //获得Fragment管理器


            case R.id.rb_channel:
                if (fragment1 == null) {
                    fragment1 = new Fragment1();

                }
                //如果tab2不为空，把tab2隐藏就是、
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                if (fragment3 != null) {
                    transaction.hide(fragment3);
                }
                if (fragment4 != null) {
                    transaction.hide(fragment4);
                }
                if (!fragment1.isAdded()) {

                    transaction.add(R.id.ly_content, fragment1);
                } else {
                    transaction.show(fragment1);
                }
                if (toolbar != null) {
                    toolbar.setTitle("提醒");//设置标题为提醒

                }
                break;
            case R.id.rb_message:
                if (fragment2 == null) {
                    fragment2 = new Fragment2();

                }
                //如果tab2不为空，把tab2隐藏就是、
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                if (fragment3 != null) {
                    transaction.hide(fragment3);
                }
                if (fragment4 != null) {
                    transaction.hide(fragment4);
                }
                if (!fragment2.isAdded()) {

                    transaction.add(R.id.ly_content, fragment2);
                } else {
                    transaction.show(fragment2);
                }
                if (toolbar != null) {
                    toolbar.setTitle("信息");//设置标题为信息
                }

                break;
            case R.id.rb_better:
                if (fragment3 == null) {
                    fragment3 = new Fragment3();
                }
                //如果tab2不为空，把tab2隐藏就是、
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                if (fragment4 != null) {
                    transaction.hide(fragment4);
                }
                if (!fragment3.isAdded()) {

                    transaction.add(R.id.ly_content, fragment3);
                } else {
                    transaction.show(fragment3);
                }
                if (toolbar != null) {
                    toolbar.setTitle("我的");//设置标题为我的
                }
                break;
            case R.id.rb_setting:
                if (fragment4 == null) {
                    fragment4 = new Fragment4();
                }
                //如果tab2不为空，把tab2隐藏就是、
                if (fragment1 != null) {
                    transaction.hide(fragment1);
                }
                if (fragment2 != null) {
                    transaction.hide(fragment2);
                }
                if (fragment3 != null) {
                    transaction.hide(fragment3);
                }
                if (!fragment4.isAdded()) {

                    transaction.add(R.id.ly_content, fragment4);
                } else {
                    transaction.show(fragment4);
                }
                if (toolbar != null) {
                    toolbar.setTitle("设置");//设置标题为设置
                }
                break;
            default:
                break;
        }
        transaction.commit();

    }


    /**
     * 界面加载显示第一个碎片
     */
    private void setDefaultFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        //使用管理器开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (fragment1 == null) {
            fragment1 = new Fragment1();
        }
        //如果tab2不为空，把tab2隐藏就是、
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
        if (fragment4 != null) {
            transaction.hide(fragment4);
        }
        if (!fragment1.isAdded()) {

            transaction.add(R.id.ly_content, fragment1);
            transaction.add(R.id.ly_content, fragment2);
            transaction.add(R.id.ly_content, fragment3);
            transaction.add(R.id.ly_content, fragment4);

        } else {
            transaction.show(fragment1);
        }

        transaction.commit();
    }


    /**
     * 设置抽屉滑动的控制范围
     *
     * @param activity
     * @param drawerLayout
     * @param displayWidthPercentage 占全屏的份额为0~1
     */


    private void setDrawerLeftEdgeSize(Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField = drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);

            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }


    /**
     * @param keyCode
     * @param event   实现点击两下退出程序的作用
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                System.exit(0);
            }
            return false;

        }

        return onKeyDown(keyCode, event);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

}
