package com.fundation.search.model;/*
 * @autor: mauricioramirez
 * Project: File_Search_B
 * Package: com.fundation.search.model
 * Version: 1.0
 */

import java.io.IOException;
import java.util.List;

public interface ISearch {

    /**
     * Search items by a criteria object and a limit of max items return
     * @param criteria
     * @param limit
     * @return a Li
     * @throws IOException
     */
    List<StorageUnit> searchItems(SearchCriteria criteria, Integer limit) throws IOException;
}
