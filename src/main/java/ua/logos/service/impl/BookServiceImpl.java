package ua.logos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.logos.domain.BookDTO;
import ua.logos.entity.BookEntity;
import ua.logos.exception.ResourceNotFoundException;
import ua.logos.repository.BookRepository;
import ua.logos.service.BookService;
import ua.logos.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapperUtils bookMapper;

    @Override
    public void saveBook(BookDTO dto) {
        BookEntity entity = bookMapper.map(dto, BookEntity.class);
        bookRepository.save(entity);
    }

    @Override
    public BookDTO findBookById(Long id) {
        BookEntity entity = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record with id[" + id + "] not found")
        );

        BookDTO dto = bookMapper.map(entity,BookDTO.class);
        return dto;
    }

    @Override
    public List<BookDTO> findAllBooks() {
        List<BookEntity> entities = bookRepository.findAll();
        List<BookDTO> dtos = bookMapper.mapAll(entities, BookDTO.class);
        return dtos;
    }

    @Override
    public void addImageToBook(Long id, String fileName) {
        BookEntity book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record with id[" + id + "] not found")
        );
        book.setImage(fileName);
        bookRepository.save(book);
    }

    @Override
    public BookDTO findBookByName(String name) {
        return null;
    }

    @Override
    public BookDTO findBookByAuthor(String name) {
        return null;
    }

    @Override
    public void deleteBook(Long id) {
        BookEntity entity = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Record with id [" + id + "] not found")
        );
        if(entity != null) {
            bookRepository.deleteById(id);
        }
    }
    

    @Override
    public BookDTO changeAuthor(Long id) {
        return null;
    }

    @Override
    public BookDTO changeName(Long id) {
        return null;
    }

    @Override
    public BookDTO changeShortInfo(Long id) {
        return null;
    }

    @Override
    public BookDTO changeLanguage(Long id) {
        return null;
    }

    @Override
    public BookDTO changeDate(Long id) {
        return null;
    }

    @Override
    public BookDTO changeQuantity(Long id) {
        return null;
    }

    @Override
    public BookDTO changePrice(Long id) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
    BookEntity entity = bookRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Record with id[" + id + "] not found")
    );
        if(entity != null) {
            bookRepository.deleteById(id);
        }
    }
}
