package com.example.application.controller;

import com.example.application.Application;
import com.example.application.models.Folder;
import com.example.application.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@RestController
@RequestMapping("/api/folders")
public class FolderController {


    private final FolderService folderService;
    private final String mainFolderName = Application.argument0;

    @Autowired
    public FolderController(FolderService folderService){
        this.folderService = folderService;
    }

    @GetMapping("/total-size")
    public List<Folder> getTopKTotalSize() {
        PriorityQueue<Folder> queue = new PriorityQueue<>(Comparator.comparingLong(Folder::getTotalSize).reversed());
        return folderService.report(queue);
    }

    @GetMapping("/count")
    public List<Folder> getTopKCount(){
        PriorityQueue<Folder> queue = new PriorityQueue<>(Comparator.comparingLong(Folder::getFileCount).reversed());
        return folderService.report(queue);
    }

    @GetMapping("/average")
    public List<Folder> getTopKAverage(){
        PriorityQueue<Folder> queue = new PriorityQueue<>(Comparator.comparingLong(Folder::getAverageSize).reversed());
        return folderService.report(queue);
    }

    @GetMapping("/info")
    public String getInfo(){
        return folderService.generateReport(new File(mainFolderName));
    }

}