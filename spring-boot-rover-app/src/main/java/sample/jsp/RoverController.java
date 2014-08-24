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
	public ResponseEntity<String> updatePostion(@RequestBody Joystick changeTo) {

	
		System.out.println("wheel position: " + changeTo.getWheehlPos()
				+ " velocity: " + changeTo.getVelocity());

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
