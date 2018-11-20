package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.logos.domain.UserDTO;
import ua.logos.domain.request.SigninRequest;
import ua.logos.domain.response.SigninResponse;
import ua.logos.service.FileStorageService;
import ua.logos.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("users") //auth controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserDTO> dtos = userService.findAllUsers();
        return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
    }

    @PostMapping("{userId}/image")
    public ResponseEntity<Void> uploadImage(
            @PathVariable("userId") Long id,
            @RequestParam("file") MultipartFile file) {

        System.out.println(file.getOriginalFilename());
        String fileName = fileStorageService.storeFile(file);
        userService.addImageToUser(id, fileName);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }

    @GetMapping("image/{fileName}")
    public ResponseEntity<Resource> getFile(
            @PathVariable("fileName") String fileName,
            HttpServletRequest request) {

        Resource resource = fileStorageService.loadFile(fileName);

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline: filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> delete(@PathVariable("userId") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
