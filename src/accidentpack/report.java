package accidentpack;


/**
 * @author abard
 *
 */
public class report implements Comparable <report>{
	private String ID;
	private int Severity;
	private String StartTime;
	private String EndTime;
	private String Street;
	private String City;
	private String County;
	private String State;
	private String Temp;
	private String Humidity;
	private Double Visibility;
	private String Weather;
	private boolean Crossing;
	private String DayNight;
	
	public report(String ID, int Severity, String StartTime, String EndTime, String Street,
			String City, String County, String State, String Temp,String Humidity, Double Visibility,
			String Weather, boolean Crossing, String DayNight) {
		this.ID = ID;
		this.Severity = Severity;
		this.StartTime = StartTime;
		this.EndTime = EndTime;
		this.Street = Street;
		this.City = City;
		this.County = County;
		this.State = State;
		this.Temp = Temp;
		this.Humidity = Humidity;
		this.Visibility = Visibility;
		this.Weather = Weather;
		this.Crossing = Crossing;
		this.DayNight = DayNight;
	}

	
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public int getSeverity() {
		return Severity;
	}
	public void setSeverity(int Severity) {
		this.Severity = Severity;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String StartTime) {
		this.StartTime = StartTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String EndTime) {
		this.EndTime = EndTime;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String Street) {
		this.Street = Street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String City) {
		this.City = City;
	}
	public String getCounty() {
		return County;
	}
	public void setCounty(String Country) {
		this.County = Country;
	} 
	public String getState() {
		return State;
	}
	public void setState(String State) {
		this.State = State;
	}
	public String getTemp() {
		return Temp;
	}
	public void setTemp(String Temp) {
		this.Temp = Temp;
	}
	public String getHumidity() {
		return Humidity;
	}
	public void setHumidity(String Humidity) {
		this.Humidity = Humidity;
	}
	public Double getVisibility() {
		return Visibility;
	}
	public void setVisibility(Double Visibility) {
		this.Visibility = Visibility;
	}
	public String getWeather() {
		return Weather;
	}
	public void setWeather(String Weather) {
		this.Weather = Weather;
	}
	public boolean getCrossing() {
		return Crossing;
	}
	public void setCrossing(boolean Crossing) {
		this.Crossing = Crossing;
	}
	public String getDayNight() {
		return DayNight;
	}
	public void setDayNight(String DayNight) {
		this.DayNight = DayNight;
	}



	@Override
	public int compareTo(report r) {
		if(this.Visibility > r.Visibility) {
			return -1;
		}else if(this.Visibility < r.Visibility) {
			return 1;
		}else {
			return 0;
		}
	}
	
}
