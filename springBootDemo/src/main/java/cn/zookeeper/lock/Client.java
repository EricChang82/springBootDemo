package cn.zookeeper.lock;
public class Client {
	/*
	 * 抢购商品的方法 
	 * 作用：访问共享资源,获取并更新商品数量
	 */
	public static void buy() {
		System.out.println("--------【"+Thread.currentThread().getName()+"】开始购买-------");
		//获取商品数量
		int currentNumber = Product.getNumber();
		/*
		 * 如果商品数量为0,则不能购买
		 * 如果还有商品,则执行购买操作
		 */
		if(currentNumber == 0 ) {
			System.out.println("商品已被抢空！！！");
		}else {
			System.out.println("当前商品数量："+currentNumber);
			
			//购买后商品数量减1
			currentNumber--;
			Product.setNumber(currentNumber);
			
			//为了便于观察程序的运行结果，这里使线程在执行购买操作后，停顿3秒
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("--------【"+Thread.currentThread().getName()+"】  购买结束-------");
	}
}