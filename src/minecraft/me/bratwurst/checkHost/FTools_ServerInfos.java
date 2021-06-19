package me.bratwurst.checkHost;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

public class FTools_ServerInfos {
    private String serverIP;
    private String serverMotd;
    private String populationInfo;
    private String gameVersion;
    private String playerList;
    private String serverIcon;
    private boolean field_78841_f;
    private int version;
    private long pingToServer;
    private Hosting hosting;

    public FTools_ServerInfos(String serverIP, String serverMotd, String populationInfo, String gameVersion, String playerList, String serverIcon, boolean field_78841_f, int version, long pingToServer) {
        this.setServerIP(serverIP);
        this.setServerMotd(serverMotd);
        this.setPopulationInfo(populationInfo);
        this.setGameVersion(gameVersion);
        this.setPlayerList(playerList);
        this.setServerIcon(serverIcon);
        this.setField_78841_f(field_78841_f);
        this.setVersion(version);
        this.setPingToServer(pingToServer);
        this.setHosting(new Hosting(this.getServerIP()));
    }

    public String getServerIP() {
        return this.serverIP;
    }

    public String getServerMotd() {
        return this.serverMotd;
    }

    public String getPopulationInfo() {
        return this.populationInfo;
    }

    public String getGameVersion() {
        return this.gameVersion;
    }

    public String getPlayerList() {
        return this.playerList;
    }

    public String getServerIcon() {
        return this.serverIcon;
    }

    public boolean isField_78841_f() {
        return this.field_78841_f;
    }

    public int getVersion() {
        return this.version;
    }

    public long getPingToServer() {
        return this.pingToServer;
    }

    public Hosting getHosting() {
        return this.hosting;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public void setServerMotd(String serverMotd) {
        this.serverMotd = serverMotd;
    }

    public void setPopulationInfo(String populationInfo) {
        this.populationInfo = populationInfo;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
    }

    public void setPlayerList(String playerList) {
        this.playerList = playerList;
    }

    public void setServerIcon(String serverIcon) {
        this.serverIcon = serverIcon;
    }

    public void setField_78841_f(boolean field_78841_f) {
        this.field_78841_f = field_78841_f;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPingToServer(long pingToServer) {
        this.pingToServer = pingToServer;
    }

    public void setHosting(Hosting hosting) {
        this.hosting = hosting;
    }

    public static class Hosting {
        private String server;
        private JsonObject object;
        private FTools_TimeHelper timeUtils;

        public Hosting(String serverIP) {
            try {
                this.setServer("http://ip-api.com/json/" + serverIP + "?fields=status,message,continent,continentCode,country,countryCode,region,regionName,city,district,zip,lat,lon,timezone,currency,isp,org,as,asname,reverse,mobile,proxy,query");
                this.object = new JsonParser().parse(FTools_WebUtils.getWebsiteData(this.getServer())).getAsJsonObject();
                this.timeUtils = new FTools_TimeHelper();
                this.timeUtils.reset();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }

        public ArrayList<String> getResults() {
            ArrayList<String> result = new ArrayList<String>();

            if (this.isSucess("isp")) {
                result.add("\u00a7bISP: \u00a77" + this.getStringObject("isp"));
            }

            return result;
        }

        private String getStringObject(String objectName) {
            if (!objectName.isEmpty() && !objectName.contains("Unknown")) {
                return this.object.get(objectName).getAsString();
            }
            return "Can't load";
        }

        private boolean isSucess(String objectName) {
            return !this.getStringObject(objectName).equals("Can't load");
        }

        public String getServer() {
            return this.server;
        }

        public JsonObject getObject() {
            return this.object;
        }

        public FTools_TimeHelper getTimeUtils() {
            return this.timeUtils;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public void setObject(JsonObject object) {
            this.object = object;
        }

        public void setTimeUtils(FTools_TimeHelper timeUtils) {
            this.timeUtils = timeUtils;
        }
    }
}


