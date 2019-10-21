package gr.hua.intranet;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.intranet.model.NLReciver;
import gr.hua.intranet.model.NLReciverDAO;
import gr.hua.intranet.model.Representative;
import gr.hua.intranet.model.mailDAO;

@Controller
public class ReceiverController {
	
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	NLReciverDAO reciverDAO = ctx.getBean("ReceiverDAO", NLReciverDAO.class);
	mailDAO mail = ctx.getBean("mailDAO", mailDAO.class);


	@RequestMapping(value = "/newsletter", method = RequestMethod.GET)
	public ModelAndView admin() {
		return new ModelAndView("nl/NL", "command", new Representative());
	}

	@RequestMapping(value = "/addNLRreciver", method = RequestMethod.POST)
	public String addReciver(@ModelAttribute("newReciver") NLReciver customer) {


		if (!reciverDAO.CheckAndADD(customer)) {

			return "nl/NLError";
		}

		mail.SentRegistrationCompletle(customer);

		return "nl/NLOk";
	}
	
	
}
