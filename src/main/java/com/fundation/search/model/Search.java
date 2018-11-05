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

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
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

                                String itemName = unitPath.getFileName().toString();

                                if (Files.isDirectory(unitPath)) {
                                    item = new Folder();
                                    item.setType("Folder");
                                    item.setName(itemName);
                                }
                                else {
                                    item = new File();
                                    item.setType("File");
                                    item.setSize(Files.size(unitPath));
                                    String[] splitName = itemName.split("[.]");

                                    if (itemName.charAt(0) == '.') {
                                        item.setName("." + itemName.split("[.]")[1]);

                                        if (splitName.length >= 3) {
                                            ((File) item).setExtension(unitPath.getFileName().toString().split("[.]")[2]);
                                        }
                                    }
                                    else {
                                        item.setName(itemName.split("[.]")[0]);

                                        if (splitName.length >= 2) {
                                            ((File) item).setExtension(unitPath.getFileName().toString().split("[.]")[1]);
                                        }
                                    }
                                }

                                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
                                FileTime createdAt = (FileTime) Files.getAttribute(unitPath, "creationTime");
                                FileTime accessedAt = (FileTime) Files.getAttribute(unitPath, "lastAccessTime");

                                item.setCreatedAt(formatter.parse(formatter.format(createdAt.toMillis())));
                                item.setAccessedAt(formatter.parse(formatter.format(accessedAt.toMillis())));
                                item.setUpdatedAt(formatter.parse(formatter.format(Files.getLastModifiedTime(unitPath).toMillis())));
                                item.setOwner(Files.getOwner(unitPath).toString());
                                item.setPath(unitPath.toString());
                                item.setHidden(Files.isHidden(unitPath));

                                itemsList.add(item);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            catch (ParseException e) {
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

        if (criteria.getCreatedDate() != null && criteria.getCreatedDate().size() > 0) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> this.compareDates(criteria.getCreatedDate(), item, "getCreatedAt"))
                    .collect(Collectors.toList());
        }

        if (criteria.getModifiedDate() != null && criteria.getModifiedDate().size() > 0) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> this.compareDates(criteria.getModifiedDate(), item, "getUpdatedAt"))
                    .collect(Collectors.toList());
        }

        if (criteria.getAccessDate() != null && criteria.getAccessDate().size() > 0) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> this.compareDates(criteria.getAccessDate(), item, "getAccessedAt"))
                    .collect(Collectors.toList());
        }

        if (criteria.getSize() != null && criteria.getSize().size() > 0) {
            filteredList = (List<StorageUnit>) filteredList.stream()
                    .filter(item -> this.compareLongs(criteria.getSize(), item, "getSize"))
                    .collect(Collectors.toList());
        }

        return filteredList;
    }

    public Boolean compareDates(Map<Integer, Date> criteria, StorageUnit item, String method) {

        Integer index = (int) criteria.keySet().toArray()[0];
        Object compareValue = new Object();

        try {
            compareValue = StorageUnit.class.getMethod(method).invoke(item);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        switch (index) {
            case 0:
                if (((Date) compareValue).compareTo(criteria.get(index)) == 0) {
                    return true;
                }
                break;

            case 1:
                if (((Date) compareValue).compareTo(criteria.get(index)) < 0) {
                    return true;
                }
                break;

            case 2:
                if (((Date) compareValue).compareTo(criteria.get(index)) > 0) {
                    return true;
                }
                break;

            case 3:
                if (((Date) compareValue).compareTo(criteria.get(index)) <= 0) {
                    return true;
                }
                break;

            case 4:
                if (((Date) compareValue).compareTo(criteria.get(index)) >= 0) {
                    return true;
                }
                break;
        }

        return false;
    }

    public Boolean compareLongs(Map<Integer, Long> criteria, StorageUnit item, String method) {

        Integer index = (int) criteria.keySet().toArray()[0];
        Object compareValue = new Object();

        try {
            compareValue = StorageUnit.class.getMethod(method).invoke(item);
        }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        if (compareValue == null) {
            compareValue = new Long(0);
        }

        switch (index) {
            case 0:
                if ((long) compareValue == criteria.get(index)) {
                    return true;
                }
                break;

            case 1:
                if ((long) compareValue < criteria.get(index)) {
                    return true;
                }
                break;

            case 2:
                if ((long) compareValue > criteria.get(index)) {
                    return true;
                }
                break;

            case 3:
                if ((long) compareValue <= criteria.get(index)) {
                    return true;
                }
                break;

            case 4:
                if ((long) compareValue >= criteria.get(index)) {
                    return true;
                }
                break;
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        List<StorageUnit> response;
        Search test = new Search();

        SearchCriteria criteria = new SearchCriteria("/TrabajosLocal/stash/File_Search_B/src/test/java/com/fundation/search");
        criteria.setSearchText("f");
        criteria.setExtension("txt");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse("2018-11-03");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        criteria.setAccessDate(">=", date);

        criteria.setSize(">", new Long(1000));
        response = test.searchItems(criteria, null);

        System.out.println(response.size());
    }
}
