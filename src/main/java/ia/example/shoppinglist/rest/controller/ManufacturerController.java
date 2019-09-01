package ia.example.shoppinglist.rest.controller;

import ia.example.shoppinglist.rest.service.implementations.ManufacturerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/manufacturer")
public class ManufacturerController extends RestController {

    private final ManufacturerServiceImpl manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerServiceImpl manufacturerService) {
        super(manufacturerService);
        this.manufacturerService = manufacturerService;
    }
}
