// code by jph
package ch.ethz.idsc.jmex.matlab;

import java.net.Socket;

import ch.ethz.idsc.jmex.Container;
import ch.ethz.idsc.jmex.ContainerSocket;

/** socket to be used in MATLAB to send and receive {@link Container}s */
public class MfileContainerSocket extends ContainerSocket {
  /** @param socket provided by {@link MfileContainerServer} */
  MfileContainerSocket(Socket socket) {
    super(socket);
  }
}
