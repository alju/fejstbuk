package org.openblend.fejstbuk.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 * Created with IntelliJ IDEA.
 * User: priprave04
 * Date: 13.7.12
 * Time: 11:34
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Message extends Owned{
    private User recipient;
    private String text;

    @ManyToOne
    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Transient
    public String getRecap() {
        return text;
    }
}
