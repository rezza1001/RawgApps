package com.rezzza.rawgapps;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.util.Log;

public class Test {
    @SuppressLint("NotConstructor")

    public void Test(){

    }

    Handler handler = new Handler(message -> {
        Log.d("TAGRZ","Call API Search");
        return false;
    });
}
