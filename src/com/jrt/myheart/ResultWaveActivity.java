package com.jrt.myheart;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ict.hifit.bean.ECGApplication;
import com.ict.hifit.bean.ECGWave;
import com.ict.hifit.db.DBManager;

import java.util.List;

public class ResultWaveActivity extends Activity
{
  private static final String TAG = ResultWaveActivity.class.getSimpleName();
  Bitmap bitmap = null;
  Canvas canvas = null;
  DBManager db = null;
  ECGApplication ecgApplication;
  ImageView ecgImg;
  String ecgIndex = null;
  Context mContext;
  TextView mResultWave;
  Paint paintBoldGrid = null;
  Paint paintGrid = null;
  Paint paintSign = null;
  Paint paintSignArrowLine = null;
  Paint paintSignLine = null;
  Paint paintWave = null;
  int screenHeight = 500;
  int screenWidth = 720;
  ECGWave waveBean;
  List<Integer> waveList = null;

  private void drawArrow(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    double d1 = Math.atan(3.5D / 8.0D);
    double d2 = Math.sqrt(3.5D * 3.5D + 8.0D * 8.0D);
    double[] arrayOfDouble1 = rotateVec(paramInt3 - paramInt1, paramInt4 - paramInt2, d1, true, d2);
    double[] arrayOfDouble2 = rotateVec(paramInt3 - paramInt1, paramInt4 - paramInt2, -d1, true, d2);
    double d3 = paramInt3 - arrayOfDouble1[0];
    double d4 = paramInt4 - arrayOfDouble1[1];
    double d5 = paramInt3 - arrayOfDouble2[0];
    double d6 = paramInt4 - arrayOfDouble2[1];
    Double localDouble1 = new Double(d3);
    int i = localDouble1.intValue();
    Double localDouble2 = new Double(d4);
    int j = localDouble2.intValue();
    Double localDouble3 = new Double(d5);
    int k = localDouble3.intValue();
    Double localDouble4 = new Double(d6);
    int m = localDouble4.intValue();
    Path localPath = new Path();
    localPath.moveTo(paramInt3, paramInt4);
    localPath.lineTo(i, j);
    localPath.lineTo(k, m);
    localPath.close();
    this.canvas.drawPath(localPath, this.paintSignArrowLine);
  }

  private void drawEcg()
  {
    this.waveList = this.waveBean.getWaveList();
    this.waveBean.getMsPointInterval();
    this.waveBean.getValueR();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((Activity)this.mContext).getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.screenWidth = localDisplayMetrics.widthPixels;
    Log.i("tagTest", " this.waveList.size() : " +  this.waveList.size());
    Log.i("tagTest", " this.waveBean.getMsPointInterval() : " +  this.waveBean.getMsPointInterval());
    int i = this.screenWidth / this.waveList.size();
    Log.i("tagTest", " i : " +  i);
    int j = i * 40 / this.waveBean.getMsPointInterval();
    Log.i("tagTest", " j : " +  j);
    int k = this.screenWidth / j;
    int m = this.screenHeight / j;
    this.bitmap = Bitmap.createBitmap(this.screenWidth, this.screenHeight, Bitmap.Config.ARGB_8888);
    this.canvas = new Canvas(this.bitmap);
    this.paintGrid = new Paint();
    this.paintGrid.setARGB(50, 200, 10, 100);
    this.paintBoldGrid = new Paint();
    this.paintBoldGrid.setARGB(50, 200, 10, 100);
    this.paintBoldGrid.setStrokeWidth(3.0F);
    this.paintWave = new Paint();
    this.paintWave.setARGB(100, 255, 0, 0);
    this.paintWave.setStrokeWidth(2.0F);
    this.paintWave.setAntiAlias(true);
    this.paintSign = new Paint();
    this.paintSign.setARGB(100, 0, 0, 255);
    this.paintSign.setStrokeWidth(4.0F);
    this.paintSign.setTextSize(22.0F);
    this.paintSignArrowLine = new Paint();
    this.paintSignArrowLine.setARGB(100, 0, 0, 255);
    this.paintSignLine = new Paint();
    this.paintSignLine.setARGB(100, 0, 0, 255);
    DashPathEffect localDashPathEffect = new DashPathEffect(new float[] { 4.0F, 8.0F }, 1.0F);
    this.paintSignLine.setPathEffect(localDashPathEffect);
    this.paintSignLine.setStrokeWidth(4.0F);
    int n = this.screenHeight / 2;
    drawGrid(n, k, m, j);
    //drawWave(n, i, j);
    drawWaveAllPoint(n, i, j);
    //drawSign(n, i, j);
  }

