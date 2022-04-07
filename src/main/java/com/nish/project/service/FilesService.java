package com.nish.project.service;

import com.nish.project.exception.FileStorageException;
import com.nish.project.model.files.Files;
import com.nish.project.repository.files.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Service
public class FilesService {

    @Autowired
    private FilesRepository filesRepository;

    public Files storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Files dbFile = new Files(fileName, file.getContentType(), file.getBytes());

            return filesRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Files storeFile2(MultipartFile file, int id) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Files dbFile = new Files(id,fileName, file.getContentType(), file.getBytes());

            return filesRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Files getFile(int fileId) throws FileNotFoundException {
        return filesRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
    public void deleteFile(int fileId) {
        filesRepository.deleteById(fileId);
    }

    public List<Files> listAll() {
        return filesRepository.findAll();
    }

    public void save(Files databaseFile) {
        filesRepository.save(databaseFile);

    }
    public void update(Files databaseFile, int id) {
        deleteFile(id);
        databaseFile.setId(id);
        filesRepository.save(databaseFile);

    }
}
