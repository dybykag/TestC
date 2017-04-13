package shinelee.main.xposed;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Created by shine
 */
public class Happy implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("Loaded app: " + loadPackageParam.packageName);

        if (loadPackageParam.packageName.equals("com.happyelements.AndroidAnimal")) {
            XposedBridge.log("开始hook....");
            //hook加固后的包，首先hook getNewAppInstance这个方法来获取context对象
            XposedHelpers.findAndHookMethod("com.qihoo.util.StubApp4167173372", loadPackageParam.classLoader,
                    "getNewAppInstance", Context.class, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            //获取到360的Context对象，通过这个对象来获取classloader
                            final Context context = (Context) param.args[0];
                            final Looper looper = context.getMainLooper();
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    while (true) {
                                        try {
                                            Looper.loop();
                                        } catch (Throwable e) {
                                        }
                                    }
                                }
                            });

                        }
                    });
        }
    }
}
