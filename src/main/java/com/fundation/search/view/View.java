package com.fundation.search.view;


import javax.swing.*;

public class View extends JFrame {

    private SearchPanel panel;

    public View() {
        setting();
        init();
    }

    private void setting() {
        setTitle("Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(515, 405);
    }

    private void init() {
        panel = new SearchPanel();
        add(panel);
        setVisible(true);
    }

    public SearchPanel getPanel() {

        return panel;
    }

}
