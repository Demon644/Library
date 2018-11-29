package ua.logos.service;

import ua.logos.domain.BookDTO;

import java.util.List;

public interface BookService {

    void saveBook(BookDTO dto);

    BookDTO findBookById(Long id);

    BookDTO findBookByName(String name);

    BookDTO findBookByAuthor(String name);

    void deleteBook(Long id);

    BookDTO changeAuthor(Long id);

    BookDTO changeName(Long id);

    BookDTO changeShortInfo(Long id);

    BookDTO changeLanguage(Long id);

    BookDTO changeDate(Long id);

    BookDTO changeQuantity(Long id);

    BookDTO changePrice(Long id);

    List<BookDTO> findAllBooks();

    void deleteBookById(Long id);

    void addImageToBook (Long id, String fileName);

}
