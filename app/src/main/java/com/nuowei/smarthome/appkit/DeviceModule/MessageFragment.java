package com.nuowei.smarthome.appkit.DeviceModule;

import com.nuowei.smarthome.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		
		View contextView = inflater.inflate(R.layout.activity_gos_shared_list, container, false);
		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}
