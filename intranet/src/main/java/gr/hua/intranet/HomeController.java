package gr.hua.intranet;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.util.ArrayList;

import javax.servlet.http.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.intranet.model.CompanyDAO;
import gr.hua.intranet.model.ConnectedUsersDAO;
import gr.hua.intranet.model.User;
import gr.hua.intranet.model.UserDAO;

@Controller
public class HomeController extends HttpServlet{

	/*
	 * 
	 * ArrayList which contains user's characteristics.
	 */
	ArrayList<User> userList = new ArrayList<User>();
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	
	/**
	  * 
	  * Add userDAO, companyDAO, connectedUsersDAO and user Beans from spring.xml.
	  */
	UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
	CompanyDAO companyDAO = ctx.getBean("companyDAO",CompanyDAO.class);
	User user = ctx.getBean("user",User.class);
	ConnectedUsersDAO connectedUsersDAO = ctx.getBean("ConnectedUsersDAO", ConnectedUsersDAO.class);
	
	/*
	 * 
	 * If user connect as issue it becomes true.
	 */
	boolean isIssue = false;
	/*
	 * 
	 * If user connect as support it becomes true.
	 */
	boolean isSupp = false;

	/**
	  * 
	  * This is Root Page,
	  * which go to user.jsp in order to login users.
	  */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView user() {
		return new ModelAndView("user", "command", new User());
	}
	
	/*
	 * 
	 * Object which contains user's characteristics.
	 */
	User newUser;
	/**
	  * 
	  * Login method used to check from database if userName and userPass are correct,
	  * if they are correct go to index page, else go again to login form.
	  */
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public String checkUser(@ModelAttribute("SpringWeb") User user, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		userList.clear();
		newUser = userDAO.login(user.getUsername(), user.getUserpass());

		if (newUser.isUser()) {
			userList.add(newUser);
			HttpSession session = request.getSession(true);
			connectedUsersDAO.connected(newUser.getUsername(), newUser.getRole(), session.getId());

			return "redirect:/index";
		}
		return "redirect:/";
	}
	
	/**
	  * 
	  * Go to index.jsp in order to see user's data and user's operations.
	  */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());
		//isIssue = connectedUsersDAO.checkConnUserIssue(session.getId());
		
//		if(isSupp){
//			newUser.setRole("support");
//			model.addAttribute("user", userList);
//			
//			return "index";
//		}else if(isIssue){
//			newUser.setRole("issue");
//			model.addAttribute("user", userList);
//			
//			return "index";
//		}
		if (newUser.getUsername() == null) {
			
			return "redirect:/";
		}
		
		userList.clear();
		userList.add(newUser);
		model.addAttribute("user", userList);
		
		return "index";
    }
	
	/**
	  * 
	  * Success logout
	  */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
   public String logout(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		connectedUsersDAO.delete(session.getId());
		
		return "redirect:/";
   }
	
	/*
	 * 
	 * Page for bad requests.
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(){
		return "/error";
	}
}
