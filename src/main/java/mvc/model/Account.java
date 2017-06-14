package mvc.model;

import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Transient;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String number;
	@Embedded
	private Amount balance;
	@OneToMany(mappedBy="account")
	private List<Transaction> transactions;
	
	@Transient
	private double sumTransactions;
	
	public Account(){
			
	}

	public Account(String name, String number, Amount amount) {
		this.name = name;
		this.number = number;
		this.balance = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public Amount getBalanceAmount() {
		return balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getTotal(){
		return this.sumTransactions;
	}
	
	@PostLoad
	protected void computeAmount() {
		sumTransactions = this.getBalanceAmount().getValueWithFractionDigits();
		for(Transaction t: this.getTransactions()){
			sumTransactions += t.getValue().getValueWithFractionDigits();
		}
	}

	//@OneToMany(mappedBy="account")
	public List<Transaction> getTransactions() {
		return this.transactions;
	}
}
