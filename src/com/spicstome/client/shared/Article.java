package com.spicstome.client.shared;

import java.util.HashSet;
import java.util.Set;

public class Article extends Pecs{
	
	private Set<Log> logs;
	
	public Article() {
		
	}
	
	public Article(String name, int order, Folder folder, Image image) {
		super(name, order, folder, image);		
		this.logs = new HashSet<Log>();
	}
	
	public void addLog(Log log)
	{
		logs.add(log);
	}

	public void removeLog(Log log)
	{
		logs.remove(log);
	}
	
	public Set<Log> getLogs() {
		return logs;
	}

	public void setLogs(Set<Log> logs) {
		this.logs = logs;
	}

	@Override
	public String toString() {
		return "Article []";
	}
}
