package com.kjw.classschedule.util;

import android.os.HandlerThread;

/**
 * Created by jwkuang on 2016/9/23.
 */

public class ThreadUtil {
    private static HandlerThread mFileThread;

    public static HandlerThread getFileThread(){
        if(mFileThread  == null){
            mFileThread = new HandlerThread("file");
            mFileThread.start();
        }
        return mFileThread;
    }

    public static void stopFileThread(){
        if(mFileThread != null){
            mFileThread.quitSafely();
            mFileThread = null;
        }
    }
}
