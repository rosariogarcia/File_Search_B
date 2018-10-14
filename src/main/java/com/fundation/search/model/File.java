/*
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved. This software is the confidential and proprietary information of Jalasoft, ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 *
 *
 */

package com.fundation.search.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor: maramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 **/
public class File extends StorageUnit {

    protected String extension;
    protected List<String> keywords = new ArrayList<String>();

    public String get_extension() {
        return this.extension;
    }

    public List<String> get_keywords() {
        return this.keywords;
    }


    public void set_extension (String extension) {
        this.extension = extension;
    }

    public void set_keywords (List<String> keywords) {
        this.keywords = keywords;
    }


    public static void main(String[] args) {
        System.out.print("test");
    }
}
