package com.w1zer.model;

import java.util.Date;
import java.util.Objects;

public final class TODOBuilder {
    private long id;
    private String comment;
    private Date created;
    private Date shouldBeDoneBefore;
    private long userId;
    private TODOStatus status;

    private TODOBuilder() {
    }

    public TODO build() {
        return new TODO(
                id,
                comment,
                created,
                shouldBeDoneBefore,
                userId,
                status
        );
    }

    public static TODOBuilder newBuilder() {
        return new TODOBuilder();
    }

    public TODOBuilder withId(long id) {
        this.id = id;
        return this;
    }

    public TODOBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public TODOBuilder withCreated(Date created) {
        this.created = created;
        return this;
    }

    public TODOBuilder withShouldBeDoneBefore(Date shouldBeDoneBefore) {
        this.shouldBeDoneBefore = shouldBeDoneBefore;
        return this;
    }

    public TODOBuilder withUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public TODOBuilder withStatus(TODOStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TODOBuilder todoBuilder = (TODOBuilder) o;
        return id == todoBuilder.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
