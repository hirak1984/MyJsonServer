package main.java.hrk.timeline.pojo;

import java.util.List;

public class SubHeader {
	private String DateFrom;
    private String DateTo;
    private List<String> Texts;
    private String Title;
    
public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	/*	public SubHeader(String dateFrom, String dateTo, String texts) {
		super();
		DateFrom = dateFrom;
		DateTo = dateTo;
		Texts = texts;
	}*/
	public String getDateFrom() {
		return DateFrom;
	}
	public void setDateFrom(String dateFrom) {
		DateFrom = dateFrom;
	}
	public String getDateTo() {
		return DateTo;
	}
	public void setDateTo(String dateTo) {
		DateTo = dateTo;
	}

	public List<String> getTexts() {
		return Texts;
	}
	public void setTexts(List<String> texts) {
		Texts = texts;
	}
	@Override
	public String toString() {
		return "SubHeader [DateFrom=" + DateFrom + ", DateTo=" + DateTo + ", Texts=" + Texts + "]";
	}
    
}
