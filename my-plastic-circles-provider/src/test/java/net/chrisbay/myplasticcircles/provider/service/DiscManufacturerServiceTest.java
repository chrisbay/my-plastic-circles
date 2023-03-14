package net.chrisbay.myplasticcircles.provider.service;

import net.chrisbay.myplasticcircles.provider.exception.ResourceDoesNotExistException;
import net.chrisbay.myplasticcircles.provider.model.DiscManufacturer;
import net.chrisbay.myplasticcircles.provider.repository.DiscManufacturerRepository;
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
@SpringBootTest(classes = { RepositoryMocksConfig.class, DiscManufacturerService.class })
public class DiscManufacturerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    DiscManufacturerRepository discManufacturerRepository;

    @Autowired
    DiscManufacturerService discManufacturerService;

    private final List<DiscManufacturer> manufacturers = new ArrayList<>();

    @BeforeClass
    public void setup() {

        MockitoAnnotations.openMocks(this);

        DiscManufacturer manufacturer1 = new DiscManufacturer("Discraft");
        manufacturer1.setId(1);
        this.manufacturers.add(manufacturer1);

        DiscManufacturer manufacturer2 = new DiscManufacturer("Mint");
        manufacturer2.setId(2);
        this.manufacturers.add(manufacturer2);

        DiscManufacturer manufacturer3 = new DiscManufacturer("EV-7");
        manufacturer3.setId(3);
        this.manufacturers.add(manufacturer3);
    }

    @Test
    public void shouldReturnAllManufacturers() {
        when(discManufacturerRepository.findAll()).thenReturn(this.manufacturers);
        List<DiscManufacturer> res = discManufacturerService.getAll();
        assertEquals(this.manufacturers, res);
    }

    @Test
    public void shouldReturnManufacturerById() {
        DiscManufacturer manufacturer = this.manufacturers.get(0);
        when(discManufacturerRepository.findById(manufacturer.getId())).thenReturn(Optional.of(manufacturer));
        DiscManufacturer res = discManufacturerService.get(manufacturer.getId());
        assertEquals(manufacturer, res);
    }

    @Test
    public void shouldSaveManufacturer() {
        discManufacturerService.save(this.manufacturers.get(0));
        verify(discManufacturerRepository).save(any(DiscManufacturer.class));
    }

    @Test
    public void shouldDeleteManufacturer() {
        discManufacturerService.delete(this.manufacturers.get(0).getId());
        verify(discManufacturerRepository).deleteById(any(Integer.class));
    }

    @Test(expectedExceptions = ResourceDoesNotExistException.class)
    public void shouldThrowExceptionForInvalidId() {
        discManufacturerService.get(42);
    }

}
