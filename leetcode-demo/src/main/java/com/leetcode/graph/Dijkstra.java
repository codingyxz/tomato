package com.leetcode.graph;

import java.util.Scanner;

/**
 * @Desc 迪杰斯塔拉算法查找顶点0到其他顶点所有最短路径
 * @Author zhxy
 * @Date 2022/8/31 3:28 下午
 * @Version V1.0
 **/
public class Dijkstra {
    // 定义图中边数量
    static int V = 9;

    public static class MGraph {
        int[] vex = new int[V];  // 存储图中顶点
        int[][] edge = new int[V][V];  // 存储边，即顶点间关系
        int verNum, edgeNum;  // 记录图中顶点和边数量
    }

    /**
     * 获取顶点下标
     *
     * @param g
     * @param value
     * @return
     */
    public static int locateVex(MGraph g, int value) {
        int i = 0;
        // 遍历数组，找到顶点
        for (; i < g.verNum; i++) {
            if (g.vex[i] == value) {
                break;
            }
        }
        // 如果找不到，返回-1
        if (i > g.verNum) {
            System.out.println(String.format("顶点【%s】不存在", value));
            return -1;
        }
        return i;
    }

    /**
     * 构造图
     *
     * @param graph
     */
    public static void createGraph(MGraph graph) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入图的顶点数和边数：");
        graph.verNum = scanner.nextInt();
        graph.edgeNum = scanner.nextInt();

        System.out.println("输入各个顶点：");
        for (int i = 0; i < graph.verNum; i++) {
            graph.vex[i] = scanner.nextInt();
        }
        for (int i = 0; i < graph.verNum; i++) {
            for (int j = 0; j < graph.verNum; j++) {
                graph.edge[i][j] = 65535;
            }
        }

        System.out.println("输入各个边的数据：");
        for (int i = 0; i < graph.edgeNum; i++) {
            int vex1 = scanner.nextInt();
            int vex2 = scanner.nextInt();
            int w = scanner.nextInt();

            int index1 = locateVex(graph, vex1);
            int index2 = locateVex(graph, vex2);
            if (index1 == -1 || index2 == -1) {
                return;
            }

            graph.edge[index1][index2] = w;
            graph.edge[index2][index1] = w;
        }
    }


    /**
     * 操作步骤如下：
     * 1、遍历找到标识为0的最短路径的顶点，找出总权值最小的顶点vk
     * 2、此时即可认为已确定v0到vk顶点的最短路径，标记该顶点为1，表示已确定路径
     * 3、通过vk顶点，扩展v0可访问的顶点，同时比较并更新所有含有该顶点路径的顶点总权值，同时将需要更新的顶点前驱设为vk
     * 4、重复步骤1、2、3循环，每一次循环都会找到一个距v0最近的顶点，即确认了v0到该顶点的最短路径，更新v0到该顶点的权值、以及该顶点的后驱引用、该顶点的最短路径确认标识
     *
     * @param graph
     * @param v0
     * @param p
     * @param d
     */
    public static void dijkstra_minTree(MGraph graph, int v0, int[] p, int[] d) {
        // 为各个顶点配置一个标记值，用于确认该顶点是否已经找到最短路径
        int[] tab = new int[V];
        // 对各数组进行初始化
        for (int i = 0; i < graph.verNum; i++) {
            tab[i] = 0;
            d[i] = graph.edge[v0][i];  // v0到各顶点总权值
            p[i] = 0;                  // v0到各顶点最短路径
        }
        // 由于以v0位下标的顶点为起始点，所以不用再判断
        d[v0] = 0;
        tab[v0] = 1;
        int k = 0;
        for (int i = 0; i < graph.verNum; i++) {
            int min = 65535;
            // 选择到各个顶点权值最小的顶点，即为本次能确定最短路径的顶点
            for (int j = 0; j < graph.verNum; j++) {
                if (tab[j] != 1) {
                    if (d[j] < min) {
                        k = j;
                        min = d[j];
                    }
                }
            }
            // 设置该顶点的标志位为1，避免下次重复判断
            tab[k] = 1;
            // 对v0到各个顶点的权值进行更新
            for (int j = 0; j < graph.verNum; j++) {
                if (tab[j] != 1 && (min + graph.edge[k][j] < d[j])) {
                    d[j] = min + graph.edge[k][j];
                    // 记录各个最短路径上存在的顶点
                    p[j] = k;
                }
            }
        }
    }

    /**
     * 实例参数：
     * 输入图的顶点数和边数：7 9
     * 输入各个顶点：0 1 2 3 4 5 6
     * 输入各个边的数据：
     * 0 1 2
     * 0 2 6
     * 1 3 5
     * 2 3 8
     * 3 5 15
     * 3 4 10
     * 4 5 6
     * 4 6 2
     * 5 6 6
     * 最短路径为：
     * 1 - 0的最短路径中的顶点有： 1-0
     * 2 - 0的最短路径中的顶点有： 2-0
     * 3 - 0的最短路径中的顶点有： 3-1-0
     * 4 - 0的最短路径中的顶点有： 4-3-1-0
     * 5 - 0的最短路径中的顶点有： 5-3-1-0
     * 6 - 0的最短路径中的顶点有： 6-4-3-1-0
     * 源点到各顶点的最短路径长度为:
     * 0 - 1 : 2
     * 0 - 2 : 6
     * 0 - 3 : 7
     * 0 - 4 : 17
     * 0 - 5 : 22
     * 0 - 6 : 19
     */
    public static void main(String[] args) {
        MGraph graph = new MGraph();
        createGraph(graph);
        int[] p = new int[V];  // 记录顶点0到各个顶点的最短路径
        int[] d = new int[V];  // 记录顶点0到各个顶点的总权值
        dijkstra_minTree(graph, 0, p, d);

        System.out.println("最短路径为：");
        for (int i = 1; i < graph.verNum; i++) {
            System.out.println(i + "_" + 0 + " 的最短路径中的顶点有：");
            System.out.println(i + "-");
            int j = i;
            // 由于每一段最短路径上都记录着经过的顶点，所以采用嵌套的方式输出即可
            while (p[j] != 0) {
                System.out.println(p[j] + "-");
                j = p[j];
            }
            System.out.println("0");
        }

        System.out.println("源点到各顶点的最短路径长度为：");
        for (int i = 0; i < graph.verNum; i++) {
            System.out.println(graph.vex[0] + "-" + graph.vex[i] + " : " + d[i]);
        }
    }


}
