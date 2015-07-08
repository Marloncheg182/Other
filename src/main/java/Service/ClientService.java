package Service;

import Entity.Client;
import Exceptions.ValidationException;
import Utils.Loggson;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author Romanenchuk Oleg
 * Account manager class
 */

@Stateless
@Loggson
public class ClientService implements Serializable {

    @Inject
    private EntityManager manager;

    public boolean LoginChecker(final String login) {
        if (login == null)
            throw new ValidationException("not nullable");
        TypedQuery<Client> query = manager.createNamedQuery(Client.GET_BY_LOGIN, Client.class);
        query.setParameter("login", login);

        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public Client createClient(final Client client) {
        if (client == null)
            throw new ValidationException("Empty");

        manager.persist(client);

        return client;
    }

    public Client findClient(final String login) {
        if (login == null)
            throw new ValidationException("Empty");

        TypedQuery<Client> query = manager.createNamedQuery(Client.GET_BY_LOGIN, Client.class);
        query.setParameter("login", login);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Overloaded method for search by login and password
     */
    public Client findClient(final String login, final String password) {
        if (login == null)
            throw new ValidationException("Wrong login");
        if (password == null)
            throw new ValidationException("Wrong password");

        TypedQuery<Client> query = manager.createNamedQuery(Client.GET_BY_LOGIN_AND_PASSWORD, Client.class);
        query.setParameter("login", login);
        query.setParameter("password", password);

        return query.getSingleResult();
    }

    public List<Client> findAll(final Client client) {
        TypedQuery<Client> query = manager.createNamedQuery(Client.GET_ALL, Client.class);
        return query.getResultList();

    }

    public Client updateClient(final Client client){
        if (client == null)
            throw new ValidationException("Empty");

        manager.merge(client);

        return client;
    }

    public void removeClient(final Client client){
        if (client == null)
            throw new ValidationException("Empty");

        manager.remove(manager.merge(client));
    }

}
