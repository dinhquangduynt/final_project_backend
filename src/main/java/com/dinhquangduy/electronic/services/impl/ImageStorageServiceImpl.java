package com.dinhquangduy.electronic.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dinhquangduy.electronic.services.ImageStorageService;

@Service
public class ImageStorageServiceImpl implements ImageStorageService {
    private Path dir = Paths.get("uploads");

    @Override
    public void init() throws IOException {
        if (!Files.exists(dir)) {
            Files.createDirectory(dir);
        }
    }

    @Override
    public String save(MultipartFile file) throws IOException {
        long currentTime = System.currentTimeMillis();
        String fileName = file.getOriginalFilename().split("\\.")[0];
        String extension = "." + file.getOriginalFilename().split("\\.")[1];
        String fileStorePath = fileName + currentTime + extension;
        Files.copy(file.getInputStream(), dir.resolve(fileStorePath));
        return fileStorePath;
    }

    @Override
    public Resource load(String filename) throws IOException {
        Path file = dir.resolve(filename);
        Resource resource = new UrlResource(file.toUri());
        return resource;
    }

    @Override
    public void deleteAll() throws IOException {
        FileSystemUtils.deleteRecursively(dir.toFile());
    }

    @Override
    public void delete(String filename) throws IOException {
        Files.deleteIfExists(Paths.get("uploads/" + filename));

    }

    @Override
    public Stream<Path> loadAll() throws IOException {
        return Files.walk(dir, 1).filter(path -> !path.equals(dir)).map(dir::relativize);
    }

}
