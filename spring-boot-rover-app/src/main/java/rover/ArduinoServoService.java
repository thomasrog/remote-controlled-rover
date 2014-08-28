package rover;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.PostConstruct;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ArduinoServoService {

	private final Log log = LogFactory.getLog(getClass());

	private SerialPort serialPort;

	private SerialPortReader serialPortReader;

	private final int servoStart = 30;
	private final int servoEnd = 140;

	public ArduinoServoService() {
		super();
	}

	//@PostConstruct
	private void connectArduino() {
		serialPort = new SerialPort("/dev/tty.usbserial-A929L3BV");

		try {
			serialPort.openPort();

			serialPort.setParams(SerialPort.BAUDRATE_115200,
					SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);// Set params

			serialPortReader = new SerialPortReader(this.serialPort);
			serialPort.addEventListener(serialPortReader);
		} catch (SerialPortException e) {
			log.error("connect to arduino error", e);
			throw new RuntimeException(e);
		}

	}

	public void moveServo1(int percent) throws Exception {

		int servoDistance = servoEnd - servoStart;
		int correctedServoPosition = servoStart + (percent / 100)
				* servoDistance;
		this.moveServo("S1", correctedServoPosition);
	}

	public void moveServo2(int percent) throws Exception {
		int servoDistance = servoEnd - servoStart;
		int correctedServoPosition = servoStart + (percent / 100)
				* servoDistance;
		this.moveServo("S2", correctedServoPosition);
	}

	@Async
	private void moveServo(String method, int servoPos) throws Exception {

		if (!this.serialPortReader.isArduinoReady()) {
			return;
		}

		try {
			String arduinoCmd = method + " " + servoPos + "\n";
			log.debug("arduino command: " + arduinoCmd);
			serialPort.writeBytes(arduinoCmd.getBytes());

			Thread.sleep(500);

		} catch (SerialPortException e) {
			log.error("write to arduino error", e);
			throw new Exception(e);
		}

	}
}

class SerialPortReader implements SerialPortEventListener {

	private final SerialPort serialPort;

	private final Log log = LogFactory.getLog(getClass());

	AtomicBoolean arduinoReady = new AtomicBoolean(false);

	public SerialPortReader(SerialPort serialPort) {
		super();
		this.serialPort = serialPort;

	}

	public boolean isArduinoReady() {
		return this.arduinoReady.get();
	}

	public void serialEvent(SerialPortEvent event) {

		try {

			if (event.isRXCHAR() && event.getEventValue() > 0) {

				String buffer = serialPort.readString();

				log.debug("received from arduino: " + buffer);

				if (!arduinoReady.get()) {
					arduinoReady.set(true);
				}

			}

		} catch (SerialPortException e) {

			log.error("serial port error", e);
		}

	}
}