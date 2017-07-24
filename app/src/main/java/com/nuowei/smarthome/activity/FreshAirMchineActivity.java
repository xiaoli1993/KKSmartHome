package com.nuowei.smarthome.activity;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.nuowei.smarthome.R;
import com.nuowei.smarthome.util.Stringtotype;
import com.nuowei.smarthome.view.circleprogresslibrary.CircleProgress;
import com.nuowei.smarthome.view.textview.AvenirTextView;
import com.nuowei.smarthome.view.textview.DinProTextView;

import java.math.BigDecimal;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;

/**
 * Created by mac on 2017/7/24.
 */

public class FreshAirMchineActivity extends BaseActivity {


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
    @BindView(R.id.imageButton_remove)
    ImageButton imageButtonRemove;
    @BindView(R.id.myCircleProgress)
    CircleProgress myCircleProgress;
    @BindView(R.id.demo_tv)
    TextView demoTv;
    @BindView(R.id.imageButton_add)
    ImageButton imageButtonAdd;
    @BindView(R.id.image_home)
    ImageButton imageHome;
    @BindView(R.id.tv_home)
    DinProTextView tvHome;
    @BindView(R.id.btn_home)
    LinearLayout btnHome;
    @BindView(R.id.image_away)
    ImageButton imageAway;
    @BindView(R.id.tv_away)
    DinProTextView tvAway;
    @BindView(R.id.btn_away)
    LinearLayout btnAway;
    @BindView(R.id.image_disarm)
    ImageButton imageDisarm;
    @BindView(R.id.tv_disarm)
    DinProTextView tvDisarm;
    @BindView(R.id.btn_disarm)
    LinearLayout btnDisarm;
    @BindView(R.id.spinner_supply)
    MaterialSpinner spinnerSupply;
    @BindView(R.id.spinner_return)
    MaterialSpinner spinnerReturn;
    @BindView(R.id.image_runner)
    ImageButton imageRunner;
    @BindView(R.id.tv_runner)
    DinProTextView tvRunner;
    @BindView(R.id.tv_runner_onoff)
    DinProTextView tvRunnerOnoff;
    @BindView(R.id.btn_runner)
    LinearLayout btnRunner;
    @BindView(R.id.image_heating)
    ImageButton imageHeating;
    @BindView(R.id.tv_heating)
    DinProTextView tvHeating;
    @BindView(R.id.tv_heating_onoff)
    DinProTextView tvHeatingOnoff;
    @BindView(R.id.btn_heating)
    LinearLayout btnHeating;
    @BindView(R.id.image_filter)
    ImageButton imageFilter;
    @BindView(R.id.tv_filter)
    DinProTextView tvFilter;
    @BindView(R.id.tv_filter_ss)
    DinProTextView tvFilterSs;
    @BindView(R.id.btn_filter)
    LinearLayout btnFilter;
    @BindView(R.id.mRecyclerView)
    FamiliarRecyclerView mRecyclerView;
    @BindView(R.id.ll_power)
    LinearLayout btnPower;
    @BindView(R.id.scrollview)
    ScrollView scrollview;


    private Thread miusThread;
    private boolean isOnLongClick;
    private boolean miusEnable = true;
    private boolean plusEnable = true;
    private Thread plusThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresh_air_mchine);

        initViews();
        initEvens();
    }

    private void initEvens() {
        imageButtonAdd.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                // 用户当前为按下
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    plusThread = new PlusThread();
                    isOnLongClick = true;
                    plusThread.start();
                    imageButtonAdd.setImageResource(R.drawable.client_all_column_add_pass);
                }
                // 用户当前为抬起
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (plusThread != null) {
                        isOnLongClick = false;
                    }
                    imageButtonAdd.setImageResource(R.drawable.client_all_column_add);
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (plusThread != null) {
                        isOnLongClick = true;
                    }
                }
                return false;
            }
        });
        imageButtonRemove.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                // 用户当前为按下
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    miusThread = new MiusThread();
                    isOnLongClick = true;
                    miusThread.start();
                    imageButtonRemove.setImageResource(R.drawable.client_all_column_remove_pass);
                }
                // 用户当前为抬起
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (miusThread != null) {
                        isOnLongClick = false;
                    }
                    imageButtonRemove.setImageResource(R.drawable.client_all_column_remove);
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (miusThread != null) {
                        isOnLongClick = true;
                    }
                }
                return false;
            }
        });
    }

    private void initViews() {
        tbToolbar.setBackgroundResource(R.color.transparent);
        myCircleProgress.setProgress(new Random().nextInt(100));
    }

    //减操作
    class MiusThread extends Thread {
        @Override
        public void run() {
            while (isOnLongClick) {
                try {
                    Thread.sleep(200);
                    myHandler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }
    }

    //加操作
    class PlusThread extends Thread {
        @Override
        public void run() {
            while (isOnLongClick) {
                try {
                    Thread.sleep(200);
                    myHandler.sendEmptyMessage(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }
    }

    //更新文本框的值
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            setBtnEnable();
            switch (msg.what) {
                case 1:
                    if (miusEnable) {

                        demoTv.setText((new BigDecimal(
                                demoTv.getText().toString())
                                .subtract(new BigDecimal("1")))
                                + "");
                        int vlue = Stringtotype.stringToInt(demoTv.getText().toString());
                        if (vlue < 15) {
                            vlue = 15;
                        } else if (vlue > 25) {
                            vlue = 25;
                        } else {
                            vlue = 10 * (vlue - 15);
                        }
                        myCircleProgress.setProgress(vlue);
                        String sendData = String.format("%02d", vlue);
                    }
                    break;
                case 2:
                    if (plusEnable) {
                        demoTv.setText((new BigDecimal(
                                demoTv.getText().toString())
                                .add(new BigDecimal("1")))
                                + "");
                        int vlue = Stringtotype.stringToInt(demoTv.getText().toString());
                        if (vlue < 15) {
                            vlue = 15;
                        } else if (vlue > 25) {
                            vlue = 25;
                        } else {
                            vlue = 10 * (vlue - 15);
                        }
                        myCircleProgress.setProgress(vlue);
                        String sendData = String.format("%02d", vlue);
                    }
                    break;
            }

        }

    };

    //超出最大最小值范围按钮的可能与不可用
    private void setBtnEnable() {
        if (new BigDecimal(demoTv.getText().toString()).compareTo(new BigDecimal(15 + "")) > 0) {
            miusEnable = true;
        } else {
            miusEnable = false;
        }
        if (new BigDecimal(demoTv.getText().toString()).compareTo(new BigDecimal(25 + "")) < 0) {
            plusEnable = true;
        } else {
            plusEnable = false;
        }
    }

    @OnClick({R.id.image_btn_backs, R.id.btn_right, R.id.btn_home, R.id.btn_away, R.id.btn_disarm, R.id.btn_runner, R.id.btn_heating, R.id.btn_filter, R.id.ll_power})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_btn_backs:
                break;
            case R.id.btn_right:
                break;
            case R.id.btn_home:
                break;
            case R.id.btn_away:
                break;
            case R.id.btn_disarm:
                break;
            case R.id.btn_runner:
                break;
            case R.id.btn_heating:
                break;
            case R.id.btn_filter:
                break;
            case R.id.ll_power:
                break;
        }
    }
}
