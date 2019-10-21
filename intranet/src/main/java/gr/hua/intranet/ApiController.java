package gr.hua.intranet;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import gr.hua.intranet.ExceptionsController;
import gr.hua.intranet.model.Company;
import gr.hua.intranet.model.ConnectedCustomers;
import gr.hua.intranet.model.ConnectedCustomersDAO;
import gr.hua.intranet.model.Deliver;
import gr.hua.intranet.model.DeliverDAO;
import gr.hua.intranet.model.NLReciver;
import gr.hua.intranet.model.NLReciverDAO;
import gr.hua.intranet.model.PDFfile;
import gr.hua.intranet.model.Representative;
import gr.hua.intranet.model.RepresentativeDAO;
import gr.hua.intranet.model.Request;
import gr.hua.intranet.model.RequestDAO;
import gr.hua.intranet.model.mailDAO;


@Controller
public class ApiController {

	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	RequestDAO requestDAO = ctx.getBean("requestDAO", RequestDAO.class);
	RepresentativeDAO representativeDAO = ctx.getBean("customerDAO", RepresentativeDAO.class);
	DeliverDAO deliverDAO = ctx.getBean("deliverDAO", DeliverDAO.class);
	ConnectedCustomersDAO connectedCustomersDAO = ctx.getBean("ConnectedCustomersDAO", ConnectedCustomersDAO.class);
	NLReciverDAO nlReciverDAO = ctx.getBean("ReceiverDAO", NLReciverDAO.class);
	mailDAO mail = ctx.getBean("mailDAO", mailDAO.class);
	
	@RequestMapping(value = "/request/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody byte[] getRequestByID(@PathVariable int id) throws ExceptionsController{
		deliverDAO.Loadpdf(id);
		PDFfile pdf = deliverDAO.getPdfFile();

		if (pdf == null) {
			throw new ExceptionsController();
		}
	
		return pdf.getPdf();
	}

	@RequestMapping(value = "/request/id/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Request getRequestsByID(@PathVariable int id) throws ExceptionsController{
		Request r = requestDAO.getById(id);

		if (r == null) {
			throw new ExceptionsController();
		}

		return r;
	}

	@RequestMapping(value = "/request/aam/{aam}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Request> getRequestsByAAM(@PathVariable Integer aam) throws ExceptionsController{
		List<Request> r = requestDAO.getByAAM(aam);

		if (r == null) {
			throw new ExceptionsController();
		}

		return r;
	}

	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Representative> getCustomers(){
		List<Representative> representatives = representativeDAO.getAllCustomers();

		return representatives;
	}

	@RequestMapping(value = "/customer/aam/{aam}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Representative getCustomersByAAM(@PathVariable Integer aam) throws ExceptionsController{
		Representative r = representativeDAO.getCustomerByAAM(aam);

		if (r == null) {
			throw new ExceptionsController();
		}
		return r;
	}

	@RequestMapping(value = "/request/add", method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody int addRequest(@RequestBody Map<String, String> params){
		int aam = Integer.parseInt(params.get("aam"));
		int duration = Integer.parseInt(params.get("d"));
		
		Company thisCompany = new Company();
		thisCompany.setAam(aam);
		thisCompany.setBrandName(params.get("bn"));
		thisCompany.setRepName(params.get("rn"));

		Deliver requestId = deliverDAO.completeCertification(thisCompany, duration);
		requestDAO.PdfWriter(requestId.getRequestID());

		return requestId.getRequestID();
	}
	
	@RequestMapping(value = "/connectCustomer", method = RequestMethod.POST)
	private String connectCustomer(@RequestBody ConnectedCustomers cust){
		connectedCustomersDAO.connected(cust.getAam(), cust.getSessionId());
		
		return "redirect:http://localhost:8080/internet/";
	}
	
	@RequestMapping(value = "/confirmCustomer", method = RequestMethod.POST, produces = "application/json")
	private @ResponseBody ConnectedCustomers confirmConnectCustomer(@RequestBody ConnectedCustomers connectedCustomers){
		int aam = connectedCustomersDAO.checkConnCustomer(connectedCustomers.getSessionId());
		connectedCustomers.setAam(aam);

		return connectedCustomers;
	}
	
	@RequestMapping(value = "/customerLogout", method = RequestMethod.POST)
	private String logoutCustomer(@RequestBody ConnectedCustomers cust){
		connectedCustomersDAO.delete(cust.getSessionId());
		
		return "redirect:http://localhost:8080/internet/";
	}
	
	@RequestMapping(value = "/addReceiver", method = RequestMethod.POST)
	private String newsletterReceiver(@RequestBody Map<String, String> params){
		NLReciver nl = new NLReciver();
		nl.setFName(params.get("F"));
		nl.setLname(params.get("L"));
		nl.setMail(params.get("Mail"));
		boolean ok = nlReciverDAO.CheckAndADD(nl);
		if(ok){
			mail.SentRegistrationCompletle(nl);
		}
		
		return "redirect:http://localhost:8080/internet/";
	}
}