package com.Stone;

import java.awt.*;

public class ExplodeObj extends GameObj{

    static Image[] pic = new Image[6];
    int explodeCount = 0;

    static {
            //爆炸图
        for (int i = 0; i < pic.length; i++) {
            pic[i] = Toolkit.getDefaultToolkit().getImage("imgs/explode0.png");
        }
    }

    public ExplodeObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics gImage) {

        if (explodeCount < 6){
            img = pic[explodeCount];
            super.paintSelf(gImage);
            explodeCount++;
        }
    }
}
