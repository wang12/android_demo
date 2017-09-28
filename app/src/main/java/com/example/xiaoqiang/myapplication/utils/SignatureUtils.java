package com.example.xiaoqiang.myapplication.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;

import java.security.MessageDigest;

/**
 * Created by xiaoqiang on 2017/9/22.
 */

public class SignatureUtils {
    public static String getSingInfo(Context paramContext) {
        try {
            PackageInfo localPackageInfo = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 64);
            Signature[] arrayOfSignature = localPackageInfo.signatures;
            Signature localSignature = arrayOfSignature[0];
            return hexdigest(localSignature.toByteArray());
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    private static final char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String hexdigest(byte[] paramArrayOfByte) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramArrayOfByte);
            byte[] arrayOfByte = localMessageDigest.digest();
            char[] arrayOfChar = new char[32];
            int i = 0;
            int j = 0;
            for (; ; ) {
                if (i >= 16) {
                    return new String(arrayOfChar);
                }
                int k = arrayOfByte[i];
                int m = j + 1;
                arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
                j = m + 1;
                arrayOfChar[m] = hexDigits[(k & 0xF)];
                i++;
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }
}
