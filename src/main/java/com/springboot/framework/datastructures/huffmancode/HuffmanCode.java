package com.springboot.framework.datastructures.huffmancode;

import java.io.*;
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
//        String content = "i like like like java do you like a java";
//        byte[] huffmanZip = huffmanZip(content);
//        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanZip) + ",长度=" + huffmanZip.length);
//        byte[] decodeBytes = decode(huffmanZip);
////        System.out.println(Arrays.toString(decodeBytes));
//        System.out.println("解压后的内容是：" + new String(decodeBytes) + ",长度=" + decodeBytes.length);

        // 测试压缩文件
        String srcFile = "C:\\Users\\64165\\Desktop\\Java数据结构和算法\\资料\\压缩测试文件\\Uninstall.xml";
        String dstFile = "C:\\Users\\64165\\Desktop\\Java数据结构和算法\\资料\\压缩测试文件\\Uninstall.hpf";

        zipFile(srcFile, dstFile);
        System.out.println("压缩文件成功~~");

//        // 测试解压文件
//        String zipFile = "C:\\Users\\64165\\Desktop\\Java数据结构和算法\\资料\\压缩测试文件\\dst.hpf";
//        String dstFile = "C:\\Users\\64165\\Desktop\\Java数据结构和算法\\资料\\压缩测试文件\\src2.bmp";
//
//        unZipFile(zipFile, dstFile);
//        System.out.println("文件解压成功~~");
    }

    /**
     * 对压缩文件的解压
     *
     * @param zipFile 需要解压的文件路径
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        // 定义文件的输入流
        InputStream inputStream = null;
        // 定义一个对象输入流
        ObjectInputStream objectInputStream = null;
        // 定义文件的输出流
        OutputStream outputStream = null;
        try {
            // 创建文件输入流
            inputStream = new FileInputStream(zipFile);
            // 创建一个和 inputStream关联的对象输入流
            objectInputStream = new ObjectInputStream(inputStream);
            // 读取byte数组 huffmanBytes
            byte[] huffmanBytes = (byte[]) objectInputStream.readObject();
            // 读取哈夫曼表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInputStream.readObject();
            // 解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            // 将bytes 数组写入到目标文件
            outputStream = new FileOutputStream(dstFile);
            // 写数据到 dstFile 文件
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                outputStream.close();
                objectInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 将一个文件进行压缩（以哈夫曼编码的方式）
     *
     * @param srcFile 传入的文件全路径
     * @param dstFile 压缩后的文件路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        // 创建文件的输入流
        FileInputStream inputStream = null;
        // 创建输出流
        OutputStream outputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            inputStream = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的byte[]
            byte[] bytes = new byte[inputStream.available()];
            // 读取文件
            inputStream.read(bytes);
            // 直接对源文件压缩
            byte[] huffmanBytes = huffmanZip(bytes);
            // 创建文件的输出流，存放压缩文件
            outputStream = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的
            objectOutputStream = new ObjectOutputStream(outputStream);
            // 把 哈夫曼编码后的字节数组写入压缩文件
            objectOutputStream.writeObject(huffmanBytes);
            // 这里我们以对象流的方式 写入 哈夫曼编码，是为了以后我们恢复源文件时使用
            // 注意，一定要把哈夫曼编码 写入压缩文件
            objectOutputStream.writeObject(huffmanCodes);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                objectOutputStream.close();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * @param huffmanZip 哈夫曼编码压缩后的字节数组
     * @return 压缩前的字符串数组
     */
    private static byte[] decode(byte[] huffmanZip) {
        return decode(huffmanCodes, huffmanZip);
    }

    /**
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanZip   哈夫曼编码压缩后的字节数组
     * @return 压缩前的字符串数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanZip) {
        // 1.先得到 huffmanZip 对应的 二进制字符串，如1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        // 2.将huffmanZip转成二进制字符串
        for (int i = 0; i < huffmanZip.length; i++) {
            byte b = huffmanZip[i];
            // 判断，不是最后一个字节
            boolean flag = (i != huffmanZip.length - 1);
            stringBuilder.append(byteToBitString(b, flag));
        }
        // 把字符串按照指定的哈夫曼编码进行解码
        // 把哈夫曼编码表键值对调换，因为反向查询
        Map<String, Byte> map = new HashMap<>(16);
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建要给集合，存放byte
        List<Byte> byteList = new ArrayList<>();
        // 创建一个临时的字符串，用于与HashMap键校验
        StringBuilder tempString = new StringBuilder();
        // i 可以理解成就是索引，扫描stringBuilder
        for (int i = 0; i < stringBuilder.length(); i++) {
            tempString.append(stringBuilder.substring(i, i + 1));
            // 每扫描一个字符，就与HashMap键做一轮校验
            for (Map.Entry<String, Byte> entry : map.entrySet()) {
                // 如果存在
                if (entry.getKey().equals(tempString.toString())) {
                    // 将对应的value加入到byteList集合中
                    byteList.add(entry.getValue());
                    // 将校验字符串置空
                    tempString.delete(0, tempString.length());
                    break;
                }
            }
        }
        // 把byteList 中的数据放入byte[]，并返回
        byte[] decodeBytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            decodeBytes[i] = byteList.get(i);
        }
        return decodeBytes;
    }

    /**
     * 将一个byte 转成一个二进制的字符串
     * Todo 如果看不懂，参考Java基础 二进制的原码，反码，补码
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位。如果是true，表示需要补高位，如果是false表示不需要。如果是最后一个字节，无需补高位
     * @return 该b 对应的二进制的字符串（注意是补码返回）
     */
    private static String byteToBitString(byte b, boolean flag) {
        // 使用变量保存 b
        // 将 b 转成 int
        int temp = b;
        // 如果是正数，我们还需要补高位
        if (flag) {
            // 按位于或 256 1 0000 0000 | 0000 0001 => 1 0000 0001
            temp |= 256;
        }
        // 返回的是temp对应的二进制的补码
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 将前面的方法封装起来，便于我们的调用
     *
     * @param content 需要压缩的字符串
     * @return 压缩后的数组
     */
    private static byte[] huffmanZip(String content) {
        byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        // 根据 nodes 创建哈夫曼树
        Node root = createHuffmanTree(nodes);
        // 对应的哈夫曼编码（根据 哈夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(root);
        // 根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        return zip(contentBytes, huffmanCodes);
    }

    /**
     * 将前面的方法封装起来，便于我们的调用
     *
     * @param contentBytes 需要压缩的byte数组
     * @return 压缩后的数组
     */
    private static byte[] huffmanZip(byte[] contentBytes) {
        List<Node> nodes = getNodes(contentBytes);
        // 根据 nodes 创建哈夫曼树
        Node root = createHuffmanTree(nodes);
        // 对应的哈夫曼编码（根据 哈夫曼树）
        Map<Byte, String> huffmanCodes = getCodes(root);
        // 根据生成的哈夫曼编码，压缩得到压缩后的哈夫曼编码字节数组
        return zip(contentBytes, huffmanCodes);
    }

    /**
     * 将字符串对应的byte[] 数组，通过生成的哈夫曼编码，返回一个哈夫曼编码 压缩后的byte[]
     * 举例：String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes();
     * 返回的是 字符串"10101000101111111..." => 对应的byte[] zip，即8位对应一个byte，放入到zip中
     *
     * @param bytes        原始数组
     * @param huffmanCodes 生成的哈夫曼编码表
     * @return 压缩后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1.利用 huffmanCodes 将 bytes 转成 哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 2.遍历bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("编码后的huffmanCode：" + stringBuilder);
        // 3.将"10101000101111111..." 转成 byte[]
        // 统计 huffmanCodeBytes 的长度
        int huffmanCodeBytesLength = ((stringBuilder.length()) + 7) / 8;
        // 创建 压缩后的数组
        byte[] huffmanCodeBytes = new byte[huffmanCodeBytesLength];
        for (int i = 0, j = 0; i < stringBuilder.length(); i = i + 8, j++) {
            if (i + 8 > stringBuilder.length()) {
                huffmanCodeBytes[j] = (byte) Integer.parseInt(stringBuilder.substring(i), 2);
                break;
            } else {
                huffmanCodeBytes[j] = (byte) Integer.parseInt(stringBuilder.substring(i, i + 8), 2);
            }
        }
        return huffmanCodeBytes;
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
//        System.out.println(map);
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
