package com.expenseTracker.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class CategoriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "userid")
	private Long userId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userid", referencedColumnName = "id", updatable = false, insertable = false)
	private UserEntity user;

	@Column(name = "categories", columnDefinition = "TEXT[]")
	private LinkedHashSet<String> categories;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@JsonIgnore
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public LinkedHashSet<String> getCategories() {
		return categories;
	}

	public void setCategories(LinkedHashSet<String> categories) {
		this.categories = categories;
	}

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", userId=" + userId +
                ", user=" + user +
                ", categories=" + categories +
                '}';
    }

}
