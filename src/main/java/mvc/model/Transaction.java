package mvc.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String transactionType;
	private String label;
	
	@Embedded
	private Amount value;
	@ManyToOne
	@JoinColumn(name="idAccount")
	private Account account;
	
	public Transaction(){
		
	}
	
	public Transaction(String label, String transactionType, Amount value) {
		this.label=label;
		this.transactionType=transactionType;
		this.value=value;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	/*public String getBalanceAmount() {
		return balance;
	}*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Amount getValue() {
		return value;
	}
	public void setValue(Amount value) {
		this.value = value;
	}
	
	public Account getAccount(){
		return this.account;
	}
	public void setAccount(Account account){
		this.account = account;
	}

}
