package com.ict.hifit.bean;

import java.util.ArrayList;

public class ECGContent
{
  int data3Dx;
  int data3Dy;
  int data3Dz;
  String errMsg;
  int heart;
  ArrayList<Integer> orgEcgValue;
  int temperature;
  boolean touchFlag;

  public int getData3Dx()
  {
    return this.data3Dx;
  }

  public int getData3Dy()
  {
    return this.data3Dy;
  }

  public int getData3Dz()
  {
    return this.data3Dz;
  }

  public String getErrMsg()
  {
    return this.errMsg;
  }

  public int getHeart()
  {
    return this.heart;
  }

  public ArrayList<Integer> getOrgEcgValue()
  {
    return this.orgEcgValue;
  }

  public int getTemperature()
  {
    return this.temperature;
  }

  public boolean isTouchFlag()
  {
    return this.touchFlag;
  }

  public void setData3Dx(int paramInt)
  {
    this.data3Dx = paramInt;
  }

  public void setData3Dy(int paramInt)
  {
    this.data3Dy = paramInt;
  }

  public void setData3Dz(int paramInt)
  {
    this.data3Dz = paramInt;
  }

  public void setErrMsg(String paramString)
  {
    this.errMsg = paramString;
  }

  public void setHeart(int paramInt)
  {
    this.heart = paramInt;
  }

  public void setOrgEcgValue(ArrayList<Integer> paramArrayList)
  {
    this.orgEcgValue = paramArrayList;
  }

  public void setTemperature(int paramInt)
  {
    this.temperature = paramInt;
  }

  public void setTouchFlag(boolean paramBoolean)
  {
    this.touchFlag = paramBoolean;
  }
}