package mvc.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String transactionType;
	private String label;
	
	@Embedded
	private Amount recipe;
	public Transaction(String label, String transactionType, Amount recipe) {
		this.label=label;
		this.transactionType=transactionType;
		this.recipe=recipe;
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
	public Amount getRecipe() {
		return recipe;
	}
	public void setRecipe(Amount recipe) {
		this.recipe = recipe;
	}
	

}
