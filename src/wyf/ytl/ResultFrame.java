package wyf.ytl;

import javax.swing.*;

/**
 * @author liuming
 * @description
 * @date 2022/3/31
 */
public class ResultFrame extends JFrame {
    JTextArea jta = new JTextArea();
    JScrollPane jsp = new JScrollPane(jta);
    public ResultFrame(int[][] result){
        this.setTitle("结果");
        for(int i=0;i<result.length;i++){
            String temp = "";
            jta.setText(jta.getText()+"\n\t{");
            for(int j=0;j<result[0].length;j++){
                temp += result[i][j] + ",";
            }
            temp = temp.substring(0, temp.length()-1);
            jta.setText(jta.getText()+temp);
            jta.setText(jta.getText()+"}");
        }
        jta.setText(jta.getText()+"\n};");
        this.add(jsp);//添加到窗口中
        this.setBounds(100,50,400,800);//设置窗口的大小和位置
        this.setVisible(true);//设置窗口的可见性
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//关闭按钮
    }
}
