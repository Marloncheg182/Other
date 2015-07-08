package Security;

import Forms.Profile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * @author Romanenchuk Oleg
 * Callback handlers
 */

@Named
public class Callbacks implements CallbackHandler {

    @Inject
    @RequestScoped
    private Profile profile;
    
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback)
            {
                NameCallback nameCallback = (NameCallback)callback;
                nameCallback.setName(profile.getLogin());
            } else if (callback instanceof PasswordCallback){
                PasswordCallback passwordCallback = (PasswordCallback)callback;
                passwordCallback.setPassword(profile.getPassword1().toCharArray());
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}
