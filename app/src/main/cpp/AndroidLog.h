#pragma once
#ifndef STUDYMANAGER_ANDROIDLOG_H
#define STUDYMANAGER_ANDROIDLOG_H

#include <android/log.h>
#define LOGD(FORMAT,...) __android_log_print(ANDROID_LOG_DEBUG,"ywl5320",FORMAT,##__VA_ARGS__);
#define LOGE(FORMAT,...) __android_log_print(ANDROID_LOG_ERROR,"ywl5320",FORMAT,##__VA_ARGS__);

#define LOG_DEBUG false

#endif //WLPLAYER_ANDROIDLOG_H