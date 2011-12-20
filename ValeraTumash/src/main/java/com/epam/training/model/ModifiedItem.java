package com.epam.training.model;

import java.util.Date;

public class ModifiedItem extends Item {
	private int modifyQuantity;
	private Date inDate;
	private User ModifyUser;
	private boolean confirmationFlag;
	private User confirmationUser;
	private Date confirmationDate;
	
	public int getModifyQuantity() {
		return modifyQuantity;
	}
	public void setModifyQuantity(int modifyQuantity) {
		this.modifyQuantity = modifyQuantity;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public User getModifyUser() {
		return ModifyUser;
	}
	public void setModifyUser(User modifyUser) {
		ModifyUser = modifyUser;
	}
	public boolean isConfirmationFlag() {
		return confirmationFlag;
	}
	public void setConfirmationFlag(boolean confirmationFlag) {
		this.confirmationFlag = confirmationFlag;
	}
	public User getConfirmationUser() {
		return confirmationUser;
	}
	public void setConfirmationUser(User confirmationUser) {
		this.confirmationUser = confirmationUser;
	}
	public Date getConfirmationDate() {
		return confirmationDate;
	}
	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}


}