  // int screenHeightHalf, int gridWidth, int gridHeight, int gridCount
  private void drawGrid(int screenHeightHalf, int gridWidth, int gridHeight, int gridCount)
  {
    Paint localPaint1 = this.paintBoldGrid;
    
    // 中间横线
    this.canvas.drawLine(0.0F, screenHeightHalf, gridWidth * gridCount, screenHeightHalf, localPaint1);
        
    // 横线
    Paint localPaint2 = null;
    for (int i=1; i<=gridCount/2; i++)
    {
  	  if (i % 5 == 0){
  		  localPaint2 = this.paintBoldGrid;
  	  } else {
  		  localPaint2 = this.paintGrid;
  	  }
      this.canvas.drawLine(0.0F, screenHeightHalf - i * gridCount, gridWidth * gridCount, screenHeightHalf - i * gridCount, localPaint2);
      this.canvas.drawLine(0.0F, screenHeightHalf + i * gridCount, gridWidth * gridCount, screenHeightHalf + i * gridCount, localPaint2);            
    }
    
    // 纵线
    Paint localPaint3 = null;
    for (int j=1; j<=gridCount; j++)
    {    	
  	  if (j % 5 == 0){
		  localPaint3 = this.paintBoldGrid;
	  } else {
		  localPaint3 = this.paintGrid;
	  }
  	  
      this.canvas.drawLine(j * gridCount, 0.0F, j * gridCount, gridHeight * gridCount, localPaint3);      
    }
    
    this.ecgImg.setImageBitmap(this.bitmap);    
  }

  private void drawSign(int paramInt1, int paramInt2, int paramInt3)
  {
    this.canvas.drawLine(0.0F, paramInt1, this.screenWidth, paramInt1, this.paintSignLine);
    int i = paramInt2 * this.waveBean.getPosPointR();
    int j = paramInt1 + paramInt3 * (-this.waveBean.getValueR() / 100);
    int k = paramInt1 + paramInt3 * (-this.waveBean.getValueR() / 200);
    this.canvas.drawLine(10.0F, j, i, j, this.paintSignLine);
    drawArrowLine(10, paramInt1, 10, j);
    String str1 = "R = " + this.waveBean.getValueR() / 1000.0F + "mv";
    this.canvas.drawText(str1, 12.0F, k, this.paintSign);
    String str2 = "" + str1 + "\n";
    int m = paramInt2 * this.waveBean.getPosPointQ();
    int n = paramInt1 + paramInt3 * (-this.waveBean.getValueQ() / 100);
    int i1 = 20 + (paramInt1 + paramInt3 * (-this.waveBean.getValueQ() / 100));
    this.canvas.drawLine(10.0F, n, m, n, this.paintSignLine);
    drawArrowLine(10, paramInt1, 10, n);
    String str3 = "Q = " + this.waveBean.getValueQ() / 1000.0F + "mv";
    this.canvas.drawText(str3, 12.0F, i1, this.paintSign);
    String str4 = str2 + str3 + "\n";
    int i2 = paramInt2 * this.waveBean.getPosPointS();
    int i3 = paramInt1 + paramInt3 * (-this.waveBean.getValueS() / 100);
    int i4 = paramInt1 + paramInt3 * (-this.waveBean.getValueS() / 200);
    this.canvas.drawLine(i2, i3, this.screenWidth, i3, this.paintSignLine);
    drawArrowLine(-122 + this.screenWidth, paramInt1, -122 + this.screenWidth, i3);
    String str5 = "S = " + this.waveBean.getValueS() / 1000.0F + "mv";
    this.canvas.drawText(str5, -120 + this.screenWidth, i4, this.paintSign);
    String str6 = str4 + str5 + "\n";
    if ((this.waveBean.getPosStartQ() > 0) && (this.waveBean.getPosEndS() > 0) && (this.waveBean.getPosEndT() > 0))
    {
      int i5 = paramInt2 * this.waveBean.getPosStartQ();
      this.canvas.drawLine(i5, paramInt1, i5, this.screenHeight, this.paintSignLine);
      int i6 = paramInt2 * this.waveBean.getPosEndT();
      this.canvas.drawLine(i6, paramInt1, i6, this.screenHeight, this.paintSignLine);
      String str7 = "QT = " + (this.waveBean.getPosEndT() - this.waveBean.getPosStartQ()) * this.waveBean.getMsPointInterval() / 1000.0F + "ms";
      drawArrowLine(i5, -50 + this.screenHeight, i6, -50 + this.screenHeight);
      this.canvas.drawText(str7, i5 + 20, -52 + this.screenHeight, this.paintSign);
      String str8 = str6 + str7 + "\n";
      this.canvas.drawLine(i5, paramInt1, i5, 0.0F, this.paintSignLine);
      int i7 = paramInt2 * this.waveBean.getPosEndS();
      this.canvas.drawLine(i7, paramInt1, i7, 0.0F, this.paintSignLine);
      String str9 = "QRS = " + (this.waveBean.getPosEndS() - this.waveBean.getPosStartQ()) * this.waveBean.getMsPointInterval() / 1000.0F + "ms";
      drawArrowLine(i5, 50, i7, 50);
      this.canvas.drawText(str9, i5 + 20, 48.0F, this.paintSign);
      str6 = str8 + str9;
    }
    this.ecgImg.setImageBitmap(this.bitmap);
    this.mResultWave.setText(str6);
  }

