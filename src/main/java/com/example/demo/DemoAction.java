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
