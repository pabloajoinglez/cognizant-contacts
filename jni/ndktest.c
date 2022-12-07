#include<jni.h>
#include<string.h>

jstring Java_com_example_cognizantcontactsapp_ui_MainActivity_00024Companion_helloWorld(JNIEnv* env, jobject obj){
    return (*env)->NewStringUTF(env,"Hello NATIVE");
}