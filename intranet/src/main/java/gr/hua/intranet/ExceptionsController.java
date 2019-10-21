package gr.hua.intranet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "There is not requset with that id!!!")
public class ExceptionsController extends Exception {
        private static final long serialVersionUID = 100L;
}
