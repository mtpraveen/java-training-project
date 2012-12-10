package epam.course.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{

	private int number;
	private Customer client;
	private Car car;
	private Date beginOrder;
	private Date endOrder;
	private boolean accepted;
	private boolean cause;
	private int sum;
	private boolean paid;
	private String comments;

	public Order(int number, Customer client, Car car, Date beginOrder, Date endOrder) {
		this.number=number;
		this.client = client;
		this.car = car;
		this.beginOrder = beginOrder;
		this.endOrder = endOrder;
	}

	
	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public Customer getClient() {
		return client;
	}

	public void setClient(Customer client) {
		this.client = client;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Date getBeginOrder() {
		return beginOrder;
	}

	public void setBeginOrder(Date beginOrder) {
		this.beginOrder = beginOrder;
	}

	public Date getEndOrder() {
		return endOrder;
	}

	public void setEndOrder(Date endOrder) {
		this.endOrder = endOrder;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isCause() {
		return cause;
	}

	public void setCause(boolean cause) {
		this.cause = cause;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beginOrder == null) ? 0 : beginOrder.hashCode());
		result = prime * result + ((car == null) ? 0 : car.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result
				+ ((endOrder == null) ? 0 : endOrder.hashCode());
		result = prime * result + sum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (beginOrder == null) {
			if (other.beginOrder != null)
				return false;
		} else if (!beginOrder.equals(other.beginOrder))
			return false;
		if (car == null) {
			if (other.car != null)
				return false;
		} else if (!car.equals(other.car))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (endOrder == null) {
			if (other.endOrder != null)
				return false;
		} else if (!endOrder.equals(other.endOrder))
			return false;
		if (sum != other.sum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [number=" + number+" client=" + client + ", car=" + car + ", beginOrder="
				+ beginOrder + ", endOrder=" + endOrder + ", accepted="
				+ accepted + ", cause=" + cause + ", sum=" + sum + ", paid="
				+ paid + ", comments=" + comments + "]";
	}

	
}
