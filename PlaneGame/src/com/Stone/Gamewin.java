package com.Stone;

import javax.swing.*;//extends JFrame具有继承启动窗口的功能
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gamewin extends JFrame {
    //游戏状态 0未开始 1游戏中 2暂停 3通关失败 4通关成功
    public static int state = 0;

    Image offSreenImage = null;
    int width = 600;
    int height = 600;
    //游戏的重绘制次数
    int count = 1;
    //敌机出现的个数
    //游戏得分
    public static  int score = 0;
    //背景图
    BgObj bgObj = new BgObj(GameUtils.backImg,0,-1300,2);
    //我方飞机
    public PlaneObj planeObj = new PlaneObj(GameUtils.planeImg,290,440,20,20,0,this);
    //我方子弹ShellObj shellObj = new ShellObj(GameUtils.shellImg,planeObj.getX()+3,planeObj.getY()-10,14,29,5,this);
    //敌方Boss对象
    public BossObj bossObj = null;
    public void  launch(){
        //窗口是否可见
        this.setVisible(true);
        //窗口大小
        this.setSize(600,600);
        //窗口位置
        this.setLocationRelativeTo(null);
        //窗口标题
        this.setTitle("宇宙长飞船大战");

       GameUtils.gameObjList.add(bgObj);
       GameUtils.gameObjList.add(planeObj);



        //鼠标点击事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() ==1 && state ==0){//游戏未开始状态点击鼠标左键
                    state = 1;
                repaint();
                }

            }
        });

        //暂停
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() ==32){
                    switch (state){
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                        default:
                    }
                }
            }
        });

        //重复绘制
        while (true) {
            if(state == 1){
                createObj();
                repaint();
            }
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (offSreenImage == null){
            offSreenImage = createImage(width,height);
        }
        Graphics gImage = offSreenImage.getGraphics();
        gImage.fillRect(0,0,width,height);
        if (state == 0) {
            gImage.drawImage(GameUtils.backImg, 0, 0, null);
            //gImage.drawImage(GameUtils.bgImg, 0, -500,500,500, this);
            gImage.drawImage(GameUtils.bossImg, 200, 120,140,120, null);
            gImage.drawImage(GameUtils.explode, 240, 420,70,60, null);
            GameUtils.drawWord(gImage,"点击开始游戏",Color.yellow,40,180,300);
        }
        if (state == 1) {
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
            //运行中
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
        }
        if (state == 2) {
            //暂停
            gImage.drawImage(GameUtils.backImg, 0, 0, null);
            //gImage.drawImage(GameUtils.bgImg, 0, -500,500,500, this);
            //gImage.drawImage(GameUtils.bossImg, 200, 120,140,120, null);
            GameUtils.drawWord(gImage,"按空格继续游戏",Color.yellow,40,140,300);
        }
        if (state == 3) {
            //失败了
            gImage.drawImage(GameUtils.explode, planeObj.getX()-35,planeObj.getY()-50,70,60, null);
            gImage.drawImage(GameUtils.backImg, 0, 0, null);
            GameUtils.drawWord(gImage,"GAME OVER",Color.RED,45,180,300);
        }
        if (state == 4) {
            //暂停
            gImage.drawImage(GameUtils.backImg, 0, 0, null);
            //gImage.drawImage(GameUtils.bgImg, 0, -500,500,500, this);
            //gImage.drawImage(GameUtils.bossImg, 200, 120, 140, 120, null);
            GameUtils.drawWord(gImage, "通关成功", Color.yellow, 40, 200, 300);
        }
        GameUtils.drawWord(gImage,score+" 分",Color.green,40,30,100);
        g.drawImage(offSreenImage,0,0,this);
        count++;
    }

    void createObj(){
        //我方子弹
        if (count % 15 == 0) {
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg, planeObj.getX() + 3, planeObj.getY() - 10, 14, 29, 5, this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }
            if (count % 30 == 0) {
                GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImg,(int)(Math.random()*10)*50,0,49,36,5,this));
                GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() - 1));
        }
        if (count % 30 == 0 && bossObj != null) {
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImg, bossObj.getX()+76, bossObj.getY()+80,15,25,5,this));
            GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1));
        }
        if (score > 3 && bossObj == null){
            bossObj =  new BossObj(GameUtils.bossImg,250,20,150,100,5,this);
            GameUtils.gameObjList.add(bossObj);
        }
    }
    public static void main(String[] args) {
        Gamewin gamewin = new Gamewin();
        gamewin.launch();
    }
}
