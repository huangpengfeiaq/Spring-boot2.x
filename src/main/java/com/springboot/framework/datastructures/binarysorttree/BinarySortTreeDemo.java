package com.springboot.framework.datastructures.binarysorttree;

/**
 * 二叉排序树
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/30 10:59
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环的添加结点到二叉排序树
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
//         中序遍历
        binarySortTree.infixOrder();

        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("~~~");
        binarySortTree.infixOrder();
    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree {
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
