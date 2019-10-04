package com.hy.mi_ai_shortcut;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;
import android.os.Bundle;

public class QuickSetting extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
                super.onCreate(savedInstanceState);
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = pref.edit();
                boolean b = !pref.getBoolean("type",false);
                editor.putBoolean("type",b);
                editor.apply();
                Toast.makeText(this,"已设置"+(b?"非":"")+"活跃状态",Toast.LENGTH_LONG).show();
                finish();
        }
}
