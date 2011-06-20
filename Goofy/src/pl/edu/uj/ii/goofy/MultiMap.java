package pl.edu.uj.ii.goofy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;

public class MultiMap<K, V> {

	private Hashtable<K, HashSet<V>> ht;
	
	public MultiMap() {
		ht = new Hashtable<K, HashSet<V>>();
	}
	
	public void put(K key, V value) {
		if (ht.containsKey(key)) {
			ht.get(key).add(value);
		} else {
			HashSet<V> hashset = new HashSet<V>();
			hashset.add(value);
			ht.put(key, hashset);
		}
	}
	
	public void put(K key, Collection<V> values) {
		if (ht.containsKey(key)) {
			HashSet<V> hs = ht.get(key);
			for (V value : values) {
				hs.add(value);
			}
		} else {
			HashSet<V> hs = new HashSet<V>();
			for (V value : values) {
				hs.add(value);
			}
			ht.put(key, hs);
		}
	}
	
	public Collection<K> keySet() {
		return ht.keySet();
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
