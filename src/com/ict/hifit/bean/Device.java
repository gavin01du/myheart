package com.ict.hifit.bean;

public class Device
{
  String deviceAddress = null;
  String deviceName = null;

  public String getDeviceAddress()
  {
    return this.deviceAddress;
  }

  public String getDeviceName()
  {
    return this.deviceName;
  }

  public void setDeviceAddress(String paramString)
  {
    this.deviceAddress = paramString;
  }

  public void setDeviceName(String paramString)
  {
    this.deviceName = paramString;
  }
}