package net.chrisbay.service;

import net.chrisbay.dao.DiscDao;
import net.chrisbay.model.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscService implements EntityService<Disc> {

    @Autowired
    private DiscDao dao;

    @Override
    public Disc get(Integer id) {
        return dao.get(id);
    }

    @Override
    public List<Disc> getAll() {
        return dao.getAll();
    }

    @Override
    public Disc save(Disc disc) {
        return dao.save(disc);
    }

    @Override
    public Disc delete(Integer id) {
        return dao.delete(id);
    }
}
