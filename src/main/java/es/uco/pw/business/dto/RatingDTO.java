package es.uco.pw.business.dto;

/**
 * The type Rating.
 */
public class RatingDTO {

    /**
     * The Review title.
     */
    String reviewTitle;
    /**
     * The User email.
     */
    String userEmail;
    /**
     * The Points.
     */
    int points;


    /**
     * Instantiates a new Rating.
     *
     * @param userEmail   the user email
     * @param points      the points
     * @param reviewTitle the review title
     */
    public RatingDTO(String userEmail, int points, String  reviewTitle) {
        this.userEmail = userEmail;
        this.points = points;
        this.reviewTitle = reviewTitle;
    }

    /**
     * Instantiates a new Rating.
     *
     * @param csvInformation the csv information
     */
    public RatingDTO(String csvInformation){
        String[] data = csvInformation.split(",");
        this.setUserEmail(data[0]);
        this.setPoints(Integer.parseInt(data[1]));
        this.setReviewTitle(data[2]);
    }

    /**
     * Gets review title.
     *
     * @return the review title
     */
    public String getReviewTitle() {
        return reviewTitle;
    }

    /**
     * Sets review title.
     *
     * @param reviewTitle the review title
     */
    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    /**
     * Sets user email.
     *
     * @param userEmail the user email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets user email.
     *
     * @return the user email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCsv(){
        return String.format("%s,%s,%s\n",this.userEmail,this.points,this.getReviewTitle());
    }
}
