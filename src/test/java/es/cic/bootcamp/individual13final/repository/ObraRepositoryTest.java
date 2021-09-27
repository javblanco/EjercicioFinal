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

import es.cic.bootcamp.individual13final.model.Obra;

@DataJpaTest
public class ObraRepositoryTest {
	
	@Autowired
	private ObraRepository obraRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCrear() {
		
		Obra obra = generarObra();		
		Obra obraGuardada = obraRepository.save(obra);
		
		entityManager.flush();
		
		Obra obraBBDD = entityManager.find(Obra.class, obraGuardada.getId());
		
		assertEquals(obraGuardada, obraBBDD, "No se ha podido guardar la obra.");
		
	}

	@Test
	void testBuscar() {
		
		Obra obra = generarObra();		
		
		Obra obraAleer = entityManager.persist(obra);
		
		Obra obraBuscada = obraRepository.findById(obraAleer.getId()).get();
		
		assertEquals(obraAleer, obraBuscada, "No se ha podido encontrar la obra");
		
	}

	@Test
	void testListar() {
		
		Obra obra = generarObra();		
		Obra obraALeer = entityManager.persist(obra);
		
		Obra obra2 = generarObra();		
		Obra obraALeer2 = entityManager.persist(obra2);
		
		List<Obra> obras = List.of(obraALeer, obraALeer2);
		List<Obra> obrasLeidas = new ArrayList<>();
		
		obraRepository.findAll()
			.forEach(obrasLeidas::add);
		
		assertEquals(obras.size(), obrasLeidas.size(), "No se han encontrado todas las obras.");
		
	}
	
	@Test
	void testModificar() {
		
		Obra obra = generarObra();
		Obra obraAModificar = entityManager.persist(obra);
		
		Obra obraEnBaseDeDatos = obraRepository.findById(obraAModificar.getId()).get();
		obraEnBaseDeDatos.setDescripcion("La descripción ha sido modificada");		
		
		entityManager.flush();
		
		Obra obraActualizada = obraRepository.findById(obraAModificar.getId()).get();
		
		assertEquals("La descripción ha sido modificada",obraActualizada.getDescripcion());				
		
		
	}

	@Test
	void testBorrar() {
		
		Obra obra = generarObra();
		
		Obra obraABorrar = entityManager.persist(obra);
		
		entityManager.flush();
		
		obraRepository.deleteById(obraABorrar.getId());
		
		Obra obraBorrada = entityManager.find(Obra.class, obraABorrar.getId());
		
		assertNull(obraBorrada, "No se ha podido borrar la obra.");
		
	}
	
	private Obra generarObra() {
		Obra obra=new Obra();
		obra.setNombre("David");
		obra.setAutor("Miguel Ángel");
		obra.setDescripcion("Pedazo escultura fiera");
		obra.setFecha(1504);
		obra.setTipo("Escultura");
		
		return obra;
	}

}
