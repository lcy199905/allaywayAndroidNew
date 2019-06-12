package com.alleyway.pojo;

import com.alleyway.utils.JsonUtils;

import java.util.List;

public class Work {

    /**
     * id : 107
     * userId : 22
     * workType : 2
     * workLabels : 5
     * workLabelText : 网页美工
     * workText : 35个漂亮的单页面网站设计
     * workImage : 1.jpg&2.jpg&3.jpg&4.jpg&5.jpg&6.jpg&7.jpg&8.jpg&9.jpg&10.jpg&11.jpg&12.jpg&13.jpg&14.jpg&15.jpg&16.jpg&17.jpg&18.jpg&19.jpg&20.jpg&21.jpg&22.jpg&23.jpg&24.jpg&25.jpg&26.jpg&27.jpg&28.jpg&29.jpg&30.jpg&31.jpg&32.jpg&33.jpg&34.jpg&35.jpg
     * workImageList : ["1.jpg","2.jpg","3.jpg","4.jpg","5.jpg","6.jpg","7.jpg","8.jpg","9.jpg","10.jpg","11.jpg","12.jpg","13.jpg","14.jpg","15.jpg","16.jpg","17.jpg","18.jpg","19.jpg","20.jpg","21.jpg","22.jpg","23.jpg","24.jpg","25.jpg","26.jpg","27.jpg","28.jpg","29.jpg","30.jpg","31.jpg","32.jpg","33.jpg","34.jpg","35.jpg"]
     * path : 22_2_1555898057208
     * workTime : 2019-04-22 09:54:17.0
     * workLikeSize : 0
     * workDiscussSize : 0
     * workCollect : 0
     * show : 1
     * userName : 5号教师
     * userHead : 1558918206630.PNG
     */

    private int id;
    private int userId;
    private String workType;
    private int workLabels;
    private String workLabelText;
    private String workText;
    private String path;
    private String workTime;
    private int workLikeSize;
    private int workDiscussSize;
    private int workCollect;
    private int show;
    private String userName;
    private String userHead;
    private List<String> workImageList;

    public Work(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public int getWorkLabels() {
        return workLabels;
    }

    public void setWorkLabels(int workLabels) {
        this.workLabels = workLabels;
    }

    public String getWorkLabelText() {
        return workLabelText;
    }

    public void setWorkLabelText(String workLabelText) {
        this.workLabelText = workLabelText;
    }

    public String getWorkText() {
        return workText;
    }

    public void setWorkText(String workText) {
        this.workText = workText;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public int getWorkLikeSize() {
        return workLikeSize;
    }

    public void setWorkLikeSize(int workLikeSize) {
        this.workLikeSize = workLikeSize;
    }

    public int getWorkDiscussSize() {
        return workDiscussSize;
    }

    public void setWorkDiscussSize(int workDiscussSize) {
        this.workDiscussSize = workDiscussSize;
    }

    public int getWorkCollect() {
        return workCollect;
    }

    public void setWorkCollect(int workCollect) {
        this.workCollect = workCollect;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public List<String> getWorkImageList() {
        return workImageList;
    }

    public void setWorkImageList(List<String> workImageList) {
        this.workImageList = workImageList;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", userId=" + userId +
                ", workType='" + workType + '\'' +
                ", workLabels=" + workLabels +
                ", workLabelText='" + workLabelText + '\'' +
                ", workText='" + workText + '\'' +
                ", path='" + path + '\'' +
                ", workTime='" + workTime + '\'' +
                ", workLikeSize=" + workLikeSize +
                ", workDiscussSize=" + workDiscussSize +
                ", workCollect=" + workCollect +
                ", show=" + show +
                ", userName='" + userName + '\'' +
                ", userHead='" + userHead + '\'' +
                ", workImageList=" + workImageList +
                '}';
    }
}
