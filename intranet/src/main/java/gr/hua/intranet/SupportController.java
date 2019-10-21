package gr.hua.intranet;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.*;
import javax.validation.Valid;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.intranet.model.Company;
import gr.hua.intranet.model.CompanyDAO;
import gr.hua.intranet.model.ConnectedUsersDAO;
import gr.hua.intranet.model.Deliver;
import gr.hua.intranet.model.DeliverDAO;
import gr.hua.intranet.model.PDFfile;
import gr.hua.intranet.model.Representative;
import gr.hua.intranet.model.RepresentativeDAO;
import gr.hua.intranet.model.User;
import gr.hua.intranet.model.UserDAO;
import gr.hua.intranet.model.mailDAO;

@Controller
public class SupportController extends HttpServlet {
		
	/*
	 * 
	 * ArrayList which contains company's characteristics.
	 */
	ArrayList<Company> companyList = new ArrayList<Company>();
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	
	/**
	  * 
	  * Add userDAO, companyDAO, deliverDAO, connectedUsersDAO, user, company and deliver Beans from spring.xml.
	  */
	UserDAO userDAO = ctx.getBean("userDAO", UserDAO.class);
	CompanyDAO companyDAO = ctx.getBean("companyDAO",CompanyDAO.class);
	User user = ctx.getBean("user",User.class);
	DeliverDAO deliverDAO = ctx.getBean("deliverDAO", DeliverDAO.class);
	Company company = ctx.getBean("company",Company.class);
	Deliver deliver = ctx.getBean("deliver", Deliver.class);
	ConnectedUsersDAO connectedUsersDAO = ctx.getBean("ConnectedUsersDAO", ConnectedUsersDAO.class);
	Representative representative = ctx.getBean("customer", Representative.class);
	RepresentativeDAO representativeDAO = ctx.getBean("customerDAO", RepresentativeDAO.class);
	mailDAO mail = ctx.getBean("mailDAO", mailDAO.class);
	
	/*
	 * 
	 * Object which contains user's characteristics.
	 */
	User newUser;
	
	
	/**
	  * 
	  * Go to adminOptions.jsp in order to give representative's adt and company's aam.
	  */
	@RequestMapping(value = "/req", method = RequestMethod.GET)
    public ModelAndView checkRep(HttpServletRequest request,
            HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());
		
