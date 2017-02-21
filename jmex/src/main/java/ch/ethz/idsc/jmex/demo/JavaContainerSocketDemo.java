// code by jph
package ch.ethz.idsc.jmex.demo;

import java.net.Socket;

import ch.ethz.idsc.jmex.Container;
import ch.ethz.idsc.jmex.DoubleArray;
import ch.ethz.idsc.jmex.java.JavaContainerSocket;
import ch.ethz.idsc.jmex.matlab.MfileContainerServer;

/** non-public */
public class JavaContainerSocketDemo {
  public static void main(String[] args) {
    try {
      JavaContainerSocket javaContainerSocket = new JavaContainerSocket( //
          new Socket("localhost", MfileContainerServer.DEFAULT_PORT));
      for (int index = 0; index < 10; ++index) {
        {
          Container container = new Container("MPC" + index);
          container.list.add(new DoubleArray("sA23t", new int[] { 2, 3 }, new double[6]));
          container.list.add(new DoubleArray("sB35o", new int[] { 3, 5 }, new double[15]));
          container.list.add(new DoubleArray("val22", new int[] { 2, 2 }, new double[4]));
          System.out.println("write " + index);
          javaContainerSocket.writeContainer(container);
        }
        System.out.println("wait " + index);
        {
          Container container = javaContainerSocket.blocking_getContainer();
          System.out.println(container);
        }
      }
      javaContainerSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
