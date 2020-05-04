package main.java.hrk.timeline.pojo;

import java.util.LinkedList;
import java.util.List;

public class Header {
	private String DateFrom;
    private String DateTo;
    private List<String> Texts;
    private String Title;
	private List<SubHeader> subHeaders;
	
	public String getDateFrom() {
		return DateFrom;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
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
	public List<SubHeader> getSubHeaders() {
		return subHeaders;
	}
	public void setSubHeaders(List<SubHeader> subHeaders) {
		this.subHeaders = subHeaders;
	}
	public void addTo(SubHeader subHeader) {
		if(subHeaders==null) {
			subHeaders = new LinkedList<SubHeader>();
		}
		subHeaders.add(subHeader);
	}
	@Override
	public String toString() {
		return "Header [DateFrom=" + DateFrom + ", DateTo=" + DateTo + ", Texts=" + Texts + ", subHeaders=" + subHeaders
				+ "]";
	}
	
}
