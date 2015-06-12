package com.ict.hifit.bean;

public class Disease
{
  private String name = null;
  private int position = -1;
  private DiseaseType type;

  public String getName()
  {
    return this.name;
  }

  public int getPosition()
  {
    return this.position;
  }

  public DiseaseType getType()
  {
    return this.type;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPosition(int paramInt)
  {
    this.position = paramInt;
  }

  public void setType(DiseaseType paramDiseaseType)
  {
    this.type = paramDiseaseType;
  }
}