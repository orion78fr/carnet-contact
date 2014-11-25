package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.JoinPoint;

import codel.Contact;

public class StatsLogger {
	private List<String> logs = new ArrayList<String>();
	private SimpleDateFormat dt = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss - ");
	
	public synchronized void logCreateContact(JoinPoint joinPoint){
		Date d = new Date();
		
		Contact c = (Contact) joinPoint.getArgs()[0];

		logs.add(new StringBuffer().append(dt.format(d)).append("Ajout d'un contact nommé ").append(c.getFullName()).toString());
		
		deleteTooOld();
	}
	
	public synchronized void logModifyContact(JoinPoint joinPoint){
		Date d = new Date();
		
		Contact c = (Contact) joinPoint.getArgs()[0];

		logs.add(new StringBuffer().append(dt.format(d)).append("Modification du contact d'id ").append(c.getId())
				.append(" nommé ").append(c.getFullName()).toString());
		
		deleteTooOld();
	}
	
	public synchronized void logDeleteContact(JoinPoint joinPoint){
		Date d = new Date();

		Long id = (Long) joinPoint.getArgs()[0];
		
		logs.add(new StringBuffer().append(dt.format(d)).append("Suppression du contact d'id ")
				.append(id).toString());
		
		deleteTooOld();
	}
	
	private void deleteTooOld(){
		for(int i = 100; i < logs.size(); i++){
			logs.remove(0);
		}
	}

	public List<String> getLogs() {
		return logs;
	}
}
