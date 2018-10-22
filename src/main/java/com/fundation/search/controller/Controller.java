package com.fundation.search.controller;

import com.fundation.search.model.Search;
import com.fundation.search.model.StorageUnit;
import com.fundation.search.view.View;

import javax.swing.table.DefaultTableModel;
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
        view.getPanel().getButtonSearch().addActionListener(e1 -> {
            String path = view.getPanel().getTextFieldPath();
            System.out.println("path" + path);
            String filename = view.getPanel().getFilename();
            System.out.println("filename:" + filename);
            System.out.println("click!");
            List<StorageUnit> itemsList = new ArrayList<StorageUnit>();
            try {
                itemsList = search.searchItems("D:/me/cursos/devfund/repository/File_Search_B/src", "F", null);
//                itemsList = search.searchItems(path, filename, null);
                System.out.println(itemsList);
                showResults(itemsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showResults(List<StorageUnit> itemsList) {
        DefaultTableModel modelset = (DefaultTableModel) view.getPanel().getTableResults().getModel();
        modelset.addRow(itemsList.toArray());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
