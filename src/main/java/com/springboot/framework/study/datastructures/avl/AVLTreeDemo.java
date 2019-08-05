package com.springboot.framework.study.datastructures.avl;

import lombok.Data;

/**
 * 平衡二叉树
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/30 19:16
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};
//        int[] arr = {10, 12, 8, 9, 7, 6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        // 创建一个AVLTree 对象
        AVLTree avlTree = new AVLTree();
        // 添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        // 遍历
        avlTree.infixOrder();
        System.out.println("在平衡处理后~~~");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前的根结点=" + avlTree.getRoot());
        System.out.println("根结点的左子结点=" + avlTree.getRoot().left);
        System.out.println("根结点的左子结点=" + avlTree.getRoot().left.left);
    }
}

/**
 * 创建二叉排序树
 */
@Data
class AVLTree {
    private Node root;

    /**
     * 删除指定结点
     *
     * @param value 要删除的结点值
     */
    public void delNode(int value) {
        if (root == null) {
            System.out.println("二叉排序树为空，不能删除");
        } else if (root.value == value) {
            //Todo 特殊情况，建议先参考else的分析
            if (root.left != null) {
                root.value = delLeftTreeMin(root.left);
            } else if (root.right != null) {
                root.value = delRightTreeMin(root.right);
            } else {
                root = null;
            }
        } else {
            // 1.找到要删除的结点 targetNode
            Node targetNode = search(value);
            // 如果没有找到
            if (targetNode == null) {
                return;
            }
            // 2.找到 targetNode的父结点
            Node parentTargetNode = searchParent(value);
            // 3.1.如果 targetNode是有两颗子树的结点
            if (targetNode.left != null && targetNode.right != null) {
                //Todo 有难度
                targetNode.value = delRightTreeMin(targetNode.right);
            }
            // 3.2.如果 targetNode是有一颗左子树的结点
            else if (targetNode.left != null) {
                // 确定是parentTargetNode的左子结点，还是右子结点
                // 然后将其置为targetNode的左子树
                if (parentTargetNode.left == targetNode) {
                    parentTargetNode.left = targetNode.left;
                } else if (parentTargetNode.right == targetNode) {
                    parentTargetNode.right = targetNode.left;
                }
            }
            // 3.3.如果 targetNode是有一颗右子树的结点
            else if (targetNode.right != null) {
                // 确定是parentTargetNode的左子结点，还是右子结点
                // 然后将其置为targetNode的右子树
                if (parentTargetNode.left == targetNode) {
                    parentTargetNode.left = targetNode.right;
                } else if (parentTargetNode.right == targetNode) {
                    parentTargetNode.right = targetNode.right;
                }
            }
            // 3.4.如果 targetNode是叶子结点
            else {
                // 确定是parentTargetNode的左子结点，还是右子结点
                // 然后将其置为null
                if (parentTargetNode.left == targetNode) {
                    parentTargetNode.left = null;
                } else if (parentTargetNode.right == targetNode) {
                    parentTargetNode.right = null;
                }
            }
        }
    }

    /**
     * 1.返回的 以node 为根结点的二叉排序树的最大结点的值
     * 2.删除node 为根结点的二叉排序树的最大结点
     *
     * @param node 传入的结点（当作二叉排序树的根结点）
     * @return 以node 为根结点的二叉排序树的最大结点的值
     */
    private int delLeftTreeMin(Node node) {
        // 循环查找左结点，就会找到最小值
        while (node.right != null) {
            node = node.right;
        }
        // 这时 target就指向了最大结点
        // 删除最大结点
        delNode(node.value);
        return node.value;
    }

    /**
     * 1.返回的 以node 为根结点的二叉排序树的最小结点的值
     * 2.删除node 为根结点的二叉排序树的最小结点
     *
     * @param node 传入的结点（当作二叉排序树的根结点）
     * @return 以node 为根结点的二叉排序树的最小结点的值
     */
    private int delRightTreeMin(Node node) {
        // 循环查找左结点，就会找到最小值
        while (node.left != null) {
            node = node.left;
        }
        // 这时 target就指向了最小结点
        // 删除最小结点
        delNode(node.value);
        return node.value;
    }

