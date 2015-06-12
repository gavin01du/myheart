package com.ict.hifit.bean;

public class Family
{
  String birth;
  String city;
  String createTime;
  String familyId;
  String familyName;
  int height;
  String medicalHistory;
  int sex;
  String userId;
  int weight;

  private boolean equalsString(String paramString1, String paramString2)
  {
    return equalsString(paramString1, paramString2, false);
  }

  private boolean equalsString(String paramString1, String paramString2, boolean paramBoolean)
  {
    if ((paramString1 == null) && (paramString2 == null))
      return true;
    if ((paramString1 == null) || (paramString2 == null))
      return false;
    if (paramBoolean)
      return paramString1.equalsIgnoreCase(paramString2);
    return paramString1.equals(paramString2);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null)
      return false;
    try
    {
      Family localFamily = (Family)paramObject;
      if (!equalsString(this.userId, localFamily.getUserId()))
        return false;
      if (!equalsString(this.familyId, localFamily.getFamilyId()))
        return false;
      if (!equalsString(this.familyName, localFamily.getFamilyName()))
        return false;
      if (!equalsString(this.birth, localFamily.getBirth()))
        return false;
      if (!equalsString(this.city, localFamily.getCity()))
        return false;
      if (!equalsString(this.medicalHistory, localFamily.getMedicalHistory()))
        return false;
      if (this.sex != localFamily.getSex())
        return false;
      if (this.height != localFamily.getHeight())
        return false;
      int i = this.weight;
      int j = localFamily.getWeight();
      if (i != j)
        return false;
      boolean bool = true;
      return bool;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        boolean bool = false;
      }
    }
  }

  public String getBirth()
  {
    return this.birth;
  }

  public String getCity()
  {
    return this.city;
  }

  public String getCreateTime()
  {
    return this.createTime;
  }

  public String getFamilyId()
  {
    return this.familyId;
  }

  public String getFamilyName()
  {
    return this.familyName;
  }

  public int getHeight()
  {
    return this.height;
  }

  public String getMedicalHistory()
  {
    return this.medicalHistory;
  }

  public int getSex()
  {
    return this.sex;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public int getWeight()
  {
    return this.weight;
  }

  public void setBirth(String paramString)
  {
    this.birth = paramString;
  }

  public void setCity(String paramString)
  {
    this.city = paramString;
  }

  public void setCreateTime(String paramString)
  {
    this.createTime = paramString;
  }

  public void setFamilyId(String paramString)
  {
    this.familyId = paramString;
  }

  public void setFamilyName(String paramString)
  {
    this.familyName = paramString;
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setMedicalHistory(String paramString)
  {
    this.medicalHistory = paramString;
  }

  public void setSex(int paramInt)
  {
    this.sex = paramInt;
  }

  public void setUserId(String paramString)
  {
    this.userId = paramString;
  }

  public void setWeight(int paramInt)
  {
    this.weight = paramInt;
  }
}