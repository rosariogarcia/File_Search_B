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

public class Search {
    protected List<StorageUnit> itemsList = new ArrayList<StorageUnit>();
    protected Path path;
    protected String searchText;

    public List<StorageUnit> searchItems(String path, String searchText, Integer limit) throws IOException {
        this.itemsList.clear();
        limit = limit == null ? 100 : limit;

        this.path = Paths.get(path);

        if (Files.isDirectory(this.path)) {
            Files.walk(this.path)
                    .limit(limit)
                    .forEach(unit -> {

                        if (unit.getFileName()
                                .toString()
                                .toLowerCase()
                                .contains(searchText.toLowerCase())) {

                            Path unitPath = unit;

                            try {
                                StorageUnit item = new StorageUnit();

                                if (Files.isDirectory(unitPath)) {
                                    item = new Folder();
                                    item.setType("Folder");
                                } else {
                                    item = new File();
                                    item.setType("File");
                                    item.setSize(Files.size(unitPath));
                                }


                                item.setName(unitPath.getFileName().toString());
                                item.setOwner(Files.getOwner(unitPath).toString());
                                item.setUpdatedAt(Files.getLastModifiedTime(unitPath).toString());
                                item.setPath(unitPath.toString());

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

        return this.itemsList;
    }

    public static void main(String[] args) throws IOException {
        Search test = new Search();

        test.searchItems("/TrabajosLocal/stash/File_Search_B/src/main/java/com/fundation/search", "stor", null);
    }
}
