package com.Stone;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {

    //背景图片

    public static Image backImg = Toolkit.getDefaultToolkit().getImage("imgs/back5.png");
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.png");
    //boss图片
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss2.png");
    //爆炸图片
    public static Image explode = Toolkit.getDefaultToolkit().getImage("imgs/explode.png");
    //我机
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("imgs/mine2.png");
    //我方子弹
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/shoot3.png");
    //敌方小兵
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("imgs/chicken1.png");
    //敌方子弹
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/shoot1.png");



    //所有物体的集合
    public static List<GameObj> gameObjList = new ArrayList<>();
    //要删除的集合
    public static List<GameObj> removeList = new ArrayList<>();
    //我方子弹的集合
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //敌方子弹的集合
    public static List<BulletObj> bulletObjList = new ArrayList<>();
    //敌机的集合
    public static List<EnemyObj> enemyObjList = new ArrayList<>();
    //爆炸的集合
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();

    //绘制字符串的工具类
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }

}
