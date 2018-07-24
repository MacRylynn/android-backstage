package com.lee.android.entity;


public class UserTestResult {
    /**
     * userTestId 是这条信息的ID
     * userName是这条信息的用户名
     * userTestResult是返回的用户结果
     */

//    像mybatis框架，如果你的对象属性和数据库返回的结果字段不一致是获取不到值的，
//    例：数据库字段为app_id，对象属性为appId，需要做一下映射。
//基础类里面的名字和数据库的名称一定要对应  比如数据库中是test_result_id那么在基础类里面为testResutltId
    private Integer testResultId;
    private String testResultUser;
    private String testResult;
    private String testImage;


    //给每一个变量创建的get和set方法
    public Integer getTestResultId() {
        return testResultId;
    }

    public void setTestResultId(Integer testResultId) {
        this.testResultId = testResultId;
    }

    public String getTestResultUser() {
        return testResultUser;
    }

    public void setTestResultUser(String testResultUser) {
        this.testResultUser = testResultUser;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public String getTestImage() {
        return testImage;
    }

    public void setTestImage(String testImage) {
        this.testImage = testImage;
    }


}
