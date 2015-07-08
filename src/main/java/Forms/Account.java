package Forms;

import Entity.Client;
import Service.ClientService;
import Utils.Loggson;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.Serializable;

/**
 * @author Romanenchuk Oleg
 * Account class
 */

@Named
@SessionScoped
@Loggson
@CatchException
public class Account extends Controller implements Serializable {

    @Inject
    private ClientService clientService;

    @Inject
    private Profile profile;

    @Inject
    private Conversation conversation;

    @Produces
    @LoggedIn
    private Client loggedIn;

    @SuppressWarnings("CdiUnproxyableBeanTypesInspection")
    @Inject
    @SessionScoped
    private LoginContext loginContext;

    public String doLogin() throws LoginException {
        if ("".equals(profile.getLogin())) {
            addWarning("id_failed");
            return null;
        }
        if ("".equals(profile.getPassword1())) {
            addWarning("pass_failed");
            return null;
        }

        loginContext.login();
        loggedIn = clientService.findClient(profile.getLogin());
        return "main.faces";
    }

    public String createNewAccount() {
        if (clientService.LoginChecker(profile.getLogin())) {
            addWarning("login_exists");
            return null;
        }

        if ("".equals(profile.getLogin()) || "".equals(profile.getPassword1()) || "".equals(profile.getPassword2())) {
            addWarning("id_pass_failed");
            return null;
        } else if (!profile.getPassword1().equals(profile.getPassword2())) {
            addWarning("both_pass_same");
            return null;
        }

        loggedIn = new Client();
        loggedIn.setLogin(profile.getLogin());
        loggedIn.setPassword(profile.getPassword1());

        return "createaccount.faces";
    }

    public String createClient() {
        loggedIn = clientService.createClient(loggedIn);
        return "main.faces";
    }

    public String loggout() {
        loggedIn = null;
        if (!conversation.isTransient()) {
            conversation.end();
        }
        addInformation("loggedout");
        return "main.faces";
    }

    public String updateAccount() {
        loggedIn = clientService.updateClient(loggedIn);
        addInformation("account_updated");
        return "showaccount.faces";
    }

    public boolean isLoggedIn() {
        return loggedIn != null;
    }

    public Client getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Client loggedIn) {
        this.loggedIn = loggedIn;
    }

}
