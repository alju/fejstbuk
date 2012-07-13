package org.openblend.fejstbuk.ui;

import org.openblend.fejstbuk.domain.Conversation;
import org.openblend.fejstbuk.domain.Message;
import org.openblend.fejstbuk.domain.User;
import org.openblend.fejstbuk.qualifiers.Current;
import org.openblend.fejstbuk.qualifiers.LoggedIn;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 13.7.12
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
@Stateful
@Named
@RequestScoped
public class ConversationAction {

    @PersistenceContext
    private EntityManager em;

    @Inject
    @Current
    @LoggedIn
    private User user;

    private long convID;


    public long getConvID() {
        return convID;
    }

    public void setConvID(long convID) {
        this.convID = convID;
    }

    @Produces
    @Named
    public List<Message> getConversation() {
//        TypedQuery<Message> query =  em.createQuery("select m from Message m " +
//                "where (m.user.id = :userID and m.recipient.id = :peerID) " +
//                "or (m.user.id = :peerID and m.recipient.id = :userID) " +
//                "order by m.timestamp", Message.class);

        return em.find(Conversation.class, convID).getMessages();
    }

    public User getPeer() {
        return em.find(User.class, convID);
    }
}
