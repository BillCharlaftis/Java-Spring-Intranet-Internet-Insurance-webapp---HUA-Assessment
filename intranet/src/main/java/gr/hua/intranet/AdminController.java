package gr.hua.intranet;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import gr.hua.intranet.model.Admin;
import gr.hua.intranet.model.AdminDAO;
import gr.hua.intranet.model.ConnectedUsersDAO;
import gr.hua.intranet.model.Representative;
import gr.hua.intranet.model.User;
import gr.hua.intranet.model.mailDAO;

@Controller
public class AdminController {

	/*
	 * 
	 * ArrayList which contains admin's characteristics.
	 */
	ArrayList<Admin> AdminList = new ArrayList<Admin>();

	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

	/**
	 * 
	 * Add adminDAO and connectedUsersDAO Beans from spring.xml.
	 */
	AdminDAO adminDAO = ctx.getBean("AdminDAO", AdminDAO.class);
	mailDAO mail = ctx.getBean("mailDAO", mailDAO.class);
	ConnectedUsersDAO connectedUsersDAO = ctx.getBean("ConnectedUsersDAO", ConnectedUsersDAO.class);

	/*
	 * 
	 * If admin connect successfully it becomes true.
	 */
	boolean isAdmin = false;

	/*
	 * 
	 * Special uri for admin's login.
	 */
	@RequestMapping(value = "/nimda", method = RequestMethod.GET)
	public ModelAndView admin() {
		return new ModelAndView("admin", "command", new Admin());
	}

	/**
	 * 
	 * Login method which checks from database if userName and userPass are
	 * correct, if they are correct go to adminOptions page, else go again to
	 * login form.
	 */
	@RequestMapping(value = "/checkAdmin", method = RequestMethod.POST)
	public String checkAdmin(@ModelAttribute("SpringWeb") Admin admin, ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		Admin Admin = adminDAO.login(admin.getAdminName(), admin.getAdminPassword());

		if (Admin.isAdmin()) {
			AdminList.add(Admin);
			HttpSession session = request.getSession(true);
			connectedUsersDAO.connected(Admin.getAdminName(), "admin", session.getId());

			return "redirect:/adminOptions";
		}
		return "redirect:/";
	}

	/*
	 * 
	 * Admin's operations.
	 */
	@RequestMapping(value = "/adminOptions", method = RequestMethod.GET)
	public ModelAndView Options(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		isAdmin = connectedUsersDAO.checkConnUserAdmin(session.getId());

		if (isAdmin) {
			ArrayList<User> AllUsers = adminDAO.getAll();
			model.addAttribute("users", AllUsers);

			return new ModelAndView("adminOptions", "command", new User());
		}
		return new ModelAndView("redirect:/");
	}

	/*
	 * 
	 * Administrator adds new user.
	 */
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String User(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		isAdmin = connectedUsersDAO.checkConnUserAdmin(session.getId());

		if (isAdmin) {
			User newUser = new User();
			model.addAttribute("newUser", newUser);

			return "newUser";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/addOrUpdateUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("newUser") @Valid User user, BindingResult result) {

		if (result.hasErrors()) {

			return "newUser";
		}
		adminDAO.CreateUser(user);

		return "redirect:/adminOptions";
	}

	/*
	 * 
	 * Administrator edit an existing user.
	 */
	@RequestMapping("/edit/{username}")
	public String editUser(@PathVariable("username") String username, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		isAdmin = connectedUsersDAO.checkConnUserAdmin(session.getId());

		if (isAdmin) {
			model.addAttribute("newUser", adminDAO.getUser(username));

			return "updateUser";
		}
		return "redirect:/";
	}

	@RequestMapping("/update")
	public String editPerson(@ModelAttribute("updateUser") @Valid User user) {
		adminDAO.updateUser(user);

		return "redirect:/adminOptions";
	}

	/*
	 * 
	 * Administrator remove a specific user.
	 */
	@RequestMapping("/remove/{username}")
	public String removePerson(@PathVariable("username") String username, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		isAdmin = connectedUsersDAO.checkConnUserAdmin(session.getId());

		if (isAdmin) {
			adminDAO.deleteUser(username);

			return "redirect:/adminOptions";
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/updateDocumetionNL", method = RequestMethod.GET)
	public String haistaVittu( HttpServletRequest request,HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		isAdmin = connectedUsersDAO.checkConnUserAdmin(session.getId());

		if (isAdmin) {
			
			mail.SentNewsLater("We inform you that the documetion has been changed", "Please visit LINK_HERE for more informations.");
			
			return "Documentetion/UpdateOk";
		}
		return "Documentetion/UpdateFailed";
	}

}