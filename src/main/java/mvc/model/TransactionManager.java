package mvc.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class TransactionManager {
	@PersistenceContext(unitName="bankPersistenceUnit")
	private EntityManager entityManager;
	
	public Transaction saveTransaction(String libbele, String transactionType, String recipe) {//throws AccountAlreadyExistingException {
		//try {
			//getByNumber(accountNumber);
		//	throw new AccountAlreadyExistingException();
		//} catch (AccountDoesNotExistException e) {}
			Transaction newTransaction = new Transaction(libbele, transactionType, recipe);
			entityManager.persist(newTransaction);
			return newTransaction;
		}

	public List<Transaction> getListTransactions() throws TransactionDoesNotExistException{
		try{
			return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();
			
		}catch(NoResultException e){
			throw new TransactionDoesNotExistException();
		}	
	}
	
	
	/*public Account getByNumber(String accountNumber) throws AccountDoesNotExistException {
		try {
			return entityManager.createQuery("select a from Account a where a.number = :number", Account.class)
					.setParameter("number", accountNumber)
					.getSingleResult();
		} catch (NoResultException e) {
			throw new AccountDoesNotExistException();
		}
	}*/
}
