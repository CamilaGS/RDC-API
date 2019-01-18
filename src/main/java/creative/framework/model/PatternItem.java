package creative.framework.model;

import java.util.List;

/**
 *
 * @author cel
 */
public class PatternItem {
    
    int id;
    String name;
    int authorId;
    int projectCount;
    int difficultyCount;
    double difficultyAverage;
    boolean free;
    double price;
    String currency;
    int commentsCount;
    int favoritesCount;
    int ratingCount;
    double ratingAverage;
    List<String> categories;
    List<String> needleSizes;
    boolean photo;
    String craft;
    String yarnName;
    String yarnCompany;
    String published;
    List<String> tags;
    

    public void PatterenItem(int id, String name, int authorId, int projectCount, int difficultyCount,
    					double difficultyAverage, boolean free, double price, String currency,
    					int commentsCount, int favoritesCount, int ratingCount, double ratingAverage,
    					List<String> categories, List<String> needleSizes, boolean photo, String craft,
    					String yarnName, String yarnCompany, String published, List<String> tags
    					) {
    	this.id = id;
    	this.name = name;
    	this.authorId = authorId;
    	this.projectCount = projectCount;
    	this.difficultyCount = difficultyCount;
    	this.difficultyAverage = difficultyAverage;
    	this.free = free;
    	this.price = price;
    	this.currency = currency;
    	this.commentsCount = commentsCount;
    	this.favoritesCount = favoritesCount;
    	this.ratingCount = ratingCount;
    	this.ratingAverage = ratingAverage;
    	this.categories = categories;
    	this.needleSizes = needleSizes;
    	this.photo = photo;
    	this.craft = craft;
    	this.yarnName = yarnName;
    	this.yarnCompany = yarnCompany;
    	this.published = published;
    	this.tags = tags;
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public int getProjectCount() {
		return projectCount;
	}


	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}


	public int getDifficultyCount() {
		return difficultyCount;
	}


	public void setDifficultyCount(int difficultyCount) {
		this.difficultyCount = difficultyCount;
	}


	public double getDifficultyAverage() {
		return difficultyAverage;
	}


	public void setDifficultyAverage(double difficultyAverage) {
		this.difficultyAverage = difficultyAverage;
	}


	public boolean isFree() {
		return free;
	}


	public void setFree(boolean free) {
		this.free = free;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public int getCommentsCount() {
		return commentsCount;
	}


	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}


	public int getFavoritesCount() {
		return favoritesCount;
	}


	public void setFavoritesCount(int favoritesCount) {
		this.favoritesCount = favoritesCount;
	}


	public int getRatingCount() {
		return ratingCount;
	}


	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}


	public double getRatingAverage() {
		return ratingAverage;
	}


	public void setRatingAverage(double ratingAverage) {
		this.ratingAverage = ratingAverage;
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	public List<String> getNeedleSizes() {
		return needleSizes;
	}


	public void setNeedleSizes(List<String> needleSizes) {
		this.needleSizes = needleSizes;
	}


	public boolean isPhoto() {
		return photo;
	}


	public void setPhoto(boolean photo) {
		this.photo = photo;
	}


	public String getCraft() {
		return craft;
	}


	public void setCraft(String craft) {
		this.craft = craft;
	}


	public String getYarnName() {
		return yarnName;
	}


	public void setYarnName(String yarnName) {
		this.yarnName = yarnName;
	}


	public String getYarnCompany() {
		return yarnCompany;
	}


	public void setYarnCompany(String yarnCompany) {
		this.yarnCompany = yarnCompany;
	}


	public String getPublished() {
		return published;
	}


	public void setPublished(String published) {
		this.published = published;
	}


	public List<String> getTags() {
		return tags;
	}


	public void setTags(List<String> tags) {
		this.tags = tags;
	}

    
    
    
    
    
    
}
