
本次案例使用`Using Gradle`方式创建插件项目



## 创建插件
- 1、打开`idea-->new Project`进入新建页面


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198184914.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198191840.png)

## 认识插件相关配置

### plugin.xml


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198203578.png)

#### 插件首页描述信息

>将插件描述信息交给gradle来读取. 可以参考[gradle中读取插件描述信息](###gradle中读取插件描述信息)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198213339.png)

#### 配置extensions(扩展属性)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198221455.png)

###### applicationService
> 在扩展属性中配置``标签（将service示例交给`Intellij`来管理）

```xml
<!-- 将FuDocClassParserImpl类交给Intellij管理 需要的时候直接通过ApplicationManager.getApplication().getService(clazz)获取即可 -->
<applicationService serviceImplementation="com.wdf.fudoc.parse.FuDocClassParserImpl"/>
```

###### notificationGroup

>在扩展属性中配置`notificationGroup`标签(定义一个通知组). 可以参考[弹出提示框](##弹出提示框)了解他的作用和使用场景

```xml
<notificationGroup id="fu_doc_notify_group" displayType="BALLOON"/>
```

###### projectService

>在扩展属性中配置`projectService`标签（定义数据持久化组件）. 可以参考[新建第一个持久化数据组件](##新建第一个持久化数据组件)了解他的作用和使用场景
```xml
<projectService serviceImplementation="com.wdf.fudoc.config.state.FuDocSetting"/>
```

###### projectConfigurable
>在扩展属性中配置`projectConfigurable`标签(定义IDEA的配置页面). 可以参考[新建第一个配置页面](##新建第一个配置页面)了解他的作用和使用场景
```xml
<projectConfigurable instance="com.wdf.fudoc.config.configurable.FuDocSettingConfigurable"
                             id="fu.doc.setting"
                             displayName="Fu Doc"
                             nonDefaultProject="true"/>
```
- `instance`: 配置页面实现类
- `id`: 标识唯一的一个配置页面
- `displayName`: 配置页面的名称

> 还有非常多的属性等待你的扩展...


#### 配置actions(在idea上的触发行为配置)
```xml
    <actions>
        <!-- Add your actions here -->
        <action id="gen-api-doc" class="com.wdf.fudoc.action.GenFuDocAction" text="Fu Doc">
            <add-to-group group-id="EditorPopupMenu" anchor="first"/>
            <keyboard-shortcut keymap="$default" first-keystroke="alt D"/>
        </action>

    </actions>
```

- `actions`：标签下可以配置多个行为动作
- `action`：定义一组行为. 例如`鼠标右键`或`点击菜单栏按钮`或`按下某个快捷键`等触发某一个行为动作就是在这里配置
- `id`： 定义一个唯一的行为动作
- `class`： 触发这个行为动作后调用这个类
- `text`：定义这个行为的名称 例如菜单栏上某个按钮的名称
- `add-to-group`：将这个行为添加到一个组当中
- `group-id`：组ID IDEA将每个部分都定义成了一个组. 例如`EditorPopupMenu`就是鼠标邮件弹出那个菜单上所有的操作都属于这个组里
- `anchor`：在这个组中的位置
- `keyboard-shortcut`：为这个行为添加一个快捷键
- `keymap`：指定哪一套快捷键
- `first-keystroke`: 第一个快捷键（当然还可以为这个行为添加多个快捷键）

###  认识`build.gradle.kts`

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198267818.png)

#### gradle中读取插件描述信息

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198273886.png)


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198279797.png)

###### 插件描述信息示例
```markdown
<!-- Plugin description -->
# Generate MarkDown API DOC


[&emsp; GitHub   &emsp;|](https://github.com/wangdingfu/fu-api-doc-plugin)
[&emsp; Document     &emsp;|](https://wangdingfu.github.io/)
[&emsp; Issues   &emsp;|](https://github.com/wangdingfu/fu-api-doc-plugin/issues)
[&emsp; Releases &emsp;|](https://github.com/wangdingfu/fu-api-doc-plugin/releases)

## English introduction
> The api doc in markDown format is generate by one click based on Java Controller to avoid copying and pasting the api doc every time, saving valuable time for the coder

### how to use
- 1、press alt+D or right click `Fu Doc` in java class
- 2、paste in api doc system(`Show doc` or `YApi` or `MarkDown` or `others` )

### Support
- 1、Support Idea version later than 2020.3.4
- 2、Support `Controller` `Interface` `Object` `Enum` generate markdown api doc
- 3、Support for dynamic templates
- 4、Support for custom configuration of the data you need


## 中文介绍
> 基于 java 代码一键生成MarkDown格式的接口文档, 避免每次编写接口文档来回复制粘贴操作, 节省程序猿宝贵的时间

### 如何使用
- 1、在java 类中按下快捷键`alt+D` 或者 右键 `Fu Doc`. 
- 2、查看右下角是否提示当前类已生成接口文档至剪贴板
- 3、直接去自己平时使用的接口文档系统中粘贴接口文档即可


## 支持功能
- 1、当前只支持Intellij idea 2020.3.4 之后的版本
- 2、支持根据 `Controller` `Interface` `Object` `Enum` 生成markdown格式的接口文档
- 3、支持动态配置接口文档模板
- 4、支持自定义配置你需要的数据


### 其他
- 小伙伴们如果有问题可以直接给我提<a href="https://github.com/wangdingfu/fu-api-doc-plugin/issues">Issues</a>. 我会及时回复并解决


<!-- Plugin description end -->
```

###### 插件变更记录示例
```markdown
<!-- Plugin description -->

# English introduction

## V1.0.0
- support java controller generate markdown api doc to The clipboard

# 中文介绍

## V1.0.0
- 支持根据Controller代码一键生成MarkDown格式的接口文档至剪贴板

## V1.1.1
- 支持针对Controller一键生成Markdown格式的接口文档
- 支持针对接口一键生成Markdown格式的接口文档
- 支持针对 JAVA对象一键生成Markdown格式的接口文档
- 支持针对枚举一键生成Markdown格式的接口文档
- 支持在字段注释中添加@see注释标签 引用枚举

## V1.2.1
- 支持针对Controller一键生成Markdown格式的接口文档
- 支持针对接口一键生成Markdown格式的接口文档
- 支持针对 JAVA对象一键生成Markdown格式的接口文档
- 支持针对枚举一键生成Markdown格式的接口文档
- 支持在字段注释中添加@see注释标签 引用枚举
- 支持在idea-->settings-->Fu Doc中配置自定义内容
- 支持在idea-->settings-->Fu Doc中配置接口文档模板
- 支持在接口文档模板中通过`fudoc.`获取所有注释tag内容和自定义配置内容

<!-- Plugin description end -->
```



## 开始编码

### 编码前需要的配置
####  `plugin.xml`
###### 新增依赖
```xml
<depends>com.intellij.modules.java</depends>
```

####  `build.gradle.kts`

###### 添加依赖和设置编码

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198309797.png)
```
// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.2")
    type.set("IC") // Target IDE Platform

    plugins.set(listOf(/* Plugin Dependencies */
    "com.intellij.java"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
        options.encoding = "UTF-8"
    }

    patchPluginXml {
        sinceBuild.set("212")
        untilBuild.set("222.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}

```


