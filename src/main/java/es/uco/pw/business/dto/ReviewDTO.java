package es.uco.pw.business.dto;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Review.
 */
public class ReviewDTO {

    private int id;
    private  int espectacle;
    private  int points;
    private  String text;
    private int creator;
    private  List<RatingDTO> ratings;



    /**
     * Instantiates a new Review.
     *
     * @param title   the title
     * @param text    the text
     * @param creator the creator
     */
    public ReviewDTO(int id, int espectacle, String text, int creator){
        this.setId(id);
        this.espectacle = espectacle;
        this.points = 0;
        this.text = text;
        this.setCreator(creator);
        this.ratings = new ArrayList<>();
    }

    /**
     * Instantiates a new Review.
     *
     * @param csv_info the csv info
     */
    public ReviewDTO(String csv_info){
        String[] data = csv_info.split(",");
        this.espectacle = Integer.parseInt(data[0]);
        this.points = Integer.parseInt(data[1]);
        this.text = data[2];
        this.creator = Integer.parseInt(data[3].replaceAll("\n",""));
        this.ratings = new ArrayList<>();
    }

    public ReviewDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Increment points.
     *
     * @param points the points
     */
    public void incrementPoints(Optional<Integer> points){
        if (points.isPresent()){
            this.points = this.points + points.get();
        }
        else{
            this.points++;

        }
    }

    /**
     * Get points int.
     *
     * @return the int
     */
    public int getPoints(){
        for (RatingDTO it: this.ratings){
            this.points += it.getPoints();
        }
        return this.points;
    }

    
    public void setEspectavle(int espectacle){
        this.espectacle = espectacle;
    }

   
    public int getEspectacle(){
        return this.espectacle;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    /**
     * To csv string.
     *
     * @return the string
     */
    public String toCsv(){
        return String.format("%d,%d,%s,%d\n",this.espectacle, this.points, this.text,this.creator);
    }

    /**
     * Add rating boolean.
     *
     * @param rating the rating
     * @return the boolean
     */
    public boolean addRating(RatingDTO rating){
        /*if (!this.isOwner(rating.getUserEmail())) {
            this.ratings.add(rating);
            return true;
        }*/
        return false;
    }

    /**
     * Is review rated boolean.
     *
     * @param userEmail the user email
     * @return the boolean
     */
    public boolean isReviewRated(String userEmail){
        for (RatingDTO it: this.ratings){
            if (it.getUserEmail().compareTo(userEmail) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Is owner boolean.
     *
     * @param userId User Identify from id
     * @return the boolean
     */
    public boolean isOwner(int userId){
        return this.getId() == userId;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Get text string.
     *
     * @return the string
     */
    public String getText(){
        return this.text;
    }

    /**
     * Gets ratings.
     *
     * @return the ratings
     */
    public List<RatingDTO> getRatings() {
        return this.ratings;
    }

    public String getString() {
    return String.format("%s\n%s\n%d\n", this.getEspectacle(),this.getText(),getPoints());
    }

    public int getCreator() {
        return this.creator;
    }
}
