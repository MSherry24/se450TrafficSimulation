package visualizer;

import java.util.List;
import java.util.ArrayList;
import java.util.Observable;

import properties.PropertyBag;
import timeserver.TimeServer;

import model.CarAcceptor;
import model.CarSource;
import model.Data;
import model.Intersection;
import model.LightObj;
import model.RoadEnd;

import properties.PropertyBag.TrafficType;
import model.Data.Orientation;
import model.Sink;


/**
 * An example to model for a simple visualization.
 * The model contains roads organized in a matrix.
 * See {@link #Model(AnimatorBuilder, int, int)}.
 */
public class Model extends Observable {
	private Animator _animator;
	private boolean _disposed;
	private PropertyBag _propertyBag;
	private TimeServer _time;
	private Data _dataFactory;

	/** Creates a model to be visualized using the <code>builder</code>.
	 *  If the builder is null, no visualization is performed.
	 *  The number of <code>rows</code> and <code>columns</code>
	 *  indicate the number of {@link LightObj}s, organized as a 2D
	 *  matrix.  These are separated and surrounded by horizontal and
	 *  vertical {@link Road}s.  For example, calling the constructor with 1
	 *  row and 2 columns generates a model of the form:
	 *  <pre>
	 *     |  |
	 *   --@--@--
	 *     |  |
	 *  </pre>
	 *  where <code>@</code> is a {@link LightObj}, <code>|</code> is a
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
	public Model(AnimatorBuilder builder, Integer rows, Integer columns) {
		this._propertyBag = PropertyBag.makePropertyBag();
		this._time = this._propertyBag.getTimeServer();
		this._dataFactory = new Data();
		if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
			throw new IllegalArgumentException();
		}
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
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
		List<CarAcceptor> roads = new ArrayList<CarAcceptor>();
		RoadEnd[][] intersections = new RoadEnd[rows][columns];

		// Add Lights
		for (int i=0; i<rows; i++) {
			for (int j=0; j<columns; j++) {
				intersections[i][j] = Data.makeIntersection();
				builder.addLight(intersections[i][j], i, j);
				_time.enqueue(this._time.currentTime(), intersections[i][j].getLight());
			}
		}

		// Add Horizontal Roads With Lights
		boolean eastToWest = false;
		for (int i=0; i<rows; i++) {
			CarSource carsource = new CarSource(Orientation.EW);
			for (int j=0; j<=columns; j++) {
				CarAcceptor l = Data.makeRoad();
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
			CarSource carsource = new CarSource(Orientation.NS);
			for (int i=0; i<=rows; i++) {
				CarAcceptor l = Data.makeRoad();
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