function jmexDemoApp(server)
% routine is terminated anytime by pressing Ctrl+C

while 1

  socket = jmexWaitForConnection(server)

  while socket.isConnected()
    if socket.hasContainer()

% ======================================
% get data structure from client socket
container = socket.pollContainer();
[MPC,id] = jmexStruct(container)

%sol=func(MPC,id) % for instance, solve MPC here
%[res,id]=jmexStruct(sol)

% write reply to client socket
sol = ch.ethz.idsc.jmex.Container('someId');
sol.add(jmexArray('rand',rand(5)))
sol.add(jmexArray('foob',rand(2,5,3)))

socket.writeContainer(sol)

% ======================================

    else
      pause(.002)
    end
  end

  socket.close()

end