		if (newUser.getUsername() == null) {
			
			return new ModelAndView("redirect:/error");
		}
		if(newUser.getRole().equals("support")){
			return new ModelAndView("company", "command", new Company());
		}
		return new ModelAndView("redirect:/index");
    }
	
	/**
	  * 
	  * checkCompany method checks representative's adt and company's aam,
	  * if they are correct go to completeRequest,
	  * else go back again.
	  */
	Company newCompany;

	@RequestMapping(value = "/checkCompany", method = RequestMethod.POST)
	public String checkRep1(@ModelAttribute("SpringWeb") Company company, ModelMap model) {
		newCompany = companyDAO.checkCompany(company.getAdt(), company.getAam());

		if (newCompany.isCompany()) {
			companyList.add(newCompany);

			return "redirect:/completeRequest";
		}
		return "redirect:/req";
	}	
	
	/**
	  * 
	  * Go to register.jsp in order to check if company is correct in database.
	  */
	@RequestMapping(value = "/completeRequest", method = RequestMethod.GET)
    public String doRequest(Model model) {
		model.addAttribute("company",companyList);
		
		return "/register";
    }
	
	/**
	  * 
	  * Go to reqDone.jsp in order to choose duration for certification.
	  */
	@RequestMapping(value = "/completeReq", method = RequestMethod.POST)
    public ModelAndView doRequest1() {
		return new ModelAndView("reqDone", "command", new Deliver());
    }
	
	/**
	  * 
	  * completeCertification method writes in database request's data and returns the request's id.
	  */
	Deliver newDeliver;
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
    public String doRep(@ModelAttribute("SpringWeb") Deliver deliver, ModelMap model) {
		newDeliver = deliverDAO.completeCertification(newCompany, deliver.getDuration());
		
        if(newDeliver.getReqID() >0){
        	model.addAttribute("register",newDeliver.getReqID());
        	
           	return "/successReg";
        }
        return "redirect:/error";
    }

	/**
	  * 
	  * Go to company2.jsp and give representative's adt and company's aam in order to take the certification.
	  */
	@RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView receipt(HttpServletRequest request,
            HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());

		if (newUser.getUsername() == null) {

			return new ModelAndView("redirect:/error");
		}
		if(newUser.getRole().equals("support")){
			
			return new ModelAndView("company2", "command", new Company());
		}
		return new ModelAndView("redirect:/index");
    }
	
	/**
	  * 
	  * checkCompany method checks representative's adt and company's aam,
	  * if they are correct go to deliver,
	  * else go back again.
	  */
	@RequestMapping(value = "/checkCompanyRec", method = RequestMethod.POST)
	public String checkRep2(@ModelAttribute("SpringWeb") Company company, ModelMap model) {
		newCompany = companyDAO.checkCompany(company.getAdt(), company.getAam());

		if (newCompany.isCompany()) {
			companyList.add(newCompany);

			return "redirect:/deliver";
		}
		return "redirect:/print";
	}	

	/**
	  * 
	  * Go to deliverReq.jsp in order to choose certification with given request id.
	  */
	@RequestMapping(value = "/deliver", method = RequestMethod.GET)
	public ModelAndView deliverRequest(){
		return new ModelAndView("deliverReq", "command", new Deliver());
	}
	
	/**
	  * 
	  * deliverCertification method updates database that certification with given request id is delivered and print it.
	  */
	int RID = 0;
	@RequestMapping(value = "/reqIDafm", method = RequestMethod.POST)
	public String reqIDafm(@ModelAttribute("SpringWeb") Deliver deliver, ModelMap model){
		Deliver newDeliver = deliverDAO.deliverCertification(newCompany, deliver.getReqID());
		RID = newDeliver.getReqID();
		
		if(newDeliver.isDelivered()){
			
			return "redirect:/PdfPrint";
		}
		return "redirect:/error";
	}
	
	
	PDFfile pdf = null;
	
	@RequestMapping(value = "/PdfPrint", method = RequestMethod.GET)
    public String PdfFiles(Model model) {
			pdf = new PDFfile();
			deliverDAO.Loadpdf(RID);
			pdf = deliverDAO.getPdfFile();
//            model.addAttribute("pdfs", pdf);
            return "PDFprint";
    }

	@RequestMapping(value="/PreviewPdf", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF() {
						
		byte[] data = pdf.getPdf();
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.parseMediaType("application/pdf"));
		 headers.setContentDispositionFormData("", "");
		 headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		 ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		 			
		 return response;      
	}
	
	
	/*
	 * 
	 * Support adds new customer.
	 */
	@RequestMapping(value = "/newCustomer", method = RequestMethod.GET)
	public String Customer(Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());
		
		if (newUser.getUsername() == null) {

			return "redirect:/error";
		}
		if(newUser.getRole().equals("support")){
			
			Representative newRepresentative = new Representative();
			model.addAttribute("newRepresentative", newRepresentative);
			
			return "newRepresentative";
		}

		return "redirect:/";
	}
	
	@RequestMapping(value = "/addRepresentative", method = RequestMethod.POST)
	public String addCustomer(HttpServletRequest request,
            HttpServletResponse response,@ModelAttribute("newRepresentative") @Valid Representative newRepresentative, BindingResult result) {
		HttpSession session = request.getSession(true);
		
		if (result.hasErrors()) {
			
			return "newCustomer";
		}
		newRepresentative.setUsername(username());
		representativeDAO.CreateCustomer(newRepresentative, session.getId());

		return "redirect:/getRepresentative";
	}
	
	@RequestMapping(value = "/getRepresentative", method = RequestMethod.GET)
	public String getCustomer(ModelMap model,HttpServletRequest request,
            HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		Representative newRepresentative = new Representative();
		newRepresentative = representativeDAO.getCustomerBySessionID(session.getId());

		mail.SentConfirmation(newRepresentative);
		model.addAttribute("newRepresentative", newRepresentative.getUsername());
		return "customerPass";
	}
	
	/*
	 * 
	 * this function as password for customer
	 */
	int username;
	private int username(){
		Random rand = new Random();
		username = rand.nextInt(100000) +1;
		if(username<1000){
			username *= 1000;
		}else if(username<50000){
			username += 25000;
		}

		return username;
	}
}