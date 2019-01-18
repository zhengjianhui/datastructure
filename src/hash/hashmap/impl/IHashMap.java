package hash.hashmap.impl;

import hash.hashmap.IMap;

/**
 * Created by zhengjianhui on 17/10/22.
 */
public class IHashMap<K, V> implements IMap<K, V> {


    private static final int defaultLenght = 16;

    private int tableSize;

    private double defaultLoad = 0.75;

    private IEntry<K, V>[] table;

    private int size;

    IHashMap(int defaultLenght) {
        this.tableSize = defaultLenght;
        this.table = new IEntry[tableSize];
        this.size = 0;
    }


    public IHashMap() {
        this(defaultLenght);
    }

    @Override
    public V put(K key, V value) {
        int hash = getIndex(key);
        IEntry iEntry = table[hash];
        if (iEntry == null) {
            iEntry = new IEntryImpl(key, value, null, key.hashCode());
            table[hash] = iEntry;
            size++;

        } else {
            IEntry newIEntry = new IEntryImpl(key, value, null, key.hashCode());
            // TODO: 18/1/29 zhengjianhui 逻辑异常等待重构
            IEntry<K, V> hashIEntry = getEntryByHash(key);
            // 如果根据 hash 没有找到 Entry 则新建 否则替换原值
            if (hashIEntry == null) {
                newIEntry.setNext(iEntry);
                // 将链表头记入 table
                table[hash] = newIEntry;

            } else {
                hashIEntry = newIEntry;
            }
            size++;
        }

        // 旧值为空
        return null;

    }

    @Override
    public V get(K key) {
        IEntry<K, V> iEntry = getEntryByHash(key);
        return iEntry == null ? null : iEntry.getValue();
    }

    @Override
    public int size() {
        return size;
    }


    private int getIndex(Object key) {
        return key.hashCode() % (tableSize - 1);
    }

    private IEntry<K, V> getEntryByHash(K key) {
        int hash = getIndex(key);
        IEntry<K, V> iEntry = table[hash];

        // 如果为空则返回 null
        if (iEntry == null) {
            return null;
        }


        IEntry<K, V> temp = iEntry;
        // 链表则遍历, 比较 hash
        do {
            if (temp.getHashCode() == key.hashCode()) {
                return temp;
            }
        } while ((temp = temp.getNext()) != null);

        return null;

    }

    public class IEntryImpl<K, V> implements IEntry<K, V> {

        private K key;

        private V value;

        private int hashCode;

        private IEntry next;

        public IEntryImpl(K key, V value, IEntryImpl next, int hashCode) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hashCode;
        }

//        public IEntryImpl(K key, V value) {
//            this.key = key;
//            this.value = value;
//        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }


        @Override
        public IEntry getNext() {
            return next;
        }

        @Override
        public int getHashCode() {
            return hashCode;
        }

        @Override
        public void setNext(IEntry next) {
            this.next = next;
        }
    }


}
