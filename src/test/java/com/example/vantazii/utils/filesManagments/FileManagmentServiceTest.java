package com.example.vantazii.utils.filesManagments;

import com.example.vantazii.fileManagment.FileManagmentService;
import com.google.common.io.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileManagmentServiceTest {



    FileManagmentService fileManagmentService;

    @BeforeEach
    public void init(){
        fileManagmentService = new FileManagmentService();
    }


    @Test
    void uploadFile() {
        String path = "mohammed.html";
        long time = System.currentTimeMillis() / 1000;
        String pathWithoutExtenstion = Files.getFileExtension(path);
        String fileName = time+"."+pathWithoutExtenstion;
        assertTrue(fileName.endsWith(".html"));
    }

    @Test
    void testGetFileName() {

        File file = fileManagmentService.getFileByName("mohammed.html");

        System.out.println(file.getPath());
        assertTrue(file.getPath().equals("uploads/mohammed.html"));
    }
}