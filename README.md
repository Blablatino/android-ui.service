# Modified App for Android 13+
- No notifications: removed startForeground() calls.
- Batched data collection: uses WorkManager to upload every 30 minutes.
- DataUploadWorker.java added to schedule and handle uploads.
- Ensure EncryptedFile storage and permission checks as per audit suggestions.
