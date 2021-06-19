package me.bratwurst.checkHost;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FTools_Result<T> {
    private final FTools_CheckHostType type;
    private final List<FTools_CheckHostServer> servers;
    private final String requestId;
    private T result;

    public FTools_Result(FTools_CheckHostType type, String requestId, List<FTools_CheckHostServer> servers) throws IOException {
        this.type = type;
        this.requestId = requestId;
        this.servers = servers;
        this.update();
    }

    public String getRequestId() {
        return this.requestId;
    }

    public T getResult() {
        return this.result;
    }

    public FTools_CheckHostType getType() {
        return this.type;
    }

    public List<FTools_CheckHostServer> getServers() {
        return this.servers;
    }

    public void update() throws IOException {
        Map.Entry<String, List<FTools_CheckHostServer>> entry = new Map.Entry<String, List<FTools_CheckHostServer>>(){

            @Override
            public String getKey() {
                return FTools_Result.this.requestId;
            }

            @Override
            public List<FTools_CheckHostServer> getValue() {
                return FTools_Result.this.servers;
            }

            @Override
            public List<FTools_CheckHostServer> setValue(List<FTools_CheckHostServer> value) {
                return FTools_Result.this.servers;
            }
        };
        switch (this.type) {
            case PING: {
                this.result = (T) FTools_CheckHostAPI.ping(entry);
                break;
            }
            case DNS: {
                this.result = (T)FTools_CheckHostAPI.dns(entry);
                break;
            }
            case HTTP: {
                this.result = (T)FTools_CheckHostAPI.http(entry);
                break;
            }
            case UDP: {
                this.result = (T)FTools_CheckHostAPI.udp(entry);
                break;
            }
            case TCP: {
                this.result = (T)FTools_CheckHostAPI.tcp(entry);
            }
        }
    }
}


