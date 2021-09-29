package es.cic.bootcamp.individual13final.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.individual13final.model.Obra;
import es.cic.bootcamp.individual13final.model.ObraDeTipo;
import es.cic.bootcamp.individual13final.repository.ObraDeTipoRepository;

@Service
@Transactional
public class ObraDeTipoService {

    @Autowired
    private ObraDeTipoRepository obraDeTipoRepository;

	@Autowired
	private ObraService obraService;

    public Long save (ObraDeTipo obraTipo) {
        return obraDeTipoRepository.save(obraTipo).getId();
    }

    public List<ObraDeTipo> findAll() {

		List<ObraDeTipo> obraTipo = new ArrayList<>();
		obraDeTipoRepository.findAll().forEach(obraTipo::add);
		return obraTipo;

	}

    public ObraDeTipo findById(Long id) {
		ObraDeTipo ot = obraDeTipoRepository.findById(id).get();
		return ot;
	}

    public void update(ObraDeTipo ot) {

		obraDeTipoRepository.save(ot);

	}

    public void delete(Long id) {
		obraDeTipoRepository.deleteById(id);
	}

	public List<Obra> getObrasTipo(Long idTipo)
	{
		
		List<ObraDeTipo> ObrasTipo = findAll();
		List<Obra> obras = new ArrayList<>();

		for(ObraDeTipo odt : ObrasTipo)
		{
			if(idTipo == odt.getIdTipo())
			{
				obras.add(obraService.findById(odt.getIdObra()));
			}
		}

		return obras;
	}

	public void deleteAllTipo(Long idTipo)
	{
		List<ObraDeTipo> ObrasTipo = findAll();

		for(ObraDeTipo odt : ObrasTipo)
		{
			if(idTipo == odt.getIdTipo())
			{
				delete(odt.getId());
			}
		}
	}

    
}
