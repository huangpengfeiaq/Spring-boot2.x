package com.springboot.framework.datastructures.graph;

import java.util.ArrayList;

/**
 * 图
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/7/30 23:56
 */
public class Graph {
    public static void main(String[] args) {
        // 测试
        // 顶点的个数
        int n = 5;
        // 顶点的值
        String[] vertexValues = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        // 插入顶点
        for (String v : vertexValues) {
            graph.insertVertex(v);
        }
        // 添加边
        // A-B、A-C、B-C、B-D、B-E
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        // 显示
        graph.showGraph();
        // 遍历
        graph.dfs();
    }

    /**
     * 存储顶点集合
     */
    private ArrayList<String> vertexList;
    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;
    /**
     * 表示边的数目
     */
    private int numOfEdges;
    /**
     * 定义数组boolean[]，记录某个顶点是否被访问
     */
    private boolean[] isVisited;

    /**
     * 构造器
     *
     * @param n 顶点个数
     */
    public Graph(int n) {
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    /**
     * 重载深度优先遍历的方法
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
//            System.out.println(isVisited[i]);
            if (!isVisited[i]) {
                dfs(i);
            }
        }
    }

    /**
     * 深度优先遍历的方法
     */
    private void dfs(int i) {
        System.out.print(getValueByIndex(i) + " -> ");
        isVisited[i] = true;
        int w = nextIndex(i);
        if (w != -1) {
            if (!isVisited[w]) {
                dfs(w);
            }
        }
    }

    /**
     * 得到当前顶点的邻接顶点的下标w
     */
    public int nextIndex(int v1) {
        for (int i = v1; i < getNumOfVertex(); i++) {
            if (edges[v1][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int[] i : edges) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }

    /**
     * 返回v1 和v2 的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 返回顶点i对应的数据 0->"A" 1->"B"
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回顶点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 返回边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 插入顶点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示第一个顶点的对应下标，即第几个顶点("A"-"B" "A"->0 "B"->1)
     * @param v2     表示第二个顶点的对应下标
     * @param weight 权值
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
