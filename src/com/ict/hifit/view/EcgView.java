package com.ict.hifit.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.ict.hifit.bean.ECGContent;
import com.ict.hifit.ecg.EcgActivity;
import com.ict.hifit.util.GattUtils;
import com.ict.hifit.util.Log;
import java.util.ArrayList;

public class EcgView extends ImageView
{
  private static final float BASE_ZOOM_FACTOR = 1.0F;
  private static final int ECG_BASE_LINE = 50;
  private static final float ECG_POINTS_INTERVAL_TIME = 8.0F;
  private static final float ECG_POINTS_INTERVAL_UNIT_NUM = 0.2F;
  private static final float ECG_UNIT_TIME = 40.0F;
  private static final float ECG_UNIT_VOLTAGE = 0.1F;
  private static final int ECG_VIEW_VALUE = 123456;
  private static final int MAX_Y_UNIT_NUM = 50;
  private static final String TAG = EcgView.class.getSimpleName();
  private static final int VALID_DATA_SIZE = 19;
  private static final float zoomFactor = 1.0F;
  int MAX_VOLTAGE = 10000;
  private float baseLineY;
  private Bitmap bitmap = null;
  private Canvas canvas = null;
  private int changedEcgValue;
  String curTime = null;
  private float ecgPointsIntervalWidth;
  private float ecgUnitSize;
  private float ecgVoltage;
  public Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 123456)
        EcgView.this.updatePaint(paramAnonymousMessage.arg1);
    }
  };
  boolean hasStartedGattReceiver = false;
  boolean isMultiPages = false;
  int lastTemperature = 0;
  private Context mContext;
  private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if ("com.ict.bluetooth.le.ACTION_DATA_AVAILABLE".equals(paramAnonymousIntent.getAction()))
      {
        String str = paramAnonymousIntent.getStringExtra("com.ict.bluetooth.le.EXTRA_DATA");
        EcgView.this.parserData(str);
      }
    }
  };
  int monitorCount = 0;
  private int orgEcgValue = 50;
  private Paint paint = null;
  private Paint paintBaseLine = null;
  private Paint paintBoldGrid = null;
  private Paint paintGrid = null;
  int parserCount = 0;
  private int screenHeight = 0;
  private int screenWidth = 0;
  boolean startMoveMonitor = true;
  private float startX = 0.0F;
  private float startY = 0.0F;
  float stopX = 0.0F;
  float stopY = 0.0F;
  int unTouchCount = 0;
  private int viewHeight;
  private int viewWidth;
  private int xUnitNum;
  private int yUnitNum = 50;

  public EcgView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
  }

  public EcgView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  public EcgView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
  }

  private void add2Bitmap()
  {
    this.canvas.save(31);
    this.canvas.restore();
  }

  private void drawGrid(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Paint localPaint1 = this.paintBoldGrid;
    this.canvas.drawLine(0.0F, paramFloat1, paramFloat2 * paramFloat4, paramFloat1, localPaint1);
    int i = 1;
    int j = 0;
    if (i > paramFloat3 / 2.0F)
    {
      j = 0;
      if (j >= paramFloat2)
        setImageBitmap(this.bitmap);
    }
    else
    {
      if (i % 5 == 0);
      for (Paint localPaint2 = this.paintBoldGrid; ; localPaint2 = this.paintGrid)
      {
        this.canvas.drawLine(0.0F, paramFloat1 - paramFloat4 * i, paramFloat2 * paramFloat4, paramFloat1 - paramFloat4 * i, localPaint2);
        this.canvas.drawLine(0.0F, paramFloat1 + paramFloat4 * i, paramFloat2 * paramFloat4, paramFloat1 + paramFloat4 * i, localPaint2);
        i++;
        break;
      }
    }
    if (j % 5 == 0);
    for (Paint localPaint3 = this.paintBoldGrid; ; localPaint3 = this.paintGrid)
    {
      this.canvas.drawLine(paramFloat4 * j, 0.0F, paramFloat4 * j, paramFloat3 * paramFloat4, localPaint3);
      j++;
      break;
    }
  }

  private void initPaint()
  {
    this.paint = new Paint();
    this.paint.setColor(getResources().getColor(2131165247));
    this.paint.setStrokeWidth(2.0F);
    this.paintGrid = new Paint();
    this.paintGrid.setColor(getResources().getColor(2131165248));
    this.paintBoldGrid = new Paint();
    this.paintBoldGrid.setColor(getResources().getColor(2131165249));
    this.paintBoldGrid.setStrokeWidth(2.0F);
    this.paintBaseLine = new Paint();
    this.paintBaseLine.setColor(getResources().getColor(2131165250));
    this.paintBaseLine.setStrokeWidth(2.0F);
  }

  private void paintNewGrid()
  {
    this.bitmap = Bitmap.createBitmap(this.viewWidth, this.viewHeight, Bitmap.Config.ARGB_8888);
    this.canvas = new Canvas(this.bitmap);
    this.canvas.drawColor(-1);
    this.bitmap.setHasAlpha(true);
    this.startX = 0.0F;
    drawGrid(this.baseLineY, this.xUnitNum, this.yUnitNum, this.ecgUnitSize);
  }

  public int getPointNumInSize(int paramInt1, int paramInt2)
  {
    initParams(paramInt1, paramInt2);
    return 1 + (int)(this.viewWidth / this.ecgPointsIntervalWidth);
  }

  public void init(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.isMultiPages = paramBoolean;
    initParams(paramInt1, paramInt2);
    initPaint();
    paintNewGrid();
  }

  public void initParams(int paramInt1, int paramInt2)
  {
    this.viewWidth = paramInt1;
    this.viewHeight = paramInt2;
    Log.d(TAG, "screensize:" + paramInt1 + "," + paramInt2);
    this.ecgUnitSize = (this.viewHeight / this.yUnitNum);
    this.xUnitNum = Math.round(this.viewWidth / this.ecgUnitSize);
    this.ecgPointsIntervalWidth = (0.2F * this.ecgUnitSize);
    this.baseLineY = (this.viewHeight / 2);
    this.startY = this.baseLineY;
  }

  public void parserData(String paramString)
  {
    ECGContent localECGContent = GattUtils.parserECGContent(paramString, 19);
//    if (localECGContent != null)
//    {
//      if (localECGContent.getErrMsg() == null)
//        break label29;
//      Log.d(TAG, localECGContent.getErrMsg());
//    }
//    while (true)
//    {
//      return;
//      label29: if (localECGContent.getOrgEcgValue() != null)
//        for (int i = 0; i < localECGContent.getOrgEcgValue().size(); i++)
//        {
//          this.orgEcgValue = ((Integer)localECGContent.getOrgEcgValue().get(i)).intValue();
//          this.changedEcgValue = (-Math.round(0.22F * this.orgEcgValue));
//          this.handler.obtainMessage(123456, this.changedEcgValue, 0).sendToTarget();
//        }
//    }
  }

  public void startECG()
  {
    paintNewGrid();
//    this.mContext.registerReceiver(this.mGattUpdateReceiver, EcgActivity.makeGattUpdateIntentFilter());
//    this.hasStartedGattReceiver = true;
  }

  public void stopECG()
  {
    try
    {
      if (this.hasStartedGattReceiver)
        this.mContext.unregisterReceiver(this.mGattUpdateReceiver);
      this.hasStartedGattReceiver = false;
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void updatePaint(int paramInt)
  {
    this.ecgVoltage = (paramInt / 1000.0F);
    this.stopY = (this.baseLineY - 1.0F * (this.ecgVoltage * this.ecgUnitSize / 0.1F));
    if (this.startX > this.viewWidth)
    {
      if (!this.isMultiPages)
        return;
      add2Bitmap();
      paintNewGrid();
    }
    this.stopX = (this.startX + this.ecgPointsIntervalWidth);
    this.canvas.drawLine(this.startX, this.startY, this.stopX, this.stopY, this.paint);
    this.startX = this.stopX;
    this.startY = this.stopY;
    setImageBitmap(this.bitmap);
  }

  public void zoomIn()
  {
    if (this.yUnitNum < 100)
    {
      this.yUnitNum = (10 + this.yUnitNum);
      init(this.viewWidth, this.viewHeight, this.isMultiPages);
    }
  }

  public void zoomOut()
  {
    if (this.yUnitNum > 10)
    {
      this.yUnitNum = (-10 + this.yUnitNum);
      init(this.viewWidth, this.viewHeight, this.isMultiPages);
    }
  }
}