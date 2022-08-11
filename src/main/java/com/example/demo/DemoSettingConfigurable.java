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
