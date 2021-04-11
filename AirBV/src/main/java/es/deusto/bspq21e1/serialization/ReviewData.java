package es.deusto.bspq21e1.serialization;

public class ReviewData {
    
	private int code;
    private int stars;
    private String comment;

    public ReviewData(int stars, String comment) {
        this.code = (int)Math.random()*10000;
        this.stars = stars;
        this.comment = comment;
    }

    // GETTERS and SETTERS
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

	//To String
	
	@Override
	public String toString() {
		return "ReviewData [code=" + code + ", stars=" + stars + ", comment=" + comment + "]";
	}

}
