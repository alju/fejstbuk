package org.openblend.fejstbuk.domain;

import java.util.Date;

import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@MappedSuperclass
public abstract class Owned extends AbstractEntity  implements Comparable<Owned>{
    private User user;
    private Date timestamp;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Owned o) {
        return this.timestamp.compareTo(o.getTimestamp());
    }
}
