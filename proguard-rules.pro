
# Zachovanie AccessibilityService
-keep class com.example.app.** { *; }
-keepclassmembers class * {
    public <init>(...);
}

# Firebase
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**
