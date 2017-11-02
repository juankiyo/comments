package com.juan.nunez.model;

import java.io.Serializable;

public class CommentDTO implements Serializable {

    private static final long serialVersionUID = -1L;;

    private String name;
    private String comment;

    public CommentDTO() {}

    public CommentDTO(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString(){

        return "Message name = "+getName()+" ; Message Comment = "+getComment();

    }
}
