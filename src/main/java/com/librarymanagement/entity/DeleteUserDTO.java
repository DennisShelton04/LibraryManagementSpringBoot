package com.librarymanagement.entity;

public class DeleteUserDTO {
	private String deleteUser;

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	@Override
	public String toString() {
		return "DeleteUserDTO [deleteUser=" + deleteUser + "]";
	}
	
}
