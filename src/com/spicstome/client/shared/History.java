package com.spicstome.client.shared;

import java.util.ArrayList;

public class History {

	ArrayList<Log> logs;
	
	public History()
	{
		logs = new ArrayList<Log>();
	}

	public ArrayList<Log> getLogs() {
		return logs;
	}

	public void setLogs(ArrayList<Log> logs) {
		this.logs = logs;
	}

}
