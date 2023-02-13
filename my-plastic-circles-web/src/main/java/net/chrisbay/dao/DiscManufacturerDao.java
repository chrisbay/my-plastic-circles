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
        Query query = session.createQuery("from DiscManufacturer where id= :id");
        query.setParameter("id", id);
        return (DiscManufacturer) query.getSingleResult();
    }

    @Override
    public List<DiscManufacturer> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from DiscManufacturer");
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

        Query query = session.createQuery("from DiscManufacturer where id= :id");
        query.setParameter("id", id);
        DiscManufacturer discManufacturer = (DiscManufacturer) query.getSingleResult();

        query = session.createQuery("delete from DiscManufacturer where id= :id");
        query.setParameter("id", id);
        query.executeUpdate();

        return discManufacturer;
    }
}
