function demoMatsimApp(server)
% routine is terminated anytime by pressing Ctrl+C

while 1

  socket = jmexWaitForConnection(server)

  while socket.isConnected()
    if socket.hasContainer()

% ======================================
% get data structure from client socket
container = socket.pollContainer();
[MPC,id] = jmexStruct(container)

MPC.waitCustomersPerVLink'
MPC.movingVehiclesPerVLink'
MPC.availableVehiclesPerVNode'

%sol=func(MPC,id) % for instance, solve MPC here
%[res,id]=jmexStruct(sol)

% write reply to client socket
sol = ch.ethz.idsc.jmex.Container(['sol_' id]);
sol.add(jmexArray('pickupPerVLink',rand(12,1)))
sol.add(jmexArray('rebalancePerVLink',rand(25,1)))

socket.writeContainer(sol)

% ======================================

    else
      pause(.002)
    end
  end

  socket.close()

end

