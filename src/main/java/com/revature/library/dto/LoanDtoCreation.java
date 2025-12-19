package com.revature.library.dto;

import io.soabase.recordbuilder.core.RecordInterface;

@RecordInterface
public interface LoanDtoCreation {
    Long bookId();

    Long patronId();
}
