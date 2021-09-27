package es.cic.bootcamp.individual13final.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo")
public class Tipo {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String descripcion;
	private String caracteristicas;
	private String epoca;
	private String artistas;
	
	public Tipo(String nombre, String descripcion, String caracteristicas,
			String epoca, String artistas) {
		super();
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.caracteristicas=caracteristicas;
		this.epoca=epoca;
		this.artistas=artistas;
	}
	
	public Tipo() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCaracteristicas() {
		return caracteristicas;
	}
	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}
	public String getEpoca() {
		return epoca;
	}
	public void setEpoca(String epoca) {
		this.epoca = epoca;
	}
	public String getArtistas() {
		return artistas;
	}
	public void setArtistas(String artistas) {
		this.artistas = artistas;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tipo other = (Tipo) obj;
		return Objects.equals(id, other.id);
	}	
}
