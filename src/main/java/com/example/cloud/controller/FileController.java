package com.example.cloud.controller;

import com.example.cloud.entity.FileEntity;
import com.example.cloud.repository.FileRepository;
import com.example.cloud.service.AuthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private static final String STORAGE_PATH =
            System.getProperty("user.dir") + "/storage/";

    private final FileRepository fileRepository;
    private final AuthService authService;

    public FileController(FileRepository fileRepository,
                          AuthService authService) {
        this.fileRepository = fileRepository;
        this.authService = authService;
    }


    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestHeader("auth-token") String token) throws IOException {

        String user = authService.getLoginByToken(token);

        File dir = new File(STORAGE_PATH + user);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String path = STORAGE_PATH + user + "/" + file.getOriginalFilename();

        file.transferTo(new File(path));

        FileEntity entity = new FileEntity();
        entity.setFilename(file.getOriginalFilename());
        entity.setPath(path);
        entity.setOwner(user);

        fileRepository.save(entity);

        return "uploaded: " + file.getOriginalFilename();
    }


    @GetMapping
    public List<FileEntity> list(@RequestHeader("auth-token") String token) {

        String user = authService.getLoginByToken(token);

        return fileRepository.findByOwner(user);
    }


    @DeleteMapping
    public String delete(@RequestParam("id") Long id,
                         @RequestHeader("auth-token") String token) {

        String user = authService.getLoginByToken(token);

        FileEntity file = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        if (!file.getOwner().equals(user)) {
            return "Access denied";
        }

        new File(file.getPath()).delete();

        fileRepository.delete(file);

        return "deleted";
    }
}