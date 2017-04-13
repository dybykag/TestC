package shinelee.main.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

/**
 * Created by shine
 */
public class Nice implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Loaded app: " + lpparam.packageName);
        Class<?> hookMessageListenerClass = null;

        hookMessageListenerClass = lpparam.classLoader.loadClass("com.nice.main.data.enumerable.User");
        findAndHookMethod("com.nice.main.fragments.MyProfileFragment", lpparam.classLoader, "refreshUserInfo", hookMessageListenerClass, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                Object user = param.args[0];
                //  XposedHelpers.setIntField(user, "showsNum", 888888);
                XposedHelpers.setIntField(user, "zansNum", 888888);
                XposedHelpers.setIntField(user, "livePraisedNum", 888888);
                XposedHelpers.setObjectField(user, "verified", "yes");
                XposedBridge.log("Loaded app: " + "user0000000");
                // this will be called before the clock was updated by the original method
            }

            @Override
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                // this will be called after the clock was updated by the original method
//                Object user = param.args[0];
//                XposedHelpers.setIntField(user, "showsNum", 888888);
//                XposedHelpers.setIntField(user, "zansNum", 888888);
//                XposedHelpers.setIntField(user, "livePraisedNum", 888888);
//                XposedBridge.log("Loaded app: " + "user111111");
//                XposedHelpers.callMethod(param.thisObject, "refreshUserInfo", user);
            }
        });
    }
}
