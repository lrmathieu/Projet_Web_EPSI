package mvc.model;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class TransactionManager {
	@PersistenceContext(unitName = "bankPersistenceUnit")
	private EntityManager entityManager;

	public Transaction saveTransaction(String label, String type, Amount value, String accountNumber) {// throws
																										// AccountAlreadyExistingException
																										// {
		// try {
		// getByNumber(accountNumber);
		// throw new AccountAlreadyExistingException();
		// } catch (AccountDoesNotExistException e) {}

		// REQUETE POUR RECUPERER NUMERO DE COMPTE POUR TRANSACTION
		Account selectedAccount = entityManager
				.createQuery("select a from Account a where a.number = :accountNumber", Account.class)
				.setParameter("accountNumber", accountNumber).getSingleResult();
		if(type.equals("Dépense")){
			value.changeSignOfAmount();
		}
		Transaction newTransaction = new Transaction(label, type, value);
		newTransaction.setAccount(selectedAccount);
		entityManager.persist(newTransaction);
		return newTransaction;
	}

	public List<Transaction> getListTransactions() throws TransactionDoesNotExistException {
		try {
			return entityManager.createQuery("SELECT t FROM Transaction t", Transaction.class).getResultList();

		} catch (NoResultException e) {
			throw new TransactionDoesNotExistException();
		}
	}

	public List<Transaction> getListTransactionsByAccount(String accountNumber)
			throws TransactionDoesNotExistException {
		try {
			Account selectedAccount = entityManager
					.createQuery("select a from Account a where a.number = :accountNumber", Account.class)
					.setParameter("accountNumber", accountNumber).getSingleResult();
			return entityManager
					.createQuery("select t from Transaction t where t.account = :account", Transaction.class)
					.setParameter("account", selectedAccount).getResultList();
		} catch (NoResultException e) {
			throw new TransactionDoesNotExistException();
		}
	}

	public Double getAccountBalance(String accountNumber) throws TransactionDoesNotExistException {
		try {
			Account selectedAccount = entityManager
					.createQuery("select a from Account a where a.number = :accountNumber", Account.class)
					.setParameter("accountNumber", accountNumber).getSingleResult();
			Double accountBalance = selectedAccount.getBalanceAmount().getValueWithFractionDigits();
			List<Transaction> transactionList = this.getListTransactionsByAccount(accountNumber);

			for (Transaction t : transactionList) {
				accountBalance += t.getValue().getValueWithFractionDigits();
			}

			return accountBalance;
		} catch (NoResultException e) {
			throw new TransactionDoesNotExistException();
		}
	}

}
