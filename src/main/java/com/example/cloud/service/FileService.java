package com.example.cloud.service;

import com.example.cloud.entity.FileEntity;
import com.example.cloud.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public void save(FileEntity file) {
        fileRepository.save(file);
    }

    public List<FileEntity> getUserFiles(String user) {
        return fileRepository.findByOwner(user);
    }

    public FileEntity getById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public void delete(FileEntity file) {
        fileRepository.delete(file);
    }
}
