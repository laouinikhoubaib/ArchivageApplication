package com.spring.archivageapplication.Controller;


import com.spring.archivageapplication.Dto.ResponseFile;
import com.spring.archivageapplication.Dto.ResponseMessage;
import com.spring.archivageapplication.Models.Complaint;
import com.spring.archivageapplication.Models.File;
import com.spring.archivageapplication.Models.User;
import com.spring.archivageapplication.Repository.FileDBRepository;
import com.spring.archivageapplication.Repository.userRepository;
import com.spring.archivageapplication.Service.Complaint.ComplaintService;
import com.spring.archivageapplication.Service.Files.FilesStorageService;
import com.spring.archivageapplication.Service.User.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserContoller {

    @Autowired
    ComplaintService ComplaintService;

    @Autowired
    private FilesStorageService storageService;

    @Autowired
    private FileDBRepository fr;
    userService us;


    @PutMapping({"/update/{id}"})
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        us.updateUser(id, user);
        return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/AddComplaint")
    @ResponseBody
    public Complaint addComplaint(@RequestBody Complaint T) {
        return ComplaintService.addComplaint(T);

    }

    @DeleteMapping("/deleteComplaint/{Complaint_id}")
    public void deleteComplaint(@PathVariable("Complaint_id") Integer id) {
        ComplaintService.deleteComplaint(id);

    }

    @PutMapping("/updateCompalaint/{Compalaint-id}")
    public void updateComplaint(@RequestBody Complaint newcomplaint, @PathVariable("Compalaint-id") int idComplaint) {
        ComplaintService.updateComplaint(newcomplaint, idComplaint);
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {

        String message = "";
        try {
            storageService.store(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @PostMapping("/affectatFileToUser/{idfile}/{id}")
    @ResponseBody
    public void affectatFileToUser(@PathVariable("idfile") int idfile,@PathVariable("id")int id) throws IOException {

        storageService.affectatFileToUser(idfile, id);
    }


    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {

        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .toUriString();
            return new ResponseFile(dbFile.getName(), fileDownloadUri, dbFile.getType(), dbFile.getData().length);}).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @GetMapping("/files/{idfile}")
    public ResponseEntity<byte[]> getFile(@PathVariable int idfile) {

        File fileDB = storageService.getFile(idfile);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
    @PutMapping({"/updatefile/{id}"})
    public ResponseEntity<File> updateFile(@PathVariable("id") int id, @RequestBody File file) {
        storageService.updateFile(id,file);
        return new ResponseEntity<>(storageService.getFile(id), HttpStatus.OK);
    }

    @DeleteMapping({"/deletefile/{id}"})
    public ResponseEntity<User> deleteFile(@PathVariable("id") int id) {
        storageService.deleteFile(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteAllFiles")
    public ResponseEntity<HttpStatus> deleteAllFiles() {
        try {
            fr.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/between")
    public ResponseEntity<?> findBetweenDate(@RequestParam String start, @RequestParam String end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime s = LocalDateTime.parse(start, formatter);
        LocalDateTime e = LocalDateTime.parse(end, formatter);

        return new ResponseEntity<>(fr.findBetweenDate(s, e), HttpStatus.OK);
    }

}
