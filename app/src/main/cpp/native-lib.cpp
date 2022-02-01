#include <jni.h>
#include <string>
#include <fstream>
#include <ctype.h>
#include <string>
#include <sstream>
#include <vector>
#include <cstdlib>


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativedefault_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativedefault_MainActivity_stringFromJNISecond(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++ 2";
    return env->NewStringUTF(hello.c_str());
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_nativedefault_MainActivity_averageFromFilE(JNIEnv *env, jobject thiz,
                                                            jstring file_name) {
    std::ifstream reader;
    const char *c_file_name;
    c_file_name = env->GetStringUTFChars(file_name, nullptr);
    reader.open(c_file_name,std::ios::in);

    if(!reader.is_open()){
        std::string error_message = "Error has occured";
        return env->NewStringUTF(error_message.c_str());
    }

    char content[50];
    while (reader.good()){
        reader >> content;
    }

    int numbers[50];
    int iterator = 0;
    float sum = 0;
    float result_float;

    for (int i = 0; i < strlen(content); ++i) {
        if (content[i]>='0' && content[i]<='9'){
            numbers[iterator] = content[i];
            iterator += iterator;
        }
    }

    for (int i = 0; i < iterator; ++i) {
        sum += numbers[i];
    }

    result_float = sum/iterator;
    std::string result_str(std::to_string(result_float));

    return env->NewStringUTF(result_str.c_str());


}