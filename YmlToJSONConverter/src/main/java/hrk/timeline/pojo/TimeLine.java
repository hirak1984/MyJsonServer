package main.java.hrk.timeline.pojo;

import java.util.LinkedList;
import java.util.List;

public class TimeLine {
private String Title;
private List<Header> headers;

public String getTitle() {
	return Title;
}
public void setTitle(String title) {
	Title = title;
}
public List<Header> getHeaders() {
	return headers;
}
public void setHeaders(List<Header> headers) {
	this.headers = headers;
}
public void addTo(Header header) {
	if(headers==null) {
		headers = new LinkedList<Header>();
	}
	headers.add(header);
}
@Override
public String toString() {
	return "Timeline [Title=" + Title + ", headers=" + headers + "]";
}

}
