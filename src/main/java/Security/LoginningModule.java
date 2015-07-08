package Security;

import Entity.Client;
import Service.ClientService;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * @author Romanenchuk Oleg
 * Login mask module
 */
public class LoginningModule implements LoginModule {

    private CallbackHandler callbackHandler;
    private ClientService clientService;
    private BeanManager beanManager;

    private ClientService getClientService() {
        if (clientService != null) {
            return clientService;
        }

        try {
            Context context = new InitialContext();
            beanManager = (BeanManager) context.lookup("java:comp/BeanManager");
            Bean<?> bean = beanManager.getBeans(ClientService.class).iterator().next();
            CreationalContext crctx = beanManager.createCreationalContext(bean);
            clientService = ((ClientService) beanManager.getReference(bean, ClientService.class, crctx));
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return clientService;

    }

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
        getClientService();
    }

    @Override
    public boolean login() throws LoginException {

        NameCallback nameCallback = new NameCallback("Name : ");
        PasswordCallback passwordCallback = new PasswordCallback("Password : ", false);

        try {
            callbackHandler.handle(new Callback[]{nameCallback, passwordCallback});
            String username = nameCallback.getName();
            String password = new String(passwordCallback.getPassword());
            nameCallback.setName("");
            passwordCallback.clearPassword();
            Client client = clientService.findClient(username, password);

            if (client == null) {
                throw new LoginException("Access denied");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }
}
