package com.example.recipe33.servises;

import java.io.File;
import java.nio.file.Path;

public interface FilesServise {
    boolean saveToFile(String json);

    String readFromFile();


    File getDataFile();

    Path createTempFile(String suffix);


    boolean cleanDataFile();
}
