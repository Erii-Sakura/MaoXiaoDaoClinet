package neuedu.client;

import com.neuedu.pojo.*;
import com.neuedu.utils.FrameUtil;
import com.neuedu.utils.GameUtil;
import com.neuedu.utils.MusicUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class MaoXiaoDaoClinet extends FrameUtil {
    private BackGround bg = new BackGround();
    private Hero hero = new Hero(this);
//    private Bullet bullet = new Bullet();
    /*多个子弹*/
    public List<Bullet> bullets = new ArrayList<>();
    //存储怪物
    public List<Mob> mobs = new ArrayList<>();
    public List<Mobdie> mobdies = new ArrayList<>();
    /*加载窗口*/
    @Override
    public void loadFrame() {
        MusicUtil.getMusic("E:\\MaoXiaoDao\\src\\com\\neuedu\\imgs\\music.wav");
        super.loadFrame();
        this.addKeyListener(new KeyAdapter() {
            //按下
            @Override
            public void keyPressed(KeyEvent e) {
               hero.keyPressed(e);
            }
            //抬起
            @Override
            public void keyReleased(KeyEvent e) {
                hero.keyReleased(e);
            }
        });
        for (int i= 1;i<=4;i++){
            mobs.add(new Mob(500+150*i,this));
        }
    }
    //重写 将图片画在窗口上
    @Override
    public void paint(Graphics g) {
        bg.draw(g);
        hero.draw(g);
//        bullet.draw(g);
        for (int i=0;i<bullets.size();i++ ){
                Bullet bullet =bullets.get(i);
                bullet.draw(g);
                bullet.hit(mobs);
        }
        for (int i = 0;i<mobs.size();i++){
            mobs.get(i).draw(g);
        }
        for (int i = 0;i<mobdies.size();i++){
            mobdies.get(i).draw(g);
        }
    }

    public static void main(String[] args) {
        new MaoXiaoDaoClinet().loadFrame();
    }
}
