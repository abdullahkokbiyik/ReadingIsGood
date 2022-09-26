package com.getir.readingisgood.book.controller;

import com.getir.readingisgood.book.controller.dto.AddBookDTO;
import com.getir.readingisgood.book.controller.dto.GetBookDetailDTO;
import com.getir.readingisgood.book.controller.dto.UpdateStockDTO;
import com.getir.readingisgood.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/add")
    public void add(@RequestBody AddBookDTO addBookDTO) {
        bookService.add(addBookDTO.convertToDomainObject());
    }

    @PostMapping(value = "/updateStockAmount")
    public void updateStockAmount(@RequestBody UpdateStockDTO updateStockDTO) {
        bookService.updateStockAmount(updateStockDTO.convertToDomainObject());
    }

    @GetMapping(value = "/getBookDetail")
    public GetBookDetailDTO getBookDetail(@RequestParam("bookId") Long bookId) {
        return new GetBookDetailDTO(bookService.getBookById(bookId));
    }
}
