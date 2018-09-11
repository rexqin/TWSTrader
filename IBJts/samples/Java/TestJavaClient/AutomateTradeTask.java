package TestJavaClient;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimerTask;

import com.ib.client.EClientSocket;


public class AutomateTradeTask extends TimerTask {

	public IBTextPanel logger;
	
	public EClientSocket client;
	
	public HashMap contractList;
	
	public void run(){
	
		if (contractList == null) {
			return;
		}
		String symbol = null;
		Integer conid = null;
		Iterator iter = contractList.entrySet().iterator();
		while(iter.hasNext()) {
		    Map.Entry entry = (Map.Entry)iter.next();

		    symbol = (String)entry.getKey();
		    conid = (Integer)entry.getValue();
		    client.cancelMktData(conid);
		}
		
		this.log("定时监听");
		contractList.clear();
		client.cancelPositions();
		client.reqPositions();
		
	}
	
	private void log(String text) {
		if (logger != null) {
			logger.add(text);
		}
	}
}
