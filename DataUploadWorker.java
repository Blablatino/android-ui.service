package com.example.app;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import java.util.concurrent.TimeUnit;

public class DataUploadWorker extends Worker {
    public DataUploadWorker(@NonNull Context context, @NonNull WorkerParameters params) {
        super(context, params);
    }

    @NonNull
    @Override
    public Result doWork() {
        // TODO: collect stored data and send to server
        // Use FirebaseClient.sendBatch()
        return Result.success();
    }

    public static void scheduleWork(Context context) {
        PeriodicWorkRequest request =
            new PeriodicWorkRequest.Builder(DataUploadWorker.class, 30, TimeUnit.MINUTES)
                .build();
        WorkManager.getInstance(context).enqueue(request);
    }
}
