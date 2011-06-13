package pl.edu.uj.ii.goofy;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

public class MultiMap<K, V> {

	private Hashtable<K, List<V>> ht;
	
	public MultiMap() {
		ht = new Hashtable<K, List<V>>();
	}
	
	public void put(K key, V value) {
		if (ht.containsKey(key)) {
			ht.get(key).add(value);
		} else {
			LinkedList<V> list = new LinkedList<V>();
			list.add(value);
			ht.put(key, list);
		}
	}
	
	public Collection<V> getValues(K key) {
		return ht.get(key);
	}
	
	public int getValuesCount(K key) {
		if (ht.get(key) == null) {
			return 0;
		} else {
			return ht.get(key).size();
		}
	}
}
