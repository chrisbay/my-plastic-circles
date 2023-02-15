package net.chrisbay.service;

import net.chrisbay.dao.DiscManufacturerDao;
import net.chrisbay.model.DiscManufacturer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscManufacturerService implements EntityService<DiscManufacturer> {

    private final String REST_ENDPOINT_URI = "http://localhost:8081/api/manufacturer/";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscManufacturerDao dao;

    @Override
    public DiscManufacturer get(Integer id) {
        return restTemplate.getForObject(REST_ENDPOINT_URI + "{id}", DiscManufacturer.class, id);
    }

    @Override
    public DiscManufacturer[] getAll() {
        return restTemplate.getForObject(REST_ENDPOINT_URI, DiscManufacturer[].class);
    }

    @Override
    public DiscManufacturer save(DiscManufacturer discManufacturer) {

        if (discManufacturer.getId() == null) {
            return restTemplate.postForObject(REST_ENDPOINT_URI, discManufacturer, DiscManufacturer.class);
        }

        restTemplate.put(REST_ENDPOINT_URI + "{id}", discManufacturer, discManufacturer.getId());
        return get(discManufacturer.getId());
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(REST_ENDPOINT_URI + "{id}", id);
    }
}
