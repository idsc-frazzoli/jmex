// code by jph
package ch.ethz.idsc.jmex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** class emulates a struct in MATLAB with an identifier */
public class Container implements Serializable {
  public final String id;
  public final List<DoubleArray> list = new ArrayList<>();

  /** @param id is the identifier of the struct */
  public Container(String id) {
    this.id = id;
  }

  /** add {@link DoubleArray} to struct
   * 
   * @param doubleArray */
  public void add(DoubleArray doubleArray) {
    list.add(doubleArray);
  }

  @Override
  public String toString() {
    return "{" + id + ": " + list + "}";
  }
}
