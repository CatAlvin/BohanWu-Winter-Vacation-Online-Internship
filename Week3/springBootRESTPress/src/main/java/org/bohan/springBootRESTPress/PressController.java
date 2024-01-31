package org.bohan.springBootRESTPress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PressController {

    @Autowired
    private IPressService pressService;

    @GetMapping(value = "/press/{id}")
    public Press getPressById(@PathVariable long id) {
        return pressService.findById(id);
    }
}
