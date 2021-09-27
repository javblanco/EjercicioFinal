package es.cic.bootcamp.individual13final.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.individual13final.model.Obra;
import es.cic.bootcamp.individual13final.repository.ObraRepository;

@Service
@Transactional
public class ObraService {
	
	@Autowired
	private ObraRepository obraRepository;
	
	 public Long save (Obra obra) {
	        return obraRepository.save(obra).getId();
	    }

	    public List<Obra> findAll() {

			List<Obra> obra = new ArrayList<>();
			obraRepository.findAll().forEach(obra::add);
			return obra;

		}

	    public Obra findById(Long id) {
	    	Obra obra = obraRepository.findById(id).get();
			return obra;

		}

	    public void update(Obra obra) {
	    	
			obraRepository.save(obra);

		}

	    public void delete(Long id) {
	    	
			obraRepository.deleteById(id);		
		}
}
