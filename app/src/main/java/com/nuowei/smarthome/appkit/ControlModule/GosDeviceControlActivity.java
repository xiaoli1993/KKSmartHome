package com.nuowei.smarthome.appkit.ControlModule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.util.concurrent.ConcurrentHashMap;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiDeviceNetStatus;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.nuowei.smarthome.R;
import com.nuowei.smarthome.appkit.utils.HexStrUtils;
import com.nuowei.smarthome.appkit.view.HexWatcher;

public class GosDeviceControlActivity extends GosControlModuleBaseActivity
		implements OnClickListener, OnEditorActionListener, OnSeekBarChangeListener {

	/** 设备列表传入的设备变量 */
	private GizWifiDevice mDevice;

	private Switch sw_bool_power_switch;
	private Spinner sp_enum_mode;
	private TextView tv_data_room_stemp;
	private SeekBar sb_data_room_stemp;
	private Switch sw_bool_exchangeon_f;
	private Switch sw_bool_winsum_f;
	private Switch sw_bool_eheat_f;
	private Switch sw_bool_upper_elec;
	private Switch sw_bool_lower_elec;
	private Switch sw_bool_power1_comerr1;
	private TextView tv_data_rtemp;
	private TextView tv_data_room_humi;
	private TextView tv_data_upwttemp;
	private TextView tv_data_downwttemp;
	private TextView tv_data_room_co2;
	private TextView tv_data_room_pm25;
	private TextView tv_data_wt_quality;
	private TextView tv_data_room_voc;
	private TextView tv_data_power11;
	private TextView tv_data_power12;
	private TextView tv_data_power13;
	private TextView tv_data_power14;
	private TextView tv_data_power15;
	private TextView tv_data_power16;
	private TextView tv_data_power21;
	private TextView tv_data_power22;
	private TextView tv_data_power23;
	private TextView tv_data_power24;
	private TextView tv_data_power25;
	private TextView tv_data_power26;
	private TextView tv_data_power31;
	private TextView tv_data_power32;
	private TextView tv_data_power33;
	private TextView tv_data_power34;
	private TextView tv_data_power35;
	private TextView tv_data_power36;
	private TextView tv_data_power41;
	private TextView tv_data_power42;
	private TextView tv_data_power43;
	private TextView tv_data_power44;
	private TextView tv_data_power45;
	private TextView tv_data_power46;
	private TextView tv_data_all_flow;
	private TextView tv_data_all_power11;
	private TextView tv_data_all_power12;
	private TextView tv_data_all_power13;
	private TextView tv_data_all_power14;
	private TextView tv_data_all_power15;
	private TextView tv_data_all_power16;
	private TextView tv_data_all_power21;
	private TextView tv_data_all_power22;
	private TextView tv_data_all_power23;
	private TextView tv_data_all_power24;
	private TextView tv_data_all_power25;
	private TextView tv_data_all_power26;
	private TextView tv_data_all_power31;
	private TextView tv_data_all_power32;
	private TextView tv_data_all_power33;
	private TextView tv_data_all_power34;
	private TextView tv_data_all_power35;
	private TextView tv_data_all_power36;
	private TextView tv_data_all_power41;
	private TextView tv_data_all_power42;
	private TextView tv_data_all_power43;
	private TextView tv_data_all_power44;
	private TextView tv_data_all_power45;
	private TextView tv_data_all_power46;

	private enum handler_key {

		/** 更新界面 */
		UPDATE_UI,

		DISCONNECT,
	}

	private Runnable mRunnable = new Runnable() {
		public void run() {
			if (isDeviceCanBeControlled()) {
				progressDialog.cancel();
			} else {
				toastDeviceNoReadyAndExit();
			}
		}

	};

	/** The handler. */
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			handler_key key = handler_key.values()[msg.what];
			switch (key) {
			case UPDATE_UI:
				updateUI();
				break;
			case DISCONNECT:
				toastDeviceDisconnectAndExit();
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gos_device_control);
		initDevice();
		setActionBar(true, true, getDeviceName());
		initView();
		initEvent();
	}

	private void initView() {
		
		sw_bool_power_switch = (Switch) findViewById(R.id.sw_bool_power_switch);
		sp_enum_mode = (Spinner) findViewById(R.id.sp_enum_mode);
		tv_data_room_stemp = (TextView) findViewById(R.id.tv_data_room_stemp);
		sb_data_room_stemp = (SeekBar) findViewById(R.id.sb_data_room_stemp);
		sw_bool_exchangeon_f = (Switch) findViewById(R.id.sw_bool_exchangeon_f);
		sw_bool_winsum_f = (Switch) findViewById(R.id.sw_bool_winsum_f);
		sw_bool_eheat_f = (Switch) findViewById(R.id.sw_bool_eheat_f);
		sw_bool_upper_elec = (Switch) findViewById(R.id.sw_bool_upper_elec);
		sw_bool_lower_elec = (Switch) findViewById(R.id.sw_bool_lower_elec);
		sw_bool_power1_comerr1 = (Switch) findViewById(R.id.sw_bool_power1_comerr1);
		tv_data_rtemp = (TextView) findViewById(R.id.tv_data_rtemp);
		tv_data_room_humi = (TextView) findViewById(R.id.tv_data_room_humi);
		tv_data_upwttemp = (TextView) findViewById(R.id.tv_data_upwttemp);
		tv_data_downwttemp = (TextView) findViewById(R.id.tv_data_downwttemp);
		tv_data_room_co2 = (TextView) findViewById(R.id.tv_data_room_co2);
		tv_data_room_pm25 = (TextView) findViewById(R.id.tv_data_room_pm25);
		tv_data_wt_quality = (TextView) findViewById(R.id.tv_data_wt_quality);
		tv_data_room_voc = (TextView) findViewById(R.id.tv_data_room_voc);
		tv_data_power11 = (TextView) findViewById(R.id.tv_data_power11);
		tv_data_power12 = (TextView) findViewById(R.id.tv_data_power12);
		tv_data_power13 = (TextView) findViewById(R.id.tv_data_power13);
		tv_data_power14 = (TextView) findViewById(R.id.tv_data_power14);
		tv_data_power15 = (TextView) findViewById(R.id.tv_data_power15);
		tv_data_power16 = (TextView) findViewById(R.id.tv_data_power16);
		tv_data_power21 = (TextView) findViewById(R.id.tv_data_power21);
		tv_data_power22 = (TextView) findViewById(R.id.tv_data_power22);
		tv_data_power23 = (TextView) findViewById(R.id.tv_data_power23);
		tv_data_power24 = (TextView) findViewById(R.id.tv_data_power24);
		tv_data_power25 = (TextView) findViewById(R.id.tv_data_power25);
		tv_data_power26 = (TextView) findViewById(R.id.tv_data_power26);
		tv_data_power31 = (TextView) findViewById(R.id.tv_data_power31);
		tv_data_power32 = (TextView) findViewById(R.id.tv_data_power32);
		tv_data_power33 = (TextView) findViewById(R.id.tv_data_power33);
		tv_data_power34 = (TextView) findViewById(R.id.tv_data_power34);
		tv_data_power35 = (TextView) findViewById(R.id.tv_data_power35);
		tv_data_power36 = (TextView) findViewById(R.id.tv_data_power36);
		tv_data_power41 = (TextView) findViewById(R.id.tv_data_power41);
		tv_data_power42 = (TextView) findViewById(R.id.tv_data_power42);
		tv_data_power43 = (TextView) findViewById(R.id.tv_data_power43);
		tv_data_power44 = (TextView) findViewById(R.id.tv_data_power44);
		tv_data_power45 = (TextView) findViewById(R.id.tv_data_power45);
		tv_data_power46 = (TextView) findViewById(R.id.tv_data_power46);
		tv_data_all_flow = (TextView) findViewById(R.id.tv_data_all_flow);
		tv_data_all_power11 = (TextView) findViewById(R.id.tv_data_all_power11);
		tv_data_all_power12 = (TextView) findViewById(R.id.tv_data_all_power12);
		tv_data_all_power13 = (TextView) findViewById(R.id.tv_data_all_power13);
		tv_data_all_power14 = (TextView) findViewById(R.id.tv_data_all_power14);
		tv_data_all_power15 = (TextView) findViewById(R.id.tv_data_all_power15);
		tv_data_all_power16 = (TextView) findViewById(R.id.tv_data_all_power16);
		tv_data_all_power21 = (TextView) findViewById(R.id.tv_data_all_power21);
		tv_data_all_power22 = (TextView) findViewById(R.id.tv_data_all_power22);
		tv_data_all_power23 = (TextView) findViewById(R.id.tv_data_all_power23);
		tv_data_all_power24 = (TextView) findViewById(R.id.tv_data_all_power24);
		tv_data_all_power25 = (TextView) findViewById(R.id.tv_data_all_power25);
		tv_data_all_power26 = (TextView) findViewById(R.id.tv_data_all_power26);
		tv_data_all_power31 = (TextView) findViewById(R.id.tv_data_all_power31);
		tv_data_all_power32 = (TextView) findViewById(R.id.tv_data_all_power32);
		tv_data_all_power33 = (TextView) findViewById(R.id.tv_data_all_power33);
		tv_data_all_power34 = (TextView) findViewById(R.id.tv_data_all_power34);
		tv_data_all_power35 = (TextView) findViewById(R.id.tv_data_all_power35);
		tv_data_all_power36 = (TextView) findViewById(R.id.tv_data_all_power36);
		tv_data_all_power41 = (TextView) findViewById(R.id.tv_data_all_power41);
		tv_data_all_power42 = (TextView) findViewById(R.id.tv_data_all_power42);
		tv_data_all_power43 = (TextView) findViewById(R.id.tv_data_all_power43);
		tv_data_all_power44 = (TextView) findViewById(R.id.tv_data_all_power44);
		tv_data_all_power45 = (TextView) findViewById(R.id.tv_data_all_power45);
		tv_data_all_power46 = (TextView) findViewById(R.id.tv_data_all_power46);
	}

	private void initEvent() {

		sw_bool_power_switch.setOnClickListener(this);
		sp_enum_mode.setSelection(0, false);
		sp_enum_mode.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

				if (position != mode) {
					sendCommand(KEY_MODE, position);
					mode = position;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		sb_data_room_stemp.setOnSeekBarChangeListener(this);
		sw_bool_exchangeon_f.setEnabled(false);
		sw_bool_winsum_f.setEnabled(false);
		sw_bool_eheat_f.setEnabled(false);
		sw_bool_upper_elec.setEnabled(false);
		sw_bool_lower_elec.setEnabled(false);
		sw_bool_power1_comerr1.setEnabled(false);
	
	}

	private void initDevice() {
		Intent intent = getIntent();
		mDevice = (GizWifiDevice) intent.getParcelableExtra("GizWifiDevice");
		mDevice.setListener(gizWifiDeviceListener);
		Log.i("Apptest", mDevice.getDid());
	}

	private String getDeviceName() {
		if (TextUtils.isEmpty(mDevice.getAlias())) {
			return mDevice.getProductName();
		}
		return mDevice.getAlias();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getStatusOfDevice();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacks(mRunnable);
		// 退出页面，取消设备订阅
		mDevice.setSubscribe(false);
		mDevice.setListener(null);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sw_bool_power_switch:
			sendCommand(KEY_POWER_SWITCH, sw_bool_power_switch.isChecked());
			break;
		default:
			break;
		}
	}

	/*
	 * ========================================================================
	 * EditText 点击键盘“完成”按钮方法
	 * ========================================================================
	 */
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

		switch (v.getId()) {
		default:
			break;
		}
		hideKeyBoard();
		return false;

	}
	
	/*
	 * ========================================================================
	 * seekbar 回调方法重写
	 * ========================================================================
	 */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		
		switch (seekBar.getId()) {
		case R.id.sb_data_room_stemp:
			tv_data_room_stemp.setText(formatValue((progress + ROOM_STEMP_OFFSET) * ROOM_STEMP_RATIO + ROOM_STEMP_ADDITION, 1));
			break;
		default:
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		switch (seekBar.getId()) {
		case R.id.sb_data_room_stemp:
			sendCommand(KEY_ROOM_STEMP, (seekBar.getProgress() + ROOM_STEMP_OFFSET ) * ROOM_STEMP_RATIO + ROOM_STEMP_ADDITION);
			break;
		default:
			break;
		}
	}

	/*
	 * ========================================================================
	 * 菜单栏
	 * ========================================================================
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.device_more, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.action_setDeviceInfo:
			setDeviceInfo();
			break;

		case R.id.action_getHardwareInfo:
			if (mDevice.isLAN()) {
				mDevice.getHardwareInfo();
			} else {
				myToast("只允许在局域网下获取设备硬件信息！");
			}
			break;

		case R.id.action_getStatu:
			mDevice.getDeviceStatus();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Description:根据保存的的数据点的值来更新UI
	 */
	protected void updateUI() {
		
		sw_bool_power_switch.setChecked(power_switch);
		sp_enum_mode.setSelection(mode, true);
		tv_data_room_stemp.setText(room_stemp+"");
		sb_data_room_stemp.setProgress((int)((room_stemp - ROOM_STEMP_ADDITION) / ROOM_STEMP_RATIO - ROOM_STEMP_OFFSET));
		sw_bool_exchangeon_f.setChecked(exchangeon_f);
		sw_bool_winsum_f.setChecked(winsum_f);
		sw_bool_eheat_f.setChecked(eheat_f);
		sw_bool_upper_elec.setChecked(upper_elec);
		sw_bool_lower_elec.setChecked(lower_elec);
		sw_bool_power1_comerr1.setChecked(power1_comerr1);
		tv_data_rtemp.setText(rtemp+"");
		tv_data_room_humi.setText(room_humi+"");
		tv_data_upwttemp.setText(upwttemp+"");
		tv_data_downwttemp.setText(downwttemp+"");
		tv_data_room_co2.setText(room_co2+"");
		tv_data_room_pm25.setText(room_pm25+"");
		tv_data_wt_quality.setText(wt_quality+"");
		tv_data_room_voc.setText(room_voc+"");
		tv_data_power11.setText(power11+"");
		tv_data_power12.setText(power12+"");
		tv_data_power13.setText(power13+"");
		tv_data_power14.setText(power14+"");
		tv_data_power15.setText(power15+"");
		tv_data_power16.setText(power16+"");
		tv_data_power21.setText(power21+"");
		tv_data_power22.setText(power22+"");
		tv_data_power23.setText(power23+"");
		tv_data_power24.setText(power24+"");
		tv_data_power25.setText(power25+"");
		tv_data_power26.setText(power26+"");
		tv_data_power31.setText(power31+"");
		tv_data_power32.setText(power32+"");
		tv_data_power33.setText(power33+"");
		tv_data_power34.setText(power34+"");
		tv_data_power35.setText(power35+"");
		tv_data_power36.setText(power36+"");
		tv_data_power41.setText(power41+"");
		tv_data_power42.setText(power42+"");
		tv_data_power43.setText(power43+"");
		tv_data_power44.setText(power44+"");
		tv_data_power45.setText(power45+"");
		tv_data_power46.setText(power46+"");
		tv_data_all_flow.setText(all_flow+"");
		tv_data_all_power11.setText(all_power11+"");
		tv_data_all_power12.setText(all_power12+"");
		tv_data_all_power13.setText(all_power13+"");
		tv_data_all_power14.setText(all_power14+"");
		tv_data_all_power15.setText(all_power15+"");
		tv_data_all_power16.setText(all_power16+"");
		tv_data_all_power21.setText(all_power21+"");
		tv_data_all_power22.setText(all_power22+"");
		tv_data_all_power23.setText(all_power23+"");
		tv_data_all_power24.setText(all_power24+"");
		tv_data_all_power25.setText(all_power25+"");
		tv_data_all_power26.setText(all_power26+"");
		tv_data_all_power31.setText(all_power31+"");
		tv_data_all_power32.setText(all_power32+"");
		tv_data_all_power33.setText(all_power33+"");
		tv_data_all_power34.setText(all_power34+"");
		tv_data_all_power35.setText(all_power35+"");
		tv_data_all_power36.setText(all_power36+"");
		tv_data_all_power41.setText(all_power41+"");
		tv_data_all_power42.setText(all_power42+"");
		tv_data_all_power43.setText(all_power43+"");
		tv_data_all_power44.setText(all_power44+"");
		tv_data_all_power45.setText(all_power45+"");
		tv_data_all_power46.setText(all_power46+"");
	
	}

	private void setEditText(EditText et, Object value) {
		et.setText(value.toString());
		et.setSelection(value.toString().length());
		et.clearFocus();
	}

	/**
	 * Description:页面加载后弹出等待框，等待设备可被控制状态回调，如果一直不可被控，等待一段时间后自动退出界面
	 */
	private void getStatusOfDevice() {
		// 设备是否可控
		if (isDeviceCanBeControlled()) {
			// 可控则查询当前设备状态
			mDevice.getDeviceStatus();
		} else {
			// 显示等待栏
			progressDialog.show();
			if (mDevice.isLAN()) {
				// 小循环10s未连接上设备自动退出
				mHandler.postDelayed(mRunnable, 10000);
			} else {
				// 大循环20s未连接上设备自动退出
				mHandler.postDelayed(mRunnable, 20000);
			}
		}
	}

	/**
	 * 发送指令,下发单个数据点的命令可以用这个方法
	 * 
	 * <h3>注意</h3>
	 * <p>
	 * 下发多个数据点命令不能用这个方法多次调用，一次性多次调用这个方法会导致模组无法正确接收消息，参考方法内注释。
	 * </p>
	 * 
	 * @param key
	 *            数据点对应的标识名
	 * @param value
	 *            需要改变的值
	 */
	private void sendCommand(String key, Object value) {
		if (value == null) {
			return;
		}
		int sn = 5;
		ConcurrentHashMap<String, Object> hashMap = new ConcurrentHashMap<String, Object>();
		hashMap.put(key, value);
		// 同时下发多个数据点需要一次性在map中放置全部需要控制的key，value值
		// hashMap.put(key2, value2);
		// hashMap.put(key3, value3);
		mDevice.write(hashMap, sn);
		Log.i("liang", "下发命令：" + hashMap.toString());
	}

	private boolean isDeviceCanBeControlled() {
		return mDevice.getNetStatus() == GizWifiDeviceNetStatus.GizDeviceControlled;
	}

	private void toastDeviceNoReadyAndExit() {
		Toast.makeText(this, "设备无响应，请检查设备是否正常工作", Toast.LENGTH_SHORT).show();
		finish();
	}

	private void toastDeviceDisconnectAndExit() {
		Toast.makeText(GosDeviceControlActivity.this, "连接已断开", Toast.LENGTH_SHORT).show();
		finish();
	}

	/**
	 * 展示设备硬件信息
	 * 
	 * @param hardwareInfo
	 */
	private void showHardwareInfo(String hardwareInfo) {
		String hardwareInfoTitle = "设备硬件信息";
		new AlertDialog.Builder(this).setTitle(hardwareInfoTitle).setMessage(hardwareInfo)
				.setPositiveButton(R.string.besure, null).show();
	}

	/**
	 * Description:设置设备别名与备注
	 */
	private void setDeviceInfo() {

		final Dialog mDialog = new AlertDialog.Builder(this).setView(new EditText(this)).create();
		mDialog.show();

		Window window = mDialog.getWindow();
		window.setContentView(R.layout.alert_gos_set_device_info);

		final EditText etAlias;
		final EditText etRemark;
		etAlias = (EditText) window.findViewById(R.id.etAlias);
		etRemark = (EditText) window.findViewById(R.id.etRemark);

		LinearLayout llNo, llSure;
		llNo = (LinearLayout) window.findViewById(R.id.llNo);
		llSure = (LinearLayout) window.findViewById(R.id.llSure);

		if (!TextUtils.isEmpty(mDevice.getAlias())) {
			setEditText(etAlias, mDevice.getAlias());
		}
		if (!TextUtils.isEmpty(mDevice.getRemark())) {
			setEditText(etRemark, mDevice.getRemark());
		}

		llNo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mDialog.dismiss();
			}
		});

		llSure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(etRemark.getText().toString())
						&& TextUtils.isEmpty(etAlias.getText().toString())) {
					myToast("请输入设备别名或备注！");
					return;
				}
				mDevice.setCustomInfo(etRemark.getText().toString(), etAlias.getText().toString());
				mDialog.dismiss();
				String loadingText = (String) getText(R.string.loadingtext);
				progressDialog.setMessage(loadingText);
				progressDialog.show();
			}
		});

		mDialog.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				hideKeyBoard();
			}
		});
	}
	
	/*
	 * 获取设备硬件信息回调
	 */
	@Override
	protected void didGetHardwareInfo(GizWifiErrorCode result, GizWifiDevice device,
			ConcurrentHashMap<String, String> hardwareInfo) {
		super.didGetHardwareInfo(result, device, hardwareInfo);
		StringBuffer sb = new StringBuffer();
		if (GizWifiErrorCode.GIZ_SDK_SUCCESS != result) {
			myToast("获取设备硬件信息失败：" + result.name());
		} else {
			sb.append("Wifi Hardware Version:" + hardwareInfo.get(WIFI_HARDVER_KEY) + "\r\n");
			sb.append("Wifi Software Version:" + hardwareInfo.get(WIFI_SOFTVER_KEY) + "\r\n");
			sb.append("MCU Hardware Version:" + hardwareInfo.get(MCU_HARDVER_KEY) + "\r\n");
			sb.append("MCU Software Version:" + hardwareInfo.get(MCU_SOFTVER_KEY) + "\r\n");
			sb.append("Wifi Firmware Id:" + hardwareInfo.get(WIFI_FIRMWAREID_KEY) + "\r\n");
			sb.append("Wifi Firmware Version:" + hardwareInfo.get(WIFI_FIRMWAREVER_KEY) + "\r\n");
			sb.append("Product Key:" + "\r\n" + hardwareInfo.get(PRODUCT_KEY) + "\r\n");

			// 设备属性
			sb.append("Device ID:" + "\r\n" + mDevice.getDid() + "\r\n");
			sb.append("Device IP:" + mDevice.getIPAddress() + "\r\n");
			sb.append("Device MAC:" + mDevice.getMacAddress() + "\r\n");
		}
		showHardwareInfo(sb.toString());
	}
	
	/*
	 * 设置设备别名和备注回调
	 */
	@Override
	protected void didSetCustomInfo(GizWifiErrorCode result, GizWifiDevice device) {
		super.didSetCustomInfo(result, device);
		if (GizWifiErrorCode.GIZ_SDK_SUCCESS == result) {
			myToast("设置成功");
			progressDialog.cancel();
			finish();
		} else {
			myToast("设置失败：" + result.name());
		}
	}

	/*
	 * 设备状态改变回调，只有设备状态为可控才可以下发控制命令
	 */
	@Override
	protected void didUpdateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {
		super.didUpdateNetStatus(device, netStatus);
		if (netStatus == GizWifiDeviceNetStatus.GizDeviceControlled) {
			mHandler.removeCallbacks(mRunnable);
			progressDialog.cancel();
		} else {
			mHandler.sendEmptyMessage(handler_key.DISCONNECT.ordinal());
		}
	}
	
	/*
	 * 设备上报数据回调，此回调包括设备主动上报数据、下发控制命令成功后设备返回ACK
	 */
	@Override
	protected void didReceiveData(GizWifiErrorCode result, GizWifiDevice device,
			ConcurrentHashMap<String, Object> dataMap, int sn) {
		super.didReceiveData(result, device, dataMap, sn);
		Log.i("liang", "接收到数据");
		if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS && dataMap.get("data") != null) {
			getDataFromReceiveDataMap(dataMap);
			mHandler.sendEmptyMessage(handler_key.UPDATE_UI.ordinal());
		}
	}

}