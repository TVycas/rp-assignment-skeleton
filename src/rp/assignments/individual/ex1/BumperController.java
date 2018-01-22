package rp.assignments.individual.ex1;

import lejos.robotics.navigation.DifferentialPilot;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.TouchSensorEvent;
import rp.systems.ControllerWithTouchSensor;
import rp.util.Rate;

public class BumperController implements ControllerWithTouchSensor {

	private final DifferentialPilot pilot;
	private boolean runRobot;
	Rate r = new Rate(40);
	private boolean pressed = false;

	public BumperController(DifferentialDriveRobot robot) {
		pilot = robot.getDifferentialPilot();
	}

	@Override
	public void stop() {
		runRobot = false;
		pilot.stop();
	}

	@Override
	public void run() {
		runRobot = true;

		while (runRobot) {
			pilot.forward();
			r.sleep();

			if (pressed) {
				pilot.stop();
				pilot.travel(-0.1);
				pilot.stop();
				pilot.rotate(180);
				pilot.stop();
				pressed = false;
			}
			r.sleep();
		}
	}

	@Override
	public void sensorPressed(TouchSensorEvent _e) {
		pressed = true;
	}

	@Override
	public void sensorReleased(TouchSensorEvent _e) {
	}

	@Override
	public void sensorBumped(TouchSensorEvent _e) {
	}

}
