package com.kaya.pinar;

import zh.wang.android.apis.yweathergetter4a.WeatherInfo;
import zh.wang.android.apis.yweathergetter4a.YahooWeather;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherExceptionListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeatherInfoListener;
import zh.wang.android.apis.yweathergetter4a.YahooWeather.SEARCH_MODE;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainPage extends Fragment implements YahooWeatherInfoListener,
YahooWeatherExceptionListener{
	TextView text_adres,text_telefon,text_fax,text_zarf,text_saat;
	private ImageView mIvWeather0;
	private TextView mTvWeather0;
	
	private YahooWeather mYahooWeather = YahooWeather.getInstance(5000, 5000, true);

    private ProgressDialog mProgressDialog;
    
	
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View view = inflater.inflate(R.layout.activity_main_menu, container, false);
		 getActivity().setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		  mYahooWeather.setExceptionListener(this);
	        
	        mProgressDialog = new ProgressDialog(getActivity());
	        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	        mProgressDialog.show();

			mTvWeather0 = (TextView)view.findViewById(R.id.textview_weather_info_0);
			mIvWeather0 = (ImageView)view.findViewById(R.id.imageview_weather_info_0);
			
			searchByGPS();
			
		return view;
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.gc();
	}
	@Override
	public void onFailConnection(Exception e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFailParsing(Exception e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onFailFindLocation(Exception e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void gotWeatherInfo(WeatherInfo weatherInfo) {
		// TODO Auto-generated method stub
		hideProgressDialog();
		if (weatherInfo != null) {
        	
			mTvWeather0.setText("Tarih: " + weatherInfo.getCurrentConditionDate() + "\n" +
						       "Sýcaklýk : " + weatherInfo.getCurrentTempC() + "ºC\n"
					           );
			if (weatherInfo.getCurrentConditionIcon() != null) {
				mIvWeather0.setImageBitmap(weatherInfo.getCurrentConditionIcon());
			}
			
        }
		
	}

	private void searchByGPS() {
		mYahooWeather.setNeedDownloadIcons(true);
		mYahooWeather.setSearchMode(SEARCH_MODE.GPS);
		mYahooWeather.queryYahooWeatherByGPS(getActivity().getApplicationContext(), this);
	}
	
	private void showProgressDialog() {
      	if (mProgressDialog != null && mProgressDialog.isShowing()) {
      		mProgressDialog.cancel();
      	}
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
	}
	
	private void hideProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.cancel();
		}
	}
}
