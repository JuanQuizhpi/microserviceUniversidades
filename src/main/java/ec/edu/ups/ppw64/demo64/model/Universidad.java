package ec.edu.ups.ppw64.demo64.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Universidad  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="uni_id")
	private String id;
	@Column(name="uni_nombre")
	private String nombre;
	@Column(name="uni_sede")
	private String sede;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getSede() {
		return sede;
	}



	public void setSede(String sede) {
		this.sede = sede;
	}



	@Override
	public String toString() {
		return "Universidad [id=" + id + ", nombre=" + nombre + ", sede=" + sede + "]";
	}
	
	
}
