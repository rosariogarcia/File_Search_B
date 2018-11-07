/*
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved. This software is the confidential and proprietary information of Jalasoft, ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 *
 *
 */

package com.fundation.search.model;
/**
 * @autor: maramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 **/

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class File extends StorageUnit {


    protected String extension;
    protected Boolean readOnly = false;
    protected List<String> keywords = new ArrayList<String>();

    /**
     * Method that returns the extension of a file
     * @return String with the extension of the file
     */
    public String getExtension() {
        return this.extension;
    }

    /**
     * Method that returns a list of keywords of a file
     * @return List of strings with the keywords of the file
     */
    public List<String> getKeywords() {
        return this.keywords;
    }

    /**
     * Method that returns the name of a file with the extension added to it
     * @return String name + extension
     */
    public String getName() {

        if (this.extension != null) {
            return this.name + '.' + this.extension;
        }

        return this.name;
    }

    /**
     * Method that sets the extension from the name of the file that has an extension
     */
    public void setExtension () {
        if (this.name != null) {
            this.extension = this.name.split("[.]")[1];
        }
    }

    /**
     * Method that sets the extension from a string received
     * @param extension
     */
    public void setExtension (String extension) {
        this.extension = extension;
    }

    /**
     * Method that sets the keywords for a file
     * @param keywords
     */
    public void setKeywords (List<String> keywords) {
        this.keywords = keywords;
    }

    public Boolean getReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }
}
