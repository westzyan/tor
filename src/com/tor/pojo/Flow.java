package com.tor.pojo;

public class Flow {

    //流ID
    String flowID;
    //源IP
    String srcIP;
    //源端口号
    String srcPort;
    //目的IP
    String dstIP;
    //目的端口号
    String dstPort;
    //协议
    String protocol;
    //时间戳
    String timeStamp;
    //流持续时间
    String flowDuration;
    //每秒流的比特数
    String flowBytsPsec;
    //每秒数据包的个数
    String flowPktsPsec;
    //发送和接收两个数据包之间的时间间隔的平均值
    String flowIATMean;
    //发送和接收两个数据包之间的时间间隔的标准值
    String flowIATStd;
    //发送和接收两个数据包之间的时间间隔的最大值
    String flowIATMax;
    //发送和接收两个数据包之间的时间间隔的最小值
    String flowIATMin;
    //发送两个数据包的时间间隔的总和
    String fwdIATTot;
    //发送两个数据包的时间间隔的平均值
    String fwdIATMean;
    //发送两个数据包的时间间隔的标准值
    String fwdIATStd;
    //发送两个数据包的时间间隔的最大值
    String fwdIATMax;
    //发送两个数据包的时间间隔的最小值
    String fwdIATMin;
    //接收两个数据包的时间间隔的总和
    String bwdIATtot;
    //接收两个数据包的时间间隔的平均值
    String bwdIATMean;
    //接收两个数据包的时间间隔的标准值
    String bwdIATStd;
    //接收两个数据包的时间间隔的最大值
    String bwdIATMax;
    //接收两个数据包的时间间隔的最小值
    String bwdIATMin;
    //流停止之前活跃时间的平均值
    String activeMean;
    //流停止之前活跃时间的标准值
    String activeStd;
    //流停止之前活跃时间的最大值
    String activeMax;
    //流停止之前活跃时间的最小值
    String activeMin;
    //流活跃之前停止时间的平均值
    String idleMean;
    //流活跃之前停止时间的标准值
    String idleStd;
    //流活跃之前停止时间的最大值
    String idleMax;
    //流活跃之前停止时间的最小值
    String idleMin;
    //二分类标签
    String label;


    public Flow() {

    }

