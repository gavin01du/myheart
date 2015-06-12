package com.ict.hifit.bean;

import java.util.ArrayList;

public class HealthResult
{
  private int QRS = 0;
  private int RR = 0;
  private String advice = null;
  private int diseaseCount = 0;
  private ArrayList<Disease> diseaseList = null;
  private int heartRate = 0;
  private String result = null;
  private int score = 0;

  public String getAdvice()
  {
    return this.advice;
  }

  public int getDiseaseCount()
  {
    return this.diseaseCount;
  }

  public ArrayList<Disease> getDiseaseList()
  {
    return this.diseaseList;
  }

  public int getHeartRate()
  {
    return this.heartRate;
  }

  public int getQRS()
  {
    return this.QRS;
  }

  public int getRR()
  {
    return this.RR;
  }

  public String getResult()
  {
    return this.result;
  }

  public int getScore()
  {
    return this.score;
  }

  public void setAdvice(String paramString)
  {
    this.advice = paramString;
  }

  public void setDiseaseCount(int paramInt)
  {
    this.diseaseCount = paramInt;
  }

  public void setDiseaseList(ArrayList<Disease> paramArrayList)
  {
    this.diseaseList = paramArrayList;
  }

  public void setHeartRate(int paramInt)
  {
    this.heartRate = paramInt;
  }

  public void setQRS(int paramInt)
  {
    this.QRS = paramInt;
  }

  public void setRR(int paramInt)
  {
    this.RR = paramInt;
  }

  public void setResult(String paramString)
  {
    this.result = paramString;
  }

  public void setScore(int paramInt)
  {
    this.score = paramInt;
  }
}