package com.dengpan.pan.common.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dengpan.pan.common.widget.PlaceHolderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    protected View mRoot;
    protected Unbinder mRootUnbinder;
    private boolean mIsFirstInitData = true;//是否是第一次初始化数据

    protected PlaceHolderView mPlaceHolderView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // init the args
        initArgs(getArguments());
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected void initArgs(Bundle arguments) {
    }

    @Nullable
    @Override//此方法先被调用
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mRoot == null){
            int layId = getContentLayoutId();
            // 初始化当前的跟布局，但是不在创建时就添加到container里边
            View root = inflater.inflate(layId,container,false);
            initWidget(root);
            mRoot = root;
        }
        return mRoot;
    }

    protected  void initWidget(View root){
        mRootUnbinder = ButterKnife.bind(this,root);
    }

    protected abstract int getContentLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //触发第一次
        if(mIsFirstInitData){
            mIsFirstInitData = false;
            onFirstInit();
        }
        //当view 创建后  初始化数据
        initData();
    }

    protected void initData() {
    }

    protected void onFirstInit() {
    }


    public void setPlaceHolderView(PlaceHolderView mPlaceHolderView) {
        this.mPlaceHolderView = mPlaceHolderView;
    }
    public void showInfo(String info){
        Toast.makeText(getContext(),info,Toast.LENGTH_SHORT).show();
    }
    public void showLogI(String tag,String info){
        Log.i(tag,info);
    }
    public boolean onBackPressed() {
        return false;
    }
}
