package gr.hua.internet;

import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import gr.hua.internet.model.Department;
import gr.hua.internet.model.Documentation;
import gr.hua.internet.model.Legislation;

@Controller
public class DocumentsController {
	public static final String SERVER_URI = "http://localhost:8080/InsuranceOrganisation/";

	@RequestMapping(value = "/departments", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String departments(Locale locale, Model model) {
		RestTemplate restTemplate = new RestTemplate();

		Department departments = restTemplate.getForObject(SERVER_URI + "departments", Department.class);
		Map<String, Object> dep = departments.getDepartments();
		model.addAttribute("departments", dep);
		return "/departments";
	}

	@RequestMapping(value = "/legislation", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String legislation(Locale locale, Model model) {
		RestTemplate restTemplate = new RestTemplate();

		Legislation legislation = restTemplate.getForObject(SERVER_URI + "legislation", Legislation.class);
		Map<String, Object> leg = legislation.getLegislation();
		model.addAttribute("legislation", leg);
		return "/legislation";
	}

	@RequestMapping(value = "/documentation", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String documentation(Locale locale, Model model) {
		RestTemplate restTemplate = new RestTemplate();

		Documentation documentation = restTemplate.getForObject(SERVER_URI + "documentation", Documentation.class);
		Map<String, Object> leg = documentation.getDocumentation();
		model.addAttribute("documentation", leg);
		return "/documentation";
	}

}
