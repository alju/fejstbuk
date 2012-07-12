package org.openblend.fejstbuk.ui;

import org.openblend.fejstbuk.domain.Owned;
import org.openblend.fejstbuk.domain.Post;
import org.openblend.fejstbuk.domain.Status;
import org.openblend.fejstbuk.domain.User;
import org.openblend.fejstbuk.qualifiers.Current;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
@Stateless
public class RestfullWallImpl implements RestfullWall {
    private static final Comparator<Owned> COMPARATOR = new OwnedComparator();

    private EntityManager em;
    private User current;

    @SuppressWarnings("unchecked")
    public List<Status> activity(long userID) throws IOException {
        TypedQuery<Status> query = em.createQuery("select o from Status o where o.user.id = :uid", Status.class);
        query.setParameter("uid", userID);
        List<Status> list = query.getResultList();
        for (Status post :list){
            post.getUser().getPosts();
        }
        return list;
    }

    @Inject
    public void setCurrent(@Current User current) {
        this.current = current;
    }

    @PersistenceContext
    public void setEm(EntityManager em) {
        this.em = em;
    }

    private static class OwnedComparator implements Comparator<Owned> {
        public int compare(Owned o1, Owned o2) {
            return (int) (o2.getTimestamp().getTime() - o1.getTimestamp().getTime());
        }
    }
}
