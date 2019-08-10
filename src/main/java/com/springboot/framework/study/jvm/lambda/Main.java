package com.springboot.framework.study.jvm.lambda;

import javax.swing.*;

/**
 * lambda测试演示
 *
 * @author huangpengfei
 * @version 1.0
 * @date 2019/8/10 11:56
 */
public class Main extends JFrame {
    private JButton jButton;

    public Main() {

        this.setBounds(200, 200, 400, 200);
        this.setTitle("lambda测试");

        jButton = new JButton("click");
//        jButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("clicked");
//            }
//        });

        jButton.addActionListener(e -> System.out.println("Hello"));

        this.add(jButton);

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }
}
