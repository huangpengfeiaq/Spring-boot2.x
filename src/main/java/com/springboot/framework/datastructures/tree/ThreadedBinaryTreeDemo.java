package com.springboot.framework.datastructures.tree;

import lombok.Data;

/**
 * 线索化二叉树
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/28 21:27
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试中序线索二叉树的功能
        HeroNode2 root = new HeroNode2(1, "Tom");
        HeroNode2 node2 = new HeroNode2(3, "Jack");
        HeroNode2 node3 = new HeroNode2(6, "Smith");
        HeroNode2 node4 = new HeroNode2(8, "Mary");
        HeroNode2 node5 = new HeroNode2(10, "King");
        HeroNode2 node6 = new HeroNode2(8, "Dim");

        // 二叉树，后面要递归创建，现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        // 测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        System.out.println("10号结点的前驱结点是：");
        System.out.println(node5.getLeft());
        System.out.println("10号结点的后继结点是：");
        System.out.println(node5.getRight());
    }
}

/**
 * 定义BinaryTree
 */
@Data
class ThreadedBinaryTree {
    /**
     * 初始结点
     */
    private HeroNode2 root;
    /**
     * 在递归进行时，pre总是保留前一个结点
     */
    private HeroNode2 pre;

    /**
     * threadedNodes方法重载
     */
    public void threadedNodes() {
        threadedNodes(root);
    }

    /**
     * 编写对二叉树进行中序线索化的方法
     *
     * @param node 就是当前需要线索化的结点
     */
    public void threadedNodes(HeroNode2 node) {
        // 如果node==null，不能线索化
        if (node == null) {
            return;
        }
        //1.先线索化左子树
        threadedNodes(node.getLeft());
        //2.线索化当前结点[有难度]

        //如果可以的话，处理当前结点的前驱结点
        if (node.getLeft() == null) {
            // 让当前结点的左指针指向前驱结点
            node.setLeft(pre);
            // 修改当前结点的左指针的类型
            node.setLeftType(1);
        }
        //如果可以的话，处理前驱结点的后继结点（由下次一次遍历执行）
        if (pre != null && pre.getRight() == null) {
            // 让前驱结点的右指针指向当前结点
            pre.setRight(node);
            // 修改前驱结点的右指针类型
            node.setLeftType(1);
        }
        // !!! 每处理一个节点后，让当前结点是下一个结点的前驱结点
        pre = node;

        //3.线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * 删除结点
     */
    public void delNode(int no) {
        if (this.root != null) {
            if (root.getNo() == no) {
                this.root = null;
                return;
            }
            this.root.delNode(no);
        } else {
            System.out.println("二叉树为空，无法删除");
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 前序查找
     */
    public HeroNode2 preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else {
            throw new RuntimeException("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 中序查找
     */
    public HeroNode2 infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else {
            throw new RuntimeException("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    /**
     * 后序查找
     */
    public HeroNode2 postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            throw new RuntimeException("二叉树为空，无法遍历");
        }
    }
}

/**
 * 先创建HeroNode2 结点
 */
@Data
class HeroNode2 {
    private int no;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;
    /**
     * 指针类型，0-指向左子树，1-指向前驱结点
     */
    private int leftType = 0;
    /**
     * 指针类型，0-指向右子树，1-指向后继结点
     */
    private int rightType = 0;

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * 递归删除结点
     * 1.如果删除的结点是叶子结点，则删除该结点
     * 2.如果删除的结点是非叶子结点，则删除该子树
     */
    public void delNode(int no) {
        // 递归向左
        if (this.left != null) {
            if (this.left.no == no) {
                this.left = null;
                return;
            } else {
                this.left.delNode(no);
            }
        }
        // 递归向右
        if (this.right != null) {
            if (this.right.no == no) {
                this.right = null;
            } else {
                this.right.delNode(no);
            }
        }
    }

    /**
     * 编写前序遍历的方法
     */
    public void preOrder() {
        // 输出父结点
        System.out.println(this);
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 编写前序查找的方法
     */
    public HeroNode2 preOrderSearch(int no) {
        HeroNode2 resNode = null;
        // 如果找到
        if (this.no == no) {
            return this;
        } else {
            System.out.println("I was executed");
        }
        // 递归向左子树前序遍历
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 编写中序遍历的方法
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 编写中序查找的方法
     */
    public HeroNode2 infixOrderSearch(int no) {
        HeroNode2 resNode = null;
        // 递归向左子树前序遍历
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 如果找到
        if (this.no == no) {
            return this;
        } else {
            System.out.println("I was executed");
        }
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    /**
     * 编写后序遍历的方法
     */
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 编写后序查找的方法
     */
    public HeroNode2 postOrderSearch(int no) {
        HeroNode2 resNode = null;
        // 递归向左子树前序遍历
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 如果找到
        if (this.no == no) {
            return this;
        } else {
            System.out.println("I was executed");
        }
        return resNode;
    }
}