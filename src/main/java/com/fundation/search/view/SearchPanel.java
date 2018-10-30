package com.fundation.search.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JLabel labelPath;
    private JTextField textFieldPath;
    private JButton buttonBrowse;
    private JFileChooser fileChooser;

    private JLabel labelFilename;
    private JTextField filename;

    private JLabel labelSize;
    private JComboBox optionSize;
    private JTextField size;

    private JLabel labelExtension;
    private JTextField extension;

    private JCheckBox readOnly;

    private JCheckBox hidden;

    private JLabel labelOwner;
    private JTextField owner;

    private JLabel labelContent;
    private JTextField content;

    private JLabel labelType;
    private JComboBox type;

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
        JPanel searchControls = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        labelPath = new JLabel("Path:");
        searchControls.add(labelPath);
        textFieldPath = new JTextField();
        searchControls.add(textFieldPath);
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
        searchControls.add(buttonBrowse);

        labelFilename = new JLabel("Filename:");
        searchControls.add(labelFilename);
        filename = new JTextField();
        searchControls.add(filename);

        // size
        labelSize = new JLabel("Size:");
        searchControls.add(labelSize);

        optionSize = new JComboBox();
        optionSize.addItem("<");
        optionSize.addItem(">");
        optionSize.addItem("==");
        searchControls.add(optionSize);

        size = new JTextField();
        searchControls.add(size);
        // end size

        // extension
        labelExtension = new JLabel("Extension");
        searchControls.add(labelExtension);

        extension = new JTextField();
        searchControls.add(extension);
        // end extension

        // checks
        readOnly = new JCheckBox("Read Only", false);
        searchControls.add(readOnly);

        hidden = new JCheckBox("Hidden", false);
        searchControls.add(hidden);
        // end checks

        // owner
        labelOwner = new JLabel("Owner");
        searchControls.add(labelOwner);

        owner = new JTextField();
        searchControls.add(owner);
        // end owner


        // content
        labelContent = new JLabel("Content");
        searchControls.add(labelContent);

        content = new JTextField();
        searchControls.add(content);
        // end content

        //type
        labelContent = new JLabel("Type");
        searchControls.add(labelContent);

        type = new JComboBox();
        type.addItem("all");
        type.addItem("Folder");
        type.addItem("File");
        searchControls.add(type);
        // end type
        add(searchControls);

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
