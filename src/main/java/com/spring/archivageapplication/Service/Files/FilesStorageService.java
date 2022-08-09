package com.spring.archivageapplication.Service.Files;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.spring.archivageapplication.Models.File;
import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.FileDBRepository;
import com.spring.archivageapplication.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FilesStorageService {



    @Autowired
    userRepository ur;

    @Autowired
    FileDBRepository fr;


    public  File store(MultipartFile file) throws IOException {

        String name = StringUtils.cleanPath(file.getOriginalFilename());
        File files = new File(name, file.getContentType(), file.getBytes());

        return fr.save(files);
    }

    public File getFile(int id) {
        return fr.findById(id).get();
    }

    public Stream<File> getAllFiles() {
        return fr.findAll().stream();
    }


    public void affectatFileToUser(int idfile, long id) {
        User user=ur.findById(id).get();
        File file=fr.findById(idfile).get();
        file.setUser(user);
        fr.save(file);

    }

    public List<File> findBetweenDate(LocalDateTime start, LocalDateTime end) {


        return fr.findBetweenDate(start, end).stream().sorted(Comparator.comparing(File::getIdfile))
                .collect(Collectors.toList());
    }

}