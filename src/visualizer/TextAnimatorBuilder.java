package visualizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import visualizer.AnimatorBuilder;
import model.CarAcceptor;
import model.RoadEnd;
import model.Vehicle;
import visualizer.Animator;

/** 
 * A class for building Animators.
 */
public class TextAnimatorBuilder implements AnimatorBuilder {
  TextAnimator _animator;
  public TextAnimatorBuilder() {
    _animator = new TextAnimator();
  }
  public Animator getAnimator() {
    if (_animator == null) { throw new IllegalStateException(); }
    Animator returnValue = _animator;
    _animator = null;
    return returnValue;
  }
  public void addLight(RoadEnd d, int i, int j) {
    _animator.addLight(d,i,j);
  }
  public void addHorizontalRoad(CarAcceptor l, int i, int j, boolean eastToWest) {
    _animator.addRoad(l,i,j);
  }
  public void addVerticalRoad(CarAcceptor l, int i, int j, boolean southToNorth) {
    _animator.addRoad(l,i,j);
  }


  /** Class for drawing the Model. */
  private static class TextAnimator implements Animator {

    /** Triple of a model element <code>x</code> and coordinates. */
    private static class Element<T> {
      T x;
      int i;
      int j;
      Element(T x, int i, int j) {
        this.x = x;
        this.i = i;
        this.j = j;
      }
    }
    
    private List<Element<CarAcceptor>> _roadElements;
    private List<Element<RoadEnd>> _lightElements;
    TextAnimator() {
      _roadElements = new ArrayList<Element<CarAcceptor>>();
      _lightElements = new ArrayList<Element<RoadEnd>>();
    }    
    void addLight(RoadEnd x, int i, int j) {
      _lightElements.add(new Element<RoadEnd>(x,i,j));
    }
    void addRoad(CarAcceptor x, int i, int j) {
      _roadElements.add(new Element<CarAcceptor>(x,i,j));
    }
    
    public void dispose() { }
    
    public void update(Observable o, Object arg) {
      for (Element<RoadEnd> e : _lightElements) {
        System.out.print("Light at (" + e.i + "," + e.j + "): ");
        if (e.x.getLight().getState()) {
          System.out.println("Blue");
        } else {
          System.out.println("Yellow");
        }
      }
      for (Element<CarAcceptor> e : _roadElements) {
        for (Vehicle d : e.x.getCars()) {
          System.out.print("Road at (" + e.i + "," + e.j + "): ");
          System.out.println("Car at " + d.getFrontPosition());
        }
      }
    }
  }
}