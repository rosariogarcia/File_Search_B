package com.fundation.search.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchPanel extends JPanel {

    private GridLayout experimentLayout = new GridLayout(2, 0);

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
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    }

    private void init() {
        JPanel pathControls = new JPanel();
        pathControls.setMaximumSize(new Dimension(350, 25));

        pathControls.setLayout(new BoxLayout(pathControls, BoxLayout.LINE_AXIS));

        labelPath = new JLabel();
        labelPath.setText("Path:");

        textFieldPath = new JTextField();
        buttonBrowse = new JButton("...");

        buttonBrowse.addActionListener(e -> {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new java.io.File("."));
            fileChooser.setDialogTitle("Select Source");
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
        filenameControls.setMaximumSize(new Dimension(400, 25));
        filenameControls.setLayout(new BoxLayout(filenameControls, BoxLayout.LINE_AXIS));

        labelFilename = new JLabel();
        labelFilename.setText("Filename:");
        filenameControls.add(labelFilename);

        filename = new JTextField();
        filename.setPreferredSize(new Dimension(400, 25));
        filenameControls.add(filename);
        add(filenameControls);

        buttonSearch = new JButton("Search");
        add(buttonSearch);

        String[][] data = {{"", ""}, {"", ""}};
        String[] columns = {"Name", "Type", "Owner", "Create Date", "Modified Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        tableResults = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tableResults);

        add(scrollPane);
    }

    public String getFilename() {
        return filename.getText();
    }

    public String getTextFieldPath() {
        return textFieldPath.getText();
    }

    public JButton getButtonSearch() {
        return buttonSearch;
    }

    public JTable getTableResults() {
        return tableResults;
    }
}
