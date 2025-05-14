
package com.example.app;

import android.content.Context;
import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseClient {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static FirebaseAuth auth = FirebaseAuth.getInstance();
    private static FirebaseUser user;

    public static void init() {
        if (auth.getCurrentUser() == null) {
            auth.signInAnonymously().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    Log.d("FIREBASE", "Signed in as: " + user.getUid());
                } else {
                    Log.e("FIREBASE", "Auth failed: " + task.getException());
                }
            });
        } else {
            user = auth.getCurrentUser();
        }
    }

    public static void pushLog(Context ctx, String path, String data) {
        if (user != null) {
            try {
                DatabaseReference ref = db.getReference("devices")
                        .child(user.getUid())
                        .child(path);
                ref.push().setValue(data);
            } catch (Exception e) {
                BackupLogger.cache(ctx, path, data);
            }
        } else {
            // fallback ak ešte neprebehla autentifikácia
            BackupLogger.cache(ctx, path, data);
        }
    }
}
