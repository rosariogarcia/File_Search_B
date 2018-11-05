package com.fundation.search.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.toedter.calendar.JCalendar;
import javafx.application.Application;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import sun.util.calendar.JulianCalendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

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
    private JComboBox optionExtension;

    private JCheckBox readOnly;

    private JCheckBox hidden;

    private JLabel labelOwner;
    private JTextField owner;

    private JLabel labelContent;
    private JTextField content;

    private JLabel labelType;
    private JComboBox type;

    private JLabel labelCreateDate;
    private JCalendar createDate;

    private JLabel labelModifiedDate;
    private JCalendar modifiedDate;

    private JLabel labelAccessedDate;
    private JCalendar accessDate;

    private JButton buttonSearch;
    private JButton buttonClose;

    private JTable tableResults;

    public SearchPanel() {
        setting();
        init();
    }

    private void setting() {
        setLayout(new BorderLayout());
    }

    private void init() {

        JPanel searchControls = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        //Search looking for a path
        labelPath = new JLabel("Path:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(5, 5, 0, 5);
        searchControls.add(labelPath, constraints);
        textFieldPath = new JTextField();
        textFieldPath.setSize(150, 20);
        constraints.insets = new Insets(2, 0, 0, 5);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(textFieldPath, constraints);

        //Path Browse button
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
        constraints.gridwidth = 1;
        constraints.gridx = 2;
        constraints.gridy = 0;
        searchControls.add(buttonBrowse, constraints);

        //Search files by File Name
        labelFilename = new JLabel("Filename:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        searchControls.add(labelFilename, constraints);
        filename = new JTextField();
        filename.setPreferredSize(new Dimension(150, 20));
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(filename, constraints);

        //Search files by Size
        labelSize = new JLabel("Size:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        searchControls.add(labelSize, constraints);
        optionSize = new JComboBox();
        optionSize.addItem("<");
        optionSize.addItem(">");
        optionSize.addItem("==");
        optionSize.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionSize, constraints);
        size = new JTextField();
        size.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(size, constraints);

        // Search files by extension
        labelExtension = new JLabel("Extension");
        labelExtension.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelExtension, constraints);
        optionExtension = new JComboBox();
        optionExtension.addItem("Mb");
        optionExtension.addItem("Kb");
        optionExtension.addItem("Gb");
        optionExtension.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionExtension, constraints);
        extension = new JTextField();
        extension.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(extension, constraints);

        // Search read only files
        readOnly = new JCheckBox("Read Only", false);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(readOnly, constraints);

        // Search Hidden files
        hidden = new JCheckBox("Hidden", false);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(hidden, constraints);

        // Search files by Owner
        labelOwner = new JLabel("Owner");
        labelOwner.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelOwner, constraints);
        owner = new JTextField();
        owner.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(owner, constraints);

        // Search files by Content
        labelContent = new JLabel("Content");
        labelContent.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelContent, constraints);
        content = new JTextField();
        content.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(content, constraints);

        //Search files by type
        labelType = new JLabel("Type");
        labelType.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelType, constraints);
        type = new JComboBox();
        type.addItem("all");
        type.addItem("Folder");
        type.addItem("File");
        type.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 7;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(type, constraints);

        //Search files by Created Date
        labelCreateDate = new JLabel("Created Date");
        labelCreateDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelCreateDate, constraints);
        createDate = new JCalendar();
//        createDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(createDate, constraints);

        //Search files by Modified Date
        labelModifiedDate = new JLabel("Modified Date");
        labelModifiedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelModifiedDate, constraints);
        modifiedDate = new JCalendar();
//        modifiedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(modifiedDate, constraints);

        //Search files by Accessed Date
        labelAccessedDate = new JLabel("Access Date");
        labelAccessedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelAccessedDate, constraints);
        accessDate = new JCalendar();
//        accessDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(accessDate, constraints);

        //General Browse button
        buttonSearch = new JButton("Search");
        buttonSearch.setPreferredSize(new Dimension(50, 20));
        constraints.gridx = 1;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(buttonSearch, constraints);

        //Close button to finish searching
        buttonClose = new JButton("Close");
        buttonClose.setPreferredSize(new Dimension(70, 20));
        buttonClose.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                }
        );
        constraints.gridx = 2;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(buttonClose, constraints);

        //Table to have search results
        JPanel north = new JPanel(new FlowLayout());
        north.add(searchControls);
        north.setPreferredSize(new Dimension(300, 600));
        add(north, BorderLayout.NORTH);

        String[][] data = {{"", ""}, {"", ""}};
        String[] columns = {"Path", "File Name", "Size", "Extension", "Read Only", "Hidden", "Owner", "Content", "Type", "Created Date", "Modified Date", "Accessed Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        tableResults = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tableResults);

        add(scrollPane, BorderLayout.CENTER);
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

    public String getOptionSize() {
        return optionSize.getSelectedItem().toString();
    }

    public String getSizeTextField() {
        return size.getText();
    }

    public String getExtension() {
        return extension.getText();
    }

    public Boolean getReadOnly() {
        return readOnly.isSelected();
    }

    public Boolean getHidden() {
        return hidden.isSelected();
    }

    public String getOwner() {
        return owner.getText();
    }

    public String getContent() {
        return content.getText();
    }

    public String getType() {
        return type.getSelectedItem().toString();
    }

    public Date getCreateDate() {
        return createDate.getDate();
    }

    public Date getModifiedDate() {
        return modifiedDate.getDate();
    }

    public Date getAccessedDate() {
        return accessDate.getDate();
    }

    public String getOptionExtension() {
        return optionExtension.getSelectedItem().toString();
    }
}