    public Flow(String flowID, String srcIP, String srcPort, String dstIP, String dstPort, String protocol, String timeStamp, String flowDuration, String flowBytsPsec, String flowPktsPsec, String flowIATMean, String flowIATStd, String flowIATMax, String flowIATMin, String fwdIATTot, String fwdIATMean, String fwdIATStd, String fwdIATMax, String fwdIATMin, String bwdIATtot, String bwdIATMean, String bwdIATStd, String bwdIATMax, String bwdIATMin, String activeMean, String activeStd, String activeMax, String activeMin, String idleMean, String idleStd, String idleMax, String idleMin, String label) {
        this.flowID = flowID;
        this.srcIP = srcIP;
        this.srcPort = srcPort;
        this.dstIP = dstIP;
        this.dstPort = dstPort;
        this.protocol = protocol;
        this.timeStamp = timeStamp;
        this.flowDuration = flowDuration;
        this.flowBytsPsec = flowBytsPsec;
        this.flowPktsPsec = flowPktsPsec;
        this.flowIATMean = flowIATMean;

        this.flowIATStd = flowIATStd;
        this.flowIATMax = flowIATMax;
        this.flowIATMin = flowIATMin;

        this.fwdIATTot = fwdIATTot;
        this.fwdIATMean = fwdIATMean;
        this.fwdIATStd = fwdIATStd;

        this.fwdIATMax = fwdIATMax;
        this.fwdIATMin = fwdIATMin;
        this.bwdIATtot = bwdIATtot;

        this.bwdIATMean = bwdIATMean;
        this.bwdIATStd = bwdIATStd;
        this.bwdIATMax = bwdIATMax;
        this.bwdIATMin = bwdIATMin;
        this.activeMean = activeMean;
        this.activeStd = activeStd;
        this.activeMax = activeMax;
        this.activeMin = activeMin;
        this.idleMean = idleMean;
        this.idleStd = idleStd;
        this.idleMax = idleMax;
        this.idleMin = idleMin;
        this.label = label;
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

    public String getDstIP() {
        return dstIP;
    }

    public void setDstIP(String dstIP) {
        this.dstIP = dstIP;
    }

    public String getDstPort() {
        return dstPort;
    }

    public void setDstPort(String dstPort) {
        this.dstPort = dstPort;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFlowDuration() {
        return flowDuration;
    }

    public void setFlowDuration(String flowDuration) {
        this.flowDuration = flowDuration;
    }

    public String getFlowBytsPsec() {
        return flowBytsPsec;
    }

    public void setFlowBytsPsec(String flowBytsPsec) {
        this.flowBytsPsec = flowBytsPsec;
    }

    public String getFlowPktsPsec() {
        return flowPktsPsec;
    }

    public void setFlowPktsPsec(String flowPktsPsec) {
        this.flowPktsPsec = flowPktsPsec;
    }

    public String getFlowIATMean() {
        return flowIATMean;
    }

    public void setFlowIATMean(String flowIATMean) {
        this.flowIATMean = flowIATMean;
    }

    public String getFlowIATStd() {
        return flowIATStd;
    }

    public void setFlowIATStd(String flowIATStd) {
        this.flowIATStd = flowIATStd;
    }

    public String getFlowIATMax() {
        return flowIATMax;
    }

    public void setFlowIATMax(String flowIATMax) {
        this.flowIATMax = flowIATMax;
    }

    public String getFlowIATMin() {
        return flowIATMin;
    }

    public void setFlowIATMin(String flowIATMin) {
        this.flowIATMin = flowIATMin;
    }

    public String getFwdIATTot() {
        return fwdIATTot;
    }

    public void setFwdIATTot(String fwdIATTot) {
        this.fwdIATTot = fwdIATTot;
    }

    public String getFwdIATMean() {
        return fwdIATMean;
    }

    public void setFwdIATMean(String fwdIATMean) {
        this.fwdIATMean = fwdIATMean;
    }

    public String getFwdIATStd() {
        return fwdIATStd;
    }

    public void setFwdIATStd(String fwdIATStd) {
        this.fwdIATStd = fwdIATStd;
    }

    public String getFwdIATMax() {
        return fwdIATMax;
    }

    public void setFwdIATMax(String fwdIATMax) {
        this.fwdIATMax = fwdIATMax;
    }

    public String getFwdIATMin() {
        return fwdIATMin;
    }

    public void setFwdIATMin(String fwdIATMin) {
        this.fwdIATMin = fwdIATMin;
    }

    public String getBwdIATtot() {
        return bwdIATtot;
    }

    public void setBwdIATtot(String bwdIATtot) {
        this.bwdIATtot = bwdIATtot;
    }

    public String getBwdIATMean() {
        return bwdIATMean;
    }

    public void setBwdIATMean(String bwdIATMean) {
        this.bwdIATMean = bwdIATMean;
    }

    public String getBwdIATStd() {
        return bwdIATStd;
    }

    public void setBwdIATStd(String bwdIATStd) {
        this.bwdIATStd = bwdIATStd;
    }

    public String getBwdIATMax() {
        return bwdIATMax;
    }

    public void setBwdIATMax(String bwdIATMax) {
        this.bwdIATMax = bwdIATMax;
    }

    public String getBwdIATMin() {
        return bwdIATMin;
    }

    public void setBwdIATMin(String bwdIATMin) {
        this.bwdIATMin = bwdIATMin;
    }

    public String getActiveMean() {
        return activeMean;
    }

    public void setActiveMean(String activeMean) {
        this.activeMean = activeMean;
    }

    public String getActiveStd() {
        return activeStd;
    }

    public void setActiveStd(String activeStd) {
        this.activeStd = activeStd;
    }

    public String getActiveMax() {
        return activeMax;
    }

    public void setActiveMax(String activeMax) {
        this.activeMax = activeMax;
    }

    public String getActiveMin() {
        return activeMin;
    }

    public void setActiveMin(String activeMin) {
        this.activeMin = activeMin;
    }

    public String getIdleMean() {
        return idleMean;
    }

    public void setIdleMean(String idleMean) {
        this.idleMean = idleMean;
    }

    public String getIdleStd() {
        return idleStd;
    }

    public void setIdleStd(String idleStd) {
        this.idleStd = idleStd;
    }

    public String getIdleMax() {
        return idleMax;
    }

    public void setIdleMax(String idleMax) {
        this.idleMax = idleMax;
    }

    public String getIdleMin() {
        return idleMin;
    }

    public void setIdleMin(String idleMin) {
        this.idleMin = idleMin;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
