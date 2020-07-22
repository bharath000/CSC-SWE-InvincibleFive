package com.backend.backend.model;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name= "profiles")
public class User_Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private Long userid;
    @NotBlank
    private String user_role;
    @NotBlank
    private  String email;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String organisation_name;
    @NotBlank
    private String phone_number;
    @NotBlank
    private String streat;
    @NotBlank
    private String apt_number;
    public User_Profile(){
        super();
    }


    public User_Profile(@NotBlank Long userid, @NotBlank String user_role, @NotBlank String email, @NotBlank String zipcode, @NotBlank String organisation_name, @NotBlank String phone_number, @NotBlank String streat, @NotBlank String apt_number) {
        super();
        this.userid = userid;
        this.user_role = user_role;
        this.email = email;
        this.zipcode = zipcode;
        this.organisation_name = organisation_name;
        this.phone_number = phone_number;
        this.streat = streat;
        this.apt_number = apt_number;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUser_id(Long user_id) {
        this.userid = user_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getOrganisation_name() {
        return organisation_name;
    }

    public void setOrganisation_name(String organisation_name) {
        this.organisation_name = organisation_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getStreat() {
        return streat;
    }

    public void setStret(String stret) {
        this.streat = stret;
    }

    public String getApt_number() {
        return apt_number;
    }

    public void setApt_number(String apt_number) {
        this.apt_number = apt_number;
    }
}
