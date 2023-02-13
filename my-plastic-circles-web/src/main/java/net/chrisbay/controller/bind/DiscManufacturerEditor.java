package net.chrisbay.controller.bind;

import net.chrisbay.model.DiscManufacturer;
import net.chrisbay.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.beans.PropertyEditorSupport;

public class DiscManufacturerEditor extends PropertyEditorSupport {

    EntityService<DiscManufacturer> discManufacturerService;

    public DiscManufacturerEditor (EntityService<DiscManufacturer> discManufacturerService) {
        this.discManufacturerService = discManufacturerService;
    }

    @Override
    public void setAsText(String text) {
        DiscManufacturer manufacturer = discManufacturerService.get(Integer.parseInt(text));
        setValue(manufacturer);
    }
}
