// code by jph
package ch.ethz.idsc.jmex;

import java.io.Serializable;

/** class emulates a multi-dimensional double array in MATLAB with a name */
public class DoubleArray implements Serializable {
  public final String name;
  public final int[] size;
  public final double[] value;

  /** creates a multi-dimensional double array
   * 
   * @param name of array
   * @param size
   * @param value entries in array */
  public DoubleArray(String name, int[] size, double[] value) {
    this.name = name;
    this.size = size;
    this.value = value;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int val : size)
      stringBuilder.append(val + " ");
    return name + ": [" + stringBuilder.toString().trim() + "]";
  }
}
