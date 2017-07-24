package com.nuowei.smarthome.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.nuowei.smarthome.R;
import com.nuowei.smarthome.view.textview.AvenirTextView;



import butterknife.BindView;
import butterknife.OnClick;
import cn.iwgang.familiarrecyclerview.FamiliarRefreshRecyclerView;

public class ActivityLimitSettingActivity extends BaseActivity {

    @BindView(R.id.mListRecyclerView)
    FamiliarRefreshRecyclerView mListRecyclerView;
    @BindView(R.id.image_btn_backs)
    ImageButton imageBtnBacks;
    @BindView(R.id.tv_title)
    AvenirTextView tvTitle;
    @BindView(R.id.btn_right)
    ImageButton btnRight;
    @BindView(R.id.tv_right)
    AvenirTextView tvRight;
    @BindView(R.id.tb_toolbar)
    RelativeLayout tbToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_setting);


    }

    @OnClick({R.id.image_btn_backs, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_btn_backs:
                break;
            case R.id.btn_right:
                break;
        }
    }
}
