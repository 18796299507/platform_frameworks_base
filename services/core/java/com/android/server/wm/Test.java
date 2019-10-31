package com.android.server.wm;

import android.os.IBinder;
/**
 * Created by sYYLG on 19/10/30.
 */
public class Test{
    public static ActivityRecord forToken(IBinder token) {
        return ActivityRecord.forTokenLocked(token);
    }
}
