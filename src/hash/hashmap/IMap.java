package hash.hashmap;

/**
 * Created by zhengjianhui on 17/10/22.
 */
public interface IMap<K, V> {

    V put(K key, V value);

    V get(K key);

    int size();

    interface IEntry<K, V> {

        K getKey();

        V getValue();

        IEntry getNext();

        int getHashCode();

        void setNext(IEntry next);

    }
}
