package com.thtf.gradleplugin.test_plugin

class AppConstant {
    /** 版本号*/
    def static final VER = "1.0.0"
    /**打印信息时候的前缀*/
    def static final TAG = "<replugin-host-v${VER}>"
    /**外部用户配置信息*/
    def static final USER_CONFIG = "repluginHostConfig"
    /** 用户Task组 */
    def static final TASKS_GROUP = "replugin-plugin"

    /** Task前缀 */
    def static final TASKS_PREFIX = "rp"

    /** 用户Task:安装插件 */
    def static final TASK_SHOW_PLUGIN = TASKS_PREFIX + "ShowPlugins"

    /** 用户Task:Generate任务 */
    def static final TASK_GENERATE = TASKS_PREFIX + "Generate"

    AppConstant() {}
}