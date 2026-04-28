package com.example.cloud.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String path;
    private String owner;

    public FileEntity() {}

    public Long getId() { return id; }

    public String getFilename() { return filename; }

    public String getPath() { return path; }

    public String getOwner() { return owner; }

    public void setId(Long id) { this.id = id; }

    public void setFilename(String filename) { this.filename = filename; }

    public void setPath(String path) { this.path = path; }

    public void setOwner(String owner) { this.owner = owner; }
}