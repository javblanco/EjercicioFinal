package es.cic.bootcamp.individual13final.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.bootcamp.individual13final.model.Obra;
import es.cic.bootcamp.individual13final.model.ObraDeTipo;
import es.cic.bootcamp.individual13final.service.ObraDeTipoService;

@RestController
@RequestMapping("/api/obraTipo")
@CrossOrigin(origins = "http://localhost:4200")

public class ObraDeTipoController {
    
    private static final Logger LOGGER = Logger.getLogger(ObraDeTipoController.class.getName());

    @Autowired
    private ObraDeTipoService obraDeTipoService;

    @PostMapping()
    public Long save(@Validated @RequestBody ObraDeTipo obraTipo) {

        LOGGER.info("guarda un registro de ObraTipo");
        return obraDeTipoService.save(obraTipo);

    }

    @GetMapping
    public List<ObraDeTipo> findAll() {

        LOGGER.info("busca todos los registros de obraTipo");
        return obraDeTipoService.findAll();

    }

    @GetMapping("/{id}")
    public ObraDeTipo findById(@PathVariable(name = "id") Long id) {

        LOGGER.info("busca por id un registro de obraTipo");
        return obraDeTipoService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody ObraDeTipo obraTipo) {

        LOGGER.info("actualiza un registro de actividadProceso");
        obraDeTipoService.update(obraTipo);

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id) {

        LOGGER.info("borra un registro de obraTipo");
        obraDeTipoService.delete(id);

    }

    @GetMapping("/getObra/{id}")
    public List<Obra> getObraTipo(@PathVariable(name = "id") Long id) {

        LOGGER.info("muestra las actividades de registro");
        return obraDeTipoService.getObrasTipo(id);

    }


}
