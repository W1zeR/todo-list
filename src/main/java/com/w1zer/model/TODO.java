package com.w1zer.model;

import java.util.Date;
import java.util.Objects;

public class TODO {
    private long id;
    private String comment;
    private Date created;
    private Date shouldBeDoneBefore;
    private long userId;
    private TODOStatus status;

    public TODO(long id, String comment, Date created, Date shouldBeDoneBefore, long userId, TODOStatus status) {
        this.id = id;
        this.comment = comment;
        this.created = created;
        this.shouldBeDoneBefore = shouldBeDoneBefore;
        this.userId = userId;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getShouldBeDoneBefore() {
        return shouldBeDoneBefore;
    }

    public void setShouldBeDoneBefore(Date shouldBeDoneBefore) {
        this.shouldBeDoneBefore = shouldBeDoneBefore;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public TODOStatus getStatus() {
        return status;
    }

    public void setStatus(TODOStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TODO todo = (TODO) o;
        return id == todo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
