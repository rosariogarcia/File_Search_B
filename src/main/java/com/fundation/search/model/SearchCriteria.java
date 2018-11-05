package com.fundation.search.model;/*
 * @autor: mauricioramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 */

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SearchCriteria {
    protected String path;
    protected String searchText;
    protected String extension;
    protected Boolean hidden;
    protected String owner;
    protected Map<Integer, Date> createdDate;
    protected Map<Integer, Date> modifiedDate;
    protected Map<Integer, Date> accessDate;
    protected Map<Integer, Long> size;

    public SearchCriteria(String path) throws RuntimeException {
        if (path != null) {
            this.path = path;
        }
        else {
            throw new RuntimeException("A path is needed to create a SearchCriteria object");
        }
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setCreatedDate(String operator, Date createdDate) {
        this.createdDate = new HashMap<Integer, Date>();

        switch (operator){
            case "==":
                this.createdDate.put(0, createdDate);
                break;

            case "<":
                this.createdDate.put(1, createdDate);
                break;

            case ">":
                this.createdDate.put(2, createdDate);
                break;

            case "<=":
                this.createdDate.put(3, createdDate);
                break;

            case ">=":
                this.createdDate.put(4, createdDate);
                break;

            default:
                this.createdDate.put(0, createdDate);
        }
    }

    public void setModifiedDate(String operator, Date modifiedDate) {
        this.modifiedDate = new HashMap<Integer, Date>();

        switch (operator){
            case "==":
                this.modifiedDate.put(0, modifiedDate);
                break;

            case "<":
                this.modifiedDate.put(1, modifiedDate);
                break;

            case ">":
                this.modifiedDate.put(2, modifiedDate);
                break;

            case "<=":
                this.modifiedDate.put(3, modifiedDate);
                break;

            case ">=":
                this.modifiedDate.put(4, modifiedDate);
                break;

            default:
                this.modifiedDate.put(0, modifiedDate);
        }
    }

    public void setAccessDate(String operator, Date accessDate) {
        this.accessDate = new HashMap<Integer, Date>();

        switch (operator){
            case "==":
                this.accessDate.put(0, accessDate);
                break;

            case "<":
                this.accessDate.put(1, accessDate);
                break;

            case ">":
                this.accessDate.put(2, accessDate);
                break;

            case "<=":
                this.accessDate.put(3, accessDate);
                break;

            case ">=":
                this.accessDate.put(4, accessDate);
                break;

            default:
                this.accessDate.put(0, accessDate);
        }
    }

    public void setSize(String operator, Long size) {
        this.size = new HashMap<Integer, Long>();

        switch (operator){
            case "==":
                this.size.put(0, size);
                break;

            case "<":
                this.size.put(1, size);
                break;

            case ">":
                this.size.put(2, size);
                break;

            case "<=":
                this.size.put(3, size);
                break;

            case ">=":
                this.size.put(4, size);
                break;

            default:
                this.size.put(0, size);
        }
    }


    public String getSearchText() {
        return this.searchText;
    }

    public String getPath() {
        return this.path;
    }

    public Boolean getHidden() {
        return this.hidden;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getExtension() {
        return this.extension;
    }

    public Map<Integer, Date> getCreatedDate() {
        return this.createdDate;
    }

    public Map<Integer, Date> getModifiedDate() {
        return this.modifiedDate;
    }

    public Map<Integer, Date> getAccessDate() {
        return this.accessDate;
    }

    public Map<Integer, Long> getSize() {
        return this.size;
    }
}
