package me.bratwurst.checkHost;

public enum FTools_CheckHostType {
    PING("ping"),
    TCP("tcp"),
    UDP("udp"),
    HTTP("http"),
    DNS("dns");

    private final String value;

    private FTools_CheckHostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


