package kz.ali.foodHibirnate.controller;

import kz.ali.foodHibirnate.entities.Manufacturer;
import kz.ali.foodHibirnate.repositories.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ManufacturerController {

    private final ManufacturerRepository manufacturerRepository;  // Внедрение зависимости


    @GetMapping(value = "/addmanufacture")
    public String addCountryPage() {
        return "addmanufacture";
    }

    @PostMapping(value = "/addmanufacture")
    public String addManufacture(@RequestParam(name = "manufacture_name") String name,
                                 @RequestParam(name = "manufacture_code") String code) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(name);
        manufacturer.setCode(code);
        manufacturerRepository.save(manufacturer);
        return "redirect:/";
    }
}
