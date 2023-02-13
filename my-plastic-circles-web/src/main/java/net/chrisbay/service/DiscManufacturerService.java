package net.chrisbay.service;

import net.chrisbay.dao.DiscManufacturerDao;
import net.chrisbay.model.DiscManufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscManufacturerService implements EntityService<DiscManufacturer> {

    @Autowired
    private DiscManufacturerDao dao;

    @Override
    public DiscManufacturer get(Integer id) {
        return dao.get(id);
    }

    @Override
    public List<DiscManufacturer> getAll() {
        return dao.getAll();
    }

    @Override
    public DiscManufacturer save(DiscManufacturer discManufacturer) {
        return dao.save(discManufacturer);
    }

    @Override
    public DiscManufacturer delete(Integer id) {
        return dao.delete(id);
    }
}
