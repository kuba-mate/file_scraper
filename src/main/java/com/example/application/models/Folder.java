package com.example.application.models;

public class Folder {
    private String name;
    private long totalSize;
    private int fileCount;

    public Folder(String name, long totalSize, int fileCount) {
        this.name = name;
        this.totalSize = totalSize;
        this.fileCount = fileCount;
    }

    public String getName() {
        return name;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public int getFileCount() {
        return fileCount;
    }

    public long getAverageSize(){
        if(fileCount == 0)
            return 0;
        else
            return totalSize/fileCount;
    }

}
