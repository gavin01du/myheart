package com.ict.hifit.bean;

public class ScreenInfo
{
  float density;
  int heightPixels;
  int widthPixels;

  public float getDensity()
  {
    return this.density;
  }

  public int getHeightPixels()
  {
    return this.heightPixels;
  }

  public int getWidthPixels()
  {
    return this.widthPixels;
  }

  public void setDensity(float paramFloat)
  {
    this.density = paramFloat;
  }

  public void setHeightPixels(int paramInt)
  {
    this.heightPixels = paramInt;
  }

  public void setWidthPixels(int paramInt)
  {
    this.widthPixels = paramInt;
  }
}