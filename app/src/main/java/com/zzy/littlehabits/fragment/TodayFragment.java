package com.zzy.littlehabits.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zzy.littlehabits.R;

public class TodayFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //inflater.inflate() 用于将对应的fragment布局文件填充进当前的TodayFragment类中
        //第一个参数表示 layout目录中对应的fragment布局文件的id（与文件名一致）,后两个参数很少用到
        view = inflater.inflate(R.layout.fragment_today, container, false);


        //此处可以添加TodayFragment创建时，需要执行的一些逻辑代码


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //此处可以添加TodayFragment恢复可见时(即从其他Activity或Fragment返回时)，需要执行的一些逻辑代码
    }

}
