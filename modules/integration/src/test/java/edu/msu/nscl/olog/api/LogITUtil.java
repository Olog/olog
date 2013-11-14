package edu.msu.nscl.olog.api;

import java.util.Collection;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

/**
 * Defines Helper methods to compare LogBuilder and Log, taking into
 * consideration the fact that the builder do not have Ids, versions or create
 * dates
 * 
 * @author shroffk
 * 
 */
public class LogITUtil {

    	/**
	 * Check is the entries represented by the builders <tt>logs</tt> were successfully created.
	 * This check ignores the ID check since the builders do not have Id's or create times
	 * 
	 * @param logs
	 * @param result
	 * @return
	 */
	public static boolean compare(Collection<LogBuilder> logs, Collection<Log> result) {
	    if(logs.size() != result.size()){
		return false;
	    }else{
		return Collections2.transform(result, new Function<Log, ComparableLog>() {
		    
		    public ComparableLog apply(Log object){
			return new ComparableLog(object);
		    }
		}).containsAll(Collections2.transform(logs, new Function<LogBuilder, ComparableLog>(){
		    
		    public ComparableLog apply(LogBuilder object){
			return new ComparableLog(object.build());
		    }
		}));
	    }
	}
	
	/**
	 * 
	 * @param logs
	 * @param result
	 * @return
	 */
	public static boolean compareLog(Collection<Log> logs, Collection<Log> result){
	    if(logs.size() != result.size()){
		return false;
	    }else{
		return Collections2.transform(result, new Function<Log, ComparableLog>() {
		    
		    public ComparableLog apply(Log object){
			return new ComparableLog(object);
		    }
		}).containsAll(Collections2.transform(logs, new Function<Log, ComparableLog>(){
		    
		    public ComparableLog apply(Log object){
			return new ComparableLog(object);
		    }
		}));
	    }
	}
	
	/**
	 * 
	 * @param logBuilder
	 * @param log
	 * @return
	 */
	public static boolean compare(LogBuilder logBuilder, Log log){
	    ComparableLog log1 = new ComparableLog(logBuilder.build());
	    ComparableLog log2 = new ComparableLog(log);
	    return log1.equals(log2);
	}
	

	
	/**
	 * Check if result contains log
	 * @param log
	 * @param result
	 * @return
	 */
	public static boolean contains(Collection<Log> result, LogBuilder log) {
	return Collections2.transform(result,
		new Function<Log, ComparableLog>() {

		    public ComparableLog apply(Log object) {
			return new ComparableLog(object);
		    }
		}).contains(new ComparableLog(log.build()));
	}
	
	/**
	 * 
	 * @param result
	 * @param log
	 * @return
	 */
	public static boolean contains(Collection<Log> result, Log log) {
	return Collections2.transform(result,
		new Function<Log, ComparableLog>() {

		    public ComparableLog apply(Log object) {
			return new ComparableLog(object);
		    }
		}).contains(new ComparableLog(log));
	}
	
	
	public static class ComparableLog{
	   
	    private String description;
	    private String level;
	    private Collection<Logbook> logbooks;
	    private Collection<Tag> tags;
	    private Collection<Property> properties;
	    /**
	     * @param log
	     */
	    ComparableLog(Log log) {
		this.description = log.getDescription();
		this.level = log.getLevel();
		this.logbooks = log.getLogbooks();
		this.tags = log.getTags();
		this.properties = log.getProperties();
	    }
	    /* (non-Javadoc)
	     * @see java.lang.Object#hashCode()
	     */
	    @Override
	    public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
			+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
			+ ((level == null) ? 0 : level.hashCode());
//		result = prime * result
//			+ ((logbooks == null) ? 0 : logbooks.hashCode());
//		result = prime * result
//			+ ((properties == null) ? 0 : properties.hashCode());
//		result = prime * result
//			+ ((tags == null) ? 0 : tags.hashCode());
		return result;
	    }
	    /* (non-Javadoc)
	     * @see java.lang.Object#equals(java.lang.Object)
	     */
	    @Override
	    public boolean equals(Object obj) {
		if (this == obj)
		    return true;
		if (obj == null)
		    return false;
		if (getClass() != obj.getClass())
		    return false;
		ComparableLog other = (ComparableLog) obj;
		if (description == null) {
		    if (other.description != null)
			return false;
		} else if (!description.equals(other.description))
		    return false;
		if (level == null) {
		    if (other.level != null)
			return false;
		} else if (!level.equals(other.level))
		    return false;
//		if (logbooks == null) {
//		    if (other.logbooks != null)
//			return false;
//		} else if (!logbooks.equals(other.logbooks))
//		    return false;
//		if (properties == null) {
//		    if (other.properties != null)
//			return false;
//		} else if (!properties.equals(other.properties))
//		    return false;
//		if (tags == null) {
//		    if (other.tags != null)
//			return false;
//		} else if (!tags.equals(other.tags))
//		    return false;
		return true;
	    }

	    
	}
}
