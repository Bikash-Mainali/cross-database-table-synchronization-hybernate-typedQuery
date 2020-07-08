
package com.sync.dto;

public class JoinEntityDto {
	private String laptopUser;
	private int rollNo;

	public JoinEntityDto(int rollNo, String laptopUser) {
		super();
		this.laptopUser = laptopUser;
		this.rollNo = rollNo;
	}

	public String getLaptopUser() {
		return laptopUser;
	}

	public void setLaptopUser(String laptopUser) {
		this.laptopUser = laptopUser;
	}

	public int getRollNo() {
		return rollNo;
	}

	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
}
