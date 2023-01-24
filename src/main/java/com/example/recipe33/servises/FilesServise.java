package com.example.recipe33.servises;

public interface FilesServise {
    boolean saveToFile(String json, String dataFileName);

    String readFromFile(String dataFileName);
}
