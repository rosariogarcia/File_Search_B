package com.fundation.search.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/*
 * @autor: mauricioramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 */public class SearchTest {

     Search search;
     SearchCriteria criteria;
     List<StorageUnit> itemList;

    @Before
    public void setUp() throws Exception {
        this.search = new Search();
        this.criteria = new SearchCriteria("/TrabajosLocal/stash/File_Search_B/src/test/java/com/fundation/search");
    }

    @Test
    public void searchItemsByName() {
        this.criteria.setSearchText("Search");
        try {
            itemList = this.search.searchItems(criteria, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(2);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsByExtension() {
        this.criteria.setExtension("java");
        try {
            itemList = this.search.searchItems(criteria, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(3);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsByOwner() {
        this.criteria.setOwner("mauricioramirez");
        try {
            itemList = this.search.searchItems(criteria, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(10);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsByHidden() {
        this.criteria.setHidden(false);
        try {
            itemList = this.search.searchItems(criteria, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(9);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsBySize() {
        this.criteria.setSize(">", new Long(300));
        try {
            itemList = this.search.searchItems(criteria, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(3);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsByCreatedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = formatter.parse("2018-11-03");
            this.criteria.setCreatedDate("<", date);

            itemList = this.search.searchItems(criteria, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(6);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsByModifiedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = formatter.parse("2018-11-03");
            this.criteria.setModifiedDate("<", date);

            itemList = this.search.searchItems(criteria, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(5);

        assertEquals(expected, actual);
    }

    @Test
    public void searchItemsByAccessedDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date;

        try {
            date = formatter.parse("2018-11-03");
            this.criteria.setAccessDate("<", date);

            itemList = this.search.searchItems(criteria, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        Integer actual = itemList.size();
        Integer expected = new Integer(2);

        assertEquals(expected, actual);
    }
}