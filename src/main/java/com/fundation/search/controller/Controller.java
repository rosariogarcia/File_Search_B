/*
 * @(#)Controller.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Jalasoft, ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 *
 */

package com.fundation.search.controller;

import com.fundation.search.model.Search;
import com.fundation.search.model.SearchCriteria;
import com.fundation.search.model.StorageUnit;
import com.fundation.search.view.View;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.List;

/**
 * Controller class integrate View and Model modules
 * Contains action listener button to perform searching
 *
 * @author Rosario Garcia
 */
public class Controller {
    /**
     * Represents View class from View Module
     */
    private View view;

    /**
     * Represents search class from Model module
     */
    private Search search;

    /**
     * Creates View and Search new instances
     * Performs search behavior
     *
     * @throws IOException
     */
    public Controller() throws IOException {
        view = new View();
        search = new Search();
        buttonEvent();
    }

    /**
     * Contains action listener to perform searching when click search button
     * <p>
     * Get path and filename parameters from View components and send it to searchItems method
     * from model module to get result of searching
     */
    private void buttonEvent() {
        view.getPanel().getButtonSearch().addActionListener(e1 -> {
            try {
                String path = view.getPanel().getTextFieldPath();
                String filename = view.getPanel().getFilename();

                SearchCriteria criteria = new SearchCriteria(path);
                System.out.println("Print all data from view");

                System.out.println("Path:" + view.getPanel().getTextFieldPath());
                System.out.println("filename:" + view.getPanel().getFilename());
                System.out.println("Size option:" + view.getPanel().getOptionSize());
                System.out.println("Size text:" + view.getPanel().getSizeTextField());
                System.out.println("Size type:" + view.getPanel().getOptionExtension());
                System.out.println("Extens:" + view.getPanel().getExtension());
                System.out.println("read:" + view.getPanel().getReadOnly());
                System.out.println("hidden:" + view.getPanel().getHidden());
                System.out.println("owner:" + view.getPanel().getOwner());
                System.out.println("type:" + view.getPanel().getType());
                System.out.println("create:" + view.getPanel().getCreateDate());
                System.out.println("modified:" + view.getPanel().getModifiedDate());
                System.out.println("update:" + view.getPanel().getAccessedDate());

//                criteria.setExtension(view.getPanel().getExtension());
//                criteria.setHidden(view.getPanel().getHidden());
//                criteria.setOwner(view.getPanel().getOwner());
//                criteria.setCreatedDate(view.getPanel().getOptionSize(), view.getPanel().getCreateDate());
//                criteria.setModifiedDate(view.getPanel().getOptionSize(), view.getPanel().getModifiedDate());
//                criteria.setAccessDate(view.getPanel().getOptionSize(), view.getPanel().getAccessedDate());
//                criteria.setSize(view.getPanel().getOptionSize(), Validator.getAppropiateSize(view.getPanel().getSizeTextField(), view.getPanel().getSizeTextField()));

                List<StorageUnit> itemsList = search.searchItems(criteria, null);
                showResults(itemsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * Sets table from View module to show results
     *
     * @param itemsList list    Contains data to show
     */
    public void showResults(List<StorageUnit> itemsList) {

        DefaultTableModel model = (DefaultTableModel) view.getPanel().getTableResults().getModel();
        model.setRowCount(0);

        for (StorageUnit item : itemsList) {
            String[] data = {
                    item.getPath().toString(),
                    item.getName(),
                    item.getSize().toString(),
                    item.getHidden().toString(),
                    item.getOwner(),
                    item.getType(),
                    item.getCreatedAt().toString(),
                    item.getUpdatedAt().toString(),
                    item.getAccessedAt().toString()};
            model.addRow(data);
        }
    }
}