  private void drawWave(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2 * this.waveBean.getPosStartQ();
    int j = paramInt1;
    this.canvas.drawLine(0, paramInt1, i, j, this.paintWave);
    if (this.waveBean.getPosPointQ() != 0)
    {
      int i17 = i;
      int i18 = j;
      i = paramInt2 * this.waveBean.getPosPointQ();
      j = paramInt1 + paramInt3 * (0 - this.waveBean.getValueQ() / 100);
      this.canvas.drawLine(i17, i18, i, j, this.paintWave);
    }
    if ((this.waveBean.getPosStartR() != 0) && (this.waveBean.getPosPointQ() != this.waveBean.getPosStartR()))
    {
      int i15 = i;
      int i16 = j;
      i = paramInt2 * this.waveBean.getPosStartR();
      j = paramInt1 + paramInt3 * (0 - ((Integer)this.waveList.get(this.waveBean.getPosStartR())).intValue() / 100);
      this.canvas.drawLine(i15, i16, i, j, this.paintWave);
    }
    if (this.waveBean.getPosPointR() != 0)
    {
      int i13 = i;
      int i14 = j;
      i = paramInt2 * this.waveBean.getPosPointR();
      j = paramInt1 + paramInt3 * (0 - this.waveBean.getValueR() / 100);
      this.canvas.drawLine(i13, i14, i, j, this.paintWave);
    }
    if ((this.waveBean.getPosEndR() != 0) && (this.waveBean.getPosPointS() != this.waveBean.getPosEndR()))
    {
      int i11 = i;
      int i12 = j;
      i = paramInt2 * this.waveBean.getPosEndR();
      j = paramInt1 + paramInt3 * (0 - ((Integer)this.waveList.get(this.waveBean.getPosEndR())).intValue() / 100);
      this.canvas.drawLine(i11, i12, i, j, this.paintWave);
    }
    if (this.waveBean.getPosPointS() != 0)
    {
      int i9 = i;
      int i10 = j;
      i = paramInt2 * this.waveBean.getPosPointS();
      j = paramInt1 + paramInt3 * (0 - this.waveBean.getValueS() / 100);
      this.canvas.drawLine(i9, i10, i, j, this.paintWave);
    }
    if ((this.waveBean.getPosEndS() != 0) && (this.waveBean.getPosPointT() != this.waveBean.getPosEndS()))
    {
      int i7 = i;
      int i8 = j;
      i = paramInt2 * this.waveBean.getPosEndS();
      j = paramInt1 + paramInt3 * (0 - ((Integer)this.waveList.get(this.waveBean.getPosEndS())).intValue() / 100);
      this.canvas.drawLine(i7, i8, i, j, this.paintWave);
    }
    if (this.waveBean.getPosPointT() != 0)
    {
      int i5 = i;
      int i6 = j;
      i = paramInt2 * this.waveBean.getPosPointT();
      j = paramInt1 + paramInt3 * (0 - this.waveBean.getValueT() / 100);
      this.canvas.drawLine(i5, i6, i, j, this.paintWave);
    }
    if ((this.waveBean.getPosEndT() != 0) && (this.waveBean.getPosPointT() != this.waveBean.getPosEndT()))
    {
      int i3 = i;
      int i4 = j;
      i = paramInt2 * this.waveBean.getPosEndT();
      j = paramInt1 + paramInt3 * (0 - ((Integer)this.waveList.get(this.waveBean.getPosEndT())).intValue() / 100);
      this.canvas.drawLine(i3, i4, i, j, this.paintWave);
    }
    if (j != paramInt1)
    {
      int i1 = i;
      int i2 = j;
      i++;
      j = paramInt1;
      this.canvas.drawLine(i1, i2, i, j, this.paintWave);
    }
    int k = i;
    int m = j;
    int n = this.screenWidth;
    this.canvas.drawLine(k, m, n, paramInt1, this.paintWave);
    this.ecgImg.setImageBitmap(this.bitmap);
  }

