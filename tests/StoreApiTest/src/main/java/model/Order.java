package model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Order{
	@SerializedName("id")
	private int id;

	@SerializedName("petId")
	private int petId;

	@SerializedName("quantity")
	private int quantity;

	@SerializedName("shipDate")
	private String shipDate;

	@SerializedName("status")
	private String status;

	@SerializedName("complete")
	private boolean complete;

	public Order(int id, int petId, int quantity, String shipDate, String status, boolean complete) {
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.status = status;
		this.complete = complete;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return id == order.id && petId == order.petId &&
				quantity == order.quantity && complete == order.complete &&
				Objects.equals(status, order.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, petId, quantity, status, complete);
	}

	public int getPetId(){
		return petId;
	}

	public int getQuantity(){
		return quantity;
	}

	public int getId(){
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public String getShipDate(){
		return shipDate;
	}

	public boolean isComplete(){
		return complete;
	}

	public String getStatus(){
		return status;
	}
}