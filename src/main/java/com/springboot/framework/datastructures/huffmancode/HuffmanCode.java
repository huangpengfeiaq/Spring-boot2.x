package com.springboot.framework.datastructures.huffmancode;

import java.util.*;

/**
 * 哈夫曼编码
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/29 17:06
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
//        System.out.println(Arrays.toString(contentBytes));
        // 目前数组长度为40
        System.out.println(content.length());

        List<Node> nodes = getNodes(contentBytes);
        System.out.println("nodes=" + nodes);

        // 测试，创建的哈夫曼二叉树
        System.out.print("哈夫曼树,");
        Node root = createHuffmanTree(nodes);

        // 前序遍历
        System.out.println("前序遍历");
        root.preOrder();

        // 测试，创建的哈夫曼二叉树
        System.out.println("哈夫曼编码表");
        System.out.println(getCodes(root));
    }

    /**
     * 生成哈夫曼树对应的哈夫曼编码
     * 思路：
     * 1.将哈夫曼编码表存放在Map<Byte,String> 形式
     * 2.在生成哈夫曼编码表示，需要去拼接路径，定义一个StringBuilder 存储某个叶子结点的路径
     */
    static Map<Byte, String> huffmanCodes = new HashMap<>(16);
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：将传入的node结点的所有叶子结点的哈夫曼编码，并放入到huffmanCodes集合
     *
     * @param root 根结点
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root左子树
        getCodes(root.leftNode, "0", stringBuilder);
        // 处理root右子树
        getCodes(root.rightNode, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的哈夫曼编码，并放入到huffmanCodes集合
     *
     * @param node          传入结点
     * @param code          路径：左子结点是 0，右子结点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code 加入到 stringBuilder2
        stringBuilder2.append(code);
        // 如果node == null 不处理
        if (node != null) {
            // 判断当前node 是非叶子结点，还是叶子结点
            if (node.data == null) {
                // 向左递归处理
                getCodes(node.leftNode, "0", stringBuilder2);
                // 向右递归处理
                getCodes(node.rightNode, "1", stringBuilder2);
            } else {
                // 就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 编写一个方法，将准备构建哈夫曼树的Node 结点放到List
     *
     * @param bytes 接收字节数组
     * @return 返回就是List 形式 [Node{data=32, weight=9}, Node{data=97, weight=5}...]
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 1.创建一个ArrayList
        List<Node> nodes = new ArrayList<>();
        // 2.遍历 bytes，统计 每一个byte 出现的次数 -> map[key,value]
        Map<Byte, Integer> map = new HashMap<>(16);
        for (byte b : bytes) {
            map.merge(b, 1, (oldValue, newValue) -> oldValue + newValue);
        }
        // 把每一个键值对转成一个Node 对象，并加入到nodes集合
        // 遍历map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        System.out.println(map);
        return nodes;
    }

    /**
     * 创建哈夫曼树的方法
     *
     * @param nodes 需要创建成哈夫曼树的集合
     * @return 创建好后的哈夫曼树的root结点
     */
    public static Node createHuffmanTree(List<Node> nodes) {
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
     * 存放数据，比如'a' => 97 ；' '  => 32
     */
    Byte data;
    /**
     * 结点权值（表示字符出现的次数）
     */
    int weight;
    /**
     * 指向左子结点
     */
    Node leftNode;
    /**
     * 指向右子结点
     */
    Node rightNode;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Node(Node leftNode, Node rightNode) {
        this.leftNode = leftNode;
        this.rightNode = rightNode;
        this.weight = leftNode.weight + rightNode.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.weight - o.weight;
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
}
