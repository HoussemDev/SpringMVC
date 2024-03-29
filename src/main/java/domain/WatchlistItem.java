package domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import validation.GoodMovie;
import validation.Priority;

@GoodMovie
public class WatchlistItem {

	@NotBlank(message="Please enter the title")
	private String title;
	private String rating;
	@Priority
	private String priority;
	@Size(max=50, message="comment should be maximum 50 characters")
	private String comment;
	private int id;
	public WatchlistItem(String title, String rating, String priority, String comment, int id) {
		super();
		this.title = title;
		this.rating = rating;
		this.priority = priority;
		this.comment = comment;
		this.id = id;
	}
	public WatchlistItem() {
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	

	
	
}
