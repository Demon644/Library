package ua.logos.service;

import org.springframework.data.domain.Pageable;
import ua.logos.domain.AuthorDTO;

import java.util.List;

public interface AuthorService {
    void saveAuthor(AuthorDTO dto);

    AuthorDTO findAuthorById(Long id);

    List<AuthorDTO> findAllAuthors();

    void deleteAuthorById(Long id);

    List<AuthorDTO> findAuthorsByPage(Pageable pageable);

    void addImageToAuthor (Long id, String fileName);
}
