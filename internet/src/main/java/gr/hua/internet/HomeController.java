package gr.hua.internet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(Model model) {
		return "home";

	}
	
	@RequestMapping(value = "/logo", method = RequestMethod.GET)
	 public ResponseEntity<byte[]> logo(Model model) {

	  File fi = new File(
	    System.getProperty("user.home") + "/Desktop/internet/src/main/webapp/resources/hua.jpg");
	  System.out.println(fi.toString());

	  byte[] fileContent = null;
	  try {
	   fileContent = Files.readAllBytes(fi.toPath());
	  } catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }

	  HttpHeaders headers = new HttpHeaders();
	  headers.setContentType(MediaType.parseMediaType("image/jpeg"));
	  headers.setContentDispositionFormData("", "");
	  headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	  ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(fileContent, headers, HttpStatus.OK);

	  System.out.println(response.toString());
	  return response;
	 }
}
