package org.openblend.fejstbuk.ui;

import org.openblend.fejstbuk.domain.Conversation;
import org.openblend.fejstbuk.domain.Message;
import org.openblend.fejstbuk.domain.User;
import org.openblend.fejstbuk.qualifiers.Current;
import org.openblend.fejstbuk.qualifiers.LoggedIn;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 13.7.12
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */

@RequestScoped
@Named
@Stateful
public class SendMsgAction {

    @PersistenceContext
    EntityManager em;

    @LoggedIn
    @Current
    @Inject
    private User user;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void send(long convID) {
        Conversation c = em.find(Conversation.class, convID);

        Message m = new Message();
        m.setText(text);
        m.setTimestamp(new Date());
        m.setUser(user);
        if(c.getPeerA().getId() == user.getId()){
            m.setRecipient(em.find(User.class, c.getPeerB().getId()));
        }else{
            m.setRecipient(em.find(User.class, c.getPeerA().getId()));
        }

        c.addMessage(m);
    }
}