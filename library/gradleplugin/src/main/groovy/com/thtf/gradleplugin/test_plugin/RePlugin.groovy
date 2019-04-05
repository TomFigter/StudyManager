package com.thtf.gradleplugin.test_plugin
import com.android.build.gradle.AppPlugin
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class RePlugin implements Plugin<Project> {
    def static TAG = AppConstant.TAG
    def project
    def config

    @Override
    void apply(Project project) {
        println("${TAG} Welcome To Replugin World! ")
        this.project = project;
        project.extensions.create(AppConstant.USER_CONFIG, RepluginConfig)
        if (project.plugins.hasPlugin(AppPlugin)) {
            def android = project.extensions.getByType(AppExtension)
            android.applicationVariants.all { variant ->
                addShowPluginTask(variant)
                if (config==null){
                    config=project.extensions.getByName(AppConstant.USER_CONFIG)
                    checkUserConfig(config)
                }
                def appID=variant.generateBuildConfig.appPackageName
                def newManifest=ComponentsGenerator.generateComponent(appID,config)
                def variantData=variant.variantData
                def scope=variantData.scope
                
                
            }
        }
    }
    /**
     *添加 [查看所有插件信息] 任务
     * @param variant
     */
    def addShowPluginTask(def variant){
        def variantData=variant.variantData
        def scope=variantData.scope
        def showPluginsTaskName=scope.getTaskName(AppConstant.TASK_SHOW_PLUGIN,"")
        def showPluginTask=project.task(showPluginsTaskName)
        
        showPluginTask.doLast{
            IFileCreator creator=new PluginBuiltinJsonCreator(project,variant,config)
            def dir=creator.getFileDir()
            if (!dir.exists()){
                println("${AppConstant.TAG} The ${dir.absolutePath} does not exist ")
                println("${AppConstant.TAG} pluginsInfo=null")
                return 
            }
            
            
        }
    }
}