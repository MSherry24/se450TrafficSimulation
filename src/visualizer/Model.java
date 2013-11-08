package visualizer;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Car;
import model.CarSource;
import model.Light;
import model.PropertyBag;
import model.PropertyBag.TrafficType;
import model.Road;
import model.Car.Orientation;
import model.Sink;
import model.TimeServerLinked;


/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimatorBuilder, int, int)}.
 */
public class Model extends Observable {
	private Animator _animator;
	private boolean _disposed;
	private PropertyBag _propertyBag;
	private TimeServerLinked _time;

	/** Creates a model to be visualized using the <code>builder</code>.
	 *  If the builder is null, no visualization is performed.
	 *  The number of <code>rows</code> and <code>columns</code>
	 *  indicate the number of {@link Light}s, organized as a 2D
	 *  matrix.  These are separated and surrounded by horizontal and
	 *  vertical {@link Road}s.  For example, calling the constructor with 1
	 *  row and 2 columns generates a model of the form:
	 *  <pre>
	 *     |  |
	 *   --@--@--
	 *     |  |
	 *  </pre>
	 *  where <code>@</code> is a {@link Light}, <code>|</code> is a
	 *  vertical {@link Road} and <code>--</code> is a horizontal {@link Road}.
	 *  Each road has one {@link Car}.
	 *
	 *  <p>
	 *  The {@link AnimatorBuilder} is used to set up an {@link
	 *  Animator}.
	 *  {@link AnimatorBuilder#getAnimator()} is registered as
	 *  an observer of this model.
	 *  <p>
	 */
	public Model(AnimatorBuilder builder, Integer rows, Integer columns, PropertyBag propertyBag) {
		this._propertyBag = propertyBag;
		this._time = new TimeServerLinked();
		if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
			throw new IllegalArgumentException();
		}
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		//_agents = new ArrayList<Agent>();
		setup(builder, rows, columns);
		_animator = builder.getAnimator();
		super.addObserver(_animator);
		this._time.addObserver(_animator);
	}

	/**
	 * Run the simulation for <code>duration</code> model seconds.
	 */
	public void run(double duration) {
		if (_disposed)
			throw new IllegalStateException();
		this._time.run(this._propertyBag.getRunTime());
		super.setChanged();
		super.notifyObservers();
	}

	/**
	 * Throw away this model.
	 */
	public void dispose() {
		_animator.dispose();
		_disposed = true;
	}

	/**
	 * Construct the model, establishing correspondences with the visualizer.
	 */
	private void setup(AnimatorBuilder builder, Integer rows, Integer columns) {
		List<Road> roads = new ArrayList<Road>();
		Light[][] intersections = new Light[rows][columns];
		Boolean reverse;

		// Add Lights
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				intersections[i][j] = new Light(this._propertyBag, this._time);
				builder.addLight(intersections[i][j], i, j);
				_time.enqueue(this._time.currentTime(), intersections[i][j]);
			}
		}

		// Add Horizontal Roads With Lights
		boolean eastToWest = false;
		for (int i=0; i<rows; i++) {
			CarSource carsource = new CarSource(this._propertyBag, this._time, Orientation.EW);
			for (int j=0; j<=columns; j++) {
				Road l = new Road(_propertyBag);
				if (j == 0) {
					carsource.setNextRoad(l);
					l.setNextRoad(intersections[i][j]);
				}
				else if (j == columns) {
					intersections[i][j-1].setNextRoad(l, Orientation.EW);
					l.setNextRoad(new Sink());
				}
				else {
					intersections[i][j-1].setNextRoad(l, Orientation.EW);
					l.setNextRoad(intersections[i][j]);
				}

				builder.addHorizontalRoad(l, i, j, eastToWest);
				roads.add(l);
			}
			if (_propertyBag.getTrafficPattern() == TrafficType.ALTERNATING) {
				eastToWest = !eastToWest;
			}
		}

		//		 Add Vertical Roads With Lights
		boolean southToNorth = false;
		for (int j=0; j<columns; j++) {
			CarSource carsource = new CarSource(this._propertyBag, this._time, Orientation.NS);
			for (int i=0; i<=rows; i++) {
				Road l = new Road(_propertyBag);
				if (i == 0) {
					carsource.setNextRoad(l);
					l.setNextRoad(intersections[i][j]);	
				}
				else if (i == rows) {
					intersections[i-1][j].setNextRoad(l, Orientation.NS);
					l.setNextRoad(new Sink());
				}
				else {
					intersections[i-1][j].setNextRoad(l, Orientation.NS);
					l.setNextRoad(intersections[i][j]);
				}

				builder.addVerticalRoad(l, i, j, southToNorth);
				roads.add(l);
			}
			if (_propertyBag.getTrafficPattern() == TrafficType.ALTERNATING) {
				southToNorth = !southToNorth;
			}
		}
	}
}