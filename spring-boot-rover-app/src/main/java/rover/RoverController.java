package rover;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RoverController {

	private final ArduinoServoService arduinoService;

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	public RoverController(ArduinoServoService arduinoService) {
		super();
		this.arduinoService = arduinoService;
	}

	@RequestMapping(value = "/joystick", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePostion(@RequestBody Joystick changeTo) {

		log.debug("wheel position: " + changeTo.getWheehlPos() + " velocity: "
				+ changeTo.getVelocity());

		int wheelPosCorrected = (int) ((changeTo.getWheehlPos() / 180.0) * 100.0);

		boolean reverseVelocity = false;
		if (wheelPosCorrected < 0) {
			reverseVelocity = true;
			wheelPosCorrected += 100;
		}

		try {
			// this.arduinoService.moveServo1(wheelPosCorrected);

			log.debug("wheelpos corrected " + wheelPosCorrected);
		} catch (Exception e) {
			log.error("", e);
		}

		int velocityPosCorrected = (int) ((changeTo.getVelocity() * 10.0 / 50.0) * 100.0);

		if (reverseVelocity) {
			velocityPosCorrected *= -1;
		}

		if (velocityPosCorrected > 50) {
			velocityPosCorrected = 50;
		}
		if (velocityPosCorrected < -50) {
			velocityPosCorrected = -50;
		}

		velocityPosCorrected += 50;

		try {
			log.debug("velocityPosCorrectedpos corrected "
					+ velocityPosCorrected);
			// this.arduinoService.moveServo2(velocityPosCorrected);
		} catch (Exception e) {
			log.error("", e);
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
