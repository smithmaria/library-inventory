package com.soit.libraryinventory;

import com.soit.libraryinventory.entity.Book;
import com.soit.libraryinventory.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/viewAddForm")
    public String viewAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/Books/book";
    }

    @GetMapping("/viewUpdateForm")
    public String viewUpdateForm(@RequestParam("bookId") int bookId, Model model) {
        Book book = bookService.findById(bookId);
        if (book == null) {
            return "redirect:/Books/book";
        }

        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/updateBook")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/Books/book";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam("bookId") int bookId) {
        bookService.deleteById(bookId);
        return "redirect:/Books/book";
    }

    @GetMapping("/book")
    public String listBooks(Model model) {

        List<Book> books = bookService.findAll();

        model.addAttribute("books", books);

        return "list-books";
    }
}

