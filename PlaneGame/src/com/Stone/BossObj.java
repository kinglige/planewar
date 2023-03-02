package com.Stone;

import java.awt.*;

public class BossObj extends  GameObj{

    int life = 10;

    public BossObj(Image img, int x, int y, int width, int height, double speed, Gamewin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        if (x > 550|| x < -50){
            speed = -speed;
        }
        x += speed;
        for(ShellObj shellObj : GameUtils.shellObjList){
            if (this.getRec().intersects(shellObj.getRec())){
                shellObj.setX(-100);
                shellObj.setY(100);
                life--;
            }
            if (life <= 0){
                Gamewin.state = 4;//敌机爆炸
            }
        }
        //血条的绘制
        gImage.setColor(Color.WHITE);
        gImage.fillRect(20,40,100,10);
        gImage.setColor(Color.RED);
        gImage.fillRect(20,40,life*100/10,10);
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
