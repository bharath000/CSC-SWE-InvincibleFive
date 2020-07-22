package com.backend.backend.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name= "bounty")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private  String user_role;

    @NotBlank
    @Size(max=6)
    private  Long zipcode;

    public Users(){
        super();
    }

    public Users( @NotBlank String username, @NotBlank String password, @NotBlank String user_role, String email, Long zipcode) {
        super();
      //this.id = id;
        this.username = username;
        this.password = password;
        this.user_role = user_role;
        this.email = email;
        this.zipcode =  zipcode;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setZipcode(Long zipcode) {
        this.zipcode = zipcode;
    }

    public Long getZipcode() {
        return zipcode;
    }

}
