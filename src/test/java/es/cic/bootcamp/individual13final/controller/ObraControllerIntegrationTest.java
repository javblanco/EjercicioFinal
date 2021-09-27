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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ObraControllerIntegrationTest {
	
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
		
		Obra obra  = generarObra();
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/obra")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(obra));
		
		  MvcResult result = this.mvc
			.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",notNullValue()))
			.andReturn();
		
		  String content = result.getResponse().getContentAsString();
		  long idDeLaObra = Long.parseLong(content);
		  
		  obra.setId(idDeLaObra);
		  
		  Obra ObraBBDD = entityManager.find(Obra.class, idDeLaObra);
		  assertThat(ObraBBDD)
          	.usingRecursiveComparison()
          	.isEqualTo(obra);
	}
	
	@Test
	void testFindById() throws Exception {
		Obra obra  = generarObra();
		
		entityManager.persist(obra);
		entityManager.flush();
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/obra/{id}", obra.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);		
		
		this.mvc
			.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",notNullValue()))
			.andExpect(jsonPath("$.id", is(obra.getId().intValue())));
	}

	@Test
	void testFindAll() throws Exception {
		Obra obra  = generarObra();
		entityManager.persist(obra);
		
		Obra obra2  = generarObra();
		entityManager.persist(obra2);
		entityManager.flush();
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/obra")
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
		Obra obra  = generarObra();
		entityManager.persist(obra);
		entityManager.flush();
		
		Obra obraModificado  = generarObra();
		obraModificado.setId(obra.getId());
		obraModificado.setDescripcion("Esta pardo el David este eh");
		
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/obra")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(obraModificado));
	
		 
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk());
		
		Obra obraBBDD = entityManager.find(Obra.class, obraModificado.getId());

		assertThat(obraBBDD)
      	.usingRecursiveComparison()
      	.isEqualTo(obraModificado);
	}

	@Test
	void testDeleteById() throws Exception {
		Obra obra = generarObra();
		entityManager.persist(obra);
		entityManager.flush();
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/obra/{id}", obra.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk());

	    Obra obraBBDD = entityManager.find(Obra.class, obra.getId());
	    assertNull(obraBBDD, "No borra la obra");
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
