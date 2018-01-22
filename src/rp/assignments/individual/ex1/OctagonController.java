package rp.assignments.individual.ex1;

import rp.robotics.DifferentialDriveRobot;
import rp.systems.StoppableRunnable;

public class OctagonController implements StoppableRunnable {

	private DifferentialDriveRobot robot;
	private float sideLength;
	private int cornerAngle = 45;
	private final int NUMB_OF_CORNERS = 8;

	public OctagonController(DifferentialDriveRobot robot, float sideLength) {
		this.robot = robot;
		this.sideLength = sideLength;
	}

	@Override
	public void run() {
		for (int i = 0; i < NUMB_OF_CORNERS; i++) {
			robot.getDifferentialPilot().travel(sideLength);
			robot.getDifferentialPilot().rotate(cornerAngle);
		}
	}

	@Override
	public void stop() {
	}

}
