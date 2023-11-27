package com.example.application.service;

import com.example.application.Application;
import com.example.application.models.Folder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

@Service
public class FolderService {

    private static boolean first = true;
    private final String mainFolderName = Application.argument0;
    private final int K = Application.argument1;

    public List<Folder> report(PriorityQueue<Folder> queue){
        calculateFolderSizeAndCount(new File(mainFolderName), queue);

        List<Folder> topKFolders = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            topKFolders.add(queue.poll());
        }
        return topKFolders;
    }


    public String generateReport(File folder) {
        StringBuilder sb = new StringBuilder();
        String type1 = " which has ";
        String type2 = " files";

        File[] files = folder.listFiles();
        int fileCount = 0;
        for (File file : Objects.requireNonNull(files)) {
            if (file.isFile()) {
                fileCount++;
            }
        }

        if(fileCount == 1){
            type2 = " file";
        }

        if(first){
            type1 = " has ";
            first = false;
        }

        sb.append("folder ").append(folder.getName()).append(type1).append(fileCount).append(type2);

        boolean first = true;
        for (File file : files) {
            if (file.isDirectory()) {
                if (first) {
                    sb.append(" and contains ");
                    first = false;
                } else {
                    sb.append(" and ");
                }
                sb.append(generateReport(file));
            }
        }

        return sb.toString();
    }

    public long calculateFolderSizeAndCount(File folder, PriorityQueue<Folder> queue) {
        long size = 0;
        int count = 0;

        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    size += file.length();
                    count++;
                } else if (file.isDirectory()) {
                    size += calculateFolderSizeAndCount(file, queue);
                    count += Objects.requireNonNull(file.listFiles()).length;
                }
            }
        }
        queue.add(new Folder(folder.getAbsolutePath(),size, count));

        return size;
    }

}
