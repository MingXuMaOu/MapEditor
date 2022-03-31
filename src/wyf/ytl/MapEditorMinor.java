package wyf.ytl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author liuming
 * @description
 * @date 2022/3/29
 */
public class MapEditorMinor extends JFrame implements MouseListener,ActionListener {

    private Image imageZ;
    private Image imageTemp;
    private int imageCols;
    private int imageRows;
    private int width;
    private int height;
    private int cols;
    private int rows;

    private Icon tempii;
    private int result[][];
    private int tempNumber;

    private JPanel jps;
    private JPanel jpx;

    private JLabel jpas[][];
    private JLabel jpax[][];

    private JScrollPane jsps;
    private JScrollPane jspx;
    private JSplitPane jspz;




    private JMenuBar jMenuBar = new JMenuBar();//菜单栏

    private JMenu[] jMenu = {//菜单项数组
            new JMenu("文件"),
    };
    private JMenuItem[] jFileItem = {//文件菜单中的子菜单
            new JMenuItem("生成"),
    };
    public MapEditorMinor(Image image,int imageCols,int imageRows,int rows,int cols){
        this.imageZ = image;
        this.imageCols = imageCols;
        this.imageRows = imageRows;
        this.rows = rows;
        this.cols = cols;
        this.width = imageZ.getWidth(this) / imageCols;
        this.height = imageZ.getHeight(this)/ imageRows;


        setBounds(10,10,1300,1000);
        setVisible(true);

        for(JMenuItem item : jFileItem){//将子菜单添加到文件菜单下
            jMenu[0].add(item);
            item.addActionListener(this);//添加监听
        }
        for(JMenu temp: jMenu){
            jMenuBar.add(temp);//将菜单添加到菜单栏
        }
        result = new int[rows][cols];
        jps = new JPanel();
        jps.setPreferredSize(new Dimension((width + 2) * cols,(height + 2) * rows));
        jps.setLayout(null);

        jpas = new JLabel[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                jpas[i][j] = new JLabel();
                jpas[i][j].setBackground(Color.GRAY);
                jpas[i][j].setOpaque(true);
                jpas[i][j].setBounds(j * (width + 2),i * (height + 2),width,height);
                jpas[i][j].addMouseListener(this);
                jps.add(jpas[i][j]);
            }
        }
        jsps = new JScrollPane(jps);


        jpx = new JPanel();
        jpx.setPreferredSize(new Dimension((width + 2) * imageCols,(height + 2) * imageRows));
        jpx.setLayout(null);

        jpax = new JLabel[imageRows][imageCols];
        for (int i = 0; i < imageRows; i++) {
            for (int j = 0; j < imageCols; j++) {
                jpax[i][j] = new JLabel();
                jpax[i][j].setBackground(Color.BLUE);
                jpax[i][j].setBounds(j * (width + 2),i * (height + 2),width,height);
                jpax[i][j].addMouseListener(this);
                jpx.add(jpax[i][j]);
            }
        }
        jspx = new JScrollPane(jpx);

        jspz = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsps,jspx);
        jspz.setDividerLocation(400);
        jspz.setDividerSize(4);

        add(jspz);

        for (int i = 0; i < imageRows; i++) {
            for (int j = 0; j < imageCols; j++) {
                imageTemp = createImage(width,height);
                Graphics g = imageTemp.getGraphics();
                g.drawImage(imageZ,0,0,width,height,j * width,i * height,(j + 1) * width,(i + 1) * height,this);
                ImageIcon ii = new ImageIcon(imageTemp);
                jpax[i][j].setIcon(ii);
            }
        }
        setJMenuBar(jMenuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jFileItem[0]){
            new ResultFrame(result);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if(o instanceof  JLabel){
            boolean iss = false;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if(jpas[i][j] == o){
                        iss = true;
                    }
                }
            }
            if(iss){
                if(tempii != null){
                    ((JLabel)o).setIcon(tempii);
                    tempii = ((JLabel)o).getIcon();
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            if(jpas[i][j] == o){
                                result[i][j] = tempNumber;
                            }
                        }
                    }
                }
            }else{
                tempii = ((JLabel)o).getIcon();
                for (int i = 0; i < imageRows; i++) {
                    for (int j = 0; j < imageCols; j++) {
                        if(jpax[i][j] == o){
                            tempNumber = i * imageCols + j + 1;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
