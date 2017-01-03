package rp.assignments.individual.ex1;

import lejos.robotics.RangeFinder;
import rp.config.RangeFinderDescription;
import rp.robotics.DifferentialDriveRobot;
import rp.robotics.EventBasedTouchSensor;
import rp.systems.ControllerWithTouchSensor;
import rp.systems.StoppableRunnable;

/**
 * In this class you must edit the "create*" methods to provide instances of
 * your controller objects in order to pass the automated tests. For example, if
 * you have a class called BumperCar which implements
 * {@code ControllerWithTouchSensor} for the bumper controller part of the
 * assignment, you would edit the createBumperController method as follows:
 * 
 * <pre>
 * {@code
 * public static ControllerWithTouchSensor createBumperController(
 * 		DifferentialDriveRobot _robot) {
 * 	return new BumperCar(_robot);
 * }
 * }
 * </pre>
 * 
 *
 */
public class SolutionFactory {

	public static StoppableRunnable createEquilateralTriangleController(
			DifferentialDriveRobot _robot, Float _sideLength) {
		return new TriangleController(_robot, _sideLength);
	}

	public static StoppableRunnable createSquareController(
			DifferentialDriveRobot _robot, Float _sideLength) {
		return null;
	}

	public static StoppableRunnable createDecagonController(
			DifferentialDriveRobot _robot, Float _sideLength) {
		return null;
	}

	public static ControllerWithTouchSensor createBumperController(
			DifferentialDriveRobot _robot) {
		return null;
	}

	public static EventBasedTouchSensor createVirtualBumper(
			RangeFinderDescription _desc, RangeFinder _ranger, Float _touchRange) {
		return null;
	}

}