package datastructure.tree;

import org.junit.Before;
import org.junit.Test;

import datastructure.line.nodeList.doubleNodeList.DLNode;
import datastructure.line.nodeList.doubleNodeList.Iterator.Iterator;
import datastructure.tree.node.BinTreeNode;

/**
 * Created by zhengjianhui on 17/2/6.
 */
public class BinTreeTest {

    private BinTreeNode a;

    /**
     *
     *          A
     *      B       C
     *   D    E   F   G
     *
     *   先序遍历 结果为
     *   A BDE CFG
     *
     */
    @Before
    public void before() {
        System.out.println("建立基础二叉树结构");

        a = new BinTreeNode();
        a.setData("A");
        BinTreeNode b = new BinTreeNode();
        b.setData("B");
        BinTreeNode c = new BinTreeNode();
        c.setData("C");
        BinTreeNode d = new BinTreeNode();
        d.setData("D");
        BinTreeNode e = new BinTreeNode();
        e.setData("E");
        BinTreeNode f = new BinTreeNode();
        f.setData("F");
        BinTreeNode g = new BinTreeNode();
        g.setData("G");

        a.setLeftChild(b);
        a.setRightChild(c);
        b.setLeftChild(d);
        b.setRightChild(e);
        c.setLeftChild(f);
        c.setRightChild(g);

    }

    @Test
    public void test() {
        System.out.println("开始遍历");
        Iterator it = a.preOrder();

        while(!it.isDone()) {
            DLNode b = (DLNode)it.currentItem();
            BinTreeNode c = (BinTreeNode) b.getData();
            System.out.println(c.getData());
            it.next();
        }

    }

}
