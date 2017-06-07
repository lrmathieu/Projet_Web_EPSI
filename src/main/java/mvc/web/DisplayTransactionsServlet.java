package mvc.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.model.Transaction;
import mvc.model.TransactionDoesNotExistException;
import mvc.model.TransactionManager;

@WebServlet("/displayTransactions")
public class DisplayTransactionsServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	@EJB
	private TransactionManager transactionManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Transaction> transactionsList;
        try {
            transactionsList = this.transactionManager.getListTransactions();
            req.setAttribute("transactionsList", transactionsList);
            req.getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
			//resp.sendRedirect(req.getContextPath() + "/transactionsDisplay");

        } catch (TransactionDoesNotExistException e) {
            log("Aucune transaction a afficher", e);
            req.setAttribute("error", "no.transactions");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/displayTransactions.jsp").forward(req, resp);
            //Logger.getLogger(TransactionsDisplayServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
		
	}
}
