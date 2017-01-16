package managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.RowEditEvent;

import entidades.Funcionario;
import services.FuncionarioService;

@ManagedBean
@SessionScoped
public class FuncionarioManagedBean {

	private FuncionarioService funcService =new FuncionarioService();
	private Funcionario funcionario = new Funcionario();
	private List <Funcionario> funcionarios = null;
	 
	public void salvar(){
		funcService.salvar(funcionario);
		
		if(funcionarios != null)
			funcionarios.add(funcionario);
		
		funcionario = new Funcionario();
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public List <Funcionario> getAllFuncionarios(){
		if(funcionarios == null)
			funcionarios =  funcService.allFuncionarios();
		
		return funcionarios;
	}
	
	public void onRowEdit(RowEditEvent event) {
		Funcionario f =  ((Funcionario) event.getObject());
		funcService.upDate(f);
		
    }
	
	public void remover(Funcionario funcionario){
		funcService.delete(funcionario);
		funcionarios.remove(funcionario);
	}
	

}
