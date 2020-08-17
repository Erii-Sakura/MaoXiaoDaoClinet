package neuedu.pojo;

import com.neuedu.client.MaoXiaoDaoClinet;
import com.neuedu.utils.ImgUtil;

import java.awt.*;
import java.util.List;

public class Bullet {
    private Image image;
    private int x;
    private int y;
    private int speed;
    private Direction dir;
    private int width;
    private int height;
    private MaoXiaoDaoClinet mxc;
    private boolean live = true;

    public Bullet(int x, int y, Direction dir, MaoXiaoDaoClinet mxc) {
        this.mxc = mxc;
        this.x = x;
        this.dir = dir;
        this.y = y;
        this.speed = 90;
//        this.image = ImgUtil.getImage("right_bullet");
        if (this.dir == Direction.RIGHT) {
            this.image = ImgUtil.getImage("right_bullet");
        } else if (this.dir == Direction.LEFT) {
            this.image = ImgUtil.getImage("left_bullet");
        }
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

    public void draw(Graphics g) {
        if (!live) {
           this.mxc.bullets.remove(this);
        }
        g.drawImage(this.image, x, y, null);
        move();
    }

    public void move() {
        if (this.dir == Direction.RIGHT) {
            x += speed;
        } else if (this.dir == Direction.LEFT) {
            x -= speed;
        }
//        x+=speed;
    }

    //获取图片矩形
    public Rectangle getRectangle() {
        return new Rectangle(x, y, width, height);
    }

    //打怪方法
    public boolean hit(Mob mob) {
        if (this.getRectangle().intersects(mob.getRectangle())) {
            this.live = false;
//            this.mxc.bullets.remove(this);
//            this.mxc.mobs.remove(mob);
//            mob.live = false;
            mob.hp-=7;
            if (mob.hp<=0){
                mob.live = false;
                Mobdie mobdie = new Mobdie(mob.x, mxc);
                this.mxc.mobdies.add(mobdie);
            }

//            mobdie.live = false;
        }
        return false;
    }

    public boolean hit(List<Mob> mobs) {
        for (int i = 0; i < mobs.size(); i++) {
            if (hit(mobs.get(i))) {
                return true;
            }
        }
        return false;
    }
}