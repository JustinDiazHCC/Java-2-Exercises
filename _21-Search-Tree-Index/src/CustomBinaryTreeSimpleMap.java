import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of SimpleMap using a custom binary search tree
 */
public class CustomBinaryTreeSimpleMap<K extends Comparable<K>, V> implements SimpleMap<K, V> {

    private final Map<K, V> map;

    public CustomBinaryTreeSimpleMap() { map = new TreeMap<>(); }

    @Override
    public void clear() {
        for (int i = 0; i < map.size(); i++) {
            if (map.size() > 0) {
                map.remove(i, map.get(i));
            }
        }
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V get(K key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return null;
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (map.containsKey(key)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        if (map.size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
