package com.dengpan.pan.common.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.dengpan.pan.common.widget.PlaceHolderView;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    protected PlaceHolderView mPlaceHolderView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindows();

        if(initArgs(getIntent().getExtras())){
            //得到界面Id 并设置到Activity中
            int layId = getContentLayoutId();
            setContentView(layId);
            ButterKnife.bind(this);
            initBefor();
            initWidget();
            initData();
        }else{
            finish();
        }
    }


    protected void initBefor() {
    }

    protected void initWidget() {
//        ButterKnife.bind(this);


    }

    protected void initData() {


    }

    /**
     * 初始化相关参数，错误返回false
     * @param extras
     * @return
     */
    protected boolean initArgs(Bundle extras) {
        return true;
    }

    protected abstract int getContentLayoutId();

    //初始化之前调用
    protected void initWindows() {
    }

    @Override
    public boolean onSupportNavigateUp() {
        // 点击返回时
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments =getSupportFragmentManager().getFragments();
        if(fragments != null && fragments.size()>0){
            for(Fragment fragment:fragments){
                if(fragment instanceof com.dengpan.pan.common.app.BaseFragment){
                    if(((com.dengpan.pan.common.app.BaseFragment)fragment).onBackPressed()){
                        //判断当前fragment 是否做过返回处理 有的话，直接返回
                        return;
                    }
                }
            }
        }
        finish();
    }

    /**
     * 设置占位布局
     * @param mPlaceHolderView
     */
    public void setmPlaceHolderView(PlaceHolderView mPlaceHolderView) {
        this.mPlaceHolderView = mPlaceHolderView;
    }
}
