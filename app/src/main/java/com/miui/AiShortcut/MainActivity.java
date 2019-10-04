package com.miui.AiShortcut;

import android.app.*;
import android.os.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        cmdUtil.openMiAiShortcut(MainActivity.this);
		finish();
    }
}
