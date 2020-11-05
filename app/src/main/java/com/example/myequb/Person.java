package com.example.myequb;

public class Person {
private int personImage;

    public int getPersonImage() {
        return personImage;
    }

    public void setPersonImage(int personImage) {
        this.personImage = personImage;
    }

    private String Name;
    private String Phone_num;
    private String Email;
    private int Status;
    private int Remove;

    public int getRemove() {
        return Remove;
    }

    public void setRemove(int remove) {
        this.Remove = remove;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    private int Amount;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone_num() {
        return Phone_num;
    }

    public void setPhone_num(String phone_num) {
        Phone_num = phone_num;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Person(String name, String phone_num, String email,int personImage,int amount,int status,int remove) {
        this.personImage=personImage;
        Name = name;
        Phone_num = phone_num;
        Email = email;
        Remove = remove;
        Amount=amount;
        Status=status;
    }

    public Person(String name, String phone_num, String email,int amount,int status,int remove) {

        Name = name;
        Phone_num = phone_num;
        Email = email;
        Remove = remove;
        Amount=amount;
        Status=status;
    }
}
