package com.fundation.search.controller;

import javax.swing.*;

public class Validator {
    public static long getAppropiateSize(String size, String sizeOption) {
        Long bytes = null;
        switch (sizeOption) {
            case "Gb":
                bytes = Long.valueOf(size) * 1024 * 1024 * 1024;
                break;
            case "Mb":
                bytes = Long.valueOf(size) * 1024 * 1024;
                break;
            case "Kb":
                bytes = Long.valueOf(size) * 1024;
                break;
        }
        return bytes;
    }

    public static Boolean validatePathNotNull(String path) {
        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The Path es required");
            return false;
        }
        return true;
    }
}
