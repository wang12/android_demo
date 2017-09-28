
cmake_minimum_required(VERSION 3.4.1)

include_directories(./include)

add_library(
            libx264
            STATIC
            IMPORTED
            /Users/xiaoqiang/workspace/mycodec/MyApplication3/app/jni/lib/libx264.a
            )

add_library( # Sets the name of the library.
             native-lib

             SHARED

             com_example_xiaoqiang_myapplication_jni_LibX264.c
             )
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )


target_link_libraries(

            native-lib

            ${log-lib}

            ${libx264}

            )