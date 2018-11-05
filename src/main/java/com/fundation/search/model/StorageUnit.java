/*
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved. This software is the confidential and proprietary information of Jalasoft, ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 *
 *
 */

package com.fundation.search.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Date;

/**
 * @autor: maramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 **/
public class StorageUnit {

    protected String name;
    protected Long size;
    protected Path path;
    protected String type;
    protected String owner;
    protected Date createdAt;
    protected Date updatedAt;
    protected Date accessedAt;
    protected Boolean hidden;

    public StorageUnit () {

    }

    public void setName (String name) {
        this.name = name;
    }

    public void setSize () throws IOException {
        this.size = Files.size(this.path);
    }

    public void setSize (Long size) throws IOException {
        this.size = size;
    }

    public void setPath (String path) {
        this.path = Paths.get(path);
    }

    public void setType (String type) {
        this.type = type;
    }

    public void setOwner (String owner) {
        this.owner = owner;
    }

    public void setCreatedAt (Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt (Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAccessedAt(Date accessedAt) {
        this.accessedAt = accessedAt;
    }

    public void setHidden (Boolean hidden) {
        this.hidden = hidden;
    }


    public String getName () {
        return this.name;
    }

    public Long getSize () {
        return this.size;
    }

    public Path getPath () {
        return this.path;
    }

    public String getType () {
        return this.type;
    }

    public String getOwner () {
        return this.owner;
    }

    public Date getCreatedAt () {
        return this.createdAt;
    }

    public Date getUpdatedAt () {
        return this.updatedAt;
    }

    public Date getAccessedAt() {
        return this.accessedAt;
    }

    public Boolean getHidden () {
        return this.hidden;
    }

}
