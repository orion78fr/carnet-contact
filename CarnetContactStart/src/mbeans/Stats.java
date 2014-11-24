package mbeans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.hibernate.jmx.StatisticsService;

import utils.AppContextSingleton;
import utils.StatsLogger;

@ManagedBean
@RequestScoped
public class Stats {
	private List<String> logs;
	private StatisticsService stats;
	
	public StatisticsService getStats() {
		return stats;
	}

	public Stats(){
		logs = ((StatsLogger) AppContextSingleton.getContext().getBean("logBean")).getLogs();
		stats = ((StatisticsService) AppContextSingleton.getContext().getBean("hibernateStatisticsBean"));
	}

	public List<String> getLogs() {
		return logs;
	}
}
