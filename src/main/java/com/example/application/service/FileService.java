package com.example.application.service;

import com.example.application.models.MyFile;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    private List<String> folders = new ArrayList<>();

    public MyFile calculateFileStats(File folder) {
        MyFile myFile = new MyFile();

        for(String f : folders){
            if(folder.getName().equals(f)){
                System.err.println("Error, infinite loop found in " + folder.getAbsolutePath());
                System.exit(0);
            }
        }
        folders.add(folder.getName());

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    myFile.combine(calculateFileStats(file));
                } else {
                    long size = file.length();
                    myFile.add(size);
                }
            }
        }

        return myFile;
    }

    public void setFoldersNull(){
        this.folders = new ArrayList<>();
    }

}
