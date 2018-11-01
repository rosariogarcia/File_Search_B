/*
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved. This software is the confidential and proprietary information of Jalasoft, ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 *
 *
 */

package com.fundation.search.model;/*
 * @autor: mauricioramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Search implements ISearch {
    protected List<StorageUnit> itemsList = new ArrayList<>();
    protected Path path;

    public List<StorageUnit> searchItems(SearchCriteria criteria, Integer limit) throws IOException {
        this.itemsList.clear();
        limit = limit == null ? 100 : limit;
        String searchText = criteria.getSearchText();
        String path = criteria.getPath();
        String searchString = (searchText == null || searchText.equals("")) ? "*" : searchText;

        this.path = Paths.get(path);

        if (Files.isDirectory(this.path)) {
            Files.walk(this.path)
                    .limit(limit)
                    .forEach(unitPath -> {

                        if (unitPath.getFileName()
                                .toString()
                                .toLowerCase()
                                .contains(searchString.toLowerCase()) || searchString.equals("*")) {

                            try {
                                StorageUnit item;

                                if (Files.isDirectory(unitPath)) {
                                    item = new Folder();
                                    item.setType("Folder");
                                } else {
                                    item = new File();
                                    item.setType("File");
                                    item.setSize(Files.size(unitPath));
                                    ((File) item).setExtension(unitPath.getFileName().toString().split("[.]")[1]);
                                }

                                item.setName(unitPath.getFileName().toString().split("[.]")[0]);
                                item.setOwner(Files.getOwner(unitPath).toString());
                                item.setCreatedAt(Files.getAttribute(unitPath, "creationTime").toString());
                                item.setUpdatedAt(Files.getLastModifiedTime(unitPath).toString());
                                item.setPath(unitPath.toString());
                                item.setHidden(Files.isHidden(unitPath));

                                itemsList.add(item);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        else {
            System.out.println("Not a valid path: " + this.path);
        }

        this.itemsList = filterByCriteria(criteria, this.itemsList);

        return this.itemsList;
    }

    public List<StorageUnit> filterByCriteria(SearchCriteria criteria, List<StorageUnit> itemsList) {

        List<StorageUnit> filteredList = itemsList;

        if (criteria.getExtension() != null && !criteria.getExtension().equals("")) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> "File".equals(item.getType()))
                    .filter(item -> criteria.getExtension().equals(((File) item).getExtension()))
                    .collect(Collectors.toList());
        }

        if (criteria.getHidden() != null) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> item.getHidden() == criteria.getHidden())
                    .collect(Collectors.toList());
        }

        if (criteria.getOwner() != null && !criteria.getOwner().equals("")) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> criteria.getOwner().equals(item.getOwner()))
                    .collect(Collectors.toList());
        }

        return filteredList;
    }

    public static void main(String[] args) throws IOException {
        List<StorageUnit> response;
        Search test = new Search();

        SearchCriteria criteria = new SearchCriteria("/TrabajosLocal/stash/File_Search_B/src/test/java/com/fundation/search");
//        criteria.setSearchText("f");
        criteria.setExtension("txt");

        response = test.searchItems(criteria, null);

        System.out.println(response.size());
    }
}
