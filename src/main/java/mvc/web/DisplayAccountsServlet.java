package mvc.web;

import java.io.IOException;
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

@WebServlet("/home")
public class DisplayAccountsServlet extends HttpServlet {

private static final long serialVersionUID = 1L;
	
	@EJB
	private AccountManager accountManager;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Account> accountsList;
        try {
        	accountsList = this.accountManager.getListAccounts();
            req.setAttribute("accountsList", accountsList);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);

        } catch (AccountDoesNotExistException e) {
            log("Aucune compte a afficher", e);
            req.setAttribute("error", "no.accounts");
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        }		
	}
}
