package neuedu.utils;

import com.neuedu.pojo.Constant;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameUtil extends Frame{
    // 解决图片闪烁的问题，用双缓冲方法解决闪烁问题
    Image backImg = null;

    // 重写update()方法，在窗口的里层添加一个虚拟的图片
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
            // 如果虚拟图片不存在，创建一个和窗口一样大小的图片
            backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }
        // 获取到虚拟图片的画笔
        Graphics backg = backImg.getGraphics();
        Color c = backg.getColor();
        backg.setColor(Color.WHITE);
        backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        backg.setColor(c);
        // 调用虚拟图片的paint()方法，每50ms刷新一次
        paint(backg);
        g.drawImage(backImg, 0, 0, null);
    }
    /*多线程*/
    class MyThread extends Thread {
        @Override
        public void run() {
            for (; ; ) {
                repaint();
                /*时间延迟*/
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*加载窗口*/
    public void loadFrame() {
        //标题
        this.setTitle("冒险岛");
        //大小
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        //窗口位置
        this.setLocationRelativeTo(null);
        //是否显示
        this.setVisible(true);
        //窗口颜色
        this.setBackground(Color.white);
        /*窗口监听时间  关闭*/
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //启动线程
        new MyThread().start();
    }
}
