package com.fundation.search.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    private JPanel singleSearch;
    private JPanel results;
    private JPanel advancedSearch;

    private JTable tableResults;

    private JLabel labelPath;
    private JTextField textFieldPath;
    private JButton searchButton;
    private JPanel SearchPanel;
    private JTextField filename;
    private JLabel labelFilename;

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
        textFieldPath.setPreferredSize(new Dimension(350, 25));

        filename = new JTextField();
        filename.setPreferredSize(new Dimension(300, 25));
        add(labelPath);
        add(textFieldPath);
        add(labelFilename);
        add(filename);

        String[][] data = {{"Hello", "World"},{"Hello", "World"}};
        String[] columns = {"Name", "Type", "Owner","Create Date", "Modified Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        JScrollPane scrollPane = new JScrollPane(tableResults);
        tableResults.setFillsViewportHeight(true);


        tableResults = new JTable(model);
        add(tableResults.getTableHeader(), BorderLayout.CENTER);
        add(tableResults, BorderLayout.CENTER);

        String[][] dataToShow = {{"sdfs", "sda"},{"asdfsd", "World"}};

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Tickles!");
                setResults(dataToShow);
            }
        });

        add(tableResults);
        add(searchButton);

    }

    public String getPath(){
        return textFieldPath.getText();
    }

    public void setResults(String[][] data){
        DefaultTableModel model = (DefaultTableModel) tableResults.getModel();
        model.addRow(data);
    }


}
