/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dji.jms.lab10.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.amu.dji.jms.lab10.books.model.Book;
import pl.edu.amu.dji.jms.lab10.books.repository.BookRepository;

/**
 *
 * @author Ozga
 */
@RestController
@RequestMapping(value = "/book")
public class BookResource {

    @Autowired
    private BookRepository books;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Book> getBooksList() {

        return books.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable String id) {

        return books.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createBook(@RequestBody Book book) {
        if (!books.exists(book.getIsbn()))  {
            books.save(book);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateBook(@PathVariable String id, @RequestBody Book book) {

        if (books.exists(id)) {
            books.save(book);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String id) {

        if (books.exists(id)) {
            books.delete(id);
        }
    }

}
