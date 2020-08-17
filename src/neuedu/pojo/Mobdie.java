package neuedu.pojo;

import com.neuedu.client.MaoXiaoDaoClinet;
import com.neuedu.utils.ImgUtil;

import java.awt.*;

public class Mobdie {
    private static Image[] images = new Image[100];
    static {
        for (int i = 0 ;i<=13;i++){
            images[i]= ImgUtil.getImage("mob_die"+i);
        }
    }
    private int x;
    private int y;
    private int width;
    private int height;
    private MaoXiaoDaoClinet mxc;
    public boolean live = true;
    public Mobdie(int x, MaoXiaoDaoClinet mxc) {
        this.x=x;
        this.mxc=mxc;
        this.height=images[0].getHeight(null);
        this.y=Constant.GAME_HEIGHT-182-height;
    }
    public int count=0;
    public void draw(Graphics g){
        if (!live){
            return;
        }
        if (count>13){
           this.live=false;
        }
        g.drawImage(images[count++],x,y,null);
    }
}
