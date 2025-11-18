package com.abhinay.bankapp.bankapp.entity;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    @NotNull(message = "Username cannot be null")
    private String username;

    @Column(name = "phone_number", nullable = false,unique = true)
    @NotNull(message = "Phone Number cannot be null")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "delete_flag", nullable = false)
    @NotNull(message = "Delete flag must have a value")
    private String deleteFlag="N";

    public Users() {
    }

    public Users(long id, String deleteFlag, String username, String phoneNumber, String password) {
        this.id = id;
        this.deleteFlag = deleteFlag;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                '}';
    }
}
