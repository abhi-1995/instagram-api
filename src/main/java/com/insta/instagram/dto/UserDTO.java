package com.insta.instagram.dto;

import java.util.Objects;

public class UserDTO {

    private Integer id;
    private String userName;
    private String email;
    private String name;
    private String userImage;

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public UserDTO(Integer id, String userName, String email, String name, String userImage) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.name = name;
        this.userImage = userImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO userDTO)) return false;
        return getId().equals(userDTO.getId()) && getUserName().equals(userDTO.getUserName()) && getEmail().equals(userDTO.getEmail()) && getName().equals(userDTO.getName()) && getUserImage().equals(userDTO.getUserImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getEmail(), getName(), getUserImage());
    }
}
