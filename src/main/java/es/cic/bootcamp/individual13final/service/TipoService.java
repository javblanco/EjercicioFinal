package es.cic.bootcamp.individual13final.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.individual13final.model.Tipo;
import es.cic.bootcamp.individual13final.repository.TipoRepository;

@Service
@Transactional
public class TipoService {

	@Autowired
	private TipoRepository tipoRepository;
	
	 public Long save (Tipo tipo) {
	        return tipoRepository.save(tipo).getId();
	    }

	    public List<Tipo> findAll() {

			List<Tipo> tipo = new ArrayList<>();
			tipoRepository.findAll().forEach(tipo::add);
			return tipo;

		}

	    public Tipo findById(Long id) {
	    	
	    	Tipo tipo = tipoRepository.findById(id).get();
			return tipo;

		}

	    public void update(Tipo tipo) {
	    	
			tipoRepository.save(tipo);

		}

	    public void delete(Long id) {
	    	
			tipoRepository.deleteById(id);		
		}
	
}
