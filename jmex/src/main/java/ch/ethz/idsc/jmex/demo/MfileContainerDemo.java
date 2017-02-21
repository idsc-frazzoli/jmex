// code by jph
package ch.ethz.idsc.jmex.demo;

import ch.ethz.idsc.jmex.Container;
import ch.ethz.idsc.jmex.DoubleArray;

/** a demo to handle {@link Container} in MATLAB */
public class MfileContainerDemo {
  public static int staticFunc() {
    return 1;
  }

  public int value;

  public MfileContainerDemo(int value) {
    this.value = value;
  }

  public Container getContainer() {
    Container dc = new Container("sync");
    dc.list.add(new DoubleArray("A23test", new int[] { 2, 3 }, new double[6]));
    dc.list.add(new DoubleArray("B35only", new int[] { 3, 5 }, new double[15]));
    return dc;
  }
}
