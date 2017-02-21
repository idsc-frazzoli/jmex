# jmex

In order to run the jmexDemoApp


1) import the jar file to the classpath

javaaddpath('jmex-0.0.1.jar')

2) start a server in MATLAB in the main window

server = ch.ethz.idsc.jmex.matlab.MfileContainerServer()

3) call the demo app

demoApp(server)



...

at some point, you can close the server using

server.close()



Remark:
It is not recommended to start the server from an m.file!
Instead, the server instance should be accessible from
the global scope in order to be able to close it properly.

