package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.UniversidadesDAO;
import ec.edu.ups.ppw64.demo64.model.Universidad;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUniversidades {

	@Inject
	private UniversidadesDAO daoUniversidad;

	
	public void guardarUniversidades(Universidad universidad) {
		// TODO Auto-generated method stub
		Universidad uni = daoUniversidad.read(universidad.getId());
		if (uni != null){
			daoUniversidad.update(universidad);
		}else {
			daoUniversidad.insert(universidad);
		}
		
	}

	
	public void actualizarUniversidad(Universidad universidad) throws Exception {
		// TODO Auto-generated method stub
		Universidad uni = daoUniversidad.read(universidad.getId());
		if (uni != null){
			daoUniversidad.update(universidad);
		}else {
			throw new Exception("Universidad no existe");
		}
	}

	
	public Universidad getUniversidadPorID(String id) throws Exception {
		// TODO Auto-generated method stub
		Universidad uni = daoUniversidad.read(id);
		if(uni != null) {
			return daoUniversidad.getUniversidadPorID(id);
		}else {
			throw new Exception("Universidad no existe");
		}
	}

	
	public void borrarUniversidad(String id) {
		// TODO Auto-generated method stub
		daoUniversidad.delete(id);
	}

	
	public List<Universidad> getUniversidades() {
		// TODO Auto-generated method stub
		return daoUniversidad.getList();
	}
}
