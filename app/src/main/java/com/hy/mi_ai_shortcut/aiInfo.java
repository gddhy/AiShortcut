package com.hy.mi_ai_shortcut;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class aiInfo {
    public static boolean hasMiAi(Context context){
        String MiAiPackage = "com.miui.voiceassist";
        boolean isInstalled = hasApplication(context,MiAiPackage);
        if(isInstalled){
            String aiVer = getVersionName(context,MiAiPackage);
            try {
                return compareVersion(aiVer, "4.2") >= 0;
            }
            catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }

    }

    /**
     * 获取当前包名信息
     * 这些信息是从相对应的Androdimanifest.xml
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context, String packageName) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(packageName,
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 版本名
     * @param context
     * @return
     */
    private static String getVersionName(Context context,String packageName) {
        return getPackageInfo(context,packageName).versionName;
    }

    /**
     * 外部名比较 versionName
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     * @param version1
     * @param version2
     * @return
     */
    private static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception();
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用.；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0)
        {
            //再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    //判断是否安装某个应用
    private static boolean hasApplication(Context context, String packageName){
        PackageManager packageManager = context.getPackageManager();

        //获取系统中安装的应用包的信息
        List<PackageInfo> listPackageInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < listPackageInfo.size(); i++) {
            if(listPackageInfo.get(i).packageName.equalsIgnoreCase(packageName)){
                return true;
            }
        }
        return false;

    }
}
