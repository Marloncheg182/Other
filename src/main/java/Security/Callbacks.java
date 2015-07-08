package Security;

import javax.inject.Named;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

/**
 * @author Romanenchuk Oleg
 */

@Named
public class Callbacks implements CallbackHandler {


    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

    }
}
