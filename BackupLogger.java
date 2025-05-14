
package com.example.app;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BackupLogger {
    public static void cache(Context ctx, String path, String data) {
        try {
            // Normalizuj názov adresára
            path = path.replaceAll("[^a-zA-Z0-9_\-]", "_");
            File dir = new File(ctx.getFilesDir(), "offline_logs/" + path);
            if (!dir.exists()) dir.mkdirs();

            String filename = System.currentTimeMillis() + ".log";
            File f = new File(dir, filename);
            try (FileOutputStream out = new FileOutputStream(f)) {
                String timestamped = "[" + System.currentTimeMillis() + "] " + data;
                out.write(timestamped.getBytes());
            }
        } catch (IOException e) {
            Log.e("BACKUP", "Failed to cache: " + e.getMessage());
        }
    }
}
