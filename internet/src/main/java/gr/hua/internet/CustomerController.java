package gr.hua.internet;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.internet.model.ConnectedCustomers;
import gr.hua.internet.model.NLReciver;
import gr.hua.internet.model.Representative;
import gr.hua.internet.model.Request;

@Controller
public class CustomerController {

	public static final String SERVER_URI = "http://localhost:8080/InsuranceOrganisation/";

	@RequestMapping(value = "/request/new", method = RequestMethod.GET)
	public ModelAndView newReq(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);

		ConnectedCustomers connectedCustomers = new ConnectedCustomers();
		connectedCustomers.setAam(0);
		connectedCustomers.setSessionId(session.getId());

		RestTemplate restTemplate = new RestTemplate();
		connectedCustomers = restTemplate.postForObject(SERVER_URI + "confirmCustomer", connectedCustomers,
				ConnectedCustomers.class);

		if (connectedCustomers.getAam() != 0)
			return new ModelAndView("customer/request", "command", new Request());

		return new ModelAndView("redirect:/user");
	}

	@RequestMapping(value = "/addNewRequest", method = RequestMethod.POST)
	private String addNewRequest(@ModelAttribute("SpringWeb") Request request, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		int r = 10;
		Map<String, String> params = new HashMap<String, String>();
		params.put("aam", request.getAam() + "");
		params.put("bn", request.getBrandName());
		params.put("rn", request.getRepName());
		params.put("d", request.getDuration() + "");
		int i = restTemplate.postForObject(SERVER_URI + "request/add", params, int.class);

		model.addAttribute("register", i);
		return "customer/getReqID";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView customerLogin(Locale locale, Model model) {
		return new ModelAndView("customer/login", "command", new Representative());
	}

	@RequestMapping(value = "/checkCustomer", method = RequestMethod.POST)
	public String customerAuthentication(@ModelAttribute("SpringWeb") Representative representative, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("aam", representative.getAAM() + "");
		RestTemplate restTemplate = new RestTemplate();
		Representative result = restTemplate.getForObject(SERVER_URI + "customer/aam/{aam}", Representative.class,
				params);

		if (representative.getUsername() == result.getUsername()) {
			HttpSession session = request.getSession(true);
			ConnectedCustomers connectedCustomers = new ConnectedCustomers();
			connectedCustomers.setAam(representative.getAAM());
			connectedCustomers.setSessionId(session.getId());
			ConnectedCustomers connCust = restTemplate.postForObject(SERVER_URI + "connectCustomer", connectedCustomers,
					ConnectedCustomers.class);

			return "redirect:/";
		}

		return "redirect:/user";
	}

	@RequestMapping(value = "/getPdf", method = RequestMethod.GET)
	public ModelAndView getPdf(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);

		ConnectedCustomers connectedCustomers = new ConnectedCustomers();
		connectedCustomers.setAam(0);
		connectedCustomers.setSessionId(session.getId());

		RestTemplate restTemplate = new RestTemplate();
		connectedCustomers = restTemplate.postForObject(SERVER_URI + "confirmCustomer", connectedCustomers,
				ConnectedCustomers.class);

		if (connectedCustomers.getAam() != 0)
			return new ModelAndView("customer/reqId", "command", new Request());

		return new ModelAndView("redirect:/user");
	}

	byte[] pdf = null;

	@RequestMapping(value = "/printPdf", method = RequestMethod.POST)
	public String customerGetPdf(@ModelAttribute("SpringWeb") Request request, Locale locale, Model model) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", request.getRequestId() + "");
		RestTemplate restTemplate = new RestTemplate();

		pdf = restTemplate.getForObject(SERVER_URI + "request/{id}", byte[].class, params);

		return "customer/PDFprint";
	}

	@RequestMapping(value = "/PreviewPdf", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF() {

		byte[] data = pdf;

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.setContentDispositionFormData("", "");
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/newsletter", method = RequestMethod.GET)
	public ModelAndView newsletter() {
		return new ModelAndView("newsletter", "command", new NLReciver());
	}
	
	@RequestMapping(value = "/newsletterdata", method = RequestMethod.POST)
	private String newsletterData(@ModelAttribute("SpringWeb") NLReciver reciver, Model model) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, String> params = new HashMap<String, String>();
		params.put("F", reciver.getFname());
		params.put("L", reciver.getLname());
		params.put("Mail", reciver.getMail());
		String i = restTemplate.postForObject(SERVER_URI + "/addReceiver", params, String.class);
		return "newsletter";
	}

	@RequestMapping(value = "/customerLogout", method = RequestMethod.GET)
	public String customerAuthenticationLogout(@ModelAttribute("SpringWeb") Representative representative, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("aam", representative.getAAM() + "");

		RestTemplate restTemplate = new RestTemplate();
		Representative result = restTemplate.getForObject(SERVER_URI + "customer/aam/{aam}", Representative.class,
				params);

		if (representative.getUsername() == result.getUsername()) {
			HttpSession session = request.getSession(true);
			ConnectedCustomers connectedCustomers = new ConnectedCustomers();
			connectedCustomers.setAam(representative.getAAM());
			connectedCustomers.setSessionId(session.getId());
			ConnectedCustomers connCust = restTemplate.postForObject(SERVER_URI + "connectCustomer", connectedCustomers,
					ConnectedCustomers.class);

			return "redirect:/";
		}

		return "redirect:/";
	}

}