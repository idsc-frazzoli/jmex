// code by jph
package ch.ethz.idsc.jmex;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public abstract class ObjectSocket {
  final Socket socket;
  private ObjectOutputStream objectOutputStream;
  private ObjectInputStream objectInputStream = null;
  private volatile boolean launched = true;

  public ObjectSocket(final Socket socket) {
    this.socket = socket;
    try {
      // flush the stream immediately to ensure that constructors for receiving ObjectInputStreams will not block when reading the header
      objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
      objectOutputStream.flush();
    } catch (Exception myException) {
      myException.printStackTrace();
    }
    Thread myThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          if (objectInputStream == null)
            // constructor blocks until the corresponding ObjectOutputStream has written and flushed the header
            objectInputStream = new ObjectInputStream(socket.getInputStream());
          while (launched) {
            Object object = objectInputStream.readObject(); // blocking, might give EOFException
            handle(object);
          }
        } catch (Exception myException) {
          if (launched) {
            message("stop read");
            // myException.printStackTrace();
          }
        }
        launched = false;
      }
    });
    myThread.start();
  }

  public abstract void handle(Object object);

  protected synchronized void protected_write(Object object) throws Exception {
    objectOutputStream.writeObject(object);
    objectOutputStream.flush();
  }

  public boolean isConnected() {
    return launched;
  }

  public void close() {
    launched = false;
    try {
      socket.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void message(String message) {
    System.out.println(getClass().getSimpleName() + ": " + message);
  }
}
