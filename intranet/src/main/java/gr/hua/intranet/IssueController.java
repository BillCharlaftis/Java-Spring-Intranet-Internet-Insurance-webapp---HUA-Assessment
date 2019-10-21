package gr.hua.intranet;

/**
 * 
 * Charlaftis Basileios it21370, Mitropoulos Ioannis it21338, Petsopoulos Tilemachos it21374
 */

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.intranet.model.ConnectedUsersDAO;
import gr.hua.intranet.model.PDFfile;
import gr.hua.intranet.model.Request;
import gr.hua.intranet.model.RequestDAO;
import gr.hua.intranet.model.User;

@Controller
public class IssueController {
		
	/*
	 * 
	 * ArrayList which contains request's characteristics.
	 */
	ArrayList<Request> requestList = new ArrayList<Request>();
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	
	/**
	  * 
	  * Add requestDAO and connectedUsersDAO Beans from spring.xml.
	  */
	RequestDAO requestDAO = ctx.getBean("requestDAO", RequestDAO.class);
	ConnectedUsersDAO connectedUsersDAO = ctx.getBean("ConnectedUsersDAO", ConnectedUsersDAO.class);
	
	/*
	 * 
	 * If user who try to use this is issue then becomes true.
	 */
	boolean isIssue = false;
	
	/*
	 * 
	 * Object which contains user's characteristics.
	 */
	User newUser;
	
	/*
	 * 
	 * List with all requests in order to choose one to edit.
	 */
	@RequestMapping(value = "/allRequests", method = RequestMethod.GET)
    public ModelAndView edit(Model model,HttpServletRequest request,
            HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());
		
		if (newUser.getUsername() == null) {

			return new ModelAndView("redirect:/error");
		}

		if(newUser.getRole().equals("issue")){
			List<Request> requestList = requestDAO.getAll();
			model.addAttribute("requests", requestList);
			
			return new ModelAndView("request", "command", new Request());
		}
		return new ModelAndView("redirect:/index");
    }
	
	@RequestMapping(value = "/getRequest", method = RequestMethod.POST)
    public String editRequest(@ModelAttribute("SpringWeb") Request request, ModelMap model) {
		requestList.clear();
		Request newRequest = requestDAO.getById(request.getRequestId());
		requestList.add(newRequest);
		
		return "redirect:/editRequest";
            
    }
	
	@RequestMapping(value = "/editRequest", method = RequestMethod.GET)
    public String index(Model model,HttpServletRequest request,
            HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());
		
		if (newUser.getUsername() == null) {

			return "redirect:/error";
		}

		if(newUser.getRole().equals("issue")){
			model.addAttribute("request", requestList);
			
            return "request1";
		}
        return "redirect:/index";
    }
	
	ArrayList<PDFfile> pdfList;
	@RequestMapping(value = "/PdfFiles", method = RequestMethod.GET)
    public String PdfFiles(Model model) {
			pdfList = new ArrayList<PDFfile>();
			pdfList = requestDAO.getAllPDFS();
			pdfList.get(0).getRequestID();
            model.addAttribute("pdfs", pdfList);
            
            return "request2";
    }
	
	@RequestMapping(value="/getpdf/{name}", method=RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF(@ModelAttribute("SpringWeb") String name) {
						
		byte[] data = null ;
		
		for(int i=0;i<pdfList.size();i++){
			data = pdfList.get(i).getPdf();
		}
		
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.parseMediaType("application/pdf"));
		 headers.setContentDispositionFormData(""+name, ""+name);
		 headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		 ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
		 
		 return response;      
	}
	
	
	/**
	  * 
	  * acceptReq method used in order to accept this request.
	  */
	@RequestMapping(value = "/accept")
    public String requestAccept(Model model) {		
		requestDAO.acceptReq(pdfList.get(0).getRequestID());
		
        return "redirect:/allRequests";
    }
	
	/**
	  * 
	  * declineReq method used in order to decline this request.
	  */
	@RequestMapping(value = "/decline/{requestID}")
    public String requestDecline(@PathVariable("requestID") int requestID, Model model,HttpServletRequest request,
            HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		newUser = connectedUsersDAO.checkConnUserRole(session.getId());
		
		if (newUser.getUsername() == null) {

			return "redirect:/error";
		}
		
		if(newUser.getRole().equals("issue")){
			requestDAO.declineReq(requestID);
		}
        return "redirect:/allRequests";
    }
}
