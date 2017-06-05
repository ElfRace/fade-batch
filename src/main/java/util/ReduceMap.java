package util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Vector;

public class ReduceMap<K, V> implements Map<K, Collection<V>> {
	private HashMap<K, List<V>> map;

	public ReduceMap() {
		super();
		map = new HashMap<K, List<V>>();
	}

	public int size() {
		Collection<List<V>> values = map.values();
		int size = 0;
		for (List<V> list : values) {
			size += list.size();
		}
		return size;
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		for (List<V> list : map.values()) {
			if (list.contains(value))
				return true;
		}
		return false;
	}

	/**
	 * 有序，不去重，返回值可能为null
	 */
	@Deprecated
	public Collection<V> get(Object key) {
		return map.get(key);
	}

	/**
	 * 有序，不去重
	 */
	public List<V> getList(Object key) {
		Collection<V> list = map.get(key);
		if (list == null)
			return new ArrayList<V>();
		return new ArrayList<V>(list);
	}

	/**
	 * 无序，去重
	 */
	public Set<V> getSet(Object key) {
		Collection<V> list = map.get(key);
		if (list == null)
			return new HashSet<V>();
		return new HashSet<V>(list);
	}

	public Vector<V> getVector(Object key) {
		Collection<V> list = map.get(key);
		if (list == null)
			return new Vector<V>();
		return new Vector<V>(list);
	}

	/**
	 * 有序
	 */
	public Stack<V> getStack(Object key) {
		List<V> list = map.get(key);
		Stack<V> stack = new Stack<V>();
		if (list != null)
			for (int i = list.size() - 1; i >= 0; i--) {
				stack.push(list.get(i));
			}
		return stack;
	}

	public Collection<V> put(K key, Collection<V> value) {
		Collection<V> list = getCollection(key);
		list.addAll(value);
		return null;
	}

	public void put(Collection<K> key, V value) {
		for (K k : key) {
			put(k, value);
		}
	}

	public Collection<V> put(K key, V value) {
		Collection<V> list = getCollection(key);
		list.add(value);
		return null;
	}

	private Collection<V> getCollection(K key) {
		List<V> list = map.get(key);
		if (list == null)
			list = new ArrayList<V>();
		return list;
	}

	public Collection<V> remove(Object key) {
		return map.remove(key);
	}

	public boolean remove(Object key, Object value) {
		@SuppressWarnings("unchecked")
		Collection<V> list = getCollection((K) key);
		return list.remove(value);
	}

	public void putAll(Map<? extends K, ? extends Collection<V>> m) {
		for (Entry<? extends K, ? extends Collection<V>> entry : m.entrySet()) {
			put(entry.getKey(), entry.getValue());
		}
	}

	public void clear() {
		map.clear();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Collection<Collection<V>> values() {
		return (Collection) map.values();
	}

	/**
	 * entryset中的size是key的数量，若要得到当前map中values的数量请使用size()方法
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Entry<K, Collection<V>>> entrySet() {
		return (Set) map.entrySet();
	}

}
