package com.spring.servicios;

import com.spring.dao.InstrumentDaoImpl;
import com.spring.entidad.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrument")
public class InstrumentService {
    @Autowired
    InstrumentDaoImpl instrumentDao;

    //@GetMapping("/listar")
    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    List<Instrument> findAll() {
        return instrumentDao.findAll();
    }

    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    public void save(@RequestBody Instrument Instrument) {
        instrumentDao.save(Instrument);
    }


}
