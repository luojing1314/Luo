package com.luo.choice.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.luo.choice.R;
import com.luo.choice.activity.MainActivity;
import com.luo.choice.data.LoadData;

public class LoaddingFragment extends Fragment implements View.OnClickListener{
	private RotateAnimation rotate;
	private View ll_loadding;
	private View ll_load_error;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_loadding_page,null);
		Button btn_load_again = (Button) view.findViewById(R.id.btn_load_again);
		btn_load_again.setOnClickListener(this);
		ll_loadding = view.findViewById(R.id.ll_loadding);
		ll_loadding.setVisibility(View.VISIBLE);
		ll_load_error = view.findViewById(R.id.ll_load_error);
		ImageView iv_loadding = (ImageView) view.findViewById(R.id.iv_loadding);
		rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		rotate.setDuration(500);
		rotate.setRepeatCount(Animation.INFINITE);
		iv_loadding.startAnimation(rotate);
		return view;
	}
	public void setErrorLayout(){
		ll_loadding.setVisibility(View.INVISIBLE);
		ll_load_error.setVisibility(View.VISIBLE);
	}
	public void setLoaddingLayout(){
		ll_loadding.setVisibility(View.VISIBLE);
		ll_load_error.setVisibility(View.INVISIBLE);
	}
	//按钮的点击事件
	@Override
	public void onClick(View v){
		MainActivity mainActivity = (MainActivity) getActivity();
		switch (MainActivity.mCurrentSelected){
			case MainActivity.TEXT_PAGE:
				if(mainActivity.getLoaddingFragment()!=null){
					mainActivity.getLoaddingFragment().setLoaddingLayout();
				}else{
					mainActivity.showLoaddingFragment(true);
				}
				LoadData.getDataFromServer(MainActivity.TEXT_PAGE,mainActivity);
				break;
			case MainActivity.PICTURE_PAGE:
				if(mainActivity.getLoaddingFragment()!=null){
					mainActivity.getLoaddingFragment().setLoaddingLayout();
				}else{
					mainActivity.showLoaddingFragment(true);
				}
				LoadData.getDataFromServer(MainActivity.PICTURE_PAGE,mainActivity);
				break;
			case MainActivity.VIDEO_PAGE:
				if(mainActivity.getLoaddingFragment()!=null){
					mainActivity.getLoaddingFragment().setLoaddingLayout();
				}else{
					mainActivity.showLoaddingFragment(true);
				}
				LoadData.getDataFromServer(MainActivity.VIDEO_PAGE,mainActivity);
				break;
		}
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(rotate!=null){
			rotate.cancel();
			rotate = null;
		}
	}

}
