package com.jrt.myheart;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ict.hifit.bean.ECGApplication;
import com.ict.hifit.bean.ECGWave;
import com.ict.hifit.bean.HeartResult;
import com.ict.hifit.util.ECGUtils;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private static int MAX_ECG = 0;
    private static float MAX_TMP = 0F;
    private static int MIN_ECG = 0;
    private static float MIN_TMP = 0F;
    private static int ECG_SAMPLE_RATE = 0;
    private static int MAX_COUNT_TIME = 30000;    
    static 
    {
        MAX_ECG = 90;
        MIN_ECG = 60;
        MAX_TMP = 37F;
        MIN_TMP = 36F;
        ECG_SAMPLE_RATE = 64;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        
        // 设置心电图数据
        setEkgWaveBeen();
        
        //Button button2EKG = (Button) findViewById(R.id.button2EKG);
        //button2EKG.setOnClickListener(button2EKGListener);
        
        Button button2EKG02 = (Button) findViewById(R.id.button2EKG02);
        button2EKG02.setOnClickListener(button2EKGListener02);
    }

	private void setEkgWaveBeen() {
		ECGApplication ecgApplication = ((ECGApplication)getApplication());
		
		Random random = new Random();
        List<Integer> ecgMVList = new LinkedList<Integer>();
         
        for (int i=0; i<20000; i++){
        	//int s = random.nextInt(65535)-32768;
        	int s = random.nextInt(3000)-1500;
        	//int s = i;
        	ecgMVList.add(s);        	
        }
        
//        ECGWave waveBean = new ECGWave(); 
//        waveBean.setWaveList(ecgMVList);
//        waveBean.setMsPointInterval(10);
//        ecgApplication.setWave(waveBean);
        Log.i("tagTest", "MAX_COUNT_TIME : " + MAX_COUNT_TIME + ", size : " + ecgMVList.size() + ", result : " + MAX_COUNT_TIME / ecgMVList.size());
        ECGWave waveBean02 = ECGUtils.waveAnalysis(ecgMVList, ECG_SAMPLE_RATE, MAX_COUNT_TIME);
        
        List<Integer> dataList = waveBean02.getWaveList();

        StringBuffer ecgMVData = new StringBuffer();
        Log.i("tagTest", "data size : " + dataList.size());
        for (Integer data : dataList){
        	Log.i("tagTest", "data : " + data);
        	ecgMVData.append(data);
        	ecgMVData.append(",");         	
        }
        
        for (Integer data : dataList){
        	Log.i("tagTest", "data : " + data);
        	ecgMVData.append(data);
        	ecgMVData.append(",");         	
        }
        
        for (Integer data : dataList){
        	Log.i("tagTest", "data : " + data);
        	ecgMVData.append(data);
        	ecgMVData.append(",");         	
        }
        
        for (Integer data : dataList){
        	Log.i("tagTest", "data : " + data);
        	ecgMVData.append(data);
        	ecgMVData.append(",");         	
        }
        
        
//        for (int i=0; i<120; i++){
//        	int s = random.nextInt(100)-50;
//        	//int s = i;
//        	ecgMVData.append(s);
//        	ecgMVData.append(",");        	
//        }
        
        Log.i("tagTest", "waveBean02.getMsPointInterval() : " + waveBean02.getMsPointInterval());
        Log.i("tagTest", "waveBean02.getValueQ() : " + waveBean02.getValueQ());
        Log.i("tagTest", "waveBean02.getValueR() : " + waveBean02.getValueR());
        Log.i("tagTest", "waveBean02.getValueS() : " + waveBean02.getValueS());
        Log.i("tagTest", "waveBean02.getValueT() : " + waveBean02.getValueT());
        
        ecgApplication.setWave(waveBean02);
        
        
        HeartResult result = new HeartResult();
        result.setEcgMVData(ecgMVData.toString());
        ecgApplication.setHeart(result);
        
	}

    // 跳转到心电图
    private OnClickListener button2EKGListener = new OnClickListener(){ 
    	@Override 
    	public void onClick(View view) {    		
    		startActivity(new Intent (MainActivity.this, ResultWaveActivity.class) );  
    	}
    }; 

    // 跳转到心电图
    private OnClickListener button2EKGListener02 = new OnClickListener(){ 
    	@Override 
    	public void onClick(View view) {    		
    		startActivity(new Intent (MainActivity.this, DetailEcgActivity.class) );  
    	}
    };
    
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
