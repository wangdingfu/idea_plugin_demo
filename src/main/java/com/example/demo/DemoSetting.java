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
