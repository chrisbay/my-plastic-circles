package net.chrisbay.dao;

import net.chrisbay.model.Disc;
import net.chrisbay.model.DiscManufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Repository
public class DiscDao implements Dao<Disc> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Disc get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Disc.class, id);
    }

    @Override
    public List<Disc> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Disc> cq = cb.createQuery(Disc.class);
        Root<Disc> root = cq.from(Disc.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public Disc save(Disc disc) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(disc);
        return disc;
    }

    @Override
    public Disc delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Disc disc = session.byId(Disc.class).load(id);
        session.delete(disc);
        return disc;
    }
}
