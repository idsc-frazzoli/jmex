// code by jph
package ch.ethz.idsc.jmex.java;

import java.net.Socket;

import ch.ethz.idsc.jmex.Container;
import ch.ethz.idsc.jmex.ContainerSocket;

/** socket of Java client to communicate with MATLAB
 * to send and receive {@link Container}s */
public class JavaContainerSocket extends ContainerSocket {
  public JavaContainerSocket(Socket socket) {
    super(socket);
  }

  /** blocking call until {@link Container} has been sent from MATLAB
   * 
   * @return received container */
  public Container blocking_getContainer() {
    try {
      while (!hasContainer())
        Thread.sleep(10);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return pollContainer();
  }

  @Override
  public void protected_handle(Container container) {
    // do nothing
  }
}
