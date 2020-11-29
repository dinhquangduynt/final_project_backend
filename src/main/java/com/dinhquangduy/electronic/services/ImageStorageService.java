package com.dinhquangduy.electronic.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    
    void init()  throws IOException;

    String save(MultipartFile file) throws IOException;

    Resource load(String filename)  throws IOException;

    void deleteAll()  throws IOException;

    void delete(String filename)  throws IOException;

    Stream<Path> loadAll()  throws IOException;

}
