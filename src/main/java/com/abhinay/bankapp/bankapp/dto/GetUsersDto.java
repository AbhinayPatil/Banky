package com.abhinay.bankapp.bankapp.dto;

public class GetUsersDto {
    private String id;
    private String username;
    public GetUsersDto() {
    }

    public GetUsersDto(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + username + '\'' +
                '}';
    }
}

