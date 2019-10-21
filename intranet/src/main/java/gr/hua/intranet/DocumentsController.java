package gr.hua.intranet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import gr.hua.intranet.model.Department;
import gr.hua.intranet.model.Documentation;
import gr.hua.intranet.model.Legislation;

@Controller
public class DocumentsController {
	
	ArrayList<Department> departmentsList = new ArrayList<>();
	ArrayList<Legislation> legislationList = new ArrayList<>();
	ArrayList<Documentation> documentationList = new ArrayList<>();

	@RequestMapping(value = "/departments", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Department getDepartmentsInJSON() {
		departmentsList.clear();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			Department dep = mapper.readValue(new File(System.getProperty("user.home")+"/Desktop/departments.json"),
					Department.class);
			departmentsList.add(dep);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return departmentsList.get(0);
	}

	@RequestMapping(value = "/legislation", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Legislation getLegislationInJSON() {
		legislationList.clear();
		ObjectMapper mapper = new ObjectMapper();

		try {
			Legislation leg = mapper.readValue(new File(System.getProperty("user.home")+"/Desktop/legislation.json"),
					Legislation.class);
			legislationList.add(leg);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return legislationList.get(0);
	}
	
	@RequestMapping(value = "/documentation", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody Documentation getDocumentationInJSON() {
		documentationList.clear();
		ObjectMapper mapper = new ObjectMapper();

		try {
			Documentation doc = mapper.readValue(new File(System.getProperty("user.home")+"/Desktop/InsuranceOrganisation/src/main/resources/jsons/documentation.json"),
					Documentation.class);
			documentationList.add(doc);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return documentationList.get(0);
	}

}