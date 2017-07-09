import java.util.*;

//1.implements Observer
public class ObserverGraph implements Observer{

	public ObserverGraph(Stock stock) {
		// 3. Observable���� ���
		stock.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// 2. notify�Ǿ����� ������ ����
		Stock stock = (Stock) o;
		System.out.print("ObserverGraph : ");
		int count = (int)(stock.getPrice() / 10.0);
		for (int i = 0; i < count; i++) {
			System.out.print("*");
		}
		System.out.println("("+(int)stock.getPrice()+")");
	}

}