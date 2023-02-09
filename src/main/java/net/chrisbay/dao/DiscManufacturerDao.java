package net.chrisbay.dao;

import net.chrisbay.model.DiscManufacturer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class DiscManufacturerDao implements Dao<DiscManufacturer> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public DiscManufacturer get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(DiscManufacturer.class, id);
    }

    @Override
    public List<DiscManufacturer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<DiscManufacturer> cq = cb.createQuery(DiscManufacturer.class);
        Root<DiscManufacturer> root = cq.from(DiscManufacturer.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public DiscManufacturer save(DiscManufacturer discManufacturer) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(discManufacturer);
        return discManufacturer;
    }

    @Override
    public DiscManufacturer delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        DiscManufacturer discManufacturer = session.byId(DiscManufacturer.class).load(id);
        session.delete(discManufacturer);
        return discManufacturer;
    }
}
