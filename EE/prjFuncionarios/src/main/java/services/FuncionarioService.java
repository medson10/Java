package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Funcionario;



public class FuncionarioService {
	
	private EntityManagerFactory emf;
	
	
	
	public FuncionarioService() {
		emf = Persistence.createEntityManagerFactory("prjFuncionarios");
	}

	public void salvar(Funcionario funcionario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(funcionario);
		em.getTransaction().commit();
		em.close();
	}

	public List<Funcionario> allFuncionarios() {
		List<Funcionario> list;

		EntityManager em = emf.createEntityManager();
		list = em.createQuery("Select f from Funcionario f").getResultList();
		em.close();

		return list;

	}

	public void upDate(Funcionario funcionario) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(funcionario);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Funcionario funcionario) {
		EntityManager em = emf.createEntityManager();
		funcionario = em.find(Funcionario.class, funcionario.getCodigo());
		em.getTransaction().begin();
		em.remove(funcionario);
		em.getTransaction().commit();
		em.close();
	}
}
