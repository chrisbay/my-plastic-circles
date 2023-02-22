package net.chrisbay.myplasticcirclesprovider.service;

import net.chrisbay.myplasticcirclesprovider.RepositoryMocksConfig;
import net.chrisbay.myplasticcirclesprovider.exception.ResourceDoesNotExistException;
import net.chrisbay.myplasticcirclesprovider.model.Disc;
import net.chrisbay.myplasticcirclesprovider.model.DiscManufacturer;
import net.chrisbay.myplasticcirclesprovider.repository.DiscRepository;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@SpringBootTest(classes = { RepositoryMocksConfig.class, DiscRepository.class, DiscService.class })
public class DiscServiceTest extends AbstractTestNGSpringContextTests {


    @Autowired
    DiscService discService;

    @Autowired
    DiscRepository discRepository;

    private final List<Disc> discs = new ArrayList<>();

    @BeforeClass
    public void setup() {

        MockitoAnnotations.openMocks(this);

        DiscManufacturer discManufacturer = new DiscManufacturer("Discraft");

        Disc disc1 = new Disc("Avenger SS", discManufacturer, 10, 5, -3, 1);
        disc1.setId(1);
        this.discs.add(disc1);

        Disc disc2 = new Disc("Wasp", discManufacturer, 5, 3, 0, 2);
        disc2.setId(2);
        this.discs.add(disc2);

        Disc disc3 = new Disc("Buzzz", discManufacturer, 5, 4, -1, 1);
        disc3.setId(3);
        this.discs.add(disc3);
    }

    @Test
    public void shouldReturnAllDiscs() {
        when(discRepository.findAll()).thenReturn(this.discs);
        List<Disc> res = discService.getAll();
        assertEquals(this.discs, res);
    }

    @Test
    public void shouldReturnDiscById() {
        Disc disc = this.discs.get(0);
        when(discRepository.findById(disc.getId())).thenReturn(Optional.of(disc));
        Disc res = discService.get(disc.getId());
        assertEquals(disc, res);
    }

    @Test
    public void shouldSaveDisc() {
        discService.save(this.discs.get(0));
        verify(discRepository).save(any(Disc.class));
    }

    @Test
    public void shouldDeleteDisc() {
        discService.delete(this.discs.get(0).getId());
        verify(discRepository).deleteById(any(Integer.class));
    }

    @Test(expectedExceptions = ResourceDoesNotExistException.class)
    public void shouldThrowExceptionForInvalidId() {
        discService.get(42);
    }

}
