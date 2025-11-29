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

    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "email cannot be null")
    private String email;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "name cannot be null")
    private String name;

    @Column(name = "phone_number", nullable = false,unique = true)
    @NotNull(message = "Phone Number cannot be null")
    private String phoneNumber;

    @Column(name = "password", nullable = false)
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "delete_flag", nullable = false)
    @NotNull(message = "Delete flag must have a value")
    private String deleteFlag="N";

    @Column(name = "role", nullable = false)
    @NotNull(message = "Role must have a value")
    private String role="User";

    @Column(name = "account_number", nullable = false)
    @NotNull(message = "Account number must have a value")
    private String accountNumber;

    public Users() {
    }

    public Users(long id, String email, String name, String phoneNumber, String password, String deleteFlag, String role, String accountNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.deleteFlag = deleteFlag;
        this.role = role;
        this.accountNumber = accountNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", role='" + role + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
