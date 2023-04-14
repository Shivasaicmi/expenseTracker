package com.expenseTracker.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "budget")
public class BudgetEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserEntity user;
	
	@Column(name = "category")
	private String category;
	

	@Column(name = "total_budget")
	private long totalBudget;
	
	@Column(name = "expenditure")
	private long expenditure;

	public BudgetEntity(long userId, String category, long totalBudget, long expenditure) {
		super();
		this.userId = userId;
		this.category = category;
		this.totalBudget = totalBudget;
		this.expenditure = expenditure;
	}

	public BudgetEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public long getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(long totalBudget) {
		this.totalBudget = totalBudget;
	}

	public long getExpenditure() {
		return expenditure;
	}

	public void setExpenditure(long expenditure) {
		this.expenditure = expenditure;
	}

	@Override
	public String toString() {

		return "BudgetEntity [id=" + id + ", userId=" + userId + ", user=" + user + ", category=" + category
				+ ", totalBudget=" + totalBudget + ", expenditure=" + expenditure + "]";
	}
}
