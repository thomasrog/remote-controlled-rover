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

	private static int lastServo1Pos = 0;
	private static int lastServo2Pos = 0;

	public ArduinoServoService() {
		super();
	}

	@PostConstruct
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
		log.debug("percent servo1" + percent);
	
		final int servoStart = 50;
		final int servoEnd = 150;
		int servoDistance = servoEnd - servoStart;
		int correctedServoPosition = (int) (servoStart + ((float) percent / 100.0)
				* servoDistance);

		log.debug("servo1 " + correctedServoPosition);

		if (Math.abs(lastServo1Pos - correctedServoPosition) > 10) {
			this.moveServo("S1", correctedServoPosition);
			lastServo1Pos = correctedServoPosition;
		}
	}

	public void moveServo2(int percent) throws Exception {
		log.debug("percent servo2" + percent);
		final int servoStart = 30;
		final int servoEnd = 90;
		int servoDistance = servoEnd - servoStart;
		int correctedServoPosition = (int) (servoStart + ((float) percent / 100.0)
				* servoDistance);
		log.debug("servo2 " + correctedServoPosition);
		if (Math.abs(lastServo2Pos - correctedServoPosition) > 10) {
			this.moveServo("S2", correctedServoPosition);
			lastServo2Pos = correctedServoPosition;
		}
	}

	private void moveServo(String method, int servoPos) throws Exception {

		if (!this.serialPortReader.isArduinoReady()) {
			log.debug("arduino not connected");
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