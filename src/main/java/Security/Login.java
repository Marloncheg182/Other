package Security;

import Utils.ConfigProperty;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.net.URISyntaxException;

/**
 * @author Romanenchuk Oleg
 * Loggining security class
 */

public class Login {

    @Inject
    private Callbacks callback;

    @Produces
    public LoginContext produceLoginContext(@ConfigProperty("loginConfigsFile") String loginConfigsFile,
                                            @ConfigProperty("loginModuleName") String loginModuleName )throws LoginException, URISyntaxException{
        System.setProperty("java.security.auth.login.config", new File(Login.class.getResource(loginConfigsFile).toURI()).getPath());

        try {
            return new LoginContext(loginModuleName, callback);
        } catch (Exception e) {
            System.out.println("wrong!");
            return null;
        }

    }

}
