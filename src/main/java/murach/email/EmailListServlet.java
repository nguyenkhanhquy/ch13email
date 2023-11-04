package murach.email;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import murach.business.User;
import murach.data.UserDB;

@WebServlet(urlPatterns = { "/emailList" })
public class EmailListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");

		String url = "/index.jsp";

		// get current action
		String action = request.getParameter("action");
		if (action == null) {
			action = "join"; // default action
		}

		// perform action and set URL to appropriate page
		if (action.equals("join")) {
			url = "/index.jsp"; // the "join" page
		} else if (action.equals("add")) {
			// get parameters from the request
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");

			// store data in User object
			User user = new User();
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);

			// validate the parameters
			String message;
			if (UserDB.emailExists(user.getEmail())) {
				message = "This email address already exists.<br>" + "Please enter another email address.";
				url = "/index.jsp";
			} else {
				message = "";
				url = "/thanks.jsp";
				UserDB.insert(user);
			}
			request.setAttribute("user", user);
			request.setAttribute("message", message);
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}