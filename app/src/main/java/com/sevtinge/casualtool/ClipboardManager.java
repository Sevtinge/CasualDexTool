package com.sevtinge.casualtool;

import android.app.ActivityThread;
import android.content.ClipData;
import android.content.Context;
import android.os.Looper;
import android.system.Os;
import android.util.Log;

public class ClipboardManager {
    public static void putTextToClipboard(Context context, String str) {
        ((android.content.ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", str));
    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> uncaughtException(t, e));
        try {
            String content = args[0];
            Os.setuid(1000);
            Looper.prepareMainLooper();
            Context context = getContext();
            putTextToClipboard(context, content);
            try {
                Thread.sleep(2000L);
                System.exit(0);
            } catch (InterruptedException e) {
                Log.d("ClipboardManager", "Exception in thread: " + Log.getStackTraceString(e));
            }
            Looper.loop();
        } catch (Throwable throwable) {
            Log.d("ClipboardManager", "Exception in main: " + Log.getStackTraceString(throwable));
        }
    }

    private static Context getContext() {
        ActivityThread activityThread = ActivityThread.systemMain();
        return activityThread.getSystemContext();
    }
    
   private static void uncaughtException(Thread t, Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Uncaught exception: ");
        sb.append(Log.getStackTraceString(e));
        Log.d("ClipboardManager", sb.toString());
    }
}
