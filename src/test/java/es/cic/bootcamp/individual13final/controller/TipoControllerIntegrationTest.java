package es.cic.bootcamp.individual13final.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.cic.bootcamp.individual13final.model.Obra;
import es.cic.bootcamp.individual13final.model.Tipo;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TipoControllerIntegrationTest {
	
	@Autowired
	private MockMvc mvc;
		
	@Autowired
	private EntityManager entityManager;	
	
	@Autowired
    ObjectMapper mapper;	


	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void testSave() throws Exception {
		
		Tipo tipo = generarTipo();
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/tipo")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(tipo));
		
		  MvcResult result = this.mvc
			.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",notNullValue()))
			.andReturn();
		
		  String content = result.getResponse().getContentAsString();
		  long idDelTipo = Long.parseLong(content);
		  
		  tipo.setId(idDelTipo);
		  
		  Tipo TipoBBDD = entityManager.find(Tipo.class, idDelTipo);
		  assertThat(TipoBBDD)
          	.usingRecursiveComparison()
          	.isEqualTo(tipo);
	}
	
	@Test
	void testFindById() throws Exception {
		Tipo tipo = generarTipo();
		
		entityManager.persist(tipo);
		entityManager.flush();
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/tipo/{id}", tipo.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);		
		
		this.mvc
			.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",notNullValue()))
			.andExpect(jsonPath("$.id", is(tipo.getId().intValue())));
	}

	@Test
	void testFindAll() throws Exception {
		Tipo tipo = generarTipo();
		entityManager.persist(tipo);
		
		Tipo tipo2 = generarTipo();
		entityManager.persist(tipo2);
		entityManager.flush();
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/tipo")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);	
		
		
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk())
		.andExpect(
			jsonPath("$",notNullValue()))
		.andExpect(
			jsonPath("$", hasSize(2)));
	}

	@Test
	void testUpdate() throws Exception {
		Tipo tipo = generarTipo();
		entityManager.persist(tipo);
		entityManager.flush();
		
		Tipo tipoModificado  = generarTipo();
		tipoModificado.setId(tipo.getId());
		tipoModificado.setDescripcion("Obras hechas a mano durante mucho tiempo");
		
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/tipo")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(tipoModificado));
	
		 
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk());
		
		Tipo tipoBBDD = entityManager.find(Tipo.class, tipoModificado.getId());

		assertThat(tipoBBDD)
      	.usingRecursiveComparison()
      	.isEqualTo(tipoModificado);
	}

	@Test
	void testDeleteById() throws Exception {
		Tipo tipo = generarTipo();

		entityManager.persist(tipo);
		entityManager.flush();
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/tipo/{id}", tipo.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk());

	    Obra tipoBBDD = entityManager.find(Obra.class, tipo.getId());
	    assertNull(tipoBBDD, "No borra el tipo de obras");
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
