//
// Created by xiaoqiang on 2017/9/27.
//
#include "com_example_xiaoqiang_myapplication_jni_LibX264.h"
#define TAG "jni"
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,TAG,__VA_ARGS__) // 定义LOGD类型
/*
 * Class:     com_example_xiaoqiang_myapplication_jni_LibX264
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_xiaoqiang_myapplication_jni_LibX264_init
        (JNIEnv * env, jobject object)
{


}

/*
 * Class:     com_example_xiaoqiang_myapplication_jni_LibX264
 * Method:    encoded
 * Signature: ([BII)[B
 */
JNIEXPORT jbyteArray JNICALL Java_com_example_xiaoqiang_myapplication_jni_LibX264_encoded
        (JNIEnv * env, jobject obj, jbyteArray byteArray,jint widht,jint height) {
    jbyte buffer[1000];
    int i =0;
    for(i =0;i<1000;i++){
        buffer[i]= i;
    }
    LOGD("NEW出新的对象");
    jbyteArray array = (*env)->NewByteArray(env,1000);
    (*env)->SetByteArrayRegion(env,array,0,1000,buffer);

    return array;
}

/*
 * Class:     com_example_xiaoqiang_myapplication_jni_LibX264
 * Method:    release
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_example_xiaoqiang_myapplication_jni_LibX264_release
        (JNIEnv * env, jobject object) {
}
