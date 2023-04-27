package com.librarymanagement.entity;

import lombok.Data;

@Data
public class BookAddDTO {
    private String bookName;
    private String bookAuthor;
    private String bookPublish;
    private String bookField;

}
