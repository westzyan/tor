package com.tor.pojo;

public class Flow {

    private String flowID;
    private String srcIP;
    private String srcPort;
    private String dstPort;
    private String dstIP;
    private String protocol;
    private String timestamp;
    private String flowDuration;
    private String totFwdPkts;
    private String totalFiatNumeric;
    private String totalBiatNumeric;
    private String minFiatNumeric;
    private String minBiatNumeric;
    private String lable;


    public Flow(){

    }

    public String getFlowID() {
        return flowID;
    }

    public void setFlowID(String flowID) {
        this.flowID = flowID;
    }

    public String getSrcIP() {
        return srcIP;
    }

    public void setSrcIP(String srcIP) {
        this.srcIP = srcIP;
    }

    public String getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(String srcPort) {
        this.srcPort = srcPort;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getDstIP() {
        return dstIP;
    }

    public void setDstIP(String dstIP) {
        this.dstIP = dstIP;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFlowDuration() {
        return flowDuration;
    }

    public void setFlowDuration(String flowDuration) {
        this.flowDuration = flowDuration;
    }

    public String getTotFwdPkts() {
        return totFwdPkts;
    }

    public void setTotFwdPkts(String totFwdPkts) {
        this.totFwdPkts = totFwdPkts;
    }

    public String getTotalFiatNumeric() {
        return totalFiatNumeric;
    }

    public void setTotalFiatNumeric(String totalFiatNumeric) {
        this.totalFiatNumeric = totalFiatNumeric;
    }

    public String getTotalBiatNumeric() {
        return totalBiatNumeric;
    }

    public void setTotalBiatNumeric(String totalBiatNumeric) {
        this.totalBiatNumeric = totalBiatNumeric;
    }

    public String getMinFiatNumeric() {
        return minFiatNumeric;
    }

    public void setMinFiatNumeric(String minFiatNumeric) {
        this.minFiatNumeric = minFiatNumeric;
    }

    public String getMinBiatNumeric() {
        return minBiatNumeric;
    }

    public void setMinBiatNumeric(String minBiatNumeric) {
        this.minBiatNumeric = minBiatNumeric;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
