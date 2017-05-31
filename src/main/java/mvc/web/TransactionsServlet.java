package mvc.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.Account;
import mvc.model.AccountAlreadyExistingException;
import mvc.model.AccountDoesNotExistException;
import mvc.model.AccountManager;
import mvc.model.Amount;
@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	@EJB
	private AccountManager accountManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/transaction.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		try {			
			String transactionRecipeInteger = req.getParameter("transactionRecipeInteger");
			String transationRecipeFraction = req.getParameter("transationRecipeFraction");
			Amount amount = new Amount(transactionRecipeInteger, transationRecipeFraction);
			
			
			//Account account = accountManager.save(req.getParameter("accountName"), req.getParameter("accountNumber"), amount);
			//resp.sendRedirect(req.getContextPath() + "/account?accountNumber=" + account.getNumber());
		} catch (NumberFormatException nfe) {
			req.setAttribute("error", "invalid.amount.format");
			//getServletContext().getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp").forward(req, resp);
		} //catch (AccountAlreadyExistingException e) {
			//log("le compte existe déjà", e);
			//req.setAttribute("error", "account.already.exists");
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/transaction.jsp").forward(req, resp);
		//}
	}


}