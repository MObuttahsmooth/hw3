public class Order{
	String user;
	int id;
	String productName;
	int quantity;

	public Order(String user, int id, String productName, int quantity){
		this.user = user;
		this.id = id;
		this.productName = productName;
		this.quantity = quantity;
	}

	public String toString(){
		return (id + ", " + productName + ", " + quantity);
	}
}