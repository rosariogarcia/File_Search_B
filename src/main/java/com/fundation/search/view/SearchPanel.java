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

        // size
        JPanel sizeControls = new JPanel();
        sizeControls.setLayout(new GridLayout(0, 3));

        labelSize = new JLabel();
        labelSize.setText("Size:");
        sizeControls.add(labelSize);

        optionSize = new JComboBox();
        optionSize.addItem("<");
        optionSize.addItem(">");
        optionSize.addItem("==");
        sizeControls.add(optionSize);


        size = new JTextField();
        size.setPreferredSize(new Dimension(350, 25));
        sizeControls.add(size);

        add(sizeControls);
        // end size

        // extension
        JPanel extensionControls = new JPanel();
        sizeControls.setLayout(new GridLayout(0, 2));
        labelExtension = new JLabel("Extension");
        extensionControls.add(labelExtension);

        extension = new JTextField();
        extensionControls.add(extension);

        add(extensionControls);

        // end extension

        // checks
        JPanel checkControls = new JPanel();
        sizeControls.setLayout(new GridLayout(0, 2));

        readOnly = new JCheckBox("Read Only", false);
        checkControls.add(readOnly);

        hidden = new JCheckBox("Hidden", false);
        checkControls.add(hidden);

        add(checkControls);

        // end checks

        // owner
        JPanel ownerControls = new JPanel();
        ownerControls.setLayout(new GridLayout(0, 2));
        labelOwner = new JLabel("Owner");
        ownerControls.add(labelOwner);

        owner = new JTextField();
        ownerControls.add(owner);

        add(ownerControls);

        // end owner


        // content
        JPanel contentControls = new JPanel();
        contentControls.setLayout(new GridLayout(0, 2));
        labelContent = new JLabel("Content");
        ownerControls.add(labelContent);

        content = new JTextField();
        ownerControls.add(content);

        add(contentControls);

        // end content

        //type
        JPanel typeControls = new JPanel();
        contentControls.setLayout(new GridLayout(0, 2));
        labelContent = new JLabel("Content");
        typeControls.add(labelContent);

        type = new JComboBox();
        type.addItem("all");
        type.addItem("Folder");
        type.addItem("File");
        typeControls.add(type);
        add(typeControls);
        // end type

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
