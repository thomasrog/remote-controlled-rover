package sample.jsp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoverController {

	@RequestMapping(value = "/joystick", method = RequestMethod.PUT)
	public ResponseEntity<String> updateDose(@RequestBody Joystick changeTo) {

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
