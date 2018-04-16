package cr.ac.unadeca.galeriaproyecto.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

import cr.ac.unadeca.galeriaproyecto.activities.LoginActivity;
import cr.ac.unadeca.galeriaproyecto.activities.MainActivity;
import cr.ac.unadeca.galeriaproyecto.subclase.UserInfo;

/**
 * Created by pc on 11/4/2018.
 */

public class Session {
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "GaleriaPref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_USERINFO = "user_info";

    // User name (make variable public to access from outside)
    public static final String KEY_USER_ID = "id";

    // Email address (make variable public to access from outside)
    public static final String KEY_USER_NAME = "name";
    public Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createLoginSession(long id, String name){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing id
        editor.putLong(KEY_USER_ID, id);

        // Storing name in pref
        editor.putString(KEY_USER_NAME, name);
        //storing role in pref


        // commit changes
        editor.commit();
    }


    public void checknotLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }



    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to Main page
     * Else won't do anything
     * */


    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, MainActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }



    }
    public HashMap<String, UserInfo> getUserDetails(){
        HashMap<String, UserInfo> user = new HashMap<String, UserInfo>();
        // user name
        UserInfo useri = new UserInfo();

        useri.userId = pref.getLong(KEY_USER_ID, Long.valueOf("0"));
        useri.userName= pref.getString(KEY_USER_NAME,null);


        user.put(KEY_USERINFO, useri);

        // return user
        return user;
    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
}
