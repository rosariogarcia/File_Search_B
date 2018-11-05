package com.fundation.search.view;

import com.toedter.calendar.JDateChooser;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private JLabel labelType;
    private JComboBox type;

    private JLabel labelCreateDate;
    private JComboBox optionCreateDate;
    private JDateChooser dateChooser;

    private JLabel labelModifiedDate;
    private JComboBox optionModifiedDate;
    private JDateChooser dateChooserM;

    private JLabel labelAccessedDate;
    private JComboBox optionAccessedDate;
    private JDateChooser dateChooserA;

    private JButton buttonSearch;
    private JButton buttonClose;
    private JButton buttonClear;

    private JTable tableResults;

    public SearchPanel() {
        setting();
        init();
    }

    private void setting() {
        setLayout(new BorderLayout());
    }

    /**
     * Initialize components
     *
     * @return
     */
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
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionSize, constraints);
        optionExtension = new JComboBox();
        optionExtension.addItem("Mb");
        optionExtension.addItem("Kb");
        optionExtension.addItem("Gb");
        optionExtension.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionExtension, constraints);
        size = new JTextField();
        size.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
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
        extension = new JTextField();
        extension.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
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
        JDateChooser dateChooser = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
        dateChooser.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(dateChooser, constraints);
        optionCreateDate = new JComboBox();
        optionCreateDate.addItem("<");
        optionCreateDate.addItem(">");
        optionCreateDate.addItem("==");
        optionCreateDate.addItem(">=");
        optionCreateDate.addItem("=<");
        optionCreateDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 2;
        constraints.gridy = 8;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionCreateDate, constraints);

        //Search files by Modified Date
        labelModifiedDate = new JLabel("Modified Date");
        labelModifiedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelModifiedDate, constraints);
        JDateChooser dateChooserM = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
        dateChooserM.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(dateChooserM, constraints);
        optionModifiedDate = new JComboBox();
        optionModifiedDate.addItem("<");
        optionModifiedDate.addItem(">");
        optionModifiedDate.addItem("==");
        optionModifiedDate.addItem(">=");
        optionModifiedDate.addItem("=<");
        optionModifiedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 2;
        constraints.gridy = 9;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionModifiedDate, constraints);

        //Search files by Accessed Date
        labelAccessedDate = new JLabel("Access Date");
        labelAccessedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(labelAccessedDate, constraints);
        JDateChooser dateChooserA = new JDateChooser("yyyy/MM/dd", "####/##/##", '_');
        dateChooserA.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 1;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(dateChooserA, constraints);
        optionAccessedDate = new JComboBox();
        optionAccessedDate.addItem("<");
        optionAccessedDate.addItem(">");
        optionAccessedDate.addItem("==");
        optionAccessedDate.addItem(">=");
        optionAccessedDate.addItem("=<");
        optionAccessedDate.setPreferredSize(new Dimension(70, 20));
        constraints.gridx = 2;
        constraints.gridy = 10;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(optionAccessedDate, constraints);

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

        //Clear all the components for a new search
        buttonClear = new JButton("Clear");
        buttonClear.setPreferredSize(new Dimension(70, 20));
        buttonClear.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textFieldPath.setText("");
                filename.setText("");
                size.setText("");
                extension.setText("");
                owner.setText("");
                readOnly.setSelected(false);
                hidden.setSelected(false);
                dateChooser.setCalendar(null);
                dateChooserM.setCalendar(null);
                dateChooserA.setCalendar(null);
                
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 11;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        searchControls.add(buttonClear, constraints);

        //Table to have search results
        JPanel north = new JPanel(new FlowLayout());
        north.add(searchControls);
        north.setPreferredSize(new Dimension(300, 300));
        add(north, BorderLayout.NORTH);

        String[][] data = {{"", ""}, {"", ""}};
        String[] columns = {"Path", "File Name", "Size", "Extension", "Read Only", "Hidden", "Owner", "Content", "Type", "Created Date", "Modified Date", "Accessed Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        tableResults = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tableResults);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * return files by different options
     *
     * @return
     */
    public String getTextFieldPath() {
        return textFieldPath.getText();
    }

    public JButton getButtonSearch() {
        return buttonSearch;
    }

    public String getFilename() {
        return filename.getText();
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

    public String getType() {
        return type.getSelectedItem().toString();
    }

    public Date getCreatedDate() {
        return dateChooser.getDate();
    }

    public Date getModifiedDate() {
        return dateChooserM.getDate();
    }

    public Date getAccessedDate() {
        return dateChooserA.getDate();
    }

    public String getOptionCreatedDate() {
        return type.getSelectedItem().toString();
    }

    public String getOptionModifiedDate() {
        return type.getSelectedItem().toString();
    }

    public String getOptionAccessedDate() {
        return type.getSelectedItem().toString();
    }

    public String getOptionExtension() {
        return optionExtension.getSelectedItem().toString();
    }
}
