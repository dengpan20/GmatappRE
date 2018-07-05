package com.dengpan.pan.common.widget;

import android.support.annotation.StringRes;

/**
 * 占位布局的接口定义
 */
public interface PlaceHolderView {
    //没有数据，显示恐怖剧，隐藏当前数据布局
    void triggerEmpty();
    //网络错误
    void triggerNeterrot();
    //加载错误，显示错误信息
    void triggerError(@StringRes int strRes);
    //loading
    void triggerLoading();
    void triggerOk();
    //isOK == true 加载成功 反之则 triggerEmpty
    void triggerOkOrEmpty(boolean isOk);


}
