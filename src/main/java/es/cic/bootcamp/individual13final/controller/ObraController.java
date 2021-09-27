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
import es.cic.bootcamp.individual13final.service.ObraService;

@RestController
@RequestMapping("/api/obra")
@CrossOrigin(origins = "http://localhost:4200")
public class ObraController {

	private static final Logger LOGGER = Logger.getLogger(ObraController.class.getName());

    @Autowired 
    private ObraService obraService;

    @PostMapping()
    public Long save (@Validated @RequestBody Obra obra) {
        
        LOGGER.info("guarda un registro de una obra de arte");
        return obraService.save(obra);

    }

    @GetMapping
	public List<Obra> findAll(){
		
        LOGGER.info("busca todos los registros de obras de arte");
        return obraService.findAll();
		
	}

    @GetMapping("/{id}")
	public Obra findById(@PathVariable(name= "id")Long id) {
		
        LOGGER.info("busca por id un registro de una obra de arte");
		return obraService.findById(id);
	}

    @PutMapping
	public void update(@RequestBody Obra obra) {
		
        LOGGER.info("actualiza un registro de una obra de arte");
        obraService.update(obra);
		
	}

    @DeleteMapping("/{id}")
	public void delete(@PathVariable(name= "id")Long id) {
		
        LOGGER.info("borra un registro de una obra de arte");
        obraService.delete(id);

    }
	
}
