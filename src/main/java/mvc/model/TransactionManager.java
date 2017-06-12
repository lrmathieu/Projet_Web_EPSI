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
	
	public Transaction saveTransaction(String label, String type, Amount value) {//throws AccountAlreadyExistingException {
		//try {
			//getByNumber(accountNumber);
		//	throw new AccountAlreadyExistingException();
		//} catch (AccountDoesNotExistException e) {}
		
		//REQUETE POUR RECUPERER NUMERO DE COMPTE POUR TRANSACTION
			Account selectedAccount = entityManager.createQuery("select a from Account a where a.id = :id", Account.class)
			.setParameter("id", 1)
			.getSingleResult();
			Transaction newTransaction = new Transaction(label, type, value);
			newTransaction.setAccount(selectedAccount);
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

	public List<Transaction> getListTransactionsByAccount(String accountNumber) throws TransactionDoesNotExistException {
		try{
			Account selectedAccount = entityManager.createQuery("select a from Account a where a.number = :accountNumber", Account.class)
			.setParameter("accountNumber", accountNumber)
			.getSingleResult();
			return entityManager.createQuery("select t from Transaction t where t.account = :account", Transaction.class)
			.setParameter("account", selectedAccount)
			.getResultList();
		}catch(NoResultException e){
			throw new TransactionDoesNotExistException();
		}		
	}
	
}