### 新建第一个`Action`


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198320723.png)


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198327124.png)

>创建完Action后 会自动在`plugin.xml`上新增一个行为配置

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198332709.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198338637.png)


### 弹出提示框

###### 在`plugin.xml`中配置消息组
```xml
    <extensions defaultExtensionNs="com.intellij">
        <notificationGroup id="demo_notify_group" displayType="BALLOON"/>
    </extensions>
```


###### 新建消息通知类

```java
package com.example.demo;

import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;


public class DemoNotification {

    private static final NotificationGroup NOTIFICATION_GROUP = NotificationGroupManager.getInstance()
            .getNotificationGroup("demo_notify_group");


    public static void notifyWarn(String message, Project project) {
        NOTIFICATION_GROUP.createNotification(message, NotificationType.WARNING).notify(project);
    }

    public static void notifyInfo(String message, Project project) {
        NOTIFICATION_GROUP.createNotification(message, NotificationType.INFORMATION).notify(project);
    }

    public static void notifyError(String message, Project project) {
        NOTIFICATION_GROUP.createNotification(message, NotificationType.ERROR).notify(project);
    }
}
```

###### 在`DemoAction`中调用消息通知
```java
package com.example.demo;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class DemoAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Project project = e.getProject();
        DemoNotification.notifyInfo("这是一个提示弹框", project);
        DemoNotification.notifyWarn("这是一个警告弹框", project);
        DemoNotification.notifyError("这是一个错误弹框", project);
    }
}

```

