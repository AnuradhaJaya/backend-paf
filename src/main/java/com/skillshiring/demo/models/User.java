package com.skillshiring.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String avatar;
    private String cover;
    private String fname;
    //@Column(name = "my_name")
    private String lname;
    private String description;
    private String email;
    private String password;
    private String city;
    private String work;
    private String website;
    private List<Integer> followers=new ArrayList<>();
    private List<Integer> following=new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPosts;

    // Relations
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Post> posts;
//
//    @ManyToMany(mappedBy = "likes")
//    private List<Post> likedPosts;


    public User(){}

    public User(Integer id, String avatar, String cover, String fname, String lname, String description, String email, String password, String city, String work, String website, List<Integer> followers, List<Integer> following) {
        this.id = id;
        this.avatar = avatar;
        this.cover = cover;
        this.fname = fname;
        this.lname = lname;
        this.description = description;
        this.email = email;
        this.password = password;
        this.city = city;
        this.work = work;
        this.website = website;
        this.followers = followers;
        this.following = following;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public List<Integer> getFollowing() {
        return following;
    }

    public void setFollowing(List<Integer> following) {
        this.following = following;
    }

    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }
}
