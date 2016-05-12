package com.xeehoo.health.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.BindingAdapter;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.xeehoo.health.BrainApplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AssetsUtils {
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader =
                    new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null){
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @BindingAdapter("app:imageUrl")
    public static void setImageUrl(ImageView view, String url) {
        Log.e("--imageloader", "---" + url);
        ImageLoader.getInstance().displayImage(url, view);
    }

    public static String getKey(Context context){
        final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        Log.e("token", "device id is " + tm.getDeviceId());
        String key = tm.getDeviceId();
        if (key == null || "".equals(key)){
            key = "20160418";
        }

        return key;
    }

    public static byte[] encrypt(String key, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), "DESede");
            // 实例化Cipher
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            return cipher.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }

        return null;
    }

    public static byte[] decrypt(String key, byte[] src) {
        try {
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), "DESede");
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 根据字符串生成密钥24位的字节数组
     *
     * @param keyStr
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] build3DesKey(String keyStr)
            throws UnsupportedEncodingException {
        byte[] key = new byte[24];
        byte[] temp = keyStr.getBytes("UTF-8");

        if (key.length > temp.length) {
            System.arraycopy(temp, 0, key, 0, temp.length);
        } else {
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    public static void saveParas(Context context){
//        String key = getKey(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("ydzc", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        byte[] info = encrypt(BrainApplication.mobile, BrainApplication.token.getBytes());
        editor.putString("key", Base64.encodeToString(info, Base64.DEFAULT));
        editor.putString("mobile", BrainApplication.mobile);
        editor.putBoolean("account", BrainApplication.isAccount);
        editor.commit();
    }

    public static void clearParas(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ydzc", context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

    public static void initParas(Context context){
//        String key = getKey(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("ydzc", context.MODE_PRIVATE);
        String mobile = sharedPreferences.getString("mobile", "0");
        if ("0".equals(mobile)){
            return;
        }

        String val = sharedPreferences.getString("key", "0");
        if ("0".equals(val)){
            return;
        }

        byte[] info = Base64.decode(val, Base64.DEFAULT);
        byte[] r = decrypt(mobile, info);
//        Log.e("encrypt", new String(r));

        BrainApplication.token = new String(r);
        BrainApplication.mobile = mobile;
        BrainApplication.isAccount = sharedPreferences.getBoolean("account", false);
        if ("0".equals(BrainApplication.mobile)){
            BrainApplication.isLogin = false;
        }
        else{
            BrainApplication.isLogin = true;
        }
    }
}
