package me.bratwurst.AltManager;

public final class Alt {

    private String mask;
    private String username;
    private String password;

    public Alt(final String username, final String password) {
        this(username, password, "");
    }

    public Alt(String username, String password, String mask) {
        this.mask = "";
        this.username = username;
        this.password = password;
        this.mask = mask;
    }

    public String getMask() {
        return this.mask;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
