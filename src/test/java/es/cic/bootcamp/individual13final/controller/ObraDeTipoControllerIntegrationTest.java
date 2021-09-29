package es.cic.bootcamp.individual13final.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.time.Month;
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

import es.cic.bootcamp.individual13final.model.ObraDeTipo;
import es.cic.bootcamp.individual13final.repository.ObraDeTipoRepository;



@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ObraDeTipoIntegrationTest { 

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ObraDeTipoRepository obraDeTipoRepository;
	
	@Autowired
	private EntityManager entityManager;	
	
	@Autowired
    ObjectMapper mapper;	


	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSave() throws Exception {
		
		ObraDeTipo obraDeTipo  = generarODT();

		
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/obraTipo")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(obraDeTipo));
		
		  MvcResult result = this.mvc
			.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",notNullValue()))
			.andReturn();
		
		  String content = result.getResponse().getContentAsString();
		  long idDelResultado = Long.parseLong(content);
		  
		  obraDeTipo.setId(idDelResultado);
		  
		  ObraDeTipo ObraDeTipoBBDD = entityManager.find(ObraDeTipo.class, idDelResultado);
		  assertThat(ObraDeTipoBBDD)
          	.usingRecursiveComparison()
          	.isEqualTo(obraDeTipo);
	}

	
	@Test
	void testFindById() throws Exception {
		ObraDeTipo odt = generarODT();
		entityManager.persist(odt);
		entityManager.flush();
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/obraTipo/{id}", odt.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);		
		
		this.mvc
			.perform(mockRequest)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$",notNullValue()))
			.andExpect(jsonPath("$.id", is(odt.getId().intValue())));
	}

	@Test
	void testFindAll() throws Exception {
		ObraDeTipo odt = generarODT();
		entityManager.persist(odt);
		
		ObraDeTipo odt2 = generarODT();
		entityManager.persist(odt2);
		entityManager.flush();
		
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/api/obraTipo")
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
		ObraDeTipo odt = generarODT();
		entityManager.persist(odt);
		entityManager.flush();
		
		ObraDeTipo odtModificado = generarODT();
		odtModificado.setId(odt.getId());
		odtModificado.setIdObra((long) 2);
		
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/obraTipo")
		            .contentType(MediaType.APPLICATION_JSON)
		            .accept(MediaType.APPLICATION_JSON)
		            .content(this.mapper.writeValueAsString(odtModificado));
	
		 
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk());
		
		ObraDeTipo obraDeTipoBBDD = entityManager.find(ObraDeTipo.class, odtModificado.getId());

		assertThat(obraDeTipoBBDD)
      	.usingRecursiveComparison()
      	.isEqualTo(odtModificado);
	}

	@Test
	void testDeleteById() throws Exception {
		ObraDeTipo odt = generarODT();
		entityManager.persist(odt);
		entityManager.flush();
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.delete("/api/obraTipo/{id}", odt.getId())
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	
		this.mvc
		.perform(mockRequest)
		.andDo(
			print())
		.andExpect(status().isOk());

		ObraDeTipo odtBBDD = entityManager.find(ObraDeTipo.class, odt.getId());
	    assertNull(odtBBDD, "No borra el estilo");
	}	

	private ObraDeTipo generarODT() {
		ObraDeTipo odt=new ObraDeTipo();
		odt.setIdObra((long)1);
		odt.setIdTipo((long)2);
		return odt;
	}
}
