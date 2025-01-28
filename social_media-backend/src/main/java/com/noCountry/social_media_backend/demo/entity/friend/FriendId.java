package com.noCountry.social_media_backend.demo.entity.friend;

import java.io.Serializable;
import java.util.Objects;

public class FriendId implements Serializable {

    private Integer user;
    private Integer friend;

    public FriendId() {}

    public FriendId(Integer user, Integer friend) {
        this.user = user;
        this.friend = friend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendId friendId = (FriendId) o;
        return Objects.equals(user, friendId.user) && Objects.equals(friend, friendId.friend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, friend);
    }
}