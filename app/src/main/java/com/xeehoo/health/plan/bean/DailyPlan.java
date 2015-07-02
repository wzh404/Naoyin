package com.xeehoo.health.plan.bean;

public class DailyPlan {
    private String code;
    private String time;
    private String desc;
    private String classify;
    private String classifyCode;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getClassify() {
        return classify;
    }
    public void setClassify(String classify) {
        this.classify = classify;
    }
    public String getClassifyCode() {
        return classifyCode;
    }
    public void setClassifyCode(String classifyCode) {
        this.classifyCode = classifyCode;
    }    
}
