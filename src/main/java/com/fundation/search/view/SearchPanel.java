package com.fundation.search.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchPanel extends JPanel {

    private JPanel singleSearch;
    private JPanel results;
    private JPanel advancedSearch;

    private JTable tableResults;

    private JLabel labelPath;
    private JTextField textFieldPath;
    private JButton searchButton;
    private JPanel SearchPanel;

    public static void main(String[] args){
//        Controller icon = new Controller ();
    }

    public SearchPanel(){
        setting();
        init();
    }

    private  void setting(){

    }
    private void init(){
        labelPath = new JLabel();
        labelPath.setText("Path:");

        textFieldPath = new JTextField();
        textFieldPath.setSize(25,100);

        add(labelPath);
        add(textFieldPath);
        String[][] data = {{"Hello", "World"},{"Hello", "World"}};
        setResults(data);
        add(tableResults);
    }

    public String getPath(){
        return textFieldPath.getText();
    }

    public void setResults(String[][] data){

        String[] columns = {"Name", "Type", "Owner","Create Date", "Modified Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        tableResults = new JTable(model);
    }
}
