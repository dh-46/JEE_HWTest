package tw.dh46.jeetest;

/*	20180908AM2 HW18
 * 	MVC2 實作練習
 * 	類別18負責複雜運算
 * 
 */

public class HW18 {
	int x, y;
	
	public HW18(String x, String y) {
		this.x = Integer.parseInt(x);
		this.y = Integer.parseInt(y);
	}
	
	public int add() {
		return x + y;
	}
}
