package com.abhinay.bankapp.bankapp.dto;


public class PaginatedDto {
    private Object data;
    private int totalPages;
    private long totalElements;
    private int currentPage;

    public PaginatedDto() {
    }

    public PaginatedDto(Object data, int totalPages, long totalElements, int currentPage) {
        this.data = data;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPage = currentPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
