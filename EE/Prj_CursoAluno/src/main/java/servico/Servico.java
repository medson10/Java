package servico;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelos.Curso;
import modelos.Aluno;

@ManagedBean(name="servico",eager=true)
@ApplicationScoped
public class Servico {
	private static EntityManagerFactory emf =Persistence.createEntityManagerFactory("Prj_CursoAluno");
	
	public Servico() {
		
	}
	
	public List<Curso> getCursos() {
		
		List<Curso> cursos;
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select c From Curso c",Curso.class);
		cursos = q.getResultList();
		em.close();
		return cursos;
	}
	
	public void saveAluno(Aluno a){
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(a);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void removeAluno(Aluno a){
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    		Aluno alu = em.find(Aluno.class, a.getRegistro());
	    		em.remove(alu);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public List<Aluno> getAlunos(){
		List <Aluno> alunos; 
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("Select a From Aluno a",Aluno.class);
		alunos = q.getResultList();
		em.close();
		return alunos;
	}
	
	public void upDateAluno(Aluno a){
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.merge(a);
	    em.getTransaction().commit();
	    em.close();
		
	}
	
	public void saveCurso(Curso c){
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(c);
	    em.getTransaction().commit();
	    em.close();
	}
	
	public void upDateCurso(Curso c){
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.merge(c);
	    em.getTransaction().commit();
	    em.close();
		
	}
	
	public boolean removeCurso(Curso curso){
	    boolean sucesso = false;
		try
		{
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
	   		Curso c = em.find(Curso.class, curso.getCodigo());
	   		em.remove(c);
	   		em.getTransaction().commit();
	   		em.close();
	   		sucesso = true;
		}
		catch (Exception e) {
			
		}
	
		return sucesso;
		
	}
	
	
}
