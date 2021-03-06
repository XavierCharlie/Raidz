package com.example.scoops;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.Response;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";
    public static final String PASSWORD = "PASSWORD";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();

    }

    public void createSession(String name, String email, String id){
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ID, id);
        editor.apply();
    }

    public boolean isLoginV(){return sharedPreferences.getBoolean(LOGIN, false);}

    public void checkLoginV(){
        if (!this.isLoginV()){
            Intent intent = new Intent(context, Login.class);
            context.startActivity(intent);
            ((VolunteerProfile) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID, sharedPreferences.getString(ID, null));

        return user;
    }

    public void logoutV(){
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, Login.class);
        context.startActivity(intent);
        ((VolunteerProfile) context).finish();
    }

}
