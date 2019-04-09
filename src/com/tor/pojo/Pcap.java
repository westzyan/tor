package com.tor.pojo;

import java.util.Date;

public class Pcap {

    private Integer id;
    private String fileName;
    private Float fileSize;
    private String filePath;
    private String isHandled;
    private String sha1Value;

    public String getSha1Value() {
        return sha1Value;
    }

    public void setSha1Value(String sha1Value) {
        this.sha1Value = sha1Value;
    }

    private Date createTime;
    private Date updateTime;

    public Pcap() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Float getFileSize() {
        return fileSize;
    }

    public void setFileSize(Float fileSize) {
        this.fileSize = fileSize;
    }

    public String getIsHandled() {
        return isHandled;
    }

    public void setIsHandled(String isHandled) {
        this.isHandled = isHandled;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
