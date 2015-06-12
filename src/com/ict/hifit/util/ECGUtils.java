package com.ict.hifit.util;

import android.util.Log;

import com.ict.hifit.bean.ECGWave;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ECGUtils
{
  private static int ECG_SAMPLE_RATE = 64;
  private static final String TAG = CopyOfECGUtils0611.class.getSimpleName();
  
  public static ECGWave waveAnalysis(List<Integer> paramList, int paramInt)
  {
    return waveAnalysis(paramList, ECG_SAMPLE_RATE, paramInt);
  }
  
  public static ECGWave waveAnalysis(List<Integer> dataList, int rate, int maxCountTime)
  {
	
    int dataSize = dataList.size();
    Log.i("tagTest", "waveAnalysis paramList.size() : " + dataSize);
    
    ECG_SAMPLE_RATE = rate;
    if ((dataList == null) || (dataSize == 0) || (maxCountTime <= 0) || (dataSize < 2 * ECG_SAMPLE_RATE)) {
      return null;
    }
    int interval = maxCountTime / dataSize;
    int k = 2 * ECG_SAMPLE_RATE;
    int m = (dataSize - k) / 2;
    ArrayList localArrayList = new ArrayList();
    int n = m;
    int qValue= 0;
    int rValue= 0;
    int sValue= 0;
    int tValue= 0;
    int qPos= 0;
    int rStart= 0;
    int rPos= 0;
    int rEnd= 0;
    int sPos= 0;
    int tPos= 0;
    int i11= 0;
    Iterator localIterator = null;
    if (n >= m + k)
    {
      qValue = 0;
      rValue = 0;
      sValue = 0;
      tValue = 0;
      qPos = 0;
      rStart = 0;
      rPos = 0;
      rEnd = 0;
      sPos = 0;
      tPos = 0;
      i11 = 0;
      localIterator = localArrayList.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        Log.d(TAG, "R value:%d, R pos:%d \n" + rValue + "," + rPos);
        if (rValue != 0) {
            return null;
        }

        localArrayList.add((Integer)dataList.get(n));
        n++;
        break;
      }
      Integer localInteger1 = (Integer)localIterator.next();
      Log.d(TAG, "pos:" + localInteger1);
      if (localInteger1.intValue() > rValue)
      {
        rValue = localInteger1.intValue();
        rPos = i11;
      }
      i11++;
    }

    ECGWave localECGWave = new ECGWave();
    localECGWave.setValueR(rValue);
    localECGWave.setPosPointR(rPos);
    int i12 = 0;
    int i13 = rPos;
    int i14 = localArrayList.size();
    int endS = 0;
    if (i13 >= i14) {}
    for (;;)
    {
      Log.d(TAG, "S value:%d, S pos:%d ,endR:%d ,endS:%d\n" + sValue + "," + sPos + "," + rEnd + "," + endS);
      if (sValue != 0) {
    	  break;
      }
      
      Integer localInteger2 = (Integer)localArrayList.get(i13);
      if ((localInteger2.intValue() > 0) && (i12 == 0)) {}
      label448:
      do
      {
        for (;;)
        {
          i13++;

          if (i12 == 0)
          {
            rEnd = i13;
            i12 = 1;
            sPos = i13;
            sValue = localInteger2.intValue();
          }
          else
          {
            if (localInteger2.intValue() >= sValue) {
              break label448;
            }
            sPos = i13;
            sValue = localInteger2.intValue();
          }
        }
      } while ((localInteger2.intValue() <= 0) || (sValue >= 0));
      endS = i13;
    }

    localECGWave.setValueS(sValue);
    localECGWave.setPosPointS(sPos);
    localECGWave.setPosEndR(rEnd);
    localECGWave.setPosEndS(endS);
    int i16 = endS + 1;
    int i17 = localArrayList.size();
    int tEnd = 0;
    int i19 =0;
    int i20 =0;
    int qStart = 0;
    if (i16 >= i17)
    {
      Log.d(TAG, "T value:%d, T pos:%d ,endT:%d \n" + tValue + "," + tPos + "," + tEnd);
      localECGWave.setValueT(tValue);
      localECGWave.setPosPointT(tPos);
      localECGWave.setPosEndT(tEnd);
      i19 = 0;
      i20 = rPos - 1;
      qStart = 0;
      if (i20 >= 0) {
        return null;
      }
    }
    for (;;)
    {
      Log.d(TAG, "Q value:%d, Q pos:%d ,startQ:%d ,startR:%d\n" + qValue + "," + qPos + "," + qStart + "," + rStart);
      if (qValue != 0) {
    	  return null;
      }
      
      Integer localInteger3 = (Integer)localArrayList.get(i16);
      if (localInteger3.intValue() > tValue)
      {
        tValue = localInteger3.intValue();
        tPos = i16;
      }
      if (localInteger3.intValue() < 0)
      {
        tEnd = i16;
        break;
      }
      i16++;

      Integer localInteger4 = (Integer)localArrayList.get(i20);
      if ((localInteger4.intValue() > 0) && (i19 == 0)) {}
      label801:
      do
      {
        for (;;)
        {
          i20--;
          if (i19 == 0)
          {
            rStart = i20;
            i19 = 1;
            qPos = i20;
            qValue = localInteger4.intValue();
          }
          else
          {
            if (localInteger4.intValue() >= qValue) {
              break label801;
            }
            qPos = i20;
            qValue = localInteger4.intValue();
          }
        }
      } while ((localInteger4.intValue() <= 0) || (qValue >= 0));
      qStart = i20;
    }
    localECGWave.setValueQ(qValue);
    localECGWave.setPosPointQ(qPos);
    localECGWave.setPosStartQ(qStart);
    localECGWave.setPosStartR(rStart);
    localECGWave.setMsPointInterval(interval);
    localECGWave.setWaveList(localArrayList);
    return localECGWave;
  }
}
