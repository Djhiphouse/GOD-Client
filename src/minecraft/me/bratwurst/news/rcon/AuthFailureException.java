package me.bratwurst.news.rcon;

public class AuthFailureException
        extends RconClientException {
    public AuthFailureException() {
        super("Authentication failure");
    }
}
