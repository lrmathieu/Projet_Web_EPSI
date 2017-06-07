package mvc.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.Transaction;
import mvc.model.TransactionManager;
@WebServlet("/transactions")
public class TransactionsServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	@EJB
	private TransactionManager transactionManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/transaction.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//try {			
			String recipe =req.getParameter("recipe");
			String libelle = req.getParameter("libbele");
			String transactType = req.getParameter("transactionType");
			//AmountTransact amounttt =new AmountTransact(transactionBalanceInteger, transationBalanceFraction);
			//Account account = accountManager.save(req.getParameter("accountName"), req.getParameter("accountNumber"), amount)
;
			Transaction transaction =  transactionManager.saveTransaction(libelle, transactType, recipe);
			resp.sendRedirect(req.getContextPath() + "/account?accountNumber=555ooo");
			
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