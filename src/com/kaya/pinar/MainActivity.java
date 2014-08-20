package com.kaya.pinar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity{
	Button etkinliklistele,sanatharitasi,arama,ben,iletisim;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		etkinliklistele = (Button)findViewById(R.id.etkinliklistele);
        sanatharitasi = (Button)findViewById(R.id.sanatharitasi);
        arama = (Button)findViewById(R.id.arama);
        ben = (Button)findViewById(R.id.ben);
        iletisim = (Button)findViewById(R.id.iletisim);
        
        etkinliklistele.setBackgroundDrawable(getResources().getDrawable(R.drawable.etkinliklistele_over));
        
		Fragment newFragment = new MainPage();
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_place, newFragment);
		transaction.addToBackStack(null); 
		transaction.commit();
		
	}
	
	@SuppressWarnings("deprecation")
	public void SetPageTabs(View view) {
		ResetButtons();
		
		Fragment newFragment = null; 

		if (view == findViewById(R.id.etkinliklistele)) {
			etkinliklistele.setBackgroundDrawable(getResources().getDrawable(R.drawable.etkinliklistele_over));
			newFragment = new MainPage();
		} else if (view == findViewById(R.id.sanatharitasi)) {
			sanatharitasi.setBackgroundDrawable(getResources().getDrawable(R.drawable.sanatharitasi_over));
			newFragment = new Iletisim();
		}else if (view == findViewById(R.id.arama)) {
			arama.setBackgroundDrawable(getResources().getDrawable(R.drawable.arama_over));
			newFragment = new Iletisim();
		}else if (view == findViewById(R.id.ben)) {
			ben.setBackgroundDrawable(getResources().getDrawable(R.drawable.ben_over));
			newFragment = new Iletisim();
			
		}else if (view == findViewById(R.id.iletisim)) {
			iletisim.setBackgroundDrawable(getResources().getDrawable(R.drawable.iletisim_over));
			newFragment = new Iletisim();
		}

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_place, newFragment);
		transaction.addToBackStack(null); 
		transaction.commit();
	}
	
	@SuppressWarnings("deprecation")
	private void ResetButtons() {
		// TODO Auto-generated method stub
		etkinliklistele.setBackgroundDrawable(getResources().getDrawable(R.drawable.etkinliklistele));
		sanatharitasi.setBackgroundDrawable(getResources().getDrawable(R.drawable.sanatharitasi));
		arama.setBackgroundDrawable(getResources().getDrawable(R.drawable.arama));
		ben.setBackgroundDrawable(getResources().getDrawable(R.drawable.ben));
		iletisim.setBackgroundDrawable(getResources().getDrawable(R.drawable.iletisim));
	}
}
