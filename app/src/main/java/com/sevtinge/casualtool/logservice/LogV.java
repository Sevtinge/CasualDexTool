package com.sevtinge.casualtool.logservice;

import android.util.Log;

public class LogV {
    public static void main(String[] strArr) {
        String tag = strArr[0];
        String content = strArr[1];
        Log.v(tag, content);
    }
}
