package es.cic.bootcamp.individual13final.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import es.cic.bootcamp.individual13final.model.Tipo;


@DataJpaTest
public class TipoRepositoryTest {

	@Autowired
	private TipoRepository tipoRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCrear() {
		
		Tipo tipo = generarTipo();
		Tipo tipoGuardada = tipoRepository.save(tipo);
		
		entityManager.flush();
		
		Tipo tipoBBDD = entityManager.find(Tipo.class, tipoGuardada.getId());
		
		assertEquals(tipoGuardada, tipoBBDD, "No se ha podido guardar el tipo de obra");
		
	}

	@Test
	void testBuscar() {
		
		Tipo tipo = generarTipo();
		
		Tipo tipoAleer = entityManager.persist(tipo);
		
		Tipo tipoBuscado = tipoRepository.findById(tipoAleer.getId()).get();
		
		assertEquals(tipoAleer, tipoBuscado, "No se ha podido encontrar el tipo de obra");
		
	}

	@Test
	void testListar() {
		
		Tipo tipo = generarTipo();
		Tipo tipoALeer = entityManager.persist(tipo);
		
		Tipo tipo2 = generarTipo();
		Tipo tipoALeer2 = entityManager.persist(tipo2);
		
		List<Tipo> tipos = List.of(tipoALeer, tipoALeer2);
		List<Tipo> tiposLeidos = new ArrayList<>();
		
		tipoRepository.findAll()
			.forEach(tiposLeidos::add);
		
		assertEquals(tipos.size(), tiposLeidos.size(), "No se han encontrado todos los tipos de obras.");
		
	}
	
	@Test
	void testModificar() {
		
		Tipo tipo = generarTipo();
		Tipo tipoAModificar = entityManager.persist(tipo);
		
		Tipo tipoBBDD = tipoRepository.findById(tipoAModificar.getId()).get();
		tipoBBDD.setDescripcion("La descripción ha sido modificada");		
		
		entityManager.flush();
		
		Tipo tipoActualizado = tipoRepository.findById(tipoAModificar.getId()).get();
		
		assertEquals("La descripción ha sido modificada",tipoActualizado.getDescripcion());				
		
		
	}

	@Test
	void testBorrar() {
		
		Tipo tipo = generarTipo();
		
		Tipo tipoABorrar = entityManager.persist(tipo);
		
		entityManager.flush();
		
		tipoRepository.deleteById(tipoABorrar.getId());
		
		Tipo tipoBorrado = entityManager.find(Tipo.class, tipoABorrar.getId());
		
		assertNull(tipoBorrado, "No se ha podido borrar el tipo de obra.");
		
	}
	
	private Tipo generarTipo() {
		Tipo tipo=new Tipo();
		tipo.setNombre("Escultura");
		tipo.setDescripcion("Piedra tallada a fuego");
		tipo.setCaracteristicas("Se les veia desnudos");
		tipo.setEpoca("El romanico");
		tipo.setArtistas("Miguel Ángel");
		
		return tipo;
		
	}
}
