package com.jrt.myheart;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ict.hifit.bean.Disease;
import com.ict.hifit.bean.ECGApplication;
import com.ict.hifit.bean.Family;
import com.ict.hifit.bean.HealthResult;
import com.ict.hifit.bean.HeartResult;
import com.ict.hifit.db.DBManager;
import com.ict.hifit.util.Util;
import com.ict.hifit.view.EcgView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DetailEcgActivity extends Activity
  implements View.OnClickListener
{
  private static final String TAG = DetailEcgActivity.class.getSimpleName();
  String apiKey = null;  
  DBManager db = null;
  ArrayList<Disease> diseaseList = null;
  ECGApplication ecgApp = null;
  String ecgIndex = null;
  List<Integer> ecgMVDataList = null;
  EcgView ecgView;
  String familyId = null;
  String familyName = null;
  int from = 0;

  HealthResult healthResult = null;
  ImageView imgRelaxationStar;
  ImageView imgRuler;
  ImageView imgRulerPoint;
  String key = null;
  Context mContext;

  float mx;
  float my;
String tag = "tagTest";

  private void analyzeData(HeartResult paramHeartResult)
  {
	  Log.i(tag, "analyzeData start");
    if (paramHeartResult != null)
    {
      int i = paramHeartResult.getHeartRate();

      if (paramHeartResult.getDataStatus() == 3)
        Util.showToast(this.mContext, 2131362090, 0);
    }
    else
    {
      return;
    }
    handleEcgData(paramHeartResult.getEcgMVData());
    
    Log.i(tag, "analyzeData end");
  }

  private void drawEcg()
  {
	  Log.i(tag, "drawEcg start");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)this.mContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    
    int i = localDisplayMetrics.widthPixels;
    int j = localDisplayMetrics.heightPixels;
    if(ecgMVDataList != null && ecgMVDataList.size() != 0)
    {
        int mvDataListSize = ecgMVDataList.size();
        int l = (j * 2) / 3;
        int i1 = ecgView.getPointNumInSize(i, l);
        int j1;
        int k1;
        if(i1 > 0)
            j1 = i * (int)Math.ceil((double)mvDataListSize / (double)i1);
        else
            j1 = i * 2;
        ecgViewInit(j1, l, true);
        
        Log.i(tag, "mvDataListSize : " + mvDataListSize);
        
        k1 = 0;
        while(k1 < mvDataListSize) 
        {
            ecgView.updatePaint(((Integer)ecgMVDataList.get(k1)).intValue());
            k1++;
        }
    }    
    
    Log.i(tag, "drawEcg end");
  }

  private void ecgViewInit(int i1, int m, boolean b) {
      try{    	  
          Class<EcgView> cls=EcgView.class;  
          //访问私有变量  
          Field field=cls.getDeclaredField("isMultiPages");  
          field.setAccessible(true);  
          field.set(ecgView, b);
          
          ecgView.initParams(i1, m);
          
          Paint paint = new Paint();
  	      paint.setColor(getResources().getColor(R.color.color_ecg_line));
  	      paint.setStrokeWidth(2.0F);
  	      
  	      Field fieldPaint=cls.getDeclaredField("paint");  
  	      fieldPaint.setAccessible(true); 
  	      fieldPaint.set(ecgView, paint);
        
  	    Paint paintGrid = new Paint();
  	    paintGrid.setColor(getResources().getColor(R.color.color_ecg_grid));
  	    
  	    Field fieldPaintGrid=cls.getDeclaredField("paintGrid");  
  	    fieldPaintGrid.setAccessible(true); 
  	  	fieldPaintGrid.set(ecgView, paintGrid);
  	    
  	  	Paint paintBoldGrid = new Paint();
  	    paintBoldGrid.setColor(getResources().getColor(R.color.color_ecg_big_grid));
  	    paintBoldGrid.setStrokeWidth(2.0F);
  	    
  	    Field fieldPaintBoldGrid=cls.getDeclaredField("paintBoldGrid");  
	    fieldPaintBoldGrid.setAccessible(true); 
	  	fieldPaintBoldGrid.set(ecgView, paintBoldGrid);
	  	
	  	Paint paintBaseLine = new Paint();
	  	paintBaseLine.setColor(getResources().getColor(R.color.color_ecg_baseline));
  	    paintBaseLine.setStrokeWidth(2.0F);
  	    
  	    Field fieldPaintBaseLine=cls.getDeclaredField("paintBaseLine");  
	    fieldPaintBaseLine.setAccessible(true); 
	  	fieldPaintBaseLine.set(ecgView, paintBaseLine);
          
          //获得私有方法  
          Method method=cls.getDeclaredMethod("paintNewGrid");  
          //设置私有方法可以被访问  
          method.setAccessible(true);  
          //调用私有方法  
          method.invoke(ecgView);  
                      
      }catch(Exception ex){  
          Log.e(tag, "myecgViewInit error" + ex); 
      }
	
}

private void getData()
  {
	  Log.i(tag, "getData start");
    this.ecgApp = ((ECGApplication)getApplication());
    if ((this.ecgApp != null) && (this.ecgApp.getFamilyList() != null) && (this.ecgApp.getFamilyList().size() > 0))
    {
      Family localFamily = (Family)this.ecgApp.getFamilyList().get(this.ecgApp.getCurrentFamilyIndex());
      this.familyId = localFamily.getFamilyId();
      this.familyName = localFamily.getFamilyName();

      String str2 = String.valueOf(35);


    }
    HeartResult localHeartResult = this.ecgApp.getHeart();
    if (localHeartResult == null)
      return;
    this.healthResult = this.ecgApp.getHealthResult();
    if (this.healthResult != null)
    {
      this.diseaseList = this.healthResult.getDiseaseList();
    }

    analyzeData(localHeartResult);
    
    Log.i(tag, "getData end");
  }

  public void handleEcgData(String paramString)
  {
	  Log.i(tag, "handleEcgData start");
    this.ecgMVDataList = Util.splitEcgData2List(paramString);
    drawEcg();
    
    Log.i(tag, "handleEcgData end");
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131296285:
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.mContext = this;
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_detail_ecg);
    
    this.ecgView = ((EcgView)findViewById(R.id.detail_ecgView));    
    getData();
  }

  protected void onDestroy()
  {
    if (this.db != null)
      this.db.closeDB();
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default:
      return super.onOptionsItemSelected(paramMenuItem);
    case 16908332:
    }
    finish();
    return true;
  }
}