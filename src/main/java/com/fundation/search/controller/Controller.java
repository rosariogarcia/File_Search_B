package com.fundation.search.controller;

import com.fundation.search.model.Folder;
import com.fundation.search.model.Search;
import com.fundation.search.model.StorageUnit;
import com.fundation.search.view.SearchPanel;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    SearchPanel view = new SearchPanel();
    StorageUnit file = new Folder();
    StorageUnit folder = new Folder();
    Search search;


    public Controller() {
        List<String> searchCriteria = new ArrayList<>();
        searchCriteria.add(view.getPath());
        search = new Search(searchCriteria);
        search.path = view.getPath();
        search.searchText = view.getFileName();
    }

    public void showResults() {

    }

    
}
