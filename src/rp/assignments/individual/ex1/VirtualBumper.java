package rp.assignments.individual.ex1;

import java.util.ArrayList;

import lejos.robotics.RangeFinder;
//import lejos.robotics.Touch;
//import lejos.robotics.navigation.DifferentialPilot;
import rp.config.RangeFinderDescription;
import rp.robotics.EventBasedTouchSensor;
//import rp.robotics.DifferentialDriveRobot;
//import rp.robotics.EventBasedTouchSensor;
import rp.robotics.TouchSensorEvent;
//import rp.robotics.TouchSensorEventSource;
import rp.robotics.TouchSensorListener;
import rp.systems.StoppableRunnable;
import rp.util.Rate;

public class VirtualBumper extends Thread implements EventBasedTouchSensor, StoppableRunnable {

	private final RangeFinderDescription desc;
	private final RangeFinder ranger;
	private final Float touchRange;
	private float oldValue;
	private float newValue;
	//private TouchSensorListener listener;
	private ArrayList <TouchSensorListener> listeners = new ArrayList<TouchSensorListener>();
	// private final DifferentialPilot pilot;,

	public VirtualBumper(RangeFinderDescription _desc, RangeFinder _ranger, Float _touchRange) {
		this.desc = _desc;
		this.ranger = _ranger;
		this.touchRange = _touchRange;
		start();
	}
	

	@Override
	public void run() {
		boolean wasPressed = false;
		oldValue = ranger.getRange();

		while (true) {
			if (!wasPressed && isPressed()) {
				wasPressed = true;
				newValue = ranger.getRange();
				TouchSensorEvent e = new TouchSensorEvent(oldValue, newValue);
				for(int i = 0; i < listeners.size(); i++)
					listeners.get(i).sensorPressed(e);
			}

			if (wasPressed && !isPressed()) {
				wasPressed = false;
				oldValue = newValue;
				newValue = ranger.getRange();
				TouchSensorEvent e = new TouchSensorEvent(oldValue, newValue);
				for(int i = 0; i < listeners.size(); i++) {
					listeners.get(i).sensorReleased(e);
					listeners.get(i).sensorBumped(e);
				}
			}

			Rate r = new Rate(desc.getRate());
			r.sleep();
		}
	}

	@Override
	public void addTouchSensorListener(TouchSensorListener _listener) {
		listeners.add(_listener);
	}

	@Override
	public boolean isPressed() {
		if (touchRange + desc.getNoise() >= ranger.getRange()
				&& desc.getMinRange() - desc.getNoise() <= ranger.getRange())
			return true;
		return false;
	}

}