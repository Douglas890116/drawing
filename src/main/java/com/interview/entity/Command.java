package com.interview.entity;

import com.interview.utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 命令类
 */
public class Command {
    // 创建画布命令
    private static final char CREATE = 'C';
    // 画直线命令
    private static final char LINE = 'L';
    // 画矩形命令
    private static final char RECTANGLE = 'R';
    // 着色命令
    private static final char COLOR = 'B';
    // 结束命令
    private static final char QUIT = 'Q';

    // 画布
    private Canvas canvas;
    // 最大宽度
    private int maxWidth;
    // 最大高度
    private int maxHeight;

    public void start() {
        System.out.println("命令描述：\n" +
                "C w h           創建寬度為w和高度為h的新畫布。\n" +
                "L x1 y1 x2 y2   從（x1，y1）到（x2，y2）創建一個新行。\n" +
                "                目前只支持水平或垂直線。\n" +
                "                水平和垂直線條將使用'x'字符繪製。\n" +
                "R x1 y1 x2 y2   創建一個新的矩形，其左上角是（x1，y1）和右下角是（x2，y2）。\n" +
                "                將繪製水平和垂直線使用'x'字符。\n" +
                "B x y c         使用“顏色”c填充連接到（x，y）的整個區域。\n" +
                "                該這與塗料中的“桶填充”工具的行為相同程式。\n" +
                "Q               退出該系統。\n\n");

        input();
    }

    /**
     * 获取用户的的输入信息
     */
    private void input() {
        InputStreamReader is = new InputStreamReader(System.in); //new构造InputStreamReader对象
        BufferedReader br = new BufferedReader(is);
        System.out.print("Please input command:  ");
        try {
            String command = br.readLine();
            analysisCmd(command);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 解析命令
     *
     * @param command
     */
    private void analysisCmd(String command) {
        try {
            if (command == null || command.trim().length() <= 0) {
                System.err.println("请输入指令.");
                return;
            }
            if (command.charAt(0) == QUIT) {
                System.out.println("感谢您的使用");
                System.exit(0);
            }
            if (canvas == null && !command.startsWith("C")) {
                System.err.println("画布还未创建，请使用C w h命令创建画布，w => 宽度，h => 高度");
                return;
            }
            String[] cmds = command.split(" ");
            String cmd = cmds[0];
            switch (cmd.charAt(0)) {
                case CREATE:
                    doCreate(cmds);
                    break;
                case LINE:
                    doLine(cmds);
                    break;
                case RECTANGLE:
                    doRectangle(cmds);
                    break;
                case COLOR:
                    doColor(cmds);
                    break;
                default:
                    System.err.println("未知指令.");
            }
        } finally {
            input();
        }
    }

    /**
     * 创建画布
     *
     * @param cmds
     */
    private void doCreate(String[] cmds) {
        if (cmds.length != 3) {
            System.err.println("创建画布，参数格式或内容有误");
            return;
        }

        maxWidth = Integer.parseInt(cmds[1]);
        maxHeight = Integer.parseInt(cmds[2]);
        canvas = new Canvas(maxWidth, maxHeight);
        canvas.init();
    }

    /**
     * 画直线
     *
     * @param cmds
     */
    private void doLine(String[] cmds) {
        if (cmds.length != 5) {
            System.err.println("画直线，参数格式或内容有误");
            return;
        }
        for (int i = 1; i < cmds.length; i++) {
            if (!Tools.isNumeric(cmds[i])) {
                System.err.println("画直线，参数必须都为数字");
                return;
            }
        }
        int x1 = Integer.parseInt(cmds[1]);
        int y1 = Integer.parseInt(cmds[2]);
        int x2 = Integer.parseInt(cmds[3]);
        int y2 = Integer.parseInt(cmds[4]);
        if (!checkParams(x1, y1) || !checkParams(x2, y2)) {
            System.err.println("坐标点不能超出画布");
            return;
        }
        int bigger, smaller;
        if (x1 == x2) {
            // 画竖线
            if (y1 > y2) {
                bigger = y1;
                smaller = y2;
            } else {
                bigger = y2;
                smaller = y1;
            }
            canvas.lineVertical(x1, smaller, bigger);
        } else if (y1 == y2) {
            // 画横线
            if (x1 > x2) {
                bigger = x1;
                smaller = x2;
            } else {
                bigger = x2;
                smaller = x1;
            }
            canvas.lineHorizontal(smaller, bigger, y1);
        } else {
            System.err.println("画直线，参数逻辑有误");
        }
    }

    /**
     * 画矩形
     *
     * @param cmds
     */
    private void doRectangle(String[] cmds) {
        if (cmds.length != 5) {
            System.err.println("画矩形，参数格式或内容有误");
            return;
        }
        for (int i = 1; i < cmds.length; i++) {
            if (!Tools.isNumeric(cmds[i])) {
                System.err.println("画矩形，参数必须都为数字");
                return;
            }
        }
        int x1 = Integer.parseInt(cmds[1]);
        int y1 = Integer.parseInt(cmds[2]);
        int x2 = Integer.parseInt(cmds[3]);
        int y2 = Integer.parseInt(cmds[4]);
        if (!checkParams(x1, y1) || !checkParams(x2, y2)) {
            System.err.println("坐标点不能超出画布");
            return;
        }
        if (x1 >= x2 || y1 >= y2) {
            System.err.println("画矩形，参数逻辑有误");
            return;
        }
        canvas.rectangle(x1, x2, y1, y2);
    }

    /**
     * 作色
     *
     * @param cmds
     */
    private void doColor(String[] cmds) {
        if (cmds.length != 4) {
            System.err.println("着色，参数格式或内容有误");
            return;
        }
        for (int i = 1; i < 3; i++) {
            if (!Tools.isNumeric(cmds[i])) {
                System.err.println("着色，坐标参数必须都为数字");
                return;
            }
        }
        int x = Integer.parseInt(cmds[1]);
        int y = Integer.parseInt(cmds[2]);
        if (!checkParams(x, y)) {
            System.err.println("坐标点不能超出画布");
            return;
        }
        String c = cmds[3];
        canvas.color(x, y, c.charAt(0));
    }

    /**
     * 验证参数
     *
     * @param x
     * @param y
     * @return
     */
    private boolean checkParams(int x, int y) {
        if (x == 0 || y == 0 || x >= maxWidth || y >= maxHeight) return false;
        return true;
    }
}