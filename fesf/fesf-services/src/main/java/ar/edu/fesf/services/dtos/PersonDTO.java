package ar.edu.fesf.services.dtos;

import java.io.Serializable;

import ar.edu.fesf.model.Person;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String surname;

    private int age;

    private String address;

    private String phone;

    private String email;

    private String userid;

    private String password;

    public PersonDTO() {
        super();
    }

    public PersonDTO(final Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.age = person.getAge();
        this.address = person.getAddress();
        this.phone = person.getPhone();
        this.email = person.getEmail().getValue();
        this.userid = person.getUserInfo().getUserid();
        this.password = person.getUserInfo().getPass();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(final String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

}
