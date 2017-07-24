package com.nuowei.smarthome.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nuowei.smarthome.R;
import com.nuowei.smarthome.adapter.MainListAdapter;
import com.nuowei.smarthome.modle.ListMain;
import com.nuowei.smarthome.view.textview.AvenirTextView;
import com.nuowei.smarthome.view.textview.DinProTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.iwgang.familiarrecyclerview.FamiliarRecyclerView;
import cn.iwgang.familiarrecyclerview.FamiliarRefreshRecyclerView;
import cn.iwgang.familiarrecyclerview.baservadapter.FamiliarEasyAdapter;

/**
 * Created by mac on 2017/7/23.
 */

public class HistoricalDataActivity extends BaseActivity {
    @BindView(R.id.tb_toolbar)
    RelativeLayout tbToolbar;
    @BindView(R.id.tv_title)
    AvenirTextView tvTitle;
    @BindView(R.id.tv_right)
    AvenirTextView tvRight;
    @BindView(R.id.btn_right)
    ImageButton btn_right;
    @BindView(R.id.mListRecyclerView)
    FamiliarRefreshRecyclerView mListRecyclerView;


    private FamiliarRecyclerView mFamiliarRecyclerView;
    private FamiliarEasyAdapter<String> mAdapter;

    private int[] mColors = new int[]{
            ColorTemplate.VORDIPLOM_COLORS[0],
            ColorTemplate.VORDIPLOM_COLORS[1],
            ColorTemplate.VORDIPLOM_COLORS[2]
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
//        initData();
        initEven();

    }

    @OnClick(R.id.image_btn_backs)
    public void onViewClicked() {
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
        this.finish();
    }

    private void initEven() {
        tvTitle.setText(getResources().getString(R.string.Device));
        tvRight.setVisibility(View.GONE);
        btn_right.setVisibility(View.GONE);

        mListRecyclerView.setLoadMoreEnabled(false);
        final List<String> stringList = new ArrayList<>();
        stringList.add("Temperature");
        stringList.add("Humidity");
        mAdapter = new FamiliarEasyAdapter<String>(this, R.layout.item_historical_data, stringList) {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                ImageView imageIcon = (ImageView) holder.findView(R.id.image_icon);
                DinProTextView tvTxt = (DinProTextView) holder.findView(R.id.tv_txt);
                ImageView imageMore = (ImageView) holder.findView(R.id.image_more);
                LineChart mChart = (LineChart) holder.findView(R.id.chart);

                tvTxt.setText(stringList.get(position));
                mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        Log.i("VAL SELECTED",
                                "Value: " + e.getY() + ", xIndex: " + e.getX()
                                        + ", DataSet index: " + h.getDataSetIndex());
                    }

                    @Override
                    public void onNothingSelected() {

                    }
                });
                ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

                for (int z = 0; z < 3; z++) {

                    ArrayList<Entry> values = new ArrayList<Entry>();

                    for (int i = 0; i < 3; i++) {
                        double val = (Math.random() * 32) + 3;
                        values.add(new Entry(i, (float) val));
                    }

                    LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));
                    d.setLineWidth(2.5f);
                    d.setCircleRadius(4f);

                    int color = mColors[z % mColors.length];
                    d.setColor(color);
                    d.setCircleColor(color);
                    dataSets.add(d);
                }
                ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
                ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
                ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

                LineData data = new LineData(dataSets);
                mChart.setData(data);
                mChart.invalidate();


                mChart.setDrawGridBackground(false);
                mChart.getDescription().setEnabled(false);
                mChart.setDrawBorders(false);

                mChart.getAxisLeft().setEnabled(false);
                mChart.getAxisRight().setDrawAxisLine(false);
                mChart.getAxisRight().setDrawGridLines(false);
                mChart.getXAxis().setDrawAxisLine(false);
                mChart.getXAxis().setDrawGridLines(false);

                // enable touch gestures
                mChart.setTouchEnabled(true);

                // enable scaling and dragging
                mChart.setDragEnabled(true);
                mChart.setScaleEnabled(true);

                // if disabled, scaling can be done on x- and y-axis separately
                mChart.setPinchZoom(false);

                Legend l = mChart.getLegend();
                l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
                l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
                l.setOrientation(Legend.LegendOrientation.VERTICAL);
                l.setDrawInside(false);
            }
        };

        mFamiliarRecyclerView = mListRecyclerView.getFamiliarRecyclerView();
        // ItemAnimator
        mFamiliarRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // Item Click and Item Long Click
        mListRecyclerView.setOnItemClickListener(new FamiliarRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {


            }
        });

        mListRecyclerView.setOnItemLongClickListener(new FamiliarRecyclerView.OnItemLongClickListener() {


            @Override
            public boolean onItemLongClick(FamiliarRecyclerView familiarRecyclerView, View view, int position) {

                return true;
            }
        });
        mListRecyclerView.setOnPullRefreshListener(new FamiliarRefreshRecyclerView.OnPullRefreshListener() {
            @Override
            public void onPullRefresh() {
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListRecyclerView.pullRefreshComplete();
                    }
                }, 2000);
            }
        });


        mListRecyclerView.setAdapter(mAdapter);
    }


}
