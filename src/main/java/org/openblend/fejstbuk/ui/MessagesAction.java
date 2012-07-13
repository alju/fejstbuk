package org.openblend.fejstbuk.ui;

import org.openblend.fejstbuk.domain.Conversation;
import org.openblend.fejstbuk.domain.Message;
import org.openblend.fejstbuk.domain.User;
import org.openblend.fejstbuk.qualifiers.Current;
import org.openblend.fejstbuk.qualifiers.LoggedIn;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 13.7.12
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@Named
public class MessagesAction {

    @PersistenceContext
    private EntityManager em;

    @Inject
    @Current
    @LoggedIn
    private User user;

    @Produces
    @Named
    public List<Conversation> getConversations() {
        TypedQuery<Conversation> query =  em.createQuery("select m from Conversation m " +
                "where m.peerA.id = :userID or m.peerB.id = :userID", Conversation.class);
        query.setParameter("userID", user.getId());
        List<Conversation> convs = query.getResultList();
        Collections.sort(convs);
        return convs;
    }
}