    /**
     * 查找指定结点的父结点
     *
     * @param value 要查找的结点值
     * @return 如果存在，返回该结点的父结点，否则返回null
     */
    public Node searchParent(int value) {
        if (root == null || root.value == value) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 查找指定结点
     *
     * @param value 要查找的结点值
     * @return 如果存在，返回该结点，否则返回null
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    /**
     * 添加结点
     *
     * @param node 要添加的结点
     */
    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空，不能遍历");
        } else {
            root.infixOrder();
        }
    }
}

/**
 * 创建结点类
 */
class Node implements Comparable<Node> {
    /**
     * 结点权值
     */
    int value;
    /**
     * 指向左子结点
     */
    Node left;
    /**
     * 指向右子结点
     */
    Node right;

    public Node(int value) {
        this.value = value;
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

    /**
     * 左旋转方法
     */
    private void leftRotate() {
        // 创建新的结点，以当前根结点的值
        Node newNode = new Node(this.value);
        // 把新的结点的左子树设置成当前结点的左子树
        newNode.left = this.left;
        // 把新的结点的右子树设置成当前结点的右子树的左子树
        newNode.right = this.right.left;
        // 把当前结点的值替换成右子结点的值
        this.value = this.right.value;
        // 把当前结点的右子树设置成 当前结点右子树的右子树
        this.right = this.right.right;
        // 把当前结点的左子树（左子结点）设置成新的结点
        this.left = newNode;
    }

    /**
     * 右旋转方法
     */
    private void rightRotate() {
        // 创建新的结点，以当前根结点的值
        Node newNode = new Node(this.value);
        // 把新的结点的右子树设置成当前结点的右子树
        newNode.right = this.right;
        // 把新的结点的左子树设置成当前结点的左子树的右子树
        newNode.left = this.left.right;
        // 把当前结点的值替换成左子结点的值
        this.value = this.left.value;
        // 把当前结点的左子树设置成 当前结点左子树的左子树
        this.left = this.left.left;
        // 把当前结点的右子树（右子结点）设置成新的结点
        this.right = newNode;
    }

    /**
     * 返回左子树的高度
     *
     * @return 左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回右子树的高度
     *
     * @return 右子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    /**
     * 返回当前结点的高度
     *
     * @return 以该结点为根结点的树的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 查找指定结点的父结点
     *
     * @param value 要查找的结点值
     * @return 如果存在，返回该结点的父结点，否则返回null
     */
    public Node searchParent(int value) {
        boolean existed = (this.left != null && this.left.value == value) || (this.right != null && this.right.value == value);
        if (existed) {
            return this;
        } else if (value < this.value && this.left != null) {
            return this.left.searchParent(value);
        } else if (value > this.value && this.right != null) {
            return this.right.searchParent(value);
        }
        return null;
    }

    /**
     * 查找指定结点
     *
     * @param value 要查找的结点值
     * @return 如果存在，返回该结点，否则返回null
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value && this.left != null) {
            return this.left.search(value);
        } else if (value > this.value && this.right != null) {
            return this.right.search(value);
        }
        return null;
    }

    /**
     * 添加结点
     * 递归的方式添加，注意需要满足二叉排序树的要求
     *
     * @param node 要添加的结点
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 当添加完一个结点后，如果：（右子树的高度-左子树的高度）> 1，左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            // 如果他的
            if (this.right != null && this.right.rightHeight() < this.right.leftHeight()) {
                // 先对当前结点进行左旋转
                this.left.rightRotate();
                // 直接进行右选择
                this.leftRotate();
            } else {
                // 直接进行右选择
                this.leftRotate();
            }
            return;
        }
        // 当添加完一个结点后，如果：（左子树的高度-右子树的高度）> 1，右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                // 先对当前结点进行左旋转
                this.left.leftRotate();
                // 直接进行右选择
                this.rightRotate();
            } else {
                // 直接进行右选择
                this.rightRotate();
            }
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
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
}

