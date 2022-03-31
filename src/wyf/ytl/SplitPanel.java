package wyf.ytl;

import javax.swing.*;
import java.awt.*;

/**
 * @author liuming
 * @description
 * @date 2022/3/29
 */
public class SplitPanel extends JPanel {
    Image bigImage;
    MapEditor father;

    public SplitPanel(String path,MapEditor father){
        this.father = father;

        ImageIcon ii = new ImageIcon(path);
        bigImage = ii.getImage();

        this.setPreferredSize(
                new Dimension(bigImage.getWidth(this),bigImage.getHeight(this))
        );
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bigImage,0,0,Color.WHITE,this);
        int imageWidth = bigImage.getWidth(this);
        int imageHeight = bigImage.getHeight(this);
        int countX = (int)father.jSpinnerX.getValue();

        int countY = (int)father.jSpinnerY.getValue();

        g.setColor(Color.green);
        int width = imageWidth / countX;
        for (int i = 0; i < countX; i++) {
            g.drawLine(width * i,0,width * i,imageHeight);
        }
        int height = imageHeight / countY;
        for (int i = 0; i < countY; i++) {
            g.drawLine(0,height * i,imageWidth,height * i);
        }
    }
}
