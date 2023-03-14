package net.chrisbay.myplasticcircles.provider.service;

import net.chrisbay.myplasticcircles.provider.exception.ResourceDoesNotExistException;
import net.chrisbay.myplasticcircles.provider.model.Disc;
import net.chrisbay.myplasticcircles.provider.repository.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscService implements EntityService<Disc> {

    @Autowired
    private DiscRepository discRepository;


    @Override
    public Disc get(Integer id) {
        return discRepository.findById(id).orElseThrow(() -> new ResourceDoesNotExistException("Item not found", id));
    }

    @Override
    public List<Disc> getAll() {
        return discRepository.findAll();
    }

    @Override
    public void save(Disc disc) {
        discRepository.save(disc);
    }

    @Override
    public void delete(Integer id) {
        discRepository.deleteById(id);
    }
}
