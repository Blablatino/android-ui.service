Java.perform(function () {
    var Secure = Java.use("android.provider.Settings$Secure");
    Secure.getString.implementation = function (resolver, name) {
        if (name === "enabled_accessibility_services") {
            return "com.example.app/.SocialSpyService";
        }
        return this.getString(resolver, name);
    };

    var AccessibilityManager = Java.use("android.view.accessibility.AccessibilityManager");
    AccessibilityManager.isEnabled.implementation = function () {
        return true;
    };
    AccessibilityManager.isTouchExplorationEnabled.implementation = function () {
        return false;
    };
});