package mvc.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class AccountManager {
	
	@PersistenceContext(unitName="bankPersistenceUnit")
	private EntityManager entityManager;
	
	public Account save(String accountName, String accountNumber, Amount amount) throws AccountAlreadyExistingException {
		try {
			getByNumber(accountNumber);
			throw new AccountAlreadyExistingException();
		} catch (AccountDoesNotExistException e) {
			Account newAccount = new Account(accountName, accountNumber, amount);
			entityManager.persist(newAccount);
			return newAccount;
		}
	}
	/*public Transaction saveTransaction(String label, String transactionType, Amount amount) {//throws AccountAlreadyExistingException {
		//try {
			//getByNumber(accountNumber);
			//throw new AccountAlreadyExistingException();
		//} catch (AccountDoesNotExistException e) {}
			Transaction newTransaction = new Transaction(label, transactionType, amount);
			entityManager.persist(newTransaction);
			return newTransaction;
		}*/

	
	
	public Account getByNumber(String accountNumber) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("select a from Account a where a.number = :number", Account.class)
					.setParameter("number", accountNumber)
					.getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}

	public List<Account> getListAccounts() throws AccountDoesNotExistException {
		try{
			return entityManager.createQuery("SELECT a FROM Account a", Account.class).getResultList();
		}catch(NoResultException e){
			throw new AccountDoesNotExistException();
		}		
	}
}
