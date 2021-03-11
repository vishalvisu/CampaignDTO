package BeanValidation;


import javax.validation.constraints.NotNull;

import javax.xml.transform.Source;

public class Member  {

   // private int id;


    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message= "Email is mandatory")
    private String email;

    @NotNull(message= "Biography is mandatory")
    private String biography;

    public Member(String name, String email, String biography) {
        this.name = name;
        this.email = email;
        this.biography = biography;
    }





    // Setters and Getters for name, email and biography

}