package com.example.demo;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "DemoStateSetting", storages = {@Storage("DemoStateSetting.xml")})
public class DemoStateSetting implements PersistentStateComponent<DemoStateSetting> {


    /**
     * 持久化的数据
     */
    private String stateData;

    public static DemoStateSetting getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, DemoStateSetting.class);
    }

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
