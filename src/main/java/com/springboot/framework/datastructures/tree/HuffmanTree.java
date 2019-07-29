package com.springboot.framework.datastructures.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 哈夫曼树
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/29 14:50
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};

        // 哈夫曼树的root结点
        Node root = createHuffmanTree(arr);
        root.preOrder();

    }

    /**
     * 创建哈夫曼树的方法
     *
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建号后的哈夫曼树的root结点
     */
    public static Node createHuffmanTree(int[] arr) {
        // 第一步，为了操作方便
        // 1.遍历arr 数组
        // 2.将arr的每个元素构建成一个Node
        // 3.将Node 放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int i : arr) {
            nodes.add(new Node(i));
        }
        while (nodes.size() > 1) {
            // 0.排序 从小到大
            Collections.sort(nodes);
            // 取出根结点权值最小的两颗二叉树
            // 1.取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            // 2.取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            // 3.构建新的二叉树
            Node parentNode = new Node(leftNode, rightNode);
            // 4.从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5.将parentNode加入到nodes
            nodes.add(parentNode);
            // 6.再次排序
            Collections.sort(nodes);
        }
        return nodes.get(0);
    }
}

/**
 * 创建结点类
 * 为了让Node 对象支持排序Collections集合排序
 * 让Node 实现Comparable接口
 */
class Node implements Comparable<Node> {
    /**
     * 结点权值
     */
    int value;
    /**
     * 指向左子结点
     */
    Node leftNode;
    /**
     * 指向右子结点
     */
    Node rightNode;

    public Node(int value) {
        this.value = value;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.value = leftNode.value + rightNode.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }
        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value - o.value;
    }
}