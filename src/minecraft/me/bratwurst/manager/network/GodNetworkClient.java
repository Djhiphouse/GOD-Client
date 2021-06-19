package me.bratwurst.manager.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.binding.StringExpression;
import me.bratwurst.Client;
import me.bratwurst.utils.player.PlayerUtils;
import net.minecraft.client.Minecraft;
import okhttp3.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GodNetworkClient {

    public static final Gson gson = new GsonBuilder().create();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final ExecutorService executor = Executors.newCachedThreadPool();
    private static final OkHttpClient client = new OkHttpClient();
    private final String server = "http://5.181.151.112:3000/";
    public static IRCClient ircClient = new IRCClient();

    public GodNetworkClient() {
        connectWebSocket();
    }

    public void connectWebSocket() {
        Request request = new Request.Builder().url(server + "chat/socket").build();
        client.newWebSocket(request, ircClient);
    }

    public CompletableFuture<ShadeResponse> get(List<UUID> uuids) {
        ShadeRequest request = new ShadeRequest();
        String[] ids = uuids.stream().map(UUID::toString).toArray(String[]::new);
        request.ids = ids;
        RequestBody requestBody = RequestBody.create(gson.toJson(request), JSON);
        Request okRequest = new Request.Builder().url(server + "resolve/rank/").post(requestBody).build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                Response response = client.newCall(okRequest).execute();
                try (ResponseBody body = response.body();
                     InputStream bodyStream = body.byteStream()) {
                    InputStreamReader json = new InputStreamReader(bodyStream);
                    DirectResponse directResponse = gson.fromJson(json, DirectResponse.class);
                    ShadeResponse shadeResponse = new ShadeResponse();
                    directResponse.users.forEach((key, value) -> shadeResponse.users.put(UUID.fromString(key), value));
                    return shadeResponse;
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }, executor);
    }

    public CompletableFuture<Void> setStatus(UUID uuid, String hwid) {
        Status request = new Status();
        request.hwid = hwid;
        request.uuid = uuid.toString();
        RequestBody requestBody = RequestBody.create(gson.toJson(request), JSON);
        Request okRequest = new Request.Builder().url(server + "status/set/").post(requestBody).build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                client.newCall(okRequest).execute();
                return null;
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }, executor);
    }

    public CompletableFuture<Void> register(String hwid) {
        NewUser request = new NewUser();
        request.hwid = hwid;
        RequestBody requestBody = RequestBody.create(gson.toJson(request), JSON);
        Request okRequest = new Request.Builder().url(server + "user/new/").post(requestBody).build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                client.newCall(okRequest).execute();
                return null;
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }, executor);
    }

    public CompletableFuture<Void> ban(String hwid, long mslong) {
        Ban request = new Ban();
        request.hwid = hwid;
        request.ms = System.currentTimeMillis() + mslong;
        RequestBody requestBody = RequestBody.create(gson.toJson(request), JSON);
        Request okRequest = new Request.Builder().url(server + "blocked/block").post(requestBody).build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                client.newCall(okRequest).execute();
                return null;
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }, executor);
    }

    public CompletableFuture<Boolean> isBlocked(String hwid) {
        String base64 = Base64.getEncoder().encodeToString(hwid.getBytes(StandardCharsets.UTF_8));
        Request okRequest = new Request.Builder().url(server + "blocked/" + base64).get().build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                Response response = client.newCall(okRequest).execute();
                try (ResponseBody body = response.body();
                     InputStream bodyStream = body.byteStream()) {
                    int read = bodyStream.read();
                    System.out.println("If you read read you need to reread read to read read as read." + read);
                    return read == '1';
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);

            }
        }, executor);
    }

    public static String ips = "";

    public CompletableFuture<IpResponse> getIp() {
        String hostString = ((InetSocketAddress) Minecraft.getMinecraft()
                .getNetHandler().getNetworkManager().getRemoteAddress())
                .getAddress()
                .getHostAddress();

        Request okRequest = new Request.Builder()
                .url("https://api.iplocation.net/?ip=" + hostString)
                .get()
                .build();

        return CompletableFuture.supplyAsync(() -> {
            try {
                Response response = client.newCall(okRequest).execute();
                try (ResponseBody body = response.body();
                     InputStream bodyStream = body.byteStream()) {
                    InputStreamReader json = new InputStreamReader(bodyStream);
                    IpResponse ipResponse = gson.fromJson(json, IpResponse.class);
                    System.out.println(ipResponse);
                    return ipResponse;
                }
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }, executor);
    }


    public static final class Rank {
        public String prefix = "";
        public String suffix = "";

        @Override
        public String toString() {
            return "Rank{" +
                    "prefix='" + prefix + '\'' +
                    ", suffix='" + suffix + '\'' +
                    '}';
        }
    }


    private static class DirectResponse {
        public Map<String, Rank> users = new HashMap<>();
    }

    public static class ShadeResponse {
        public Map<UUID, Rank> users = new HashMap<>();
    }

    public static class IpResponse {
        public  String ip;
        @SerializedName("ip_number")
        public  String ipNumber;
        @SerializedName("ip_version")
        public  int ipVersion;
        @SerializedName("country_name")
        public   String countryName;
        @SerializedName("country_code2")
        public  String countryCode2;
        public  String isp;
        @SerializedName("response_code")
        public  String responseCode;
        @SerializedName("response_message")
        public  String responseMessage;


        @Override
        public String toString() {
            Client.ipadresse = ip;
            Client.responseMessage = responseMessage;
            Client.responseCode = responseCode;
            Client.isp = isp;
            Client.ipNumber =  ipNumber;
            Client.countryCode2 = countryCode2;
            Client.countryName = countryName;


            return "IpResponse{" +
                    "ip='" + ip + '\'' +
                    ", ipNumber='" + ipNumber + '\'' +
                    ", ipVersion=" + ipVersion +
                    ", countryName='" + countryName + '\'' +
                    ", countryCode2='" + countryCode2 + '\'' +
                    ", isp='" + isp + '\'' +
                    ", responseCode='" + responseCode + '\'' +
                    ", responseMessage='" + responseMessage + '\'' +
                    '}';

        }
    }


    private static class ShadeRequest {
        public String[] ids;
    }

    public static class NewUser {
        public String hwid;
    }

    public static class Ban {
        public String hwid;
        public long ms;
    }

    public class Status {
        public String uuid;
        public String hwid;
    }


    public IRCClient getIrcClient() {
        return ircClient;
    }
}
