#include<jni.h>
#include<stdlib.h>
#include<string.h>

jstring Java_com_example_cognizantcontactsapp_ui_MainActivity_00024Companion_helloWorld(JNIEnv* env, jobject obj){
    return (*env)->NewStringUTF(env,"Hello NATIVE");
}

jstring Java_com_example_cognizantcontactsapp_ui_MainActivity_00024Companion_x(JNIEnv* env, jobject obj){
    return (*env)->NewStringUTF(env,"Native CTF");
}

jstring Java_com_example_cognizantcontactsapp_ui_MainActivity_00024Companion_decY(JNIEnv* env, jobject obj, jstring j_input){

    /*const jsize len = env->GetStringUTFLength(str);*/

    const char* input = (*env)->GetStringUTFChars(env,j_input, (jboolean *)0);

    char* output = malloc(sizeof(char) * strlen(input));

    char key[3] = {'A', '8', 'C'};

    int i;
    for (i = 0; i < strlen(input); i++)
        output[i] = input [i] ^ key [i % sizeof(key)];


    return (*env)->NewStringUTF(env,output);
}