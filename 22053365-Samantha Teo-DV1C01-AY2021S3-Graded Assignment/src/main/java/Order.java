
public class Order {
	protected String order;
	protected String side;
	protected String drink;
	protected String payment;
	protected String orderid;
	public Order(String order, String side, String drink, String payment, String orderid) {
		super();
		this.order = order;
		this.side = side;
		this.drink = drink;
		this.payment = payment;
		this.orderid = orderid;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSide() {
		return side;
	}
	public void setSide(String side) {
		this.side = side;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


}
