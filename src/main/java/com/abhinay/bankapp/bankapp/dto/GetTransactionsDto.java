package com.abhinay.bankapp.bankapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTransactionsDto {
    private String accountNumber;
    private String filter; // sent, received, all
    private int page;
    private int size;
}
