package com.nuowei.smarthome.appkit.ControlModule;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiDeviceNetStatus;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiDeviceListener;
import com.nuowei.smarthome.appkit.CommonModule.GosBaseActivity;
import com.nuowei.smarthome.appkit.utils.HexStrUtils;

import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class GosControlModuleBaseActivity extends GosBaseActivity {

	/*
	 * ===========================================================
	 * 以下key值对应开发者在云端定义的数据点标识名
	 * ===========================================================
	 */
	// 数据点"power_switch"对应的标识名
	protected static final String KEY_POWER_SWITCH = "power_switch";
	// 数据点"mode"对应的标识名
	protected static final String KEY_MODE = "mode";
	// 数据点"room_stemp"对应的标识名
	protected static final String KEY_ROOM_STEMP = "room_stemp";
	// 数据点"exchangeon_f"对应的标识名
	protected static final String KEY_EXCHANGEON_F = "exchangeon_f";
	// 数据点"winsum_f"对应的标识名
	protected static final String KEY_WINSUM_F = "winsum_f";
	// 数据点"eheat_f"对应的标识名
	protected static final String KEY_EHEAT_F = "eheat_f";
	// 数据点"upper_elec"对应的标识名
	protected static final String KEY_UPPER_ELEC = "upper_elec";
	// 数据点"lower_elec"对应的标识名
	protected static final String KEY_LOWER_ELEC = "lower_elec";
	// 数据点"power1_comerr1"对应的标识名
	protected static final String KEY_POWER1_COMERR1 = "power1_comerr1";
	// 数据点"rtemp"对应的标识名
	protected static final String KEY_RTEMP = "rtemp";
	// 数据点"room_humi"对应的标识名
	protected static final String KEY_ROOM_HUMI = "room_humi";
	// 数据点"upwttemp"对应的标识名
	protected static final String KEY_UPWTTEMP = "upwttemp";
	// 数据点"downwttemp"对应的标识名
	protected static final String KEY_DOWNWTTEMP = "downwttemp";
	// 数据点"room_co2"对应的标识名
	protected static final String KEY_ROOM_CO2 = "room_co2";
	// 数据点"room_pm25"对应的标识名
	protected static final String KEY_ROOM_PM25 = "room_pm25";
	// 数据点"wt_quality"对应的标识名
	protected static final String KEY_WT_QUALITY = "wt_quality";
	// 数据点"room_voc"对应的标识名
	protected static final String KEY_ROOM_VOC = "room_voc";
	// 数据点"power11"对应的标识名
	protected static final String KEY_POWER11 = "power11";
	// 数据点"power12"对应的标识名
	protected static final String KEY_POWER12 = "power12";
	// 数据点"power13"对应的标识名
	protected static final String KEY_POWER13 = "power13";
	// 数据点"power14"对应的标识名
	protected static final String KEY_POWER14 = "power14";
	// 数据点"power15"对应的标识名
	protected static final String KEY_POWER15 = "power15";
	// 数据点"power16"对应的标识名
	protected static final String KEY_POWER16 = "power16";
	// 数据点"power21"对应的标识名
	protected static final String KEY_POWER21 = "power21";
	// 数据点"power22"对应的标识名
	protected static final String KEY_POWER22 = "power22";
	// 数据点"power23"对应的标识名
	protected static final String KEY_POWER23 = "power23";
	// 数据点"power24"对应的标识名
	protected static final String KEY_POWER24 = "power24";
	// 数据点"power25"对应的标识名
	protected static final String KEY_POWER25 = "power25";
	// 数据点"power26"对应的标识名
	protected static final String KEY_POWER26 = "power26";
	// 数据点"power31"对应的标识名
	protected static final String KEY_POWER31 = "power31";
	// 数据点"power32"对应的标识名
	protected static final String KEY_POWER32 = "power32";
	// 数据点"power33"对应的标识名
	protected static final String KEY_POWER33 = "power33";
	// 数据点"power34"对应的标识名
	protected static final String KEY_POWER34 = "power34";
	// 数据点"power35"对应的标识名
	protected static final String KEY_POWER35 = "power35";
	// 数据点"power36"对应的标识名
	protected static final String KEY_POWER36 = "power36";
	// 数据点"power41"对应的标识名
	protected static final String KEY_POWER41 = "power41";
	// 数据点"power42"对应的标识名
	protected static final String KEY_POWER42 = "power42";
	// 数据点"power43"对应的标识名
	protected static final String KEY_POWER43 = "power43";
	// 数据点"power44"对应的标识名
	protected static final String KEY_POWER44 = "power44";
	// 数据点"power45"对应的标识名
	protected static final String KEY_POWER45 = "power45";
	// 数据点"power46"对应的标识名
	protected static final String KEY_POWER46 = "power46";
	// 数据点"all_flow"对应的标识名
	protected static final String KEY_ALL_FLOW = "all_flow";
	// 数据点"all_power11"对应的标识名
	protected static final String KEY_ALL_POWER11 = "all_power11";
	// 数据点"all_power12"对应的标识名
	protected static final String KEY_ALL_POWER12 = "all_power12";
	// 数据点"all_power13"对应的标识名
	protected static final String KEY_ALL_POWER13 = "all_power13";
	// 数据点"all_power14"对应的标识名
	protected static final String KEY_ALL_POWER14 = "all_power14";
	// 数据点"all_power15"对应的标识名
	protected static final String KEY_ALL_POWER15 = "all_power15";
	// 数据点"all_power16"对应的标识名
	protected static final String KEY_ALL_POWER16 = "all_power16";
	// 数据点"all_power21"对应的标识名
	protected static final String KEY_ALL_POWER21 = "all_power21";
	// 数据点"all_power22"对应的标识名
	protected static final String KEY_ALL_POWER22 = "all_power22";
	// 数据点"all_power23"对应的标识名
	protected static final String KEY_ALL_POWER23 = "all_power23";
	// 数据点"all_power24"对应的标识名
	protected static final String KEY_ALL_POWER24 = "all_power24";
	// 数据点"all_power25"对应的标识名
	protected static final String KEY_ALL_POWER25 = "all_power25";
	// 数据点"all_power26"对应的标识名
	protected static final String KEY_ALL_POWER26 = "all_power26";
	// 数据点"all_power31"对应的标识名
	protected static final String KEY_ALL_POWER31 = "all_power31";
	// 数据点"all_power32"对应的标识名
	protected static final String KEY_ALL_POWER32 = "all_power32";
	// 数据点"all_power33"对应的标识名
	protected static final String KEY_ALL_POWER33 = "all_power33";
	// 数据点"all_power34"对应的标识名
	protected static final String KEY_ALL_POWER34 = "all_power34";
	// 数据点"all_power35"对应的标识名
	protected static final String KEY_ALL_POWER35 = "all_power35";
	// 数据点"all_power36"对应的标识名
	protected static final String KEY_ALL_POWER36 = "all_power36";
	// 数据点"all_power41"对应的标识名
	protected static final String KEY_ALL_POWER41 = "all_power41";
	// 数据点"all_power42"对应的标识名
	protected static final String KEY_ALL_POWER42 = "all_power42";
	// 数据点"all_power43"对应的标识名
	protected static final String KEY_ALL_POWER43 = "all_power43";
	// 数据点"all_power44"对应的标识名
	protected static final String KEY_ALL_POWER44 = "all_power44";
	// 数据点"all_power45"对应的标识名
	protected static final String KEY_ALL_POWER45 = "all_power45";
	// 数据点"all_power46"对应的标识名
	protected static final String KEY_ALL_POWER46 = "all_power46";
	// 数据点"power1comerr_f"对应的标识名
	protected static final String KEY_POWER1COMERR_F = "power1comerr_f";
	// 数据点"power1_comerr2"对应的标识名
	protected static final String KEY_POWER1_COMERR2 = "power1_comerr2";
	// 数据点"power1_comerr3"对应的标识名
	protected static final String KEY_POWER1_COMERR3 = "power1_comerr3";
	// 数据点"power1_comerr4"对应的标识名
	protected static final String KEY_POWER1_COMERR4 = "power1_comerr4";
	// 数据点"power1_comerr5"对应的标识名
	protected static final String KEY_POWER1_COMERR5 = "power1_comerr5";
	// 数据点"power1_comerr6"对应的标识名
	protected static final String KEY_POWER1_COMERR6 = "power1_comerr6";
	// 数据点"power2_comerr1"对应的标识名
	protected static final String KEY_POWER2_COMERR1 = "power2_comerr1";
	// 数据点"power2_comerr2"对应的标识名
	protected static final String KEY_POWER2_COMERR2 = "power2_comerr2";
	// 数据点"power2_comerr3"对应的标识名
	protected static final String KEY_POWER2_COMERR3 = "power2_comerr3";
	// 数据点"power2_comerr4"对应的标识名
	protected static final String KEY_POWER2_COMERR4 = "power2_comerr4";
	// 数据点"power2_comerr5"对应的标识名
	protected static final String KEY_POWER2_COMERR5 = "power2_comerr5";
	// 数据点"power2_comerr6"对应的标识名
	protected static final String KEY_POWER2_COMERR6 = "power2_comerr6";
	// 数据点"power3_comerr1"对应的标识名
	protected static final String KEY_POWER3_COMERR1 = "power3_comerr1";
	// 数据点"power3_comerr2"对应的标识名
	protected static final String KEY_POWER3_COMERR2 = "power3_comerr2";
	// 数据点"power3_comerr3"对应的标识名
	protected static final String KEY_POWER3_COMERR3 = "power3_comerr3";
	// 数据点"power3_comerr4"对应的标识名
	protected static final String KEY_POWER3_COMERR4 = "power3_comerr4";
	// 数据点"power3_comerr5"对应的标识名
	protected static final String KEY_POWER3_COMERR5 = "power3_comerr5";
	// 数据点"power3_comerr6"对应的标识名
	protected static final String KEY_POWER3_COMERR6 = "power3_comerr6";
	// 数据点"power4_comerr1"对应的标识名
	protected static final String KEY_POWER4_COMERR1 = "power4_comerr1";
	// 数据点"power4_comerr2"对应的标识名
	protected static final String KEY_POWER4_COMERR2 = "power4_comerr2";
	// 数据点"power4_comerr3"对应的标识名
	protected static final String KEY_POWER4_COMERR3 = "power4_comerr3";
	// 数据点"power4_comerr4"对应的标识名
	protected static final String KEY_POWER4_COMERR4 = "power4_comerr4";
	// 数据点"power4_comerr5"对应的标识名
	protected static final String KEY_POWER4_COMERR5 = "power4_comerr5";
	// 数据点"power4_comerr6"对应的标识名
	protected static final String KEY_POWER4_COMERR6 = "power4_comerr6";
	// 数据点"power2comerr_f"对应的标识名
	protected static final String KEY_POWER2COMERR_F = "power2comerr_f";
	// 数据点"power3comerr_f"对应的标识名
	protected static final String KEY_POWER3COMERR_F = "power3comerr_f";
	// 数据点"power4comerr_f"对应的标识名
	protected static final String KEY_POWER4COMERR_F = "power4comerr_f";
	// 数据点"newfancomerr_f"对应的标识名
	protected static final String KEY_NEWFANCOMERR_F = "newfancomerr_f";
	// 数据点"whjerr_f"对应的标识名
	protected static final String KEY_WHJERR_F = "whjerr_f";
	// 数据点"overerr_f"对应的标识名
	protected static final String KEY_OVERERR_F = "overerr_f";
	// 数据点"ehtooth_f"对应的标识名
	protected static final String KEY_EHTOOTH_F = "ehtooth_f";
	// 数据点"fderr_f"对应的标识名
	protected static final String KEY_FDERR_F = "fderr_f";
	// 数据点"rerr_f"对应的标识名
	protected static final String KEY_RERR_F = "rerr_f";
	// 数据点"xqerr_f"对应的标识名
	protected static final String KEY_XQERR_F = "xqerr_f";
	// 数据点"supplyerr_f"对应的标识名
	protected static final String KEY_SUPPLYERR_F = "supplyerr_f";
	// 数据点"pqerr_f"对应的标识名
	protected static final String KEY_PQERR_F = "pqerr_f";
	// 数据点"comhumierr_f"对应的标识名
	protected static final String KEY_COMHUMIERR_F = "comhumierr_f";
	// 数据点"comco2err_f"对应的标识名
	protected static final String KEY_COMCO2ERR_F = "comco2err_f";
	// 数据点"eheatcomerr_f"对应的标识名
	protected static final String KEY_EHEATCOMERR_F = "eheatcomerr_f";
	// 数据点"upwterr_f"对应的标识名
	protected static final String KEY_UPWTERR_F = "upwterr_f";
	// 数据点"downwterr_f"对应的标识名
	protected static final String KEY_DOWNWTERR_F = "downwterr_f";
	// 数据点"flowcomerr_f"对应的标识名
	protected static final String KEY_FLOWCOMERR_F = "flowcomerr_f";
	// 数据点"wtqualitycomerr_f"对应的标识名
	protected static final String KEY_WTQUALITYCOMERR_F = "wtqualitycomerr_f";

	/*
	 * ===========================================================
	 * 以下数值对应开发者在云端定义的可写数值型数据点增量值、数据点定义的分辨率、seekbar滚动条补偿值
	 * _ADDITION:数据点增量值
	 * _RATIO:数据点定义的分辨率
	 * _OFFSET:seekbar滚动条补偿值
	 * APP与设备定义的协议公式为：y（APP接收的值）=x（设备上报的值）* RATIO（分辨率）+ADDITION（增量值）
	 * 由于安卓的原生seekbar无法设置最小值，因此代码中增加了一个补偿量OFFSET
	 * 实际上公式中的：x（设备上报的值）=seekbar的值+补偿值
	 * ===========================================================
	 */
	// 数据点"room_stemp"对应seekbar滚动条补偿值
	protected static final int ROOM_STEMP_OFFSET = 0;
	// 数据点"room_stemp"对应数据点增量值
	protected static final int ROOM_STEMP_ADDITION = 13;
	// 数据点"room_stemp"对应数据点定义的分辨率
	protected static final int ROOM_STEMP_RATIO = 1;
		

	/*
	 * ===========================================================
	 * 以下变量对应设备上报类型为布尔、数值、扩展数据点的数据存储
	 * ===========================================================
	 */
	// 数据点"power_switch"对应的存储数据
	protected static boolean power_switch;
	// 数据点"mode"对应的存储数据
	protected static int mode;
	// 数据点"room_stemp"对应的存储数据
	protected static int room_stemp;
	// 数据点"exchangeon_f"对应的存储数据
	protected static boolean exchangeon_f;
	// 数据点"winsum_f"对应的存储数据
	protected static boolean winsum_f;
	// 数据点"eheat_f"对应的存储数据
	protected static boolean eheat_f;
	// 数据点"upper_elec"对应的存储数据
	protected static boolean upper_elec;
	// 数据点"lower_elec"对应的存储数据
	protected static boolean lower_elec;
	// 数据点"power1_comerr1"对应的存储数据
	protected static boolean power1_comerr1;
	// 数据点"rtemp"对应的存储数据
	protected static int rtemp;
	// 数据点"room_humi"对应的存储数据
	protected static int room_humi;
	// 数据点"upwttemp"对应的存储数据
	protected static int upwttemp;
	// 数据点"downwttemp"对应的存储数据
	protected static int downwttemp;
	// 数据点"room_co2"对应的存储数据
	protected static int room_co2;
	// 数据点"room_pm25"对应的存储数据
	protected static int room_pm25;
	// 数据点"wt_quality"对应的存储数据
	protected static int wt_quality;
	// 数据点"room_voc"对应的存储数据
	protected static int room_voc;
	// 数据点"power11"对应的存储数据
	protected static int power11;
	// 数据点"power12"对应的存储数据
	protected static int power12;
	// 数据点"power13"对应的存储数据
	protected static int power13;
	// 数据点"power14"对应的存储数据
	protected static int power14;
	// 数据点"power15"对应的存储数据
	protected static int power15;
	// 数据点"power16"对应的存储数据
	protected static int power16;
	// 数据点"power21"对应的存储数据
	protected static int power21;
	// 数据点"power22"对应的存储数据
	protected static int power22;
	// 数据点"power23"对应的存储数据
	protected static int power23;
	// 数据点"power24"对应的存储数据
	protected static int power24;
	// 数据点"power25"对应的存储数据
	protected static int power25;
	// 数据点"power26"对应的存储数据
	protected static int power26;
	// 数据点"power31"对应的存储数据
	protected static int power31;
	// 数据点"power32"对应的存储数据
	protected static int power32;
	// 数据点"power33"对应的存储数据
	protected static int power33;
	// 数据点"power34"对应的存储数据
	protected static int power34;
	// 数据点"power35"对应的存储数据
	protected static int power35;
	// 数据点"power36"对应的存储数据
	protected static int power36;
	// 数据点"power41"对应的存储数据
	protected static int power41;
	// 数据点"power42"对应的存储数据
	protected static int power42;
	// 数据点"power43"对应的存储数据
	protected static int power43;
	// 数据点"power44"对应的存储数据
	protected static int power44;
	// 数据点"power45"对应的存储数据
	protected static int power45;
	// 数据点"power46"对应的存储数据
	protected static int power46;
	// 数据点"all_flow"对应的存储数据
	protected static int all_flow;
	// 数据点"all_power11"对应的存储数据
	protected static double all_power11;
	// 数据点"all_power12"对应的存储数据
	protected static double all_power12;
	// 数据点"all_power13"对应的存储数据
	protected static double all_power13;
	// 数据点"all_power14"对应的存储数据
	protected static double all_power14;
	// 数据点"all_power15"对应的存储数据
	protected static double all_power15;
	// 数据点"all_power16"对应的存储数据
	protected static double all_power16;
	// 数据点"all_power21"对应的存储数据
	protected static double all_power21;
	// 数据点"all_power22"对应的存储数据
	protected static double all_power22;
	// 数据点"all_power23"对应的存储数据
	protected static double all_power23;
	// 数据点"all_power24"对应的存储数据
	protected static double all_power24;
	// 数据点"all_power25"对应的存储数据
	protected static double all_power25;
	// 数据点"all_power26"对应的存储数据
	protected static double all_power26;
	// 数据点"all_power31"对应的存储数据
	protected static double all_power31;
	// 数据点"all_power32"对应的存储数据
	protected static double all_power32;
	// 数据点"all_power33"对应的存储数据
	protected static double all_power33;
	// 数据点"all_power34"对应的存储数据
	protected static double all_power34;
	// 数据点"all_power35"对应的存储数据
	protected static double all_power35;
	// 数据点"all_power36"对应的存储数据
	protected static double all_power36;
	// 数据点"all_power41"对应的存储数据
	protected static double all_power41;
	// 数据点"all_power42"对应的存储数据
	protected static double all_power42;
	// 数据点"all_power43"对应的存储数据
	protected static double all_power43;
	// 数据点"all_power44"对应的存储数据
	protected static double all_power44;
	// 数据点"all_power45"对应的存储数据
	protected static double all_power45;
	// 数据点"all_power46"对应的存储数据
	protected static double all_power46;
	// 数据点"power1comerr_f"对应的存储数据
	protected static boolean power1comerr_f;
	// 数据点"power1_comerr2"对应的存储数据
	protected static boolean power1_comerr2;
	// 数据点"power1_comerr3"对应的存储数据
	protected static boolean power1_comerr3;
	// 数据点"power1_comerr4"对应的存储数据
	protected static boolean power1_comerr4;
	// 数据点"power1_comerr5"对应的存储数据
	protected static boolean power1_comerr5;
	// 数据点"power1_comerr6"对应的存储数据
	protected static boolean power1_comerr6;
	// 数据点"power2_comerr1"对应的存储数据
	protected static boolean power2_comerr1;
	// 数据点"power2_comerr2"对应的存储数据
	protected static boolean power2_comerr2;
	// 数据点"power2_comerr3"对应的存储数据
	protected static boolean power2_comerr3;
	// 数据点"power2_comerr4"对应的存储数据
	protected static boolean power2_comerr4;
	// 数据点"power2_comerr5"对应的存储数据
	protected static boolean power2_comerr5;
	// 数据点"power2_comerr6"对应的存储数据
	protected static boolean power2_comerr6;
	// 数据点"power3_comerr1"对应的存储数据
	protected static boolean power3_comerr1;
	// 数据点"power3_comerr2"对应的存储数据
	protected static boolean power3_comerr2;
	// 数据点"power3_comerr3"对应的存储数据
	protected static boolean power3_comerr3;
	// 数据点"power3_comerr4"对应的存储数据
	protected static boolean power3_comerr4;
	// 数据点"power3_comerr5"对应的存储数据
	protected static boolean power3_comerr5;
	// 数据点"power3_comerr6"对应的存储数据
	protected static boolean power3_comerr6;
	// 数据点"power4_comerr1"对应的存储数据
	protected static boolean power4_comerr1;
	// 数据点"power4_comerr2"对应的存储数据
	protected static boolean power4_comerr2;
	// 数据点"power4_comerr3"对应的存储数据
	protected static boolean power4_comerr3;
	// 数据点"power4_comerr4"对应的存储数据
	protected static boolean power4_comerr4;
	// 数据点"power4_comerr5"对应的存储数据
	protected static boolean power4_comerr5;
	// 数据点"power4_comerr6"对应的存储数据
	protected static boolean power4_comerr6;
	// 数据点"power2comerr_f"对应的存储数据
	protected static boolean power2comerr_f;
	// 数据点"power3comerr_f"对应的存储数据
	protected static boolean power3comerr_f;
	// 数据点"power4comerr_f"对应的存储数据
	protected static boolean power4comerr_f;
	// 数据点"newfancomerr_f"对应的存储数据
	protected static boolean newfancomerr_f;
	// 数据点"whjerr_f"对应的存储数据
	protected static boolean whjerr_f;
	// 数据点"overerr_f"对应的存储数据
	protected static boolean overerr_f;
	// 数据点"ehtooth_f"对应的存储数据
	protected static boolean ehtooth_f;
	// 数据点"fderr_f"对应的存储数据
	protected static boolean fderr_f;
	// 数据点"rerr_f"对应的存储数据
	protected static boolean rerr_f;
	// 数据点"xqerr_f"对应的存储数据
	protected static boolean xqerr_f;
	// 数据点"supplyerr_f"对应的存储数据
	protected static boolean supplyerr_f;
	// 数据点"pqerr_f"对应的存储数据
	protected static boolean pqerr_f;
	// 数据点"comhumierr_f"对应的存储数据
	protected static boolean comhumierr_f;
	// 数据点"comco2err_f"对应的存储数据
	protected static boolean comco2err_f;
	// 数据点"eheatcomerr_f"对应的存储数据
	protected static boolean eheatcomerr_f;
	// 数据点"upwterr_f"对应的存储数据
	protected static boolean upwterr_f;
	// 数据点"downwterr_f"对应的存储数据
	protected static boolean downwterr_f;
	// 数据点"flowcomerr_f"对应的存储数据
	protected static boolean flowcomerr_f;
	// 数据点"wtqualitycomerr_f"对应的存储数据
	protected static boolean wtqualitycomerr_f;

	/*
	 * ===========================================================
	 * 以下key值对应设备硬件信息各明细的名称，用与回调中提取硬件信息字段。
	 * ===========================================================
	 */
	protected static final String WIFI_HARDVER_KEY = "wifiHardVersion";
	protected static final String WIFI_SOFTVER_KEY = "wifiSoftVersion";
	protected static final String MCU_HARDVER_KEY = "mcuHardVersion";
	protected static final String MCU_SOFTVER_KEY = "mcuSoftVersion";
	protected static final String WIFI_FIRMWAREID_KEY = "wifiFirmwareId";
	protected static final String WIFI_FIRMWAREVER_KEY = "wifiFirmwareVer";
	protected static final String PRODUCT_KEY = "productKey";

	private Toast mToast;
	
	@SuppressWarnings("unchecked")
	protected void getDataFromReceiveDataMap(ConcurrentHashMap<String, Object> dataMap) {
		// 已定义的设备数据点，有布尔、数值和枚举型数据

		if (dataMap.get("data") != null) {
			ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("data");
			for (String dataKey : map.keySet()) {
				if (dataKey.equals(KEY_POWER_SWITCH)) {
					power_switch = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_MODE)) {
					mode = (Integer) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_ROOM_STEMP)) {
			
					room_stemp = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_EXCHANGEON_F)) {
					exchangeon_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_WINSUM_F)) {
					winsum_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_EHEAT_F)) {
					eheat_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_UPPER_ELEC)) {
					upper_elec = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_LOWER_ELEC)) {
					lower_elec = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER1_COMERR1)) {
					power1_comerr1 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_RTEMP)) {
			
					rtemp = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_ROOM_HUMI)) {
			
					room_humi = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_UPWTTEMP)) {
			
					upwttemp = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_DOWNWTTEMP)) {
			
					downwttemp = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_ROOM_CO2)) {
			
					room_co2 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_ROOM_PM25)) {
			
					room_pm25 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_WT_QUALITY)) {
			
					wt_quality = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_ROOM_VOC)) {
			
					room_voc = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER11)) {
			
					power11 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER12)) {
			
					power12 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER13)) {
			
					power13 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER14)) {
			
					power14 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER15)) {
			
					power15 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER16)) {
			
					power16 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER21)) {
			
					power21 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER22)) {
			
					power22 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER23)) {
			
					power23 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER24)) {
			
					power24 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER25)) {
			
					power25 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER26)) {
			
					power26 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER31)) {
			
					power31 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER32)) {
			
					power32 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER33)) {
			
					power33 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER34)) {
			
					power34 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER35)) {
			
					power35 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER36)) {
			
					power36 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER41)) {
			
					power41 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER42)) {
			
					power42 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER43)) {
			
					power43 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER44)) {
			
					power44 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER45)) {
			
					power45 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_POWER46)) {
			
					power46 = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_ALL_FLOW)) {
			
					all_flow = (Integer) map.get(dataKey);
				}
				if (dataKey.equals(KEY_ALL_POWER11)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power11 = (Integer) map.get(dataKey);
					} else {
						all_power11 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER12)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power12 = (Integer) map.get(dataKey);
					} else {
						all_power12 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER13)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power13 = (Integer) map.get(dataKey);
					} else {
						all_power13 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER14)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power14 = (Integer) map.get(dataKey);
					} else {
						all_power14 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER15)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power15 = (Integer) map.get(dataKey);
					} else {
						all_power15 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER16)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power16 = (Integer) map.get(dataKey);
					} else {
						all_power16 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER21)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power21 = (Integer) map.get(dataKey);
					} else {
						all_power21 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER22)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power22 = (Integer) map.get(dataKey);
					} else {
						all_power22 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER23)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power23 = (Integer) map.get(dataKey);
					} else {
						all_power23 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER24)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power24 = (Integer) map.get(dataKey);
					} else {
						all_power24 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER25)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power25 = (Integer) map.get(dataKey);
					} else {
						all_power25 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER26)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power26 = (Integer) map.get(dataKey);
					} else {
						all_power26 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER31)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power31 = (Integer) map.get(dataKey);
					} else {
						all_power31 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER32)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power32 = (Integer) map.get(dataKey);
					} else {
						all_power32 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER33)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power33 = (Integer) map.get(dataKey);
					} else {
						all_power33 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER34)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power34 = (Integer) map.get(dataKey);
					} else {
						all_power34 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER35)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power35 = (Integer) map.get(dataKey);
					} else {
						all_power35 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER36)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power36 = (Integer) map.get(dataKey);
					} else {
						all_power36 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER41)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power41 = (Integer) map.get(dataKey);
					} else {
						all_power41 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER42)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power42 = (Integer) map.get(dataKey);
					} else {
						all_power42 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER43)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power43 = (Integer) map.get(dataKey);
					} else {
						all_power43 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER44)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power44 = (Integer) map.get(dataKey);
					} else {
						all_power44 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER45)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power45 = (Integer) map.get(dataKey);
					} else {
						all_power45 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_ALL_POWER46)) {
			
					if (map.get(dataKey) instanceof Integer) {
						all_power46 = (Integer) map.get(dataKey);
					} else {
						all_power46 = (Double) map.get(dataKey);
					}
				}
				if (dataKey.equals(KEY_POWER1COMERR_F)) {
					power1comerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER1_COMERR2)) {
					power1_comerr2 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER1_COMERR3)) {
					power1_comerr3 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER1_COMERR4)) {
					power1_comerr4 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER1_COMERR5)) {
					power1_comerr5 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER1_COMERR6)) {
					power1_comerr6 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2_COMERR1)) {
					power2_comerr1 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2_COMERR2)) {
					power2_comerr2 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2_COMERR3)) {
					power2_comerr3 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2_COMERR4)) {
					power2_comerr4 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2_COMERR5)) {
					power2_comerr5 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2_COMERR6)) {
					power2_comerr6 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3_COMERR1)) {
					power3_comerr1 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3_COMERR2)) {
					power3_comerr2 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3_COMERR3)) {
					power3_comerr3 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3_COMERR4)) {
					power3_comerr4 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3_COMERR5)) {
					power3_comerr5 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3_COMERR6)) {
					power3_comerr6 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4_COMERR1)) {
					power4_comerr1 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4_COMERR2)) {
					power4_comerr2 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4_COMERR3)) {
					power4_comerr3 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4_COMERR4)) {
					power4_comerr4 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4_COMERR5)) {
					power4_comerr5 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4_COMERR6)) {
					power4_comerr6 = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER2COMERR_F)) {
					power2comerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER3COMERR_F)) {
					power3comerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_POWER4COMERR_F)) {
					power4comerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_NEWFANCOMERR_F)) {
					newfancomerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_WHJERR_F)) {
					whjerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_OVERERR_F)) {
					overerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_EHTOOTH_F)) {
					ehtooth_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_FDERR_F)) {
					fderr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_RERR_F)) {
					rerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_XQERR_F)) {
					xqerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_SUPPLYERR_F)) {
					supplyerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_PQERR_F)) {
					pqerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_COMHUMIERR_F)) {
					comhumierr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_COMCO2ERR_F)) {
					comco2err_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_EHEATCOMERR_F)) {
					eheatcomerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_UPWTERR_F)) {
					upwterr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_DOWNWTERR_F)) {
					downwterr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_FLOWCOMERR_F)) {
					flowcomerr_f = (Boolean) map.get(dataKey);			
				}
				if (dataKey.equals(KEY_WTQUALITYCOMERR_F)) {
					wtqualitycomerr_f = (Boolean) map.get(dataKey);			
				}
			}
		}

		StringBuilder sBuilder = new StringBuilder();

		// 已定义的设备报警数据点，设备发生报警后该字段有内容，没有发生报警则没内容
		if (dataMap.get("alerts") != null) {
			ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("alerts");
			for (String alertsKey : map.keySet()) {
				if ((Boolean) map.get(alertsKey)) {
					sBuilder.append("报警:" + alertsKey + "=true" + "\n");
				}
			}
		}

		// 已定义的设备故障数据点，设备发生故障后该字段有内容，没有发生故障则没内容
		if (dataMap.get("faults") != null) {
			ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("faults");
			for (String faultsKey : map.keySet()) {
				if ((Boolean) map.get(faultsKey)) {
					sBuilder.append("故障:" + faultsKey + "=true" + "\n");
				}
			}
		}

		if (sBuilder.length() > 0) {
			sBuilder.insert(0, "[设备故障或报警]\n");
			myToast(sBuilder.toString().trim());
		}

		// 透传数据，无数据点定义，适合开发者自行定义协议自行解析
		if (dataMap.get("binary") != null) {
			byte[] binary = (byte[]) dataMap.get("binary");
			Log.i("", "Binary data:" + HexStrUtils.bytesToHexString(binary));
		}
	}

	GizWifiDeviceListener gizWifiDeviceListener = new GizWifiDeviceListener() {

		/** 用于设备订阅 */
		public void didSetSubscribe(GizWifiErrorCode result, GizWifiDevice device, boolean isSubscribed) {
			GosControlModuleBaseActivity.this.didSetSubscribe(result, device, isSubscribed);
		};

		/** 用于获取设备状态 */
		public void didReceiveData(GizWifiErrorCode result, GizWifiDevice device,
				ConcurrentHashMap<String, Object> dataMap, int sn) {
			GosControlModuleBaseActivity.this.didReceiveData(result, device, dataMap, sn);
		};

		/** 用于设备硬件信息 */
		public void didGetHardwareInfo(GizWifiErrorCode result, GizWifiDevice device,
				ConcurrentHashMap<String, String> hardwareInfo) {
			GosControlModuleBaseActivity.this.didGetHardwareInfo(result, device, hardwareInfo);
		};

		/** 用于修改设备信息 */
		public void didSetCustomInfo(GizWifiErrorCode result, GizWifiDevice device) {
			GosControlModuleBaseActivity.this.didSetCustomInfo(result, device);
		};

		/** 用于设备状态变化 */
		public void didUpdateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {
			GosControlModuleBaseActivity.this.didUpdateNetStatus(device, netStatus);
		};

	};

	/**
	 * 设备订阅回调
	 * 
	 * @param result
	 *            错误码
	 * @param device
	 *            被订阅设备
	 * @param isSubscribed
	 *            订阅状态
	 */
	protected void didSetSubscribe(GizWifiErrorCode result, GizWifiDevice device, boolean isSubscribed) {
	}

	/**
	 * 设备状态回调
	 * 
	 * @param result
	 *            错误码
	 * @param device
	 *            当前设备
	 * @param dataMap
	 *            当前设备状态
	 * @param sn
	 *            命令序号
	 */
	protected void didReceiveData(GizWifiErrorCode result, GizWifiDevice device,
			ConcurrentHashMap<String, Object> dataMap, int sn) {
	}

	/**
	 * 设备硬件信息回调
	 * 
	 * @param result
	 *            错误码
	 * @param device
	 *            当前设备
	 * @param hardwareInfo
	 *            当前设备硬件信息
	 */
	protected void didGetHardwareInfo(GizWifiErrorCode result, GizWifiDevice device,
			ConcurrentHashMap<String, String> hardwareInfo) {
	}

	/**
	 * 修改设备信息回调
	 * 
	 * @param result
	 *            错误码
	 * @param device
	 *            当前设备
	 */
	protected void didSetCustomInfo(GizWifiErrorCode result, GizWifiDevice device) {
	}

	/**
	 * 设备状态变化回调
	 */
	protected void didUpdateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void myToast(String string) {
		if (mToast != null) {
			mToast.setText(string);
		} else {
			mToast = Toast.makeText(getApplicationContext(), string, Toast.LENGTH_LONG);
		}
		mToast.show();
	}

	protected void hideKeyBoard() {
		// 隐藏键盘
		((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(GosControlModuleBaseActivity.this.getCurrentFocus().getWindowToken(),
				InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	
	/**
	 *Description:显示格式化数值，保留对应分辨率的小数个数，比如传入参数（20.3656，0.01），将返回20.37
	 *@param date 传入的数值
	 *@param radio 保留多少位小数
	 *@return
	 */
	protected String formatValue(double date, Object scale) {
		if (scale instanceof Double) {
			DecimalFormat df = new DecimalFormat(scale.toString());
			return df.format(date);
		}
		return Math.round(date) + "";
	}

}