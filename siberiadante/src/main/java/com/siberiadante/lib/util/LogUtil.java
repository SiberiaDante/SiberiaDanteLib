package com.siberiadante.lib.util;

import android.os.Environment;
import android.util.Config;
import android.util.Log;

import com.siberiadante.lib.SiberiaDanteLib;
import com.siberiadante.lib.constants.AppInfo;
import com.siberiadante.lib.exception.SiberiaDanteLibException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Created SiberiaDante
 * @Describe： 日志打印工具类.应用Log日志对APP的信息泄露极为致命，所以项目上线时需要关闭项目整体的Log日志输出
 * @Time: 2017/8/7
 * @Email: 994537867@qq.com
 * @GitHub: https://github.com/SiberiaDante
 * can use
 */

public class LogUtil {
    public LogUtil() {
        new SiberiaDanteLibException();
    }

    public static String TAG = "SiberiaDante";
    private static boolean isDebug = AppInfo.getInstance().isDebug();


    public static void setTag(String tag) {
        LogUtil.TAG = tag;
    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void i(String tag, String msg) {
        if (isDebug)
            Log.i(tag, msg);
    }


    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (isDebug)
            Log.d(tag, msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        if (isDebug)
            Log.e(tag, msg);
    }

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void v(String tag, String msg) {
        if (isDebug)
            Log.v(tag, msg);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, msg);
    }

    public static void w(String tag, String msg) {
        if (isDebug)
            Log.w(tag, msg);
    }

    /**
     * 带时间戳日志，级别i
     *
     * @param tag
     * @param msg
     */
    public static void printTimeLogI(String tag, String msg) {
        d(tag + "[" + DateUtil.getSDFTime(DateUtil.getTimeStamp()) + "]:" + msg);
    }

    /**
     * 带时间戳日志，级别d
     *
     * @param tag
     * @param msg
     */
    public static void printTimeLogD(String tag, String msg) {
        d(tag + "[" + DateUtil.getSDFTime(DateUtil.getTimeStamp()) + "]:" + msg);
    }

    /**
     * 带时间戳日志，级别e
     *
     * @param tag
     * @param msg
     */
    public static void printTimeLogE(String tag, String msg) {
        d(tag + "[" + DateUtil.getSDFTime(DateUtil.getTimeStamp()) + "]:" + msg);
    }

    /**
     * 带时间戳日志，级别v
     *
     * @param tag
     * @param msg
     */
    public static void printTimeLogV(String tag, String msg) {
        d(tag + "[" + DateUtil.getSDFTime(DateUtil.getTimeStamp()) + "]:" + msg);
    }

    /**
     * 带时间戳日志，级别w
     *
     * @param tag
     * @param msg
     */
    public static void printTimeLogW(String tag, String msg) {
        d(tag + "[" + DateUtil.getSDFTime(DateUtil.getTimeStamp()) + "]:" + msg);
    }

    /**
     * 分段打印出较长log文本
     *
     * @param logContent 打印文本
     * @param showLength 规定每段显示的长度（AndroidStudio控制台打印log的最大信息量大小为4k）
     * @param tag        打印log的标记
     */
    public static void showLargeLog(String tag, String logContent, int showLength) {
        if (logContent.length() > showLength) {
            String show = logContent.substring(0, showLength);
            i(tag, show);
            /*剩余的字符串如果大于规定显示的长度，截取剩余字符串进行递归，否则打印结果*/
            if ((logContent.length() - showLength) > showLength) {
                String partLog = logContent.substring(showLength, logContent.length());
                showLargeLog(tag, partLog, showLength);
            } else {
                String printLog = logContent.substring(showLength, logContent.length());
                i(tag, printLog);
            }

        } else {
            i(tag, logContent);
        }
    }

    public static void eFile(String info) {
        long timestamp = System.currentTimeMillis();
        String time = DateUtil.getSDFTime(DateUtil.getTimeStamp());
        String fileName = SiberiaDanteLib.getContext().getPackageName() + "crash-" + time + "-" + timestamp + ".log";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String path = "/sdcard/crash/";
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                fos.write(info.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
