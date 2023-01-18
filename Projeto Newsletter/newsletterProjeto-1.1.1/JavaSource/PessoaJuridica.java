import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class PessoaJuridica extends PessoaPai {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2744958072442611918L;
	
	@Column(name="cnpj")
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	
}