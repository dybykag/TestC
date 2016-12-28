#include <jni.h>
#include <string>
#include "Test.h"
#include "android/log.h"
#include "android/bitmap.h"

extern "C"

JNIEXPORT jstring JNICALL
Java_shinelee_testc_jni_JniLibrary_stringFromJNI(JNIEnv *env, jobject instance) {
    std::string hello = "Hello from C++";
    int i = 0;
    Test test;
    char *result = test.print();
 //   __android_log_print(ANDROID_LOG_DEBUG, "lll", result);
    return env->NewStringUTF(result);
}