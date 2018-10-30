package com.fundation.search.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

/*
 * @autor: mauricioramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 */public class StorageUnitTest {

     StorageUnit asset;
     Path test_path;
     Path test_file;

    @Before
    public void setUp() throws Exception {
        this.asset = new StorageUnit();
        this.test_path = Paths.get("TrabajosLocal/stash/File_Search_B/src/test/java/com/fundation/search/");
        this.test_file =  Paths.get("/TrabajosLocal/stash/File_Search_B/src/test/java/com/fundation/search/model/test_file.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void setName() {
        String expected = "test_name";
        this.asset.setName("test_name");
        String actual = this.asset.getName();

        assertEquals(expected, actual);
    }

    @Test
    public void setSize() throws IOException {
        Long expected = new Long(19);
        this.asset.setSize(Files.size(this.test_file));
        Long actual = this.asset.getSize();

        assertEquals(expected, actual);
    }

    @Test
    public void setSize1() {
    }

    @Test
    public void setPath() {
    }

    @Test
    public void setType() {
    }

    @Test
    public void setOwner() {
    }

    @Test
    public void setCreatedAt() {
    }

    @Test
    public void setUpdatedAt() {
    }

    @Test
    public void setHidden() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getSize() {
    }

    @Test
    public void getPath() {
    }

    @Test
    public void getType() {
    }

    @Test
    public void getOwner() {
    }

    @Test
    public void getCreatedAt() {
    }

    @Test
    public void getUpdatedAt() {
    }

    @Test
    public void getHidden() {
    }
}