###### 测试弹框

> 第一次运行需要下载很多东西 启动会比较慢


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198353336.png)


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198359561.png)


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198365710.png)


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198372409.png)

### 新建第一个持久化数据组件


#### 新增持久化组件

```java
package com.example.demo;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "DemoStateSetting", storages = {@Storage("DemoStateSetting.xml")})
public class DemoStateSetting implements PersistentStateComponent<DemoStateSetting> {


    /**
     * 持久化的数据
     */
    private String stateData;



    @Override
    public @Nullable DemoStateSetting getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull DemoStateSetting state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getStateData() {
        return stateData;
    }

    public void setStateData(String stateData) {
        this.stateData = stateData;
    }
}

```

##### 在`plugin.xml`中配置持久化组件
```xml
      <projectService serviceImplementation="com.example.demo.DemoStateSetting"/>
```


> 测试数据放在[##新建第一个配置页面]中测试


### 新建第一个设置页面


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198385086.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198390905.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198396469.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198402049.png)

>新建java类将页面交给`Intellij`来控制设置页面
```java
package com.example.demo;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;


public class DemoSettingConfigurable  implements SearchableConfigurable {

    private DemoSetting demoSetting;

    private Project project;

    public DemoSettingConfigurable(Project project) {
        this.project = project;
    }

    /**
     * 配置页面id
     */
    @Override
    public @NotNull @NonNls String getId() {
        return "demo.setting";
    }

    /**
     * 配置页面名称
     */
    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "Demo Settings";
    }

    /**
     * 将创建的UI面板交给Idea控制
     */
    @Override
    public @Nullable JComponent createComponent() {
        demoSetting = new DemoSetting(this.project);
        return demoSetting.getRoot();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        demoSetting.apply();
    }
}


```

> 在`plugin.xml`中配置设置页面组件
```xml
        <!-- 配置为项目等级 全局为 applicationConfigurable -->
        <projectConfigurable instance="com.example.demo.DemoSettingConfigurable"
                             id="demo.setting"
                             displayName="demo Settings"
                             nonDefaultProject="true"/>
```

> 在设置页面`DemoSetting`类中将内容填充到文本框中
```java
package com.example.demo;

import com.intellij.openapi.project.Project;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;

/**
 * @author wangdingfu
 * @date 2022-08-10 21:37:00
 */
public class DemoSetting {
    private JTextField textField1;
    private JLabel label1;
    private JPanel root;

    private Project project;

    public DemoSetting(Project project) {
        this.project = project;
        DemoStateSetting instance = DemoStateSetting.getInstance(project);
        String stateData = instance.getStateData();
        if(StringUtils.isNotBlank(stateData)){
            this.textField1.setText(stateData);
        }

    }

    /**
     * 点确定或应用会调用该方法
     */
    public void apply(){
        DemoStateSetting instance = DemoStateSetting.getInstance(project);
        instance.setStateData(this.textField1.getText());
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JLabel getLabel1() {
        return label1;
    }

    public void setLabel1(JLabel label1) {
        this.label1 = label1;
    }

    public JPanel getRoot() {
        return root;
    }

    public void setRoot(JPanel root) {
        this.root = root;
    }
}

```
> 点击调试 在弹出的窗口中进入IDEA`Settings`页面


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198417572.png)

> 测试保存数据并重启项目查看数据是否被持久化


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198424895.png)

> 重启项目后进入设置页面发现上一次保存的数据还在

> 进入项目`.idea`中. 发现是讲内容保存在了`DemoStateSetting.xml`文件中


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198432418.png)


## 打包插件并安装到idea中

###  编译插件


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198448907.png)

### 安装插件到本地


![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198455419.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198462346.png)

![上传成功！](https://ceph-dev-pub.dz11.com/fed-doc/1660198473700.png)
