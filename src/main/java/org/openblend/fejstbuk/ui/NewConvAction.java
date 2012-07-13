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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 13.7.12
 * Time: 15:47
 * To change this template use File | Settings | File Templates.
 */
@RequestScoped
@Named
@Stateful
public class NewConvAction {
    @PersistenceContext
    EntityManager em;

    @LoggedIn
    @Current
    @Inject
    private User user;

    private String text;
    private long friendID;

    public long getFriendID() {
        return friendID;
    }

    public void setFriendID(long friendID) {
        this.friendID = friendID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void create() {
        Conversation conv = new Conversation();
        conv.setPeerA(user);
        conv.setPeerB(em.find(User.class, friendID));
        conv.setMessages(new ArrayList<Message>());

        Message m = new Message();
        m.setText(text);
        m.setTimestamp(new Date());
        m.setUser(user);
        m.setRecipient(conv.getPeerB());

        conv.addMessage(m);
        em.persist(conv);
    }

    @Produces
    @Named
    public List<User> getFriends() {
        return new ArrayList<User>(user.getFriends());
    }
}
