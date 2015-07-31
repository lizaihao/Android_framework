package com.android.libcore.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import com.android.libcore.application.RootApplication;
import com.android.libcore.log.L;

/**
 * Description: 基础工具类，该类的所有函数如下所示(所有添加到该类的函数都应该在这标识出来，以防重复)
 * <ol>
 *     <li>{@link #dp2px(float)}用来将dp转换为px</li>
 *     <li>{@link #px2dp(float)}用来将px转换为dp</li>
 *     <li>{@link #isNetworkAvailable()}用来判断网络是否可用</li>
 *     <li>{@link #isNetworkWifi()}用来判断网络是否是wifi</li>
 *     <li>{@link }</li>
 * </ol>
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2015-07-14
 */
public class CommonUtils {

    public static int dp2px(float dp){
        final float scale = RootApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dp(float px) {
        final float scale = RootApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static boolean isNetworkAvailable(){
        ConnectivityManager cm = (ConnectivityManager) RootApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info!=null && info.isAvailable();
    }

    public static boolean isNetworkWifi(){
        if (!isNetworkAvailable()){
            L.e("当前网络可用，请先调用isNetworkAvailable()函数");
        }
        ConnectivityManager cm = (ConnectivityManager) RootApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info.getType() == ConnectivityManager.TYPE_WIFI)
            return true;
        return false;
    }

    /**
     * 检查手机是否会有虚拟底部navigation bar
     */
    public static boolean hasNavigationBar(){
        boolean hasMenuKey = ViewConfiguration.get(RootApplication.getInstance()).hasPermanentMenuKey();
        boolean hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK);

        if(!hasMenuKey && !hasBackKey) {
            return true;
        }
        return false;
    }
}
