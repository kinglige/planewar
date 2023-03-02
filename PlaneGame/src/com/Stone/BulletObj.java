package com.Stone;

import java.awt.*;

public class BulletObj extends GameObj{
    public BulletObj(Image img, int x, int y, int width, int height, double speed, Gamewin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //碰撞检测
        if(this.getRec().intersects(this.frame.planeObj.getRec())){
            Gamewin.state = 3;
        }
        //敌方子弹的越界消失 条件 > 600, 改变后的坐标（-300，300）
        if (y > 600){
            this.x = -300;
            this.y = 300;
            GameUtils.removeList.add(this);
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
