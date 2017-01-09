package com.example.user.seproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by Gabi on 1/9/2017.
 */

public class SaveSharedPreferences
{
    static final String PREF_USER_NAME="";
    static final String PREF_CAR_COLOR="";
    static final String PREF_CAR_MODEL="";
    static final String PREF_USER_INSURANCE_NUMBER="";
    static final String PREF_USER_PHONE_NUMBER="";
    static final String PREF_USER_REGISTRATION_NUMBER="";


    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setCarColor(Context ctx, String color)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_CAR_COLOR, color);
        editor.commit();
    }
    public static void setPrefCarModel(Context ctx, String carModel)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_CAR_MODEL, carModel);
        editor.commit();
    }

    public static void setInsuranceNumber(Context ctx, String insuranceNumber)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_INSURANCE_NUMBER, insuranceNumber);
        editor.commit();
    }

    public static void setUserPhoneNumber(Context ctx, String phoneNumber)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PHONE_NUMBER, phoneNumber);
        editor.commit();
    }

    public static void setRegistrationNumber(Context ctx, String regNr)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_REGISTRATION_NUMBER, regNr);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        Log.d("SaveSharedPreference",getSharedPreferences(ctx).getString(PREF_USER_NAME,""));
        return getSharedPreferences(ctx).getString(PREF_USER_NAME,"");
    }

    public static String getCarColor(Context ctx)
    {
        Log.d("SaveSharedPreference",getSharedPreferences(ctx).getString(PREF_CAR_COLOR,""));
        return getSharedPreferences(ctx).getString(PREF_CAR_COLOR,"");
    }

    public static String getCarModel(Context ctx)
    {
        Log.d("SaveSharedPreference",getSharedPreferences(ctx).getString(PREF_CAR_MODEL,""));
        return getSharedPreferences(ctx).getString(PREF_CAR_MODEL,"");
    }

    public static String getUserInsuranceNumber(Context ctx)
    {
        Log.d("SaveSharedPreference",getSharedPreferences(ctx).getString(PREF_USER_INSURANCE_NUMBER,""));
        return getSharedPreferences(ctx).getString(PREF_USER_INSURANCE_NUMBER,"");
    }

    public static String getPhoneNumber(Context ctx)
    {
        Log.d("SaveSharedPreference",getSharedPreferences(ctx).getString(PREF_USER_PHONE_NUMBER,""));
        return getSharedPreferences(ctx).getString(PREF_USER_PHONE_NUMBER,"");
    }

    public static String getUserRegistrationNumber(Context ctx)
    {
        Log.d("SaveSharedPreference",getSharedPreferences(ctx).getString(PREF_USER_REGISTRATION_NUMBER,""));
        return getSharedPreferences(ctx).getString(PREF_USER_REGISTRATION_NUMBER,"");
    }

}
