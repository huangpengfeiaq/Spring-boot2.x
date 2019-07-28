package com.springboot.framework.datastructures.tree;

import lombok.Data;

/**
 * 二叉树
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/28 15:59
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        // 创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        binaryTree.setRoot(root);
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        // 测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
//
//        // 测试
//        System.out.println("中序遍历");
//        binaryTree.infixOrder();
//
//        // 测试
//        System.out.println("后序遍历");
//        binaryTree.postOrder();
//
//        // 测试
//        System.out.println("前序查找，no=15");
//        HeroNode heroNodex = binaryTree.preOrderSearch(15);
//        System.out.println(heroNodex);
//
//        // 测试
//        System.out.println("中序查找，no=5");
//        HeroNode heroNodey = binaryTree.infixOrderSearch(5);
//        System.out.println(heroNodey);
//
//        // 测试
//        System.out.println("后序查找，no=5");
//        HeroNode heroNodez = binaryTree.postOrderSearch(5);
//        System.out.println(heroNodez);

        // 测试
        System.out.println("测试删除");
//        binaryTree.delNode(5);
        binaryTree.delNode(3);

        // 测试
        System.out.println("前序遍历");
        binaryTree.preOrder();
    }
}

/**
 * 定义BinaryTree
 */
@Data
class BinaryTree {
    private HeroNode root;

//    public BinaryTree(HeroNode root) {
//        this.root = root;
//    }

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
    public HeroNode preOrderSearch(int no) {
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
    public HeroNode infixOrderSearch(int no) {
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
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else {
            throw new RuntimeException("二叉树为空，无法遍历");
        }
    }
}

/**
 * 先创建HeroNode 结点
 */
@Data
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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
    public HeroNode preOrderSearch(int no) {
        HeroNode resNode = null;
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
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
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
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
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