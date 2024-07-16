package ec.edu.ups.ppw64.demo64.dao;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Universidad;

@Stateless
public class UniversidadesDAO {
	
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Universidad universidad) {
		em.persist(universidad);
	}
	
	public void update(Universidad univesidad){
		em.merge(univesidad);
	}
	public Universidad read(String id) {
		Universidad p = em.find(Universidad.class, id);
		return p;
	}
	
	public void delete(String id) {
		Universidad p = em.find(Universidad.class, id);
		em.remove(p);
	}
	
	public List<Universidad> getList(){
		String jsql = "SELECT p FROM Universidad p";
		Query query = em.createQuery(jsql,Universidad.class);
		List<Universidad> lista = query.getResultList();
		return lista;
	}
	
	public Universidad getUniversidadPorID(String id){
		String jpql = "SELECT c FROM Universidad c WHERE c.id = :id";
		Query q = em.createQuery(jpql, Universidad.class);
		q.setParameter("id", id);
		List<Universidad> universidades = q.getResultList();
		if(universidades.size()>0)
			return universidades.get(0);
		return null;
	}
	

}
