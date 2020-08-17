package neuedu.pojo;

import com.neuedu.client.MaoXiaoDaoClinet;
import com.neuedu.utils.ImgUtil;

import java.awt.*;

public class Mob {
    private static Image[] images=new Image[100];
    public boolean live=true;
    static {
        for (int i=0;i<=7;i++){
            images[i] = ImgUtil.getImage("mob_left"+i);
        }
    }
    public int x;
    private int y;
    private  int height;
    private  int width;
    public MaoXiaoDaoClinet mxc;
    public BloodBar bb;
    public int hp = Constant.MOB_HP;
    public Mob(int x, MaoXiaoDaoClinet mxc) {
        this.mxc=mxc;
        this.height = images[0].getHeight(null);
        this.width = images[0].getWidth(null);
        this.x=x;
        this.y=Constant.GAME_HEIGHT-182-height;
        this.bb = new BloodBar(this.x,this.y,this.width);
    }
    public int count=0;
    public void draw(Graphics g){
        if (count>7){
            count=0;
        }
        g.drawImage(images[count++],x,y,null);
        bb.draw(g);
        if (!live){
            this.mxc.mobs.remove(this);
        }
    }
    //获取图片矩形
    public Rectangle getRectangle(){
        return new Rectangle(x,y,width,height);
    }
    //血条
    class BloodBar{
        int x;
        int y;
        int width;
        public BloodBar(int x,int y,int width){
            this.x=x;
            this.y=y;
            this.width=width;
        }
        public void draw(Graphics g){
            g.setColor(Color.red);
            g.drawRect(x,y,width,10);
            g.setColor(Color.green);
            g.fillRect(x,y,width*(hp/100),10);
        }
    }
}
