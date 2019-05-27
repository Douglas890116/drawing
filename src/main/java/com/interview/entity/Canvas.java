package com.interview.entity;

/**
 * 画布类
 */
public class Canvas {
    /**
     * 画布宽度
     */
    private int width;
    /**
     * 画布长度
     */
    private int height;
    /**
     * 画布本体，使用二维数组表示
     * 1维表示竖坐标
     * 2维表示横坐标
     */
    private char[][] content;

    /**
     * 构造函数，赋值
     *
     * @param width
     * @param height
     */
    public Canvas(int width, int height) {
        this.width = width + 2;
        this.height = height + 2;
    }

    /**
     * 初始化画布
     */
    public void init() {
        content = new char[this.height][this.width];
        for (int i = 0, h = content.length; i < h; i++) {
            char[] horizontal = content[i];// 横坐标
            for (int j = 0, v = horizontal.length; j < v; j++) {
                if (i == 0 || i == (h - 1)) {
                    content[i][j] = '-';
                } else {
                    if (j == 0 || j == (v - 1)) content[i][j] = '|';
                    else content[i][j] = ' ';
                }
            }
        }
        printCanvas();
    }

    /**
     * 画横向直线
     *
     * @param x1
     * @param x2
     * @param y
     */
    public void lineHorizontal(int x1, int x2, int y) {
        // 横向直线，纵坐标不变，横坐标修改
        for (int i = x1; i <= x2; i++) content[y][i] = 'x';
        printCanvas();
    }

    /**
     * 画纵向直线
     *
     * @param x
     * @param y1
     * @param y2
     */
    public void lineVertical(int x, int y1, int y2) {
        // 纵向直线，横坐标不变，纵坐标修改
        for (int i = y1; i <= y2; i++) content[i][x] = 'x';
        printCanvas();
    }

    /**
     * 画矩形
     * 画矩形
     *
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     */
    public void rectangle(int x1, int x2, int y1, int y2) {
        for (int i = x1; i <= x2; i++) {
            content[y1][i] = 'x';
            content[y2][i] = 'x';
        }
        for (int i = y1; i <= y2; i++) {
            content[i][x1] = 'x';
            content[i][x2] = 'x';
        }
        printCanvas();
    }

    /**
     * 使用color填充(x,y)所在的区域
     * 效果类似于画图中的填充功能
     *
     * @param x
     * @param y
     * @param color
     */
    public void color(int x, int y, char color) {
        char origin = content[y][x];
        doColor(x, y, color, origin);
        printCanvas();
    }

    /**
     * 将原始的颜色修改
     *
     * @param x
     * @param y
     * @param color
     * @param origin
     */
    private void color(int x, int y, char color, char origin) {
        char local = content[y][x];
        if (local == origin) {
            doColor(x, y, color, origin);
        }
    }

    private void doColor(int x, int y, char color, char origin) {
        content[y][x] = color;
        color(x, y + 1, color, origin);
        color(x, y - 1, color, origin);
        color(x + 1, y, color, origin);
        color(x - 1, y, color, origin);
        color(x + 1, y + 1, color, origin);
        color(x + 1, y - 1, color, origin);
        color(x - 1, y + 1, color, origin);
        color(x - 1, y - 1, color, origin);
    }

    /**
     * 打印画布
     */
    public void printCanvas() {
        for (char[] h : content) {
            for (char v : h) System.out.print(v);
            System.out.println();
        }
    }
}