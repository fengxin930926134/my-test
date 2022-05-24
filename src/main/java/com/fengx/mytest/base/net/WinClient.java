package com.fengx.mytest.base.net;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WinClient extends JFrame {
    private JTextField tf;
    private JButton send;
    private JTextArea ta;

    public WinClient(){
        setTitle("聊天室");
        setSize(1500,800);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Font font=new Font("微软雅黑",Font.BOLD,36);
        JPanel bottom=new JPanel();
        bottom.setLayout(new BorderLayout());
        tf=new JTextField();
        tf.setFont(font);
        send=new JButton("发送");
        send.setFont(font);
        bottom.add(tf);
        bottom.add(send,BorderLayout.EAST);
        add(bottom,BorderLayout.SOUTH);
        ta=new JTextArea();
        ta.setFont(font);
        ta.setDisabledTextColor(Color.blue);
        ta.setEditable(false);
        JScrollPane sp=new JScrollPane(ta);
        add(sp);
        try {
            Socket socket=new Socket("192.168.1.109",9999);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedReader br= null;
                    try {
                        InputStreamReader isr=new InputStreamReader(socket.getInputStream());
                        br = new BufferedReader(isr);
                        while (true){
                            String s=br.readLine();
                            ta.append(s+"\n");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String s=tf.getText();
                    if (s.equals("")){
                        try {
                            PrintWriter out=new PrintWriter(socket.getOutputStream());
                            out.write(s+"\n");
                            out.flush();
                            tf.setText("");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            tf.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar()=='\n'){
                        String s=tf.getText();
                        if (s.equals(""))return;
                        try {
                            PrintWriter out=new PrintWriter(socket.getOutputStream());
                            out.write(s+"\n");
                            out.flush();
                            tf.setText("");
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void main(String[] agrs){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WinClient();
            }
        });
    }
}
