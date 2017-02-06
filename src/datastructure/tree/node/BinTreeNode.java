package datastructure.tree.node;

import datastructure.Node;
import datastructure.line.nodeList.doubleNodeList.LinkedList;
import datastructure.line.nodeList.doubleNodeList.LinkedListDLNote;
import datastructure.line.nodeList.doubleNodeList.Iterator.Iterator;

/**
 * Created by zhengjianhui on 17/2/5.
 *
 * 二叉树节点的定义
 *
 */
public class BinTreeNode implements Node {

    /**
     * 数据域
     */
    private Object data;

    /**
     * 父节点
     */
    private BinTreeNode parent;

    /**
     * 左子节点
     */
    private BinTreeNode leftChild;

    /**
     * 右子节点
     */
    private BinTreeNode rightChild;

    /**
     * 以该节点为root 的子树高度（高度由 根开始 根为1 深度由叶子开始 叶子为1 ） 叶子节点没有子节点
     */
    private int childTreeHeight;

    /**
     * 该树的后代数，包括自己
     */
    private int size;

    /** assist node status start */

    /**
     * 辅助判断父节点是否为空
     * @return
     */
    public boolean hasParent() {
        return parent != null;
    }

    /**
     * 辅助判断是否有左节点
     * @return
     */
    public boolean hasLeft() {
        return leftChild != null;
    }

    /**
     * 辅助判断是否有右节点
     * @return
     */
    public boolean hasRight() {
        return rightChild != null;
    }

    /**
     * 辅助判断是否是叶子节点
     * @return
     */
    public boolean isLeaf() {
        return (!hasLeft() && !hasRight());
    }

    /**
     * 辅助判断是否是某个节点的 左节点
     * @return
     */
    public boolean isLeftChild() {

        // 父节点不为空 且 本身等于父节点的左孩子
        return (hasParent() && this == parent.leftChild);
    }

    /**
     * 辅助判断是否是某个节点的 右节点
     * @return
     */
    public boolean isRightChild() {

        // 父节点不为空 且 本身等于父节点的右孩子
        return (hasParent() && this == parent.rightChild);
    }

    /** assist node status end */

    /** assist height start */

    /**
     * 获取以该节点为根数的高度
     * @return
     */
    public int getHeight() {
        return childTreeHeight;
    }

    public void updateHeight() {

        int newHeight = 0; // 初始的高度为0， 新高度为左右子树中最高的一个值 + 1

        // 如果有左孩子
        if(hasLeft()) {
            newHeight = Math.max(newHeight, leftChild.getHeight() + 1);
        }

        // 如果有右孩子
        if(hasRight()) {
            newHeight = Math.max(newHeight, rightChild.getHeight() + 1);
        }

        // 如果高度不变直接 return
        if(childTreeHeight == newHeight) {
            return;
        }

        // 否则更新高度
        childTreeHeight = newHeight;

        // 递归更新父树
        if(hasParent()) {
            parent.updateHeight();
        }


    }

    /** assist height end */

    /** assist size start */

    /**
     * 获取以该节点为根 树 的节点树（包括自己）
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 更新以该节点为根 树 的节点树（包括自己）
     * @return
     */
    public void updateSize() {
        int size = 1; // 初始化节点数 （1 代表 自己这个节点）

        // 如果有右孩子饿， 加上右孩子的规模
        if(hasRight()) {
            size += rightChild.getSize();
        }

        // 如果有左孩子饿， 加上左孩子的规模
        if(hasLeft()) {
            size += leftChild.getSize();
        }

        // 递归更新先祖的规模
        if(hasParent()) {
            parent.updateSize();
        }
    }

    /** assist size end */

    /** assist parent start */

    /**
     * 获取父节点
     * @return
     */
    public BinTreeNode getParent() {
        return parent;
    }

    /**
     * 断开与父节点的联系
     */
    public void sever() {

        // 没有父节点直接 return
        if(!hasParent()) {
            return;
        }

        // 判断是父节点的左孩子 还是 右孩子 并断开
        if(isLeftChild()) {
            parent.leftChild = null;
        } else {
            parent.rightChild = null;
        }

        // 更新父元素的 高度 和 节点规模
        parent.updateSize();
        parent.updateHeight();

        // 断开与父元素的联系
        parent = null;

    }

    /** assist parent end */

    /** assist left start */

    public BinTreeNode getLeftChild() {
        return leftChild;
    }

    /**
     * 替换左孩子 并返回原先的做左孩子
     * @param newLeft
     * @return
     */
    public BinTreeNode setLeftChild(BinTreeNode newLeft) {

        // 当前左孩子
        BinTreeNode now = null;
        // 当前左孩子断开与父元素的连接
        if(hasLeft()) {
            now = this.leftChild;
            now.sever();
        }

        newLeft.sever(); // 断开原先父子关系
        // 新的左孩子
        this.leftChild = newLeft;
        // 建立新左孩子的父关系
        newLeft.parent = this;

        // 更新高度 和 规模
        updateHeight();
        updateSize();

        // 返回原先的左孩子
        return now;

    }

    /** assist left end */


    /** assist right start */

    /**
     * 获取右孩子
     * @return
     */
    public BinTreeNode getRightChild() {
        return rightChild;
    }

    /**
     * 替换右孩子 并返回原先的做右孩子
     * @param newRight
     * @return
     */
    public BinTreeNode setRightChild(BinTreeNode newRight) {

        // 当前右孩子
        BinTreeNode now = null;
        // 当前右孩子断开与父元素的连接
        if(hasRight()) {
            now = this.rightChild;
            now.sever();
        }

        newRight.sever(); // 断开原先父子关系
        // 新的右孩子
        this.rightChild = newRight;
        // 建立新右孩子的父关系
        newRight.parent = this;

        // 更新高度 和 规模
        updateHeight();
        updateSize();

        // 返回原先的右孩子
        return now;

    }

    /** assist right end */


    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object obj) {
        this.data = obj;
    }


    /**
     * 递归遍历 树
     * 将树存为一个集合
     *
     */
    public Iterator preOrder() {
        LinkedList list = new LinkedListDLNote();
        // 从根节点开始
        preOrderRecursion(this, list);
        return list.elements();
    }


    /**
     *
     * @param node 节点
     * @param list 保存节点的线性表
     */
    private void preOrderRecursion(BinTreeNode node, LinkedList list) {
        // 如果节点为空则返回
        if(node == null) {
            return;
        }

        // 否则将节点插入双向链表的最后
        list.insertLast(node);
        // 遍历左树
        preOrderRecursion(node.getLeftChild(), list);
        // 遍历右树
        preOrderRecursion(node.getRightChild(), list);
    }

}
