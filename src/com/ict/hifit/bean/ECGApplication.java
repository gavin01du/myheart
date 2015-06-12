package com.ict.hifit.bean;

import android.app.Application;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ECGApplication extends Application
{
  String apiKey = null;
  int currentFamilyIndex = -1;
  Device device = null;
  HashMap<String, String> deviceMap = null;
  ArrayList<Disease> diseaseList = null;
  List<Family> familyList;
  HealthResult healthResult = null;
  HeartResult heart = null;
  boolean isNetAvailable = true;
  boolean isOffLine = false;
  boolean isShowLowBatteryTip = true;
  ScreenInfo screenInfo = null;
  String userName = null;
  String userPassword = null;
  String userPhone = null;
  ECGWave wave = null;

  public static void main(String[] paramArrayOfString)
  {
  }

  public String getApiKey()
  {
    return this.apiKey;
  }

  public int getCurrentFamilyIndex()
  {
    return this.currentFamilyIndex;
  }

  public Device getDevice()
  {
    return this.device;
  }

  public HashMap<String, String> getDeviceMap()
  {
    return this.deviceMap;
  }

  public List<Family> getFamilyList()
  {
    return this.familyList;
  }

  public HealthResult getHealthResult()
  {
    return this.healthResult;
  }

  public HeartResult getHeart()
  {
    return this.heart;
  }

  public ScreenInfo getScreenInfo()
  {
    return this.screenInfo;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public String getUserPassword()
  {
    return this.userPassword;
  }

  public String getUserPhone()
  {
    return this.userPhone;
  }

  public ECGWave getWave()
  {
    return this.wave;
  }

  public boolean isNetAvailable()
  {
    return this.isNetAvailable;
  }

  public boolean isOffLine()
  {
    return this.isOffLine;
  }

  public boolean isShowLowBatteryTip()
  {
    return this.isShowLowBatteryTip;
  }

  public void onCreate()
  {
    super.onCreate();
    this.apiKey = null;
    this.wave = null;
    this.heart = null;
  }

  public void setApiKey(String paramString)
  {
    this.apiKey = paramString;
  }

  public void setCurrentFamilyIndex(int paramInt)
  {
    this.currentFamilyIndex = paramInt;
  }

  public void setDevice(Device paramDevice)
  {
    this.device = paramDevice;
  }

  public void setDeviceMap(HashMap<String, String> paramHashMap)
  {
    this.deviceMap = paramHashMap;
  }

  public void setFamilyList(List<Family> paramList)
  {
    this.familyList = paramList;
  }

  public void setHealthResult(HealthResult paramHealthResult)
  {
    this.healthResult = paramHealthResult;
  }

  public void setHeart(HeartResult paramHeartResult)
  {
    this.heart = paramHeartResult;
  }

  public void setNetAvailable(boolean paramBoolean)
  {
    this.isNetAvailable = paramBoolean;
  }

  public void setOffLine(boolean paramBoolean)
  {
    this.isOffLine = paramBoolean;
  }

  public void setScreenInfo(ScreenInfo paramScreenInfo)
  {
    this.screenInfo = paramScreenInfo;
  }

  public void setShowLowBatteryTip(boolean paramBoolean)
  {
    this.isShowLowBatteryTip = paramBoolean;
  }

  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }

  public void setUserPassword(String paramString)
  {
    this.userPassword = paramString;
  }

  public void setUserPhone(String paramString)
  {
    this.userPhone = paramString;
  }

  public void setWave(ECGWave paramECGWave)
  {
    this.wave = paramECGWave;
  }
}