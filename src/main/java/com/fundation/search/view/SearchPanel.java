package com.fundation.search.view;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JTable tableResults;
    private JLabel labelPath;
    private JTextField textFieldPath;
    private JLabel labelFilename;
    private JTextField filename;
    private JButton searchButton;

    public SearchPanel() {
        setting();
        init();
    }

    private void setting() {

    }

    private void init() {
        labelPath = new JLabel();
        labelPath.setText("Path:");

        textFieldPath = new JTextField();
        textFieldPath.setPreferredSize(new Dimension(350, 25));
        add(labelPath);
        add(textFieldPath);

        labelFilename = new JLabel();
        labelFilename .setText("Filename:");
        add(labelFilename);

        filename = new JTextField();
        filename.setPreferredSize(new Dimension(350, 25));
        add(filename);

        tableResults = new JTable();
        tableResults.setPreferredSize(new Dimension(350, 25));
        tableResults.setBackground(Color.WHITE);
        add(tableResults);
        searchButton = new JButton();
        add(searchButton);
    }

    public String getPath() {
        return textFieldPath.getText();
    }

    public String getFilename() {
        return filename.getText();
    }


}
