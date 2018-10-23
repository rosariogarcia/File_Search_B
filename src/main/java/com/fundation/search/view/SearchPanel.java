package com.fundation.search.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    private GridLayout experimentLayout = new GridLayout(0, 1);
    private JLabel labelPath;
    private JTextField textFieldPath;
    private JButton buttonBrowse;
    private JFileChooser fileChooser;

    private JLabel labelFilename;
    private JTextField filename;

    private JButton buttonSearch;

    private JTable tableResults;

    public SearchPanel() {
        setting();
        init();
    }

    private void setting() {

    }

    private void init() {
        JPanel pathControls = new JPanel();
        pathControls.setLayout(new GridLayout(0, 3));

        setLayout(experimentLayout);
        labelPath = new JLabel();
        labelPath.setText("Path:");

        textFieldPath = new JTextField();
        buttonBrowse = new JButton("...");

        buttonBrowse.addActionListener(e ->  {
                fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                fileChooser.setDialogTitle("choosertitle");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);

                if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    System.out.println("getCurrentDirectory(): " + fileChooser.getCurrentDirectory());
                    System.out.println("getSelectedFile() : " + fileChooser.getSelectedFile());
                } else {
                    System.out.println("No Selection ");
                }
                textFieldPath.setText(fileChooser.getSelectedFile().getPath());

        });

        pathControls.add(labelPath);
        pathControls.add(textFieldPath);
        pathControls.add(buttonBrowse);
        add(pathControls);

        JPanel filenameControls = new JPanel();
        filenameControls.setLayout(new GridLayout(0, 2));

        labelFilename = new JLabel();
        labelFilename.setText("Filename:");
        filenameControls.add(labelFilename);

        filename = new JTextField();
        filename.setPreferredSize(new Dimension(350, 25));
        filenameControls.add(filename);
        add(filenameControls);

        buttonSearch = new JButton("Search");
        add(buttonSearch);

        String[][] data = {{"Hello", "World"}, {"Hello", "World"}};
        String[] columns = {"Name", "Type", "Owner", "Create Date", "Modified Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        String[][] dataToShow = {{"sdfs", "sda"}, {"asdfsd", "World"}};

        tableResults = new JTable(model);
        DefaultTableModel modelset = (DefaultTableModel) tableResults.getModel();
        model.addRow(dataToShow[0]);
        tableResults.setPreferredSize(new Dimension(350, 25));
        JScrollPane scrollPane = new JScrollPane(tableResults);
        tableResults.setFillsViewportHeight(true);
        add(tableResults.getTableHeader(), BorderLayout.CENTER);
        add(tableResults, BorderLayout.CENTER);

        add(tableResults);

    }

    public String getFilename() {
        return filename.getText();
    }
}
