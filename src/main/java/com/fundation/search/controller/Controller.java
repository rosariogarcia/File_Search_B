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

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Date;
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
        view.getPanel().getButtonSearch().addActionListener((ActionEvent e1) -> {
            SearchCriteria criteria;
            try {

                String path = view.getPanel().getTextFieldPath();
                String filename = view.getPanel().getFilename();
                String optionSize = view.getPanel().getOptionSize();
                String size = view.getPanel().getSizeTextField();
                System.out.println(size);
                String typeSize = view.getPanel().getTypeSize();
                String extension = view.getPanel().getExtension();
                Boolean readOnly = view.getPanel().getReadOnly();
                Boolean hidden = view.getPanel().getHidden();
                String owner = view.getPanel().getOwner();
                String type = view.getPanel().getType();
                Date createdDate = view.getPanel().getCreatedDate();
                String optionCreatedDate = view.getPanel().getOptionCreatedDate();
                Date modifiedDate = view.getPanel().getModifiedDate();
                String optionModifiedDate = view.getPanel().getOptionModifiedDate();
                Date accessedDate = view.getPanel().getAccessedDate();
                String optionAccessedDate = view.getPanel().getOptionAccessedDate();

                if (Validator.validatePathNotNull(path)) {
                    criteria = new SearchCriteria(path);

                    if (type != "all")
                        criteria.setType(type);
                    criteria.setSearchText(filename);
//                    criteria.setExtension(view.getPanel().getExtension());

                    if (!size.isEmpty())
                        criteria.setSize(optionSize, Validator.getAppropiateSize(size, typeSize));
                    criteria.setHidden(hidden);
                    criteria.setOwner(owner);
                    criteria.setCreatedDate(optionCreatedDate, createdDate);
                    criteria.setModifiedDate(optionModifiedDate, modifiedDate);
                    criteria.setAccessDate(optionAccessedDate, accessedDate);

                    List<StorageUnit> itemsList = search.searchItems(criteria, null);
                    showResults(itemsList);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid value "+ e.getMessage());
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
                    "",
                    "",
                    "",
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
