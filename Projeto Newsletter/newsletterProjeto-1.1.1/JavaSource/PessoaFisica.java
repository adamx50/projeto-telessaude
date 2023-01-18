import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;



@Entity
@DiscriminatorValue("1")
public class PessoaFisica extends PessoaPai {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1159003935638387381L;
	
	@Column(name="cpf")
	private String cpf;
	
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

    
}