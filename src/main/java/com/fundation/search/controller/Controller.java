package com.fundation.search.controller;

import com.fundation.search.model.Folder;
import com.fundation.search.model.Search;
import com.fundation.search.model.StorageUnit;
import com.fundation.search.view.SearchPanel;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    View view = new View();
    Search search;


    public Controller() {
        search = new Search(view.getPanel().getPath(), view.getPanel().getFilename());
    }

    public void showResults() {
        String[][] data = {{"Hello", "World"}, {"Hello", "World"}};
        String[] columns = {"Name", "Type", "Owner", "Create Date", "Modified Date"};

        DefaultTableModel model = new DefaultTableModel(data, columns);

        JScrollPane scrollPane = new JScrollPane(tableResults);
        tableResults.setFillsViewportHeight(true);


        tableResults = new JTable(model);
        add(tableResults.getTableHeader(), BorderLayout.CENTER);
        add(tableResults, BorderLayout.CENTER);

        String[][] dataToShow = {{"sdfs", "sda"}, {"asdfsd", "World"}};

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setResults(dataToShow);
            }
        });

        DefaultTableModel modelset = (DefaultTableModel) tableResults.getModel();
        model.addRow(data[0]);
    }

    
}
