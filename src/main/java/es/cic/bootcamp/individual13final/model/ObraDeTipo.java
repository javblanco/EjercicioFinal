package es.cic.bootcamp.individual13final.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ObraDeTipo {

    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private Long idObra;
    private Long idTipo;

    public ObraDeTipo() {}
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getIdObra() {
        return idObra;
    }
    public void setIdObra(Long idObra) {
        this.idObra = idObra;
    }
    public Long getIdTipo() {
        return idTipo;
    }
    public void setIdTipo(Long idTipo) {
        this.idTipo = idTipo;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObraDeTipo other = (ObraDeTipo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
	

    
}
