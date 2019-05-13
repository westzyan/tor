package com.tor.pojo;

public class Traffic {
    private int id;  //id
    private String sourceIP;
    private int sourcePort;
    private String destinationIP;
    private int destinationPort;
    private int protocol;
    private String randomBytes;
    private int cipherSuitesLength;
    private String cipherSuite;
    private int serverNameLength;
    private String serverName;
    //	private String heartbeat;
    private String subjectAndIssuer;
    private String beforeAndAfter;
    //	private String serialNumber;
    private String tor;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSourceIP() {
        return sourceIP;
    }

    public void setSourceIP(String sourceIP) {
        this.sourceIP = sourceIP;
    }

    public int getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(int sourcePort) {
        this.sourcePort = sourcePort;
    }

    public String getDestinationIP() {
        return destinationIP;
    }

    public void setDestinationIP(String destinationIP) {
        this.destinationIP = destinationIP;
    }

    public int getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(int destinationPort) {
        this.destinationPort = destinationPort;
    }

    public int getProtocol() {
        return protocol;
    }

    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }

    public String getRandomBytes() {
        return randomBytes;
    }

    public void setRandomBytes(String randomBytes) {
        this.randomBytes = randomBytes;
    }

    public int getCipherSuitesLength() {
        return cipherSuitesLength;
    }

    public void setCipherSuitesLength(int cipherSuitesLength) {
        this.cipherSuitesLength = cipherSuitesLength;
    }

    public String getCipherSuite() {
        return cipherSuite;
    }

    public void setCipherSuite(String cipherSuite) {
        this.cipherSuite = cipherSuite;
    }

    public int getServerNameLength() {
        return serverNameLength;
    }

    public void setServerNameLength(int serverNameLength) {
        this.serverNameLength = serverNameLength;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    //	public String getHeartbeat() {
//		return heartbeat;
//	}
//	public void setHeartbeat(String heartbeat) {
//		this.heartbeat = heartbeat;
//	}
    public String getSubjectAndIssuer() {
        return subjectAndIssuer;
    }

    public void setSubjectAndIssuer(String subjectAndIssuer) {
        this.subjectAndIssuer = subjectAndIssuer;
    }

    public String getBeforeAndAfter() {
        return beforeAndAfter;
    }

    public void setBeforeAndAfter(String beforeAndAfter) {
        this.beforeAndAfter = beforeAndAfter;
    }

    //	public String getSerialNumber() {
//		return serialNumber;
//	}
//	public void setSerialNumber(String serialNumber) {
//		this.serialNumber = serialNumber;
//	}
    public String getTor() {
        return tor;
    }

    public void setTor(String tor) {
        this.tor = tor;
    }

    public Traffic() {
        super();
    }

    public Traffic(int id, String sourceIP, int sourcePort, String destinationIP, int destinationPort, int protocol, String randomBytes, int cipherSuitesLength, String cipherSuite, int serverNameLength, String serverName, String subjectAndIssuer, String beforeAndAfter, String tor) {
        this.id = id;
        this.sourceIP = sourceIP;
        this.sourcePort = sourcePort;
        this.destinationIP = destinationIP;
        this.destinationPort = destinationPort;
        this.protocol = protocol;
        this.randomBytes = randomBytes;
        this.cipherSuitesLength = cipherSuitesLength;
        this.cipherSuite = cipherSuite;
        this.serverNameLength = serverNameLength;
        this.serverName = serverName;
        this.subjectAndIssuer = subjectAndIssuer;
        this.beforeAndAfter = beforeAndAfter;
        this.tor = tor;
    }

}
