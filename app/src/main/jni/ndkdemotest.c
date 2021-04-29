#include "com_example_space_jni_NDKTools.h"

JNIEXPORT jstring JNICALL Java_com_example_space_jni_NDKTools_getStringFromNDK
  (JNIEnv *env, jobject obj){
     return (*env)->NewStringUTF(env,"This is c
      JNI test");
  }