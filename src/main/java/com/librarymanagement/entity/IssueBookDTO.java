package com.librarymanagement.entity;

import lombok.Data;

@Data
public class IssueBookDTO {
    private String issueBookname;
    private String barrowerName;
    private String libId;
    private String dateOfissue;
}
