package es.cic.bootcamp.individual13final.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import es.cic.bootcamp.individual13final.model.ObraDeTipo;


@DataJpaTest
class ObraDeTipoRepositoryTest {

	@Autowired
	private ObraDeTipoRepository obraDeTipoRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCrear() {
		
		ObraDeTipo obraDeTipo = generarODT();
		
		ObraDeTipo obraDeTipoGuardada = obraDeTipoRepository.save(obraDeTipo);
		
		entityManager.flush();
		
		ObraDeTipo obraDeTipoEnBBDD = entityManager.find(ObraDeTipo.class, obraDeTipoGuardada.getId());
		
		assertEquals(obraDeTipoGuardada, obraDeTipoEnBBDD, "No se ha podido guardar la obra del estilo");
		

	}

	@Test
	void testBuscar() {
		
		ObraDeTipo obraDeTipo = generarODT();
		
		ObraDeTipo odtALeer = entityManager.persist(obraDeTipo);
		
		ObraDeTipo odtBuscada = obraDeTipoRepository.findById(odtALeer.getId()).get();
		
		assertEquals(odtALeer, odtBuscada, "No se ha podido encontrar la obra del estilo");
		
	}

	@Test
	void testListar() {
		
		ObraDeTipo obraDeTipo = generarODT();
		
		ObraDeTipo odtALeer = entityManager.persist(obraDeTipo);
		
		ObraDeTipo obraDeTipo2 = generarODT();
		
		ObraDeTipo obraDeTipoALeer2 = entityManager.persist(obraDeTipo2);
		
		List<ObraDeTipo> obraDeTipos = List.of(odtALeer, obraDeTipoALeer2);
		
		List<ObraDeTipo> obraDeTiposLeidas = new ArrayList<>();
		
		obraDeTipoRepository.findAll()
			.forEach(obraDeTiposLeidas::add);
		
		assertEquals(obraDeTipos.size(), obraDeTiposLeidas.size(), "No se han encontrado todas las obras del tipo");
		
	}
	
	@Test
	void testModificar() {
		
		ObraDeTipo obraDeTipo = generarODT();
		
		ObraDeTipo obraDeTipoAModificar = entityManager.persist(obraDeTipo);
		
		ObraDeTipo obraDeTipoEnBaseDeDatos = obraDeTipoRepository.findById(obraDeTipoAModificar.getId()).get();
		
		obraDeTipoEnBaseDeDatos.setIdObra((long) 2);		
		
		entityManager.flush();
		
		ObraDeTipo obraDeTipoActualizada = obraDeTipoRepository.findById(obraDeTipoAModificar.getId()).get();
		
		assertEquals((long) 2,obraDeTipoActualizada.getIdObra());				
		
		
	}

	@Test
	void testBorrar() {
		
		ObraDeTipo obraDeTipo = generarODT();
		
		ObraDeTipo obraDeTipoABorrar = entityManager.persist(obraDeTipo);
		
		entityManager.flush();
		
		obraDeTipoRepository.deleteById(obraDeTipoABorrar.getId());
		
		ObraDeTipo actividadBorrada = entityManager.find(ObraDeTipo.class, obraDeTipoABorrar.getId());
		
		assertNull(actividadBorrada, "No se ha podido borrar la actividad de proceso");
		
	}
	
	private ObraDeTipo generarODT() {
		ObraDeTipo odt=new ObraDeTipo();
		odt.setIdObra((long)1);
		odt.setIdTipo((long)2);
		return odt;
	}
}
