package org.openblend.fejstbuk.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 13.7.12
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Conversation extends AbstractEntity implements Comparable<Conversation>{
    private User peerA;
    private User peerB;
    private List<Message> messages;

    @ManyToOne
    public User getPeerA() {

        return peerA;
    }

    public void setPeerA(User peerA) {
        this.peerA = peerA;
    }

    @ManyToOne
    public User getPeerB() {
        return peerB;
    }

    public void setPeerB(User peerB) {
        this.peerB = peerB;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message m) {
        getMessages().add(m);
    }

    @Transient
    public Message getLastMessage() {
        if(messages.isEmpty()){
            return null;
        }
        return getMessages().get(getMessages().size()-1);
    }

    @Override
    public int compareTo(Conversation o) {
        return getLastMessage().getTimestamp().compareTo(o.getLastMessage().getTimestamp());
    }
}
