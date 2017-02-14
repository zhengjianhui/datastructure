package datastructure.search.searchTree.avl;

import org.junit.Before;
import org.junit.Test;

import datastructure.line.nodeList.doubleNodeList.DLNode;
import datastructure.line.nodeList.doubleNodeList.Iterator.Iterator;
import datastructure.strategy.Strategy;
import datastructure.tree.binTree.BinTreeNode;

/**
 * Created by zhengjianhui on 17/2/12.
 */
public class AVLTest {

    private AVLTree a1;

    private BinTreeNode a2;

    @Before
    public void before() {

        a1 = new AVLTree();
        a1.setData(15);
        a1.setRoot(a1);

        Strategy strategy = new Strategy() {
            @Override
            public int compare(Object obj1, Object obj2) {
                Integer o1 = (Integer)obj1;
                Integer o2 = (Integer)obj2;

                return o1.compareTo(o2);
            }
        };

        a1.insert(6, strategy);
        a1.insert(3, strategy);
        a1.insert(7, strategy);
        a1.insert(4, strategy);
        a1.insert(13, strategy);
        a1.insert(18, strategy);
        a2 = a1.insert(17, strategy);
        a1.insert(20, strategy);
        a1.insert(24, strategy);
        a1.insert(40, strategy);
        a1.insert(30, strategy);
        a1.insert(55, strategy);
        a1.insert(44, strategy);
        a1.insert(33, strategy);

        System.out.println(a2.getData());

    }


    @Test
    public void test() {

        System.out.println("插入前排序");

        Iterator it = a2.inorderOrder();
        while (!it.isDone()) {
            DLNode b = (DLNode)it.currentItem();
            BinTreeNode c = (BinTreeNode) b.getData();
            System.out.println(c.getData());
            it.next();
        }

        System.out.println("当前的根节点：" + a1.getRoot().getData());

    }
}
