function [S,id] = jmexStruct(container)

S = struct;

for index = 0 : container.list.size() - 1
  doubleArray = container.list.get(index);
  S = setfield(S, ...
    char(doubleArray.name), ...
    reshape(doubleArray.value,doubleArray.size'));
end

id = char(container.id);

