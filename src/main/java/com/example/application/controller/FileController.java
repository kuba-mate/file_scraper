package com.example.application.controller;

import com.example.application.Application;
import com.example.application.models.MyFile;
import com.example.application.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/api")
public class FileController {

    private final String mainFolderName = Application.argument0;
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping("/file-stats")
    public MyFile getFileStats() {
        fileService.setFoldersNull();
        return fileService.calculateFileStats(new File(mainFolderName));
    }

}
