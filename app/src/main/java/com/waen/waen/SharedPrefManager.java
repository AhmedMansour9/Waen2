package com.waen.waen;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ahmed on 19/12/2018.
 */

public class SharedPrefManager {
    private static SharedPrefManager mInstance;
        private static Context mCtx;
    private static final String SHARED_PREF_NAME = "LoginUser";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean saveUserToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user_token", token);
        editor.apply();
        return true;
    }

    public String getUserToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString("user_token", null);
    }
    public boolean saveStartTrip(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Start", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("value", key);
        editor.apply();
        return true;
    }

    public String getStartTrip(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Start", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("value", null);
    }

    public boolean saveBackup(String Backup){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Backup", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("backup", Backup);
        editor.apply();
        return true;
    }

    public String getbackup(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Backup", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("backup", null);
    }

    public boolean saveReturn(String Backup){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Return", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("return", Backup);
        editor.apply();
        return true;
    }

    public String getReturn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Return", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("return", null);
    }

    public boolean saveRole(String Role){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Role", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("role", Role);
        editor.apply();
        return true;
    }

    public String getRole(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Role", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("role", null);
    }

    public boolean saveId(String Id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Id", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", Id);
        editor.apply();
        return true;
    }

    public String getId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("Id", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("id", null);
    }
    public boolean saveBusName(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("BUSNAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("busname", key);
        editor.apply();
        return true;
    }

    public String getBuseName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("BUSNAME", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("busname", null);
    }

    public boolean saveBusNumber(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("BUSNUMBER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bus_number", key);
        editor.apply();
        return true;
    }

    public String getBusNumber(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("BUSNUMBER", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("bus_number", null);
    }

    public boolean saveBuscapcity(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("BUSCAPCITY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("bus_capcity", key);
        editor.apply();
        return true;
    }

    public String getBuscapcity(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("BUSCAPCITY", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("bus_capcity", null);
    }

    public boolean saveBusNumberStudent(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("NUMBERSTUDENT", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("number_student", key);
        editor.apply();
        return true;
    }

    public String getBusNumberSteudent(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("NUMBERSTUDENT", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("number_student", null);
    }

    public boolean saveDriverName(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("DRIVERNAME", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("drivername", key);
        editor.apply();
        return true;
    }

    public String getDriverName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("DRIVERNAME", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("drivername", null);
    }

    public boolean saveUserTokenAdmin(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("TOKENADMIN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token_admin", key);
        editor.apply();
        return true;
    }

    public String getUserTokenAdmin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("TOKENADMIN", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("token_admin", null);
    }
    public boolean saveKey(String key){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("KEY", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", key);
        editor.apply();
        return true;
    }

    public String getKey(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("KEY", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("key", null);
    }
}
