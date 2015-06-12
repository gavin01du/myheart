package com.ict.hifit.util;

import com.ict.hifit.bean.ECGContent;
import java.util.ArrayList;
import java.util.UUID;

public class GattUtils
{
  public static String ERR_MSG_INVALID_DATA = "00/C8 data error";
  public static String ERR_MSG_INVALID_LEN = "invalid data length";
  public static final int FORMAT_FLOAT = 52;
  public static final int FORMAT_SFLOAT = 50;
  public static final int FORMAT_SINT16 = 34;
  public static final int FORMAT_SINT32 = 36;
  public static final int FORMAT_SINT8 = 33;
  public static final int FORMAT_UINT16 = 18;
  public static final int FORMAT_UINT32 = 20;
  public static final int FORMAT_UINT8 = 17;
  private static int SENSOR_STATE_NOTOUCH = 0;
  private static int SENSOR_STATE_TOUCHED = 0;
  public static final long leastSigBits = -9223371485494954757L;

  private static int add(byte paramByte1, byte paramByte2)
  {
    return (paramByte1 & 0xFF) + ((paramByte2 & 0xFF) << 8);
  }

  private static int add(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    return (paramByte1 & 0xFF) + ((paramByte2 & 0xFF) << 8) + ((paramByte3 & 0xFF) << 16) + ((paramByte4 & 0xFF) << 24);
  }

  public static Float getFloatValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null);
    while (paramInt2 + (paramInt1 & 0xF) > paramArrayOfByte.length)
      return null;
    switch (paramInt1)
    {
    case 51:
    default:
      return null;
    case 50:
      int m = paramArrayOfByte[(paramInt2 + 1)];
      int n = signed((0xFF & paramArrayOfByte[paramInt2]) + ((0xF & (m & 0xFF)) << 8), 12);
      int i1 = signed((m & 0xFF) >> 4, 4);
      return Float.valueOf((float)(n * Math.pow(10.0D, i1)));
    case 52:
    }
    int i = paramArrayOfByte[(paramInt2 + 3)];
    int j = paramArrayOfByte[(paramInt2 + 2)];
    int k = paramArrayOfByte[(paramInt2 + 1)];
    return Float.valueOf((float)(signed((0xFF & paramArrayOfByte[paramInt2]) + ((k & 0xFF) << 8) + ((j & 0xFF) << 16), 24) * Math.pow(10.0D, i)));
  }

  public static Integer getHighFirstIntValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null);
    while (paramInt2 + (paramInt1 & 0xF) > paramArrayOfByte.length)
      return null;
    switch (paramInt1)
    {
    default:
      return null;
    case 18:
      return Integer.valueOf(add(paramArrayOfByte[(paramInt2 + 1)], paramArrayOfByte[paramInt2]));
    case 34:
    }
    return Integer.valueOf(signed(add(paramArrayOfByte[(paramInt2 + 1)], paramArrayOfByte[paramInt2]), 16));
  }

  public static Integer getIntValue(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null);
    while (paramInt2 + (paramInt1 & 0xF) > paramArrayOfByte.length)
      return null;
    switch (paramInt1)
    {
    default:
      return null;
    case 17:
      return Integer.valueOf(0xFF & paramArrayOfByte[paramInt2]);
    case 18:
      return Integer.valueOf(add(paramArrayOfByte[paramInt2], paramArrayOfByte[(paramInt2 + 1)]));
    case 20:
      return Integer.valueOf(add(paramArrayOfByte[paramInt2], paramArrayOfByte[(paramInt2 + 1)], paramArrayOfByte[(paramInt2 + 2)], paramArrayOfByte[(paramInt2 + 3)]));
    case 33:
      return Integer.valueOf(signed(0xFF & paramArrayOfByte[paramInt2], 8));
    case 34:
      return Integer.valueOf(signed(add(paramArrayOfByte[paramInt2], paramArrayOfByte[(paramInt2 + 1)]), 16));
    case 36:
    }
    return Integer.valueOf(signed(add(paramArrayOfByte[paramInt2], paramArrayOfByte[(paramInt2 + 1)], paramArrayOfByte[(paramInt2 + 2)], paramArrayOfByte[(paramInt2 + 3)]), 32));
  }

  public static String getStringValue(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramArrayOfByte == null);
    while (paramInt > paramArrayOfByte.length)
      return null;
    byte[] arrayOfByte = new byte[paramArrayOfByte.length - paramInt];
    for (int i = 0; ; i++)
    {
      if (i == paramArrayOfByte.length - paramInt)
        return new String(arrayOfByte);
      arrayOfByte[i] = paramArrayOfByte[(paramInt + i)];
    }
  }

  public static byte[] hexByteArrayToByteArray(byte[] paramArrayOfByte)
  {
    return hexStringToByteArray(new String(paramArrayOfByte));
  }

  public static byte[] hexStringToByteArray(String paramString)
  {
    int i = paramString.length();
    byte[] arrayOfByte = new byte[i / 2];
    for (int j = 0; ; j += 2)
    {
      if (j >= i)
        return arrayOfByte;
      arrayOfByte[(j / 2)] = ((byte)((Character.digit(paramString.charAt(j), 16) << 4) + Character.digit(paramString.charAt(j + 1), 16)));
    }
  }

  public static ECGContent parserECGContent(String paramString, int paramInt)
  {
	  return null;
  }
