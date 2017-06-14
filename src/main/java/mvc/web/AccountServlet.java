package mvc.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.Account;
import mvc.model.AccountDoesNotExistException;
import mvc.model.AccountManager;
import mvc.model.Transaction;
import mvc.model.TransactionDoesNotExistException;
import mvc.model.TransactionManager;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AccountManager accountManager;
	@EJB
	private TransactionManager transactionManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Transaction> transactionsListByAccount;

		try {
			Account account = this.accountManager.getByNumber(req.getParameter("accountNumber"));
			req.setAttribute("account", account);
			
			transactionsListByAccount = this.transactionManager.getListTransactionsByAccount(req.getParameter("accountNumber"));
            req.setAttribute("transactionsListByAccount", transactionsListByAccount);
            
            Double accountBalance = this.transactionManager.getAccountBalance(req.getParameter("accountNumber"));
            req.setAttribute("accountBalance", accountBalance);
			
            req.getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(req, resp);
		} catch (AccountDoesNotExistException e) {
			log("Account does not exist", e);
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}catch (TransactionDoesNotExistException e) {
            log("Aucune transaction a afficher", e);
            req.setAttribute("error", "no.transactions");
		}
	}
	
}

