package net.chrisbay.service;

import net.chrisbay.dao.DiscDao;
import net.chrisbay.model.Disc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DiscService implements EntityService<Disc> {

    private final String REST_ENDPOINT_URI = "http://localhost:8081/api/disc/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscDao dao;

    @Override
    public Disc get(Integer id) {
        return restTemplate.getForObject(REST_ENDPOINT_URI + "{id}", Disc.class, id);
    }

    @Override
    public Disc[] getAll() {
        return restTemplate.getForObject(REST_ENDPOINT_URI, Disc[].class);
    }

    @Override
    public Disc save(Disc disc) {

        if (disc.getId() == null) {
            return restTemplate.postForObject(REST_ENDPOINT_URI, disc, Disc.class);
        }

        restTemplate.put(REST_ENDPOINT_URI + "{id}", disc, disc.getId());
        return get(disc.getId());
    }

    @Override
    public void delete(Integer id) {
        restTemplate.delete(REST_ENDPOINT_URI + "{id}", id);
    }
}
