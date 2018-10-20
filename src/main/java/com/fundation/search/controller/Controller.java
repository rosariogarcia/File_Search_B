package com.fundation.search.controller;

import com.fundation.search.model.Search;
import com.fundation.search.model.StorageUnit;
import com.fundation.search.view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller implements ActionListener {
    View view;
    Search search;

    public Controller() throws IOException {
        view = new View();
        search = new Search();
        List<StorageUnit> itemsList = new ArrayList<StorageUnit>();
        itemsList = search.searchItems("D:/me/cursos/devfund/repository/File_Search_B/src", "F", null);
    }

    public void showResults() {
//        String[][] data = {{"Hello", "World"}, {"Hello", "World"}};
//        String[] columns = {"Name", "Type", "Owner", "Create Date", "Modified Date"};
//
//        DefaultTableModel model = new DefaultTableModel(data, columns);
//
//        JScrollPane scrollPane = new JScrollPane(tableResults);
//        tableResults.setFillsViewportHeight(true);
//
//
//        tableResults = new JTable(model);
//        add(tableResults.getTableHeader(), BorderLayout.CENTER);
//        add(tableResults, BorderLayout.CENTER);
//
//        String[][] dataToShow = {{"sdfs", "sda"}, {"asdfsd", "World"}};
//
//        searchButton = new JButton("Search");
//        searchButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                setResults(dataToShow);
//            }
//        });
//
//        DefaultTableModel modelset = (DefaultTableModel) tableResults.getModel();
//        model.addRow(data[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.getPanel().getButton().addActionListener(e1 -> System.out.println("click!"));
    }
}
