package com.booking.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order can not be Added")
public class OrderCanNotBeAddedException extends RuntimeException {
}
