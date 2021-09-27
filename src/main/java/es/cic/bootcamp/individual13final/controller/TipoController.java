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

import es.cic.bootcamp.individual13final.model.Tipo;
import es.cic.bootcamp.individual13final.service.TipoService;

@RestController
@RequestMapping("/api/tipo")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoController {

	private static final Logger LOGGER = Logger.getLogger(TipoController.class.getName());

    @Autowired 
    private TipoService tipoService;

    @PostMapping()
    public Long save (@Validated @RequestBody Tipo tipo) {
        
        LOGGER.info("guarda un registro del  tipo de arte");
        return tipoService.save(tipo);

    }

    @GetMapping
	public List<Tipo> findAll(){
		
        LOGGER.info("busca todos los registros de tipos de arte");
        return tipoService.findAll();
		
	}

    @GetMapping("/{id}")
	public Tipo findById(@PathVariable(name= "id")Long id) {
		
        LOGGER.info("busca por id un registro de un tipo de arte");
		return tipoService.findById(id);
	}

    @PutMapping
	public void update(@RequestBody Tipo tipo) {
		
        LOGGER.info("actualiza un registro del tipo de arte");
        tipoService.update(tipo);
		
	}

    @DeleteMapping("/{id}")
	public void delete(@PathVariable(name= "id")Long id) {
		
        LOGGER.info("borra un registro del tipo de arte");
        tipoService.delete(id);

    }
	
}
