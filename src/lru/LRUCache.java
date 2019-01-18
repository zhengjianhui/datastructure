package lru;

import java.util.HashMap;

/**
 * LRU-K 为了弥补 LRU 的缓存污染
 * LRU-K中的K代表最近使用的次数，因此LRU可以认为是LRU-1。LRU-K的主要目的是为了解决LRU算法“缓存污染”的问题，
 * 其核心思想是将“最近使用过1次”的判断标准扩展为“最近使用过K次”。
 * 相比LRU，LRU-K需要多维护一个队列，用于记录所有缓存数据被访问的历史。
 * 只有当数据的访问次数达到K次的时候，才将数据放入缓存。当需要淘汰数据时，LRU-K会淘汰第K次访问时间距当前时间最大的数据。
 *
 * 数据第一次被访问时，加入到历史访问列表，如果书籍在访问历史列表中没有达到K次访问，则按照一定的规则（FIFO,LRU）淘汰；
 * 当访问历史队列中的数据访问次数达到K次后，将数据索引从历史队列中删除，将数据移到缓存队列中，并缓存数据，
 * 缓存队列重新按照时间排序；缓存数据队列中被再次访问后，重新排序，需要淘汰数据时，淘汰缓存队列中排在末尾的数据，
 * 即“淘汰倒数K次访问离现在最久的数据”。
 * LRU-K具有LRU的优点，同时还能避免LRU的缺点，实际应用中LRU-2是综合最优的选择。
 * 由于LRU-K还需要记录那些被访问过、但还没有放入缓存的对象，因此内存消耗会比LRU要多。
 *
 * 传统意义的LRU算法是为每一个Cache对象设置一个计数器，每次Cache命中则给计数器+1，而Cache用完，需要淘汰旧内容，放置新内容时，
 * 就查看所有的计数器，并将最少使用的内容替换掉。它的弊端很明显，如果Cache的数量少，问题不会很大，
 * 但是如果Cache的空间过大，达到10W或者100W以上，一旦需要淘汰，则需要遍历所有计算器，其性能与资源消耗是巨大的。效率也就非常的慢了。
 *
 * 它的原理： 将Cache的所有位置都用双连表连接起来，当一个位置被命中之后，就将通过调整链表的指向，将该位置调整到链表头的位置，新加入的Cache直接加到链表头中。
 * 这样，在多次进行Cache操作后，最近被命中的，就会被向链表头方向移动，而没有命中的，而想链表后面移动，链表尾则表示最近最少使用的Cache。
 * 当需要替换内容时候，链表的最后位置就是最少被命中的位置，我们只需要淘汰链表最后的部分即可。
 *
 *
 * (本质是将使用过的数据直接移动到头部, 避免了直接插入到头部导致的数据后移被淘汰的污染, 同时表面大数据量的时候遍历计数器淘汰)
 *
 * @author zhengjianhui
 * @Created Date: 1/19/19
 * @Description:
 * @Modify by:
 */
public class LRUCache<K, V> {

    private int currentCacheSize;
    private int CacheCapcity;
    private HashMap<K, CacheNode> caches;
    private CacheNode first;
    private CacheNode last;

    public LRUCache(int size) {
        currentCacheSize = 0;
        this.CacheCapcity = size;
        caches = new HashMap(size);
    }

    public void put(K k, V v) {
        CacheNode node = caches.get(k);
        if (node == null) {

            // 如果没有命中缓存则判断缓存是否到达最大
            // 如果超出最大缓存, 则删除最后一个
            if (caches.size() >= CacheCapcity) {
                caches.remove(last.key);
                removeLast();
            }
            node = new CacheNode();
            node.key = k;
        }

        // 无论是否命中, 缓存后直接将节点移动到头部
        node.value = v;
        moveToFirst(node);
        caches.put(k, node);
    }


    public Object get(K k) {
        CacheNode node = caches.get(k);
        if (node == null) {
            return null;
        }
        moveToFirst(node);
        return node.value;
    }

    public Object remove(K k) {
        CacheNode node = caches.get(k);
        if (node != null) {
            // 同移动头部, 摘除在链表中和上一个节点的关系
            if (node.pre != null) {
                node.pre.next = node.next;
            }

            // 同移动到头部, 摘除在链表中和下一个节点的关系
            if (node.next != null) {
                node.next.pre = node.pre;
            }

            if (node == first) {
                first = node.next;
            }

            if (node == last) {
                last = node.pre;
            }
        }

        return caches.remove(k);
    }

    public void clear() {
        first = null;
        last = null;
        caches.clear();
    }


    private void moveToFirst(CacheNode node) {
        // 如果 node 就是第一个直接结束
        if (first == node) {
            return;
        }

        // 如果存在下一个节点, 则让下一个节点的上一个节点变成当前节点的上一个节点
        // 摘除当前节点在链表中和下一个节点的关系
        if (node.next != null) {
            node.next.pre = node.pre;
        }

        // 如果存在上一个节点, 则让上一个节点的下一个节点变成当前节点的下一个节点
        // 摘除当前节点在链表中和上一个节点的关系
        if (node.pre != null) {
            node.pre.next = node.next;
        }

        // 如果是最后一个则把前一个变为最后一个
        if (node == last) {
            last = last.pre;
        }


        // 如果没数据则直接将当前节点设置为首尾节点, 直接结束
        if (first == null || last == null) {
            first = last = node;
            return;
        }

        // 将当前节点直接移动到头部, 并将头部设置为当前节点的下一个节点
        node.next = first;
        first.pre = node;
        first = node;
        first.pre = null;

    }

    /**
     * 将最后一个节点的上一个设置为最后一个
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CacheNode node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }

        return sb.toString();
    }

    class CacheNode {
        CacheNode pre;
        CacheNode next;
        Object key;
        Object value;

        public CacheNode() {

        }
    }

    public static void main(String[] args) {

        LRUCache<Integer, String> lru = new LRUCache<Integer, String>(3);

        // 1:a
        lru.put(1, "a");
        System.out.println(lru.toString());
        // 2:b 1:a
        lru.put(2, "b");
        System.out.println(lru.toString());
        // 3:c 2:b 1:a
        lru.put(3, "c");
        System.out.println(lru.toString());
        // 4:d 3:c 2:b
        lru.put(4, "d");
        System.out.println(lru.toString());
        // 1:aa 4:d 3:c
        lru.put(1, "aa");
        System.out.println(lru.toString());
        // 2:bb 1:aa 4:d
        lru.put(2, "bb");
        System.out.println(lru.toString());
        // 5:e 2:bb 1:aa
        lru.put(5, "e");
        System.out.println(lru.toString());
        // 1:aa 5:e 2:bb
        lru.get(1);
        System.out.println(lru.toString());
        // 1:aa 5:e 2:bb
        lru.remove(11);
        System.out.println(lru.toString());
        //5:e 2:bb
        lru.remove(1);
        System.out.println(lru.toString());
        //1:aaa 5:e 2:bb
        lru.put(1, "aaa");
        System.out.println(lru.toString());
    }
}
