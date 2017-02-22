// code by jph
package ch.ethz.idsc.jmex.demo;

import java.net.Socket;
import java.util.Random;

import ch.ethz.idsc.jmex.Container;
import ch.ethz.idsc.jmex.DoubleArray;
import ch.ethz.idsc.jmex.java.JavaContainerSocket;
import ch.ethz.idsc.jmex.matlab.MfileContainerServer;

public class MatsimSimulator {
  private static void printArray(Container container, String field) {
    if (container.contains(field)) {
      DoubleArray doubleArray = container.get(field);
      System.out.println(doubleArray);
      for (int index = 0; index < doubleArray.value.length; ++index)
        System.out.print("[" + index + "]=" + doubleArray.value[index] + ", ");
      System.out.println();
    } else {
      System.out.println(" !!! field '" + field + "' not present !!! ");
    }
  }

  public static void main(String[] args) {
    if (args.length == 0) {
      args = new String[] { "20", "50", "20" };
    }
    final int nVNodes = Integer.parseInt(args[0]);
    final int nVLinks = Integer.parseInt(args[1]);
    final int total = Integer.parseInt(args[2]);
    Random random = new Random();
    try {
      JavaContainerSocket javaContainerSocket = new JavaContainerSocket( //
          new Socket("localhost", MfileContainerServer.DEFAULT_PORT));
      for (int iteration = 0; iteration < total; ++iteration) {
        {
          Container container = new Container("MPC" + iteration);
          {
            double[] array = new double[nVLinks];
            for (int index = 0; index < nVLinks; ++index)
              array[index] = random.nextInt(10);
            container.add(new DoubleArray("waitCustomersPerVLink", new int[] { nVLinks }, array));
          }
          {
            double[] array = new double[nVLinks];
            for (int index = 0; index < nVLinks; ++index)
              array[index] = random.nextInt(5);
            container.add(new DoubleArray("movingVehiclesPerVLink", new int[] { nVLinks }, array));
          }
          {
            double[] array = new double[nVNodes];
            for (int index = 0; index < nVNodes; ++index)
              array[index] = random.nextInt(10);
            container.add(new DoubleArray("availableVehiclesPerVNode", new int[] { nVNodes }, array));
          }
          System.out.println("write " + iteration);
          javaContainerSocket.writeContainer(container);
        }
        System.out.println("wait " + iteration);
        {
          Container container = javaContainerSocket.blocking_getContainer();
          System.out.println(container);
          printArray(container, "pickupPerVLink");
          printArray(container, "rebalancePerVLink");
        }
      }
      javaContainerSocket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
