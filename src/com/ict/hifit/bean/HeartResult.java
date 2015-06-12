package com.ict.hifit.bean;

public class HeartResult
{
  public double age;
  public boolean checked;
  public int dataStatus = 0;
  String ecgMVData;
  public String familyId;
  public String familyName;
  public int heartRate = -1;
  public String imgIndex;
  public String key;
  public String measureDate;
  public String measureTime;
  public int relaxation;
  public int risk;
  public double sdnn;
  public String userId;

  public double getAge()
  {
    return this.age;
  }

  public int getDataStatus()
  {
    return this.dataStatus;
  }

  public String getEcgMVData()
  {
    return this.ecgMVData;
  }

  public String getFamilyId()
  {
    return this.familyId;
  }

  public String getFamilyName()
  {
    return this.familyName;
  }

  public int getHeartRate()
  {
    return this.heartRate;
  }

  public String getImgIndex()
  {
    return this.imgIndex;
  }

  public String getKey()
  {
    return this.measureDate + "T" + this.measureTime;
  }

  public String getMeasureDate()
  {
    return this.measureDate;
  }

  public String getMeasureTime()
  {
    return this.measureTime;
  }

  public int getRelaxation()
  {
    return this.relaxation;
  }

  public int getRisk()
  {
    return this.risk;
  }

  public double getSdnn()
  {
    return this.sdnn;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public boolean isChecked()
  {
    return this.checked;
  }

  public void setAge(double paramDouble)
  {
    this.age = paramDouble;
  }

  public void setChecked(boolean paramBoolean)
  {
    this.checked = paramBoolean;
  }

  public void setDataStatus(int paramInt)
  {
    this.dataStatus = paramInt;
  }

  public void setEcgMVData(String paramString)
  {
    this.ecgMVData = paramString;
  }

  public void setFamilyId(String paramString)
  {
    this.familyId = paramString;
  }

  public void setFamilyName(String paramString)
  {
    this.familyName = paramString;
  }

  public void setHeartRate(int paramInt)
  {
    this.heartRate = paramInt;
  }

  public void setImgIndex(String paramString)
  {
    this.imgIndex = paramString;
  }

  public void setKey(String paramString)
  {
    this.key = paramString;
  }

  public void setMeasureDate(String paramString)
  {
    this.measureDate = paramString;
  }

  public void setMeasureTime(String paramString)
  {
    this.measureTime = paramString;
  }

  public void setRelaxation(int paramInt)
  {
    this.relaxation = paramInt;
  }

  public void setRisk(int paramInt)
  {
    this.risk = paramInt;
  }

  public void setSdnn(double paramDouble)
  {
    this.sdnn = paramDouble;
  }

  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }
}