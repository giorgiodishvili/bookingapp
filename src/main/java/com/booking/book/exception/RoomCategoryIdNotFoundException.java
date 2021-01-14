package com.booking.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Room Category not found")
public class RoomCategoryIdNotFoundException extends RuntimeException {
}
