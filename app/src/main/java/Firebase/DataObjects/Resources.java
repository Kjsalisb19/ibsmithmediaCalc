package Firebase.DataObjects;

/**
 * Created by KellySmithAd on 6/22/2017.
 */

public class Resources extends FirebaseObject{

    public Resources(){
    }

    private String title;
    private String pricePerHour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resources resources = (Resources) o;

        if (title != null ? !title.equals(resources.title) : resources.title != null) return false;
        return pricePerHour != null ? !pricePerHour.equals(resources.pricePerHour) : resources.pricePerHour != null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (pricePerHour != null ? pricePerHour.hashCode() : 0);
        return result;
    }

    //automatically generated getters and setters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(String pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
}
