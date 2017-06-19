package hash.mod;


import java.util.Random;

/**
 * Created by zhengjianhui on 17/6/19.
 */
public class MyHashMap<T> {

    private static int lenght = 8;

    private HashNode<T>[] datas = new HashNode[lenght];

    private int size = 0;

    private int nodeNumber = 0;


    public void add(T t) {
        if (lenght == size) {
            expandContainer();
        }

        int index = getIndex(t);
        HashNode node = datas[index];
        // when node not empty then get node last node
        if (node != null) {
            node = node.getLastNode();
            HashNode<T> newNode = new HashNode<T>();
            newNode.setPre(node);
            newNode.setData(t);

            node.setNext(newNode);

        } else { // when node is empty then add Node
            node = new HashNode();
            node.setData(t);
            datas[index] = node;

            size++;
        }

        nodeNumber++;

    }

    public void expandContainer() {
        lenght = lenght << 1;
        HashNode<T>[] newDatas = new HashNode[lenght];

        for (int i = 0; i < size; i++) {
            int index = getIndex((T) datas[i]);
            newDatas[index] = datas[i];

        }


        datas = newDatas;

    }


    private int getIndex(T t) {
        return hashCode(t) % lenght;
    }


    private int hashCode(T t) {
        // ....防止负数
        return Math.abs(t.hashCode());
    }


    static class HashNode<T> {

        private HashNode pre;

        private HashNode next;

        private T data;

        /**
         * get last node
         *
         * @return last node
         */
        public HashNode<T> getLastNode() {
            HashNode node = this;
            if (node.next != null) {
                node = getNext();
            }

            return node;
        }


        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public HashNode getPre() {
            return pre;
        }

        public void setPre(HashNode pre) {
            this.pre = pre;
        }

        public HashNode getNext() {
            return next;
        }

        public void setNext(HashNode next) {
            this.next = next;
        }
    }


    /**
     * 模拟容器拓展是的 hash 重新分配
     * @param args
     */
    public static void main(String[] args) {
        Random ran = new Random();
        MyHashMap<Integer> datas = new MyHashMap<>();
        datas.add(15);
        datas.add(25);
        datas.add(333);
        datas.add(4123);
        datas.add(51231);
        datas.add(666);
        datas.add(7);
        datas.add(8);
        datas.add(1231231);
        datas.add(123123123);
        datas.add(12399);
        datas.add(12723);
        datas.add(1744700222);


        datas.add(9876543);
        datas.add(123123);
        datas.add(56756);

        // container expand before the index  0 1 2 3 5 7

        // container expand
        datas.add(987987);
        datas.add(0);
        datas.add(78943);

        // container expand after the index 0 2 3 5 6 9 15





    }
}
