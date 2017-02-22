// code by jph
package ch.ethz.idsc.jmex;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** class emulates a struct in MATLAB with an identifier */
public class Container implements Serializable {
  public final String id;
  private final Map<String, DoubleArray> map = new LinkedHashMap<>();
  private final List<DoubleArray> list = new ArrayList<>();

  /** @param id is the identifier of the struct */
  public Container(String id) {
    this.id = id;
  }

  /** add {@link DoubleArray} to struct
   * 
   * @param doubleArray */
  public void add(DoubleArray doubleArray) {
    final String key = doubleArray.name;
    if (map.containsKey(key))
      throw new IllegalArgumentException("field already defined: " + key);
    map.put(key, doubleArray);
    list.add(doubleArray);
  }
  
  public int size() {
    return list.size();
  }
  
  /**
   * 
   * @param field
   * @return true if field is defined in container 
   */
  public boolean contains(String field) {
    return map.containsKey(field);
  }

  /** @param field
   * @return null of field is not defined in the container */
  public DoubleArray get(String field) {
    return map.get(field);
  }

  /** @param index
   * @return null of field is not defined in the container */
  public DoubleArray get(int index) {
    return list.get(index);
  }

  @Override
  public String toString() {
    return "{" + id + ": " + map.values() + "}";
  }
}
