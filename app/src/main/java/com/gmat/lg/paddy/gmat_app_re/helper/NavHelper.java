package com.gmat.lg.paddy.gmat_app_re.helper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;

/**
 * 主页fragment 帮助类，切换fragment
 */
public class NavHelper<T>{
    private final SparseArray<Tab<T>> tabs= new SparseArray<>();

    private final Context context;
    private final int cotainerId;
    private final FragmentManager fragmentManager;
    private final OnTabChangedLisener<T> listener;

    private Tab<T> currentTab;

    public NavHelper(Context context, int cotainerId, FragmentManager fragmentManager, OnTabChangedLisener<T> listener) {
        this.context = context;
        this.cotainerId = cotainerId;
        this.fragmentManager = fragmentManager;
        this.listener = listener;
    }
    //添加TAB
    public NavHelper<T> add(int menuId,Tab<T> tab){
        tabs.put(menuId,tab);
        return this;
    }
    public Tab<T> getCurrentTab(){
        return currentTab;
    }
    //点击菜单
    public boolean performClickMenu(int menuId){
        Tab<T> tab = tabs.get(menuId);
        if(tab != null){
            doSelect(tab);
            return true;
        }
        return false;
    }
    //处理切换操作
    private void doSelect(Tab<T> tab) {
        Tab<T> oldTab = null;
        if(currentTab != null){
            oldTab = currentTab;
            if(oldTab == tab){//点击地是当前地tab
                notifyTabReselect(tab);
                return;
            }
        }
        currentTab = tab;
        doTabChanged(currentTab,oldTab);
    }
    //切换tab

    private void doTabChanged(Tab<T> newTab, Tab<T> oldTab) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(oldTab !=null){//已经点击过tab 进行切换操作
            if(oldTab.fragment != null){
                ft.detach(oldTab.fragment);//从界面移除，存在缓存中
            }
        }
        if(newTab != null){
            if(newTab.fragment == null){
                Fragment fragment = Fragment.instantiate(context,newTab.cls.getName());
                newTab.fragment = fragment;
                ft.add(cotainerId,fragment,newTab.cls.getName());
            }else{
                ft.attach(newTab.fragment);
            }
        }
        ft.commit();
        notifyTabSelect(newTab,oldTab);
    }
    //切换完成后

    private void notifyTabSelect(Tab<T> newTab, Tab<T> oldTab) {

        if(listener != null){
            listener.onTabChanged(newTab,oldTab);
        }
    }

    private void notifyTabReselect(Tab<T> tab) {
        //TODO tab 二次点击
    }


    //时间完成后地接口
    public interface OnTabChangedLisener<T>{
        void onTabChanged(Tab<T> newTab, Tab<T> oldTab);
    }

    public static class Tab<T>{
        public Tab(Class<?> cls, T extra) {
            this.cls = cls;
            this.extra = extra;
        }

        //fragment class
        public Class<?> cls;
        //额外字段
        public T extra;
        // 内部缓存的对应的Fragment，
        // Package权限，外部无法使用
        Fragment fragment;
    }

}
