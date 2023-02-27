package net.chrisbay.myplasticcirclesprovider.service;

import net.chrisbay.myplasticcirclesprovider.exception.ResourceDoesNotExistException;
import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import net.chrisbay.myplasticcirclesprovider.repository.DiscManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscManufacturerService implements EntityService<DiscManufacturer> {

    @Autowired
    private DiscManufacturerRepository discManufacturerRepository;

    @Override
    public DiscManufacturer get(Integer id) {
        return discManufacturerRepository.findById(id).orElseThrow(() -> new ResourceDoesNotExistException("Item not found", id));
    }

    @Override
    public List<DiscManufacturer> getAll() {
        return discManufacturerRepository.findAll();
    }

    @Override
    public void save(DiscManufacturer discManufacturer) {
        discManufacturerRepository.save(discManufacturer);
    }

    @Override
    public void delete(Integer id) {
        discManufacturerRepository.deleteById(id);
    }
}
