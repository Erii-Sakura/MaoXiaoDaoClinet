package neuedu.pojo;

import com.neuedu.client.MaoXiaoDaoClinet;
import com.neuedu.utils.GameUtil;
import com.neuedu.utils.ImgUtil;
import com.neuedu.utils.MusicUtil;
import com.sun.imageio.plugins.common.ImageUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Hero {
    private static Image[] imgs = new Image[100];

    static {
        for (int i = 0; i <= 4; i++) {
            imgs[i] = ImgUtil.getImage("hero_right_walk" + i);
        }
        for (int i = 5; i <= 9; i++) {
            imgs[i] = ImgUtil.getImage("hero_left_walk" + (i - 5));
        }
        for (int i = 10; i <= 13; i++) {
            imgs[i] = ImgUtil.getImage("hero_right_shoot" + (i - 10));
        }
        for (int i = 14; i <= 17; i++) {
            imgs[i] = ImgUtil.getImage("hero_left_shoot" + (i - 14));
        }
    }

    private Image image;
    private int x;
    private int y;
    private int height;
    private int width;
    private int speed = 10;
    private boolean left, right;
    private Direction dir;
    private Action action;
    private boolean walk;
    private int walk_right_count = 0;
    private int walk_left_count = 0;
    private boolean shoot;
    private int shoot_right_count = 10;
    private int shoot_left_count = 14;
    private boolean jump;
    private double v0 = 20;
    private double t = 10;
    private static final double g = 9.8;
    private double vt = 0;
    private double changeHeight = 0;
    private MaoXiaoDaoClinet mxc;
    //是否为竖直上抛
    private boolean jump_up = true;

    public Hero(MaoXiaoDaoClinet mxc) {
        this.mxc=mxc;
        this.image = ImgUtil.getImage("hero_right_stand");
        this.height = image.getHeight(null);
        this.width = image.getWidth(null);
        this.x = 300;
        this.y = Constant.GAME_HEIGHT - 182 - height;
        this.dir = Direction.RIGHT;
        this.action = Action.STAND;
    }

    //画图片
    public void draw(Graphics g) {
        if (walk_right_count > 4) {
            walk_right_count = 0;
        }
        if (walk_left_count > 9) {
            walk_left_count = 5;
        }
        if (shoot_right_count > 13) {
            shoot_right_count = 10;
        }
        if (shoot_left_count > 17) {
            shoot_left_count = 14;
        }
        switch (this.dir) {
            case RIGHT:
                switch (this.action) {
                    case STAND:
                        g.drawImage(this.image, x, y, null);
                        break;
                    case WALK:
                        g.drawImage(imgs[walk_right_count++], x, y, null);
                        break;
                    case SHOOT:
                        g.drawImage(imgs[shoot_right_count++], x, y, null);
                        break;
                    case JUMP:
                        g.drawImage(ImgUtil.getImage("hero_right_jump"), x, y, null);
                        break;
                }
                break;
            case LEFT:
                switch (this.action) {
                    case STAND:
                        g.drawImage(ImgUtil.getImage("hero_left_stand"), x, y, null);
                        break;
                    case WALK:
                        g.drawImage(imgs[walk_left_count++], x, y, null);
                        break;
                    case SHOOT:
                        g.drawImage(imgs[shoot_left_count++], x, y, null);
                        break;
                    case JUMP:
                        g.drawImage(ImgUtil.getImage("hero_left_jump"), x, y, null);
                        break;
                }
                break;
        }
        move();
    }

    //移动
    public void move() {
        if (shoot){
            shoot();
        }
        if (jump) {
            jump();
        }
        if (right) {
            this.dir = Direction.RIGHT;
        } else if (left) {
            this.dir = Direction.LEFT;
        }
        if (walk) {
            if (right) {
                this.action = Action.WALK;
                x += speed;
            } else if (left) {
                this.action = Action.WALK;
                x -= speed;
            }
        } else if (shoot) {
            this.action = Action.SHOOT;
        } else if (jump) {
            this.action = Action.JUMP;
        } else {
            this.action = Action.STAND;
        }
        outofBound();
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                right = true;
                walk = true;
                break;
//                move();
            case KeyEvent.VK_A:
                left = true;
                walk = true;
                break;
//                move();
            case KeyEvent.VK_K:
                shoot = true;
                break;
            case KeyEvent.VK_J:
                jump = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_D:
                right = false;
                walk = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                walk = false;
                break;
            case KeyEvent.VK_K:
                shoot = false;
                break;
        }
    }

    //跳的逻辑处理
    public void jump() {
        if (jump_up) {
            vt = v0 - g * t;
            changeHeight = v0 * t;
            v0 = vt;
            y -= changeHeight;
            if (vt < 0) {
                vt = 0;
                v0=0;
                jump_up = false;
            }
        } else {
            vt = v0 + g * t;
            changeHeight = v0 * t;
            v0 = vt;
            y += changeHeight;
            if (y>=Constant.GAME_HEIGHT-height-182){
                y=Constant.GAME_HEIGHT-height-182;
                v0=20;
                jump_up = true;
                jump = false;
            }
        }
    }

    /*确定边界*/
    public void outofBound() {
        if (x < 0) {
            x = 0;
        }
        if (x > Constant.GAME_WIDTH - width) {
            x = Constant.GAME_WIDTH - width;
        }
    }
    public void shoot(){
        MusicUtil.getMusic("E:\\MaoXiaoDao\\src\\com\\neuedu\\imgs\\bullet.wav");
        if (this.dir == Direction.RIGHT) {
            Bullet bullet = new Bullet(x+width,y+height/2/2,this.dir,mxc);
            mxc.bullets.add(bullet);
        }else if (this.dir == Direction.LEFT){
            Bullet bullet = new Bullet(x-width-7,y+7,this.dir,mxc);
            mxc.bullets.add(bullet);
        }
    }
}
