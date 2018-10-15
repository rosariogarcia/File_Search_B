/*
 * @(#)AssetFile.java Copyright (c) 2018 Jalasoft.
 * 2643 Av Melchor Perez de Olguin, Colquiri Sud, Cochabamba, Bolivia.
 * All rights reserved. This software is the confidential and proprietary information of Jalasoft, ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 *
 *
 */

package com.fundation.search.model;
/*
 * @autor: mauricioramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
*/

import java.util.ArrayList;
import java.util.List;

public class Folder extends StorageUnit{

    protected List<File> files = new ArrayList<File>();
    protected List<Folder> subfolders = new ArrayList<Folder>();

    public void validate_path() {

    }
}
