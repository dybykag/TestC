package shinelee.main.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bluef on 2016/8/10.
 */

public class ReflectionUtils {
    private static final Map<String, Class> CLASS_MAP = new ConcurrentHashMap<>();

    public static Class<?> getClassForName(String className) {
        Class<?> ret = CLASS_MAP.get(className);

        if (ret == null) {
            try {
                ret = Class.forName(className);

                CLASS_MAP.put(className, ret);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return ret;
    }
}
