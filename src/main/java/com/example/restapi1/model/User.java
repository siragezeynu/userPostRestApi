package com.example.restapi1.model;

import com.sun.istack.NotNull;
import org.aspectj.bridge.Message;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
@Entity
public class User {
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    @NotEmpty(message="Name Field cannot Empty")
    @Size(min = 2)
    private String name;
    @OneToMany(mappedBy = "user")

    private Set<Posts> post;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Posts> getPost() {
        return post;
    }

    public void setPost(Set<Posts> posts) {
        this.post = posts;
    }
}
