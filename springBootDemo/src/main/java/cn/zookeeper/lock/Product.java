package cn.zookeeper.lock;
public class Product {
	// 商品数量,这里默认共有8件商品
	private static int number = 5;

	public static int getNumber() {
		return number;
	}

	public static void setNumber(int number) {
		Product.number = number;
	}
	
}