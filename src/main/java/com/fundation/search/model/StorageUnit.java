/*
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved. This software is the confidential and proprietary information of Jalasoft, ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 *
 *
 */

package com.fundation.search.model;

public abstract class StorageUnit {
    protected String name;
    protected String size;
    protected String path;
    protected String type;
    protected String owner;
    protected String created_at;
    protected String updated_at;

    public void set_name (String name) {
        this.name = name;
    }

    public void set_size (String size) {
        this.size = size;
    }

    public void set_path (String path) {
        this.path = path;
    }

    public void set_type (String type) {
        this.type = type;
    }

    public void set_owner (String owner) {
        this.owner = owner;
    }

    public void set_created_at (String created_at) {
        this.created_at = created_at;
    }

    public void set_updated_at (String updated_at) {
        this.updated_at = updated_at;
    }


    public String get_name () {
        return this.name;
    }

    public String get_size () {
        return this.size;
    }

    public String get_path () {
        return this.path;
    }

    public String get_type () {
        return this.type;
    }

    public String get_owner () {
        return this.owner;
    }

    public String get_created_at () {
        return this.created_at;
    }

    public String get_updated_at () {
        return this.updated_at;
    }

}
