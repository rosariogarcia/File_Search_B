package com.fundation.search.controller;

import com.fundation.search.model.Search;
import com.fundation.search.model.StorageUnit;
import com.fundation.search.view.View;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

public class Controller {
    View view;
    Search search;

    public Controller() throws IOException {
        view = new View();
        search = new Search();
        view.getPanel().getButtonSearch().addActionListener(e1 -> {
            try {
                String path = view.getPanel().getTextFieldPath();
                String filename = view.getPanel().getFilename();
                List<StorageUnit> itemsList = search.searchItems(path, filename, null);
                showResults(itemsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showResults(List<StorageUnit> itemsList) {

        DefaultTableModel model = (DefaultTableModel) view.getPanel().getTableResults().getModel();
        model.setRowCount(0);
        for (StorageUnit item : itemsList) {
            String[] data = {item.getName(), item.getType(), item.getOwner(), item.getCreatedAt(), item.getUpdatedAt()};
            model.addRow(data);
        }
    }
}
