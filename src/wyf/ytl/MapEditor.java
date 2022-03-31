package wyf.ytl;

import javafx.scene.control.SplitPane;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author liuming
 * @description
 * @date 2022/3/28
 */
public class MapEditor extends JFrame implements ActionListener {
    Font font = new Font("宋体",Font.BOLD,24);
    Font itemFont = new Font("宋体",Font.BOLD,20);


    JMenu jMenu = new JMenu("文件");
    JMenuItem jMenuItem = new JMenuItem("打开");

    private JMenu[] jMenus = {
            jMenu,
    };
    private JMenuItem[] jFileItems = {
            jMenuItem,
    };
    private JMenuBar jMenuBar = new JMenuBar();
    private JFileChooser jFileChooser = new JFileChooser();

    JSlider jSliderX = new JSlider(JSlider.HORIZONTAL,10,70,60);
    JSlider jSliderY = new JSlider(JSlider.VERTICAL,10,70,60);
    JSpinner jSpinnerX = new JSpinner();
    JSpinner jSpinnerY = new JSpinner();

    JLabel jLabel_rows = new JLabel("地图行数：");
    JTextField jTextField_rows = new JTextField("35");

    JLabel jLabel_cols = new JLabel("地图列数：");
    JTextField jTextField_cols = new JTextField("15");

    JButton jButton = new JButton("确定");

    JScrollPane jsp;
    SplitPanel sp;
    public MapEditor(){
        jMenu.setFont(font);
        jMenuItem.setFont(itemFont);
        for (JMenuItem item : jFileItems) {
            jMenus[0].add(item);
            item.addActionListener(this);
        }
        for (JMenu menu : jMenus) {
            jMenuBar.add(menu);
        }

        setJMenuBar(jMenuBar);

        jSliderX.setBounds(10,410,100,40);
        jSliderX.setMinorTickSpacing(2);
        jSliderX.setMajorTickSpacing(20);
        jSliderX.setPaintTicks(true);
        jSliderX.setPaintLabels(true);
        jSliderX.setValue(4);
        add(jSliderX);
        jSpinnerX.setBounds(120,410,50,20);
        jSpinnerX.setValue(12);
        add(jSpinnerX);

        jSliderY.setBounds(560,10,40,100);
        jSliderY.setMinorTickSpacing(2);
        jSliderY.setMajorTickSpacing(20);
        jSliderY.setPaintTicks(true);
        jSliderY.setPaintLabels(true);
        jSliderY.setValue(4);
        add(jSliderY);
        jSpinnerY.setBounds(560,120,40,20);
        jSpinnerY.setValue(8);
        add(jSpinnerY);




        jLabel_rows.setBounds(190,410,60,20);
        add(jLabel_rows);
        jTextField_rows.setBounds(245,410,60,20);
        add(jTextField_rows);
        jLabel_cols.setBounds(320,410,60,20);
        add(jLabel_cols);
        jTextField_cols.setBounds(375,410,60,20);
        add(jTextField_cols);

        jButton.setBounds(450,410,60,20);
        jButton.addActionListener(this);
        add(jButton);

        setTitle("地图设计器 V0.1");
        setLayout(null);
        setBounds(100,100,640,540);
        setVisible(true);


    }



    public static void main(String[] args) {
        new MapEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jFileItems[0]){
            jFileChooser.showOpenDialog(this);
            if(jFileChooser.getSelectedFile() != null){
                String path = jFileChooser.getSelectedFile().getAbsolutePath();
                ImageIcon ii = new ImageIcon(path);
                sp = new SplitPanel(path,this);

                jsp = new JScrollPane(sp);
                jsp.setBounds(5,5,ii.getImage().getWidth(this) + 5,ii.getImage().getHeight(this) + 5);
                add(jsp);
                setVisible(true);

            }
        }else if(e.getSource() == jButton){
            if(sp != null) {
                this.dispose();
                new MapEditorMinor(sp.bigImage,(int) jSpinnerX.getValue(),(int)jSpinnerY.getValue(),Integer.parseInt(jTextField_rows.getText()),Integer.parseInt(jTextField_cols.getText()));
            }
        }
    }
}
