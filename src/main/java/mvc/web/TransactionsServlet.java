package mvc.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.Account;
import mvc.model.AccountDoesNotExistException;
import mvc.model.AccountManager;
import mvc.model.Amount;
import mvc.model.Transaction;
import mvc.model.TransactionManager;
@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	@EJB
	private TransactionManager transactionManager;
	@EJB
	private AccountManager accountManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Account account = this.accountManager.getByNumber(req.getParameter("accountNumber"));
			req.setAttribute("account", account);
		} catch (AccountDoesNotExistException e) {
			log("Account does not exist", e);
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/transaction.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//try {			
			String transactionBalanceInteger =req.getParameter("transactionBalanceInteger");
			String transationBalanceFraction = req.getParameter("transationBalanceFraction");
			String libelle = req.getParameter("libbele");
			String transactType = req.getParameter("transactionType");
			Amount amountTrasact =new Amount(transactionBalanceInteger, transationBalanceFraction);
			//Account account = accountManager.save(req.getParameter("accountName"), req.getParameter("accountNumber"), amount)
			String accountNumber = req.getParameter("accountNumber");
			Transaction transaction =  transactionManager.saveTransaction(libelle, transactType, amountTrasact, accountNumber);
			resp.sendRedirect(req.getContextPath() + "/account?accountNumber=" + transaction.getAccount().getNumber());

			
			//} catch (NumberFormatException nfe) {
			//req.setAttribute("error", "invalid.amount.format");88
			//getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(req, resp);
		//} //catch (AccountAlreadyExistingException e) {
			//log("le compte existe déjà", e);
			//req.setAttribute("error", "account.already.exists");
			//getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp"); //.forward(req, resp);
		//}
	}


}