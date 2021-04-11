package es.deusto.bspq21e1.serialization;

/**
 * Class for the representation of the Review object which is going to be sended to clients.
 * @author SPQ Group 1
 * @version 1.0
 */
public class ReviewData {
    
	private int code;
    private int stars;
    private String comment;

	/**
	 * Creates the object that has the data from Review.
	 * @param stars Rating between zero and five stars.
	 * @param comment Comment of the review.
	 */
    public ReviewData(int stars, String comment) {
        this.code = (int)Math.random()*10000;
        this.stars = stars;
        this.comment = comment;
    }

    public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "ReviewData [code=" + code + ", stars=" + stars + ", comment=" + comment + "]";
	}

}
