package hrk.timeline.pojo;

import java.util.LinkedList;
import java.util.List;

public class Data {
    private String dateText;
    private String title;
    private String subtitle;
    private List<String> texts;

    public Data() {
    	super();
    }
    
    public Data(String dateText, String title, String subtitle) {
		super();
		this.dateText = dateText;
		this.title = title;
		this.subtitle = subtitle;
	}

	public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

  
	public List<String> getTexts() {
		return texts;
	}

	public void setTexts(List<String> texts) {
		this.texts = texts;
	}
	public void setText(String text) {
		if(texts==null) {
			texts = new LinkedList<String>();
		}
		texts.add(text);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateText == null) ? 0 : dateText.hashCode());
		result = prime * result + ((subtitle == null) ? 0 : subtitle.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Data other = (Data) obj;
		if (dateText == null) {
			if (other.dateText != null)
				return false;
		} else if (!dateText.equals(other.dateText))
			return false;
		if (subtitle == null) {
			if (other.subtitle != null)
				return false;
		} else if (!subtitle.equals(other.subtitle))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Data [dateText=" + dateText + ", title=" + title + ", subtitle=" + subtitle + ", texts=" + texts + "]";
	}
    
}