  private void drawWaveAllPoint(int paramInt1, int paramInt2, int paramInt3)
  {
    for (int i = 1; ; i++)
    {
      if (i >= this.waveList.size())
      {
        this.ecgImg.setImageBitmap(this.bitmap);
        return;
      }
      int j = paramInt2 * (i - 1);
      int k = i * paramInt2;
      int m = paramInt1 + paramInt3 * (0 - ((Integer)this.waveList.get(i - 1)).intValue() / 100);
      int n = paramInt1 + paramInt3 * (0 - ((Integer)this.waveList.get(i)).intValue() / 100);
      this.canvas.drawLine(j, m, k, n, this.paintWave);
    }
  }

  private void getData()
  {
    this.ecgApplication = ((ECGApplication)getApplication());
    this.ecgApplication.getHeart();
    this.waveBean = this.ecgApplication.getWave();
    if (this.waveBean != null)
      drawEcg();
  }

  public void drawArrowLine(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.canvas.drawLine(paramInt1, paramInt2, paramInt3, paramInt4, this.paintSignArrowLine);
    drawArrow(paramInt1, paramInt2, paramInt3, paramInt4);
    drawArrow(paramInt3, paramInt4, paramInt1, paramInt2);
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.mContext = this;
    getActionBar().setDisplayShowHomeEnabled(false);
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setDisplayUseLogoEnabled(false);
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_result_wave);
    this.ecgImg = ((ImageView)findViewById(R.id.result_ecg_wave_img));
    this.mResultWave = ((TextView)findViewById(R.id.result_wave));
    getData();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public double[] rotateVec(int paramInt1, int paramInt2, double paramDouble1, boolean paramBoolean, double paramDouble2)
  {
    double[] arrayOfDouble = new double[2];
    double d1 = paramInt1 * Math.cos(paramDouble1) - paramInt2 * Math.sin(paramDouble1);
    double d2 = paramInt1 * Math.sin(paramDouble1) + paramInt2 * Math.cos(paramDouble1);
    if (paramBoolean)
    {
      double d3 = Math.sqrt(d1 * d1 + d2 * d2);
      double d4 = paramDouble2 * (d1 / d3);
      double d5 = paramDouble2 * (d2 / d3);
      arrayOfDouble[0] = d4;
      arrayOfDouble[1] = d5;
    }
    return arrayOfDouble;
  }
}