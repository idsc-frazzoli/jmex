// code by jph
package ch.ethz.idsc.jmex;

import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/** allows to send and receive {@link Container}s */
public abstract class ContainerSocket extends ObjectSocket {
  // written and read to in separate threads
  private Queue<Container> queue = new ConcurrentLinkedQueue<>();

  public ContainerSocket(Socket socket) {
    super(socket);
  }

  @Override
  public final void handle(Object object) {
    queue.add((Container) object);
    protected_handle((Container) object);
  }

  protected void protected_handle(Container container) {
    // override if necessary
  }

  /** check if container has been received
   * 
   * @return true, if container is available */
  public final boolean hasContainer() {
    return !queue.isEmpty();
  }

  /** @return earliest received container */
  public final Container pollContainer() {
    return queue.poll();
  }

  /** @param container to be written via the socket */
  public final void writeContainer(Container container) {
    try {
      protected_write(container);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
