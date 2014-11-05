package domain;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextSingleton {
	private static ClassPathXmlApplicationContext context = null;
	
	public static ClassPathXmlApplicationContext getContext(){
		if(context == null){
			context = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		}
		return context;
	}
}
