package com.ict.hifit.bean;

import java.util.List;

public class ECGWave
{
  int msPointInterval;
  int posEndR;
  int posEndS;
  int posEndT;
  int posPointQ;
  int posPointR;
  int posPointS;
  int posPointT;
  int posStartQ;
  int posStartR;
  int valueQ;
  int valueR;
  int valueS;
  int valueT;
  List<Integer> waveList;

  public int getMsPointInterval()
  {
    return this.msPointInterval;
  }

  public int getPosEndR()
  {
    return this.posEndR;
  }

  public int getPosEndS()
  {
    return this.posEndS;
  }

  public int getPosEndT()
  {
    return this.posEndT;
  }

  public int getPosPointQ()
  {
    return this.posPointQ;
  }

  public int getPosPointR()
  {
    return this.posPointR;
  }

  public int getPosPointS()
  {
    return this.posPointS;
  }

  public int getPosPointT()
  {
    return this.posPointT;
  }

  public int getPosStartQ()
  {
    return this.posStartQ;
  }

  public int getPosStartR()
  {
    return this.posStartR;
  }

  public int getValueQ()
  {
    return this.valueQ;
  }

  public int getValueR()
  {
    return this.valueR;
  }

  public int getValueS()
  {
    return this.valueS;
  }

  public int getValueT()
  {
    return this.valueT;
  }

  public List<Integer> getWaveList()
  {
    return this.waveList;
  }

  public void setMsPointInterval(int paramInt)
  {
    this.msPointInterval = paramInt;
  }

  public void setPosEndR(int paramInt)
  {
    this.posEndR = paramInt;
  }

  public void setPosEndS(int paramInt)
  {
    this.posEndS = paramInt;
  }

  public void setPosEndT(int paramInt)
  {
    this.posEndT = paramInt;
  }

  public void setPosPointQ(int paramInt)
  {
    this.posPointQ = paramInt;
  }

  public void setPosPointR(int paramInt)
  {
    this.posPointR = paramInt;
  }

  public void setPosPointS(int paramInt)
  {
    this.posPointS = paramInt;
  }

  public void setPosPointT(int paramInt)
  {
    this.posPointT = paramInt;
  }

  public void setPosStartQ(int paramInt)
  {
    this.posStartQ = paramInt;
  }

  public void setPosStartR(int paramInt)
  {
    this.posStartR = paramInt;
  }

  public void setValueQ(int paramInt)
  {
    this.valueQ = paramInt;
  }

  public void setValueR(int paramInt)
  {
    this.valueR = paramInt;
  }

  public void setValueS(int paramInt)
  {
    this.valueS = paramInt;
  }

  public void setValueT(int paramInt)
  {
    this.valueT = paramInt;
  }

  public void setWaveList(List<Integer> paramList)
  {
    this.waveList = paramList;
  }
}
