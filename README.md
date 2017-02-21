# jmex

jmex is a data/struct exchange tool between `MATLAB` and java.
It was developed to swap vectors and matrices between `MATLAB` and `MATsim`.

The communication is client/server based. `MATLAB` hosts the server.

The messages passed between client and server are similar to `struct`s., i.e.
a collection of named fields with associated multi-dimensional arrays of type `double`.

In `MATLAB`, to run the `demoApp` follow these 3 steps:

## init

1) import the jar file to the classpath using the command

    javaaddpath('jmex-0.0.1.jar')

2) start a server in MATLAB in the main window

    server = ch.ethz.idsc.jmex.matlab.MfileContainerServer()

## run

3) call the demo app

    demoApp(server)

4) To test the software package, in a console you could now run

    rundemoclient.sh

## close

5) The server is closed in `MATLAB` using

    server.close()



# Remark
It is not recommended to start the server from an m.file!
Instead, the server instance should be accessible from
the global scope in order to be able to close it properly.

