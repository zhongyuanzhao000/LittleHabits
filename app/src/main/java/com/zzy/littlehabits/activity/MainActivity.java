package com.zzy.littlehabits.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.zzy.littlehabits.R;
import com.zzy.littlehabits.fragment.AllFragment;
import com.zzy.littlehabits.fragment.MineFragment;
import com.zzy.littlehabits.fragment.TimerFragment;
import com.zzy.littlehabits.fragment.TodayFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;
    private TodayFragment todayFragment;
    private AllFragment allFragment;
    private TimerFragment timerFragment;
    private MineFragment mineFragment;
    private Fragment[] fragments;
    private int lastFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化底部导航栏和fragment切换
        initBottomNavigation();

    }

    /**
     * 初始化底部导航栏和fragment切换
     */
    private void initBottomNavigation() {
        todayFragment = new TodayFragment();
        allFragment = new AllFragment();
        timerFragment = new TimerFragment();
        mineFragment = new MineFragment();
        //初始化不同的Fragment实例，并组装成数组，便于切换Fragment
        fragments = new Fragment[]{todayFragment, allFragment, timerFragment, mineFragment};

        //设置刚进入app的主界面时，首先显示TodayFragment碎片界面
        //调用getSupportFragmentManager().beginTransaction()开启Fragment处理的事务，以便于对Fragment进行切换、隐藏、展示等操作
        //事务通过replace()方法来切换不同的Fragment界面，第一个参数是Fragment所要替换区域的id(在activity_main.xml定义的),第二个参数表示即将切换到的Fragment实例
        //show()用于展示切换到的Fragment实例所对应的界面
        //最后调用commit()将前面的操作进行提交，即可完成Fragment的切换
        getSupportFragmentManager().beginTransaction().replace(R.id.host_fragment, todayFragment).show(todayFragment).commit();

        bottomNavigation = (BottomNavigationView)findViewById(R.id.nav_view);
        bottomNavigation.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED); //使所有bottom navigation items都显示出来
        //对底部导航栏设置监听器，每当导航栏的item被选中时，则跳转到对应的Fragment碎片
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.today:
                        if (lastFragment != 0) { //若上一个Fragment界面就是要切换到的界面，则不用切换
                            switchFragment(lastFragment, 0);
                            lastFragment = 0;
                        }
                        setTitle("今日打卡");
                        return true;
                    case R.id.all:
                        if (lastFragment != 1) {
                            switchFragment(lastFragment, 1);
                            lastFragment = 1;
                        }
                        setTitle("全部习惯");
                        return true;
                    case R.id.timer:
                        if (lastFragment != 2) {
                            switchFragment(lastFragment, 2);
                            lastFragment = 2;
                        }
                        setTitle("倒计时");
                        return true;
                    case R.id.mine:
                        if (lastFragment != 3) {
                            switchFragment(lastFragment, 3);
                            lastFragment = 3;
                        }
                        setTitle("我的账户");
                        return true;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     *切换fragment
     */
    private void switchFragment(int mLastFragment, int index) {
        //开启Fragment事务，以便于对Fragment进行切换、隐藏、展示等操作
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //隐藏上个Fragment
        transaction.hide(fragments[mLastFragment]);

        transaction.replace(R.id.host_fragment, fragments[index]).show(fragments[index]).commit();
    }

}
