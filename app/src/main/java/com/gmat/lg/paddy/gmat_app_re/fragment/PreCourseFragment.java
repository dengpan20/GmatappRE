package com.gmat.lg.paddy.gmat_app_re.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dengpan.pan.common.app.BaseFragment;
import com.gmat.lg.paddy.gmat_app_re.R;

/**
 * 抢先课程
 */
public class PreCourseFragment extends BaseFragment {


    public PreCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pre_course, container, false);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_pre_course;
    }

}
