package lru;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1. 新数据插入到链表头部；
 * 2. 每当缓存命中（即缓存数据被访问），则将数据移到链表头部；
 * 3. 当链表满的时候，将链表尾部的数据丢弃。
 *
 * 【命中率】
 *      当存在热点数据时，LRU的效率很好，但偶发性的、周期性的批量操作会导致LRU命中率急剧下降，缓存污染情况比较严重。
 *      大批量插入新数据并且抛弃尾部数据(因为每次插入的直接到头部, 并不是依据使用次数移动到头部, 所以每次插入数据都会后移, 以至于最后被淘汰)
 *
 * 【复杂度】
 *      实现简单。
 * 【代价】
 *      命中时需要遍历链表，找到命中的数据块索引，然后需要将数据移到头部。
 *      详见 jdk 中 LinkHashMap get 方法
 *
 * @author zhengjianhui
 * @Created Date: 1/19/19
 * @Description:
 * @Modify by:
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    /** map 的最大值, 超过部分会被淘汰 */
    private final int maxCapacity;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private final Lock lock = new ReentrantLock();

    public LRULinkedHashMap(int maxCapacity) {
        super(maxCapacity, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
    }

    /**
     * 满足条件返回true。当put进新的值方法返回true时，便移除该map中最老的键和值
     *
     * @param eldest
     * @return boolean
     */
    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            lock.lock();
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }


    @Override
    public V get(Object key) {
        try {
            lock.lock();
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        try {
            lock.lock();
            return super.put(key, value);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        try {
            lock.lock();
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        try {
            lock.lock();
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    public Collection<Map.Entry<K, V>> getAll() {
        try {
            lock.lock();
            return new ArrayList<Map.Entry<K, V>>(super.entrySet());
        } finally {
            lock.unlock();
        }
    }
}
