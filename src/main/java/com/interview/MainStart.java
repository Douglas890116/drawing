package com.interview;

import com.interview.entity.Canvas;
import com.interview.entity.Command;

/**
 *  程序启动类
 */
public class MainStart {

    public static void main(String[] args) {
//        int w = 20;
//        int h = 10;
//        Canvas canvas = new Canvas(w, h);
//        canvas.init();
//        canvas.printCanvas();
//        System.out.println("=====画横线=====");
//        canvas.lineHorizontal(1,5,1);
//        canvas.printCanvas();
//        System.out.println("=====画竖线=====");
//        canvas.lineVertical(6, 1, 5);
//        canvas.printCanvas();
//        System.out.println("=====画矩形=====");
//        canvas.rectangle(2, 7, 6, 9);
//        canvas.printCanvas();
//        System.out.println("=====着色块=====");
//        canvas.color(10, 4, 'O');
//        canvas.printCanvas();
        Command cmd = new Command();
        cmd.start();
    }
}