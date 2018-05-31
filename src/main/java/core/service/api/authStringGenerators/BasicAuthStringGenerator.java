package core.service.api.authStringGenerators;

import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.util.prefs.Preferences;

public class BasicAuthStringGenerator implements AuthStringGenerator
{
    private String user;
    private String password;

    public BasicAuthStringGenerator(Preferences config) {
        setUser(config.get("user", null))
                .setPassword(config.get("password", null));
    }

    public BasicAuthStringGenerator(String user, String password) {
        setUser(user).setPassword(password);
    }

    @Override
    public String generate() {
        byte[] authStringBytes = null;
        String authString = getUser() + ":" + getPassword();

        try {
            authStringBytes = authString.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Something went wrong while creating auth string.");
        }

        return "Basic " + Base64Utils.encodeToString(authStringBytes);
    }

    private String getUser() {
        return user;
    }

    private BasicAuthStringGenerator setUser(String user) {
        this.user = user;
        return this;
    }

    private String getPassword() {
        return password;
    }

    private BasicAuthStringGenerator setPassword(String password) {
        this.password = password;
        return this;
    }
}