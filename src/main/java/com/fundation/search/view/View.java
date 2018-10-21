package com.fundation.search.view;

import javax.swing.*;

public class View extends JFrame{

    private SearchPanel panel;

    public View(){
        setting();
        init();
    }

    private void setting(){
        setTitle("Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(415,  305);
    }

    private void init (){
        panel = new SearchPanel();
        add(panel);
        setVisible(true);
    }

    public JPanel getPanel(){
        return panel;
    }

    public static void main(String[] args){
        View view = new View();
    }
}