//  {
//    ECGContent localECGContent = null;
//    if (paramString != null)
//    {
//      int i = paramString.length();
//      localECGContent = null;
//      if (i != 0)
//        break label19;
//    }
//    label19:; 
//    byte[] arrayOfByte;
//    int j;
//    do
//    {
//      return localECGContent;
//      arrayOfByte = hexStringToByteArray(paramString.replaceAll("\\s", ""));
//      j = arrayOfByte.length;
//      localECGContent = null;
//    }
//    while (j == 0);
//    localECGContent = new ECGContent();
//    Integer localInteger = Integer.valueOf(0xFF & arrayOfByte[0]);
//    if (localInteger.intValue() == paramInt)
//    {
//      int k = Integer.valueOf(0xFF & arrayOfByte[(-6 + localInteger.intValue())]).intValue();
//      if (k == SENSOR_STATE_NOTOUCH)
//      {
//        localECGContent.setTouchFlag(false);
//        return localECGContent;
//      }
//      if (k == SENSOR_STATE_TOUCHED)
//      {
//        localECGContent.setTouchFlag(true);
//        localECGContent.setTemperature(Integer.valueOf(add(arrayOfByte[(-3 + localInteger.intValue())], arrayOfByte[(-4 + localInteger.intValue())])).intValue());
//        localECGContent.setHeart(Integer.valueOf(0xFF & arrayOfByte[(-5 + localInteger.intValue())]).intValue());
//        localECGContent.setData3Dx(getIntValue(arrayOfByte, 33, -2 + localInteger.intValue()).intValue());
//        localECGContent.setData3Dy(getIntValue(arrayOfByte, 33, -1 + localInteger.intValue()).intValue());
//        localECGContent.setData3Dz(getIntValue(arrayOfByte, 33, localInteger.intValue()).intValue());
//        int m = -6 + localInteger.intValue();
//        ArrayList localArrayList = new ArrayList();
//        for (int n = 1; ; n += 2)
//        {
//          if (n >= m)
//          {
//            if (localArrayList.size() <= 0)
//              break;
//            localECGContent.setOrgEcgValue(localArrayList);
//            return localECGContent;
//          }
//          localArrayList.add(Integer.valueOf(Integer.valueOf(signed(add(arrayOfByte[(n + 1)], arrayOfByte[n]), 16)).intValue()));
//        }
//      }
//      localECGContent.setErrMsg(ERR_MSG_INVALID_DATA);
//      return localECGContent;
//    }
//    localECGContent.setErrMsg(ERR_MSG_INVALID_LEN);
//    return localECGContent;
//  }

  private static int signed(int paramInt1, int paramInt2)
  {
    if ((paramInt1 & 1 << paramInt2 - 1) != 0)
      paramInt1 = -1 * ((1 << paramInt2 - 1) - (paramInt1 & -1 + (1 << paramInt2 - 1)));
    return paramInt1;
  }

  public static UUID toUuid(long paramLong)
  {
    return new UUID(0x1000 | paramLong << 32, -9223371485494954757L);
  }

  public static UUID toUuid(String paramString)
  {
    return UUID.fromString(paramString);
  }

  public static String toUuid128(long paramLong)
  {
    return toUuid(paramLong).toString();
  }

  public static String toUuid16(int paramInt)
  {
    return Integer.toHexString(paramInt);
  }
}