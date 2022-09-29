package com.getir.readingisgood.book.controller;

import com.getir.readingisgood.book.controller.dto.AddBookDTO;
import com.getir.readingisgood.book.controller.dto.GetBookDetailDTO;
import com.getir.readingisgood.book.controller.dto.UpdateStockDTO;
import com.getir.readingisgood.book.service.BookService;
import com.getir.readingisgood.book.service.message.BookMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/add")
    public ResponseEntity<String> add(@Valid @RequestBody AddBookDTO addBookDTO) {
        bookService.add(addBookDTO.convertToDomainObject());
        return new ResponseEntity<>(BookMessages.ADD_BOOK_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @PostMapping(value = "/updateStockAmount")
    public ResponseEntity<String> updateStockAmount(@Valid @RequestBody UpdateStockDTO updateStockDTO) {
        bookService.updateStockAmount(updateStockDTO.convertToDomainObject());
        return new ResponseEntity<>(BookMessages.UPDATE_STOCK_SUCCESS_MESSAGE, HttpStatus.OK);
    }

    @GetMapping(value = "/getBookDetail")
    public GetBookDetailDTO getBookDetail(@RequestParam("bookId") @Min(1) Long bookId) {
        return bookService.getBookDetail(bookId);
    }
}
