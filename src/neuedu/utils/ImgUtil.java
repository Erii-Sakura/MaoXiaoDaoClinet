package neuedu.utils;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImgUtil {
    /*图片路径*/
    public static  Map<String, Image> maps = new HashMap<>();
    static {
        maps.put("hero_right_stand",GameUtil.getImg("com/neuedu/imgs/hero/stand_r/stand1_0.png"));
        maps.put("hero_left_stand",GameUtil.getImg("com/neuedu/imgs/hero/stand_l/stand1_0.png"));
        //加载向右走
        for (int i = 0;i<=4;i++){
            maps.put("hero_right_walk"+i,GameUtil.getImg("com/neuedu/imgs/hero/walk_r/walk1_"+i+".png"));
        }
        //加载向左走
        for (int i = 0;i<=4;i++){
            maps.put("hero_left_walk"+i,GameUtil.getImg("com/neuedu/imgs/hero/walk_l/walk1_"+i+".png"));
        }
        //加载向右射击
        for (int i = 0;i<=3;i++){
            maps.put("hero_right_shoot"+i,GameUtil.getImg("com/neuedu/imgs/hero/shoot_r/shoot1_"+i+".png"));
        }
        //加载向左射击
        for (int i = 0;i<=3;i++){
            maps.put("hero_left_shoot"+i,GameUtil.getImg("com/neuedu/imgs/hero/shoot_l/shoot1_"+i+".png"));
        }
        //加载向右跳
        maps.put("hero_right_jump",GameUtil.getImg("com/neuedu/imgs/hero/jump/jump_r.png"));
        //加载向左跳
        maps.put("hero_left_jump",GameUtil.getImg("com/neuedu/imgs/hero/jump/jump_l.png"));
        //向右箭头
        maps.put("right_bullet",GameUtil.getImg("com/neuedu/imgs/箭右.png"));
        //向左箭头
        maps.put("left_bullet",GameUtil.getImg("com/neuedu/imgs/箭左.png"));
        //怪物图片
        for (int i = 0;i<=7;i++){
            maps.put("mob_left"+i,GameUtil.getImg("com/neuedu/imgs/mob/mob_left/0"+i+".png"));
        }
        //怪物死亡
        for (int i = 0;i<=13;i++){
            maps.put("mob_die"+i,GameUtil.getImg("com/neuedu/imgs/mob/mob_die/"+(i*120)+".png"));
        }
    }
    public  static Image getImage(String key){
        return maps.get(key);
    }
}
