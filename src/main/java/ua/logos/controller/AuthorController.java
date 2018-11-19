package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.logos.domain.AuthorDTO;
import ua.logos.domain.ErrorDTO;
import ua.logos.service.AuthorService;
import ua.logos.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AuthorDTO dto, BindingResult br) {

        if (br.hasErrors()) {
            System.out.println("Validation error");
            String errMsg = br.getFieldErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .findFirst().get().toString();

            ErrorDTO error = new ErrorDTO(errMsg);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        authorService.saveAuthor(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<AuthorDTO> dtos = authorService.findAllAuthors();
        return new ResponseEntity<List<AuthorDTO>>(dtos, HttpStatus.OK);
    }

    @GetMapping("{authorId}")
    public ResponseEntity<?> getById(@PathVariable("authorId") Long id) {
        AuthorDTO dto = authorService.findAuthorById(id);
        return new ResponseEntity<AuthorDTO> (dto, HttpStatus.OK);
    }

    @PostMapping("{authorId}/image")
    public ResponseEntity<Void> uploadImage(
            @PathVariable("authorId") Long id,
            @RequestParam("file") MultipartFile file) {

        System.out.println(file.getOriginalFilename());
        String fileName = fileStorageService.storeFile(file);
        authorService.addImageToAuthor(id, fileName);
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

    @DeleteMapping("{authorId}")
    public ResponseEntity<?> delete(@PathVariable("authorId") Long id) {
        authorService.deleteAuthorById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
