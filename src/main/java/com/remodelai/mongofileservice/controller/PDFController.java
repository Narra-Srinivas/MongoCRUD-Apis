package com.remodelai.mongofileservice.controller;

import com.remodelai.mongofileservice.model.PdfFile;
import com.remodelai.mongofileservice.repo.PdfFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api/pdf")
public class PDFController {

    @Autowired
    private PdfFileRepository pdfFileRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPdf(@RequestParam("file") MultipartFile file) {
        log.info("Processing request to upload the file: " + file.getOriginalFilename());
        try {
            // Create a new PdfFile instance
            PdfFile pdfFile = new PdfFile();
            pdfFile.setFilename(file.getOriginalFilename());
            pdfFile.setContent(file.getBytes());

            // Save the PdfFile to the database
            pdfFileRepository.save(pdfFile);

            // Log success message
            log.info("File uploaded successfully: {}", file.getOriginalFilename());

            // Return success response
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (IOException e) {
            // Log error message
            log.error("Error occurred while uploading file: {}", e.getMessage());

            // Return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error occurred while uploading file. Please try again.");
        }
    }
}
