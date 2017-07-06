package Firebase.DataObjects;

public class User extends FirebaseObject{

    public User(){
    }

    private String businessName;
    private String city;
    private String email;
    private String owner;
    private String phone;
    private String state;
    private String street;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (street != null ? !street.equals(user.street) : user.street != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (owner != null ? !owner.equals(user.owner) : user.owner != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        if (state != null ? !state.equals(user.state) : user.state != null) return false;
        return businessName != null ? businessName.equals(user.businessName) : user.businessName == null;
    }

    @Override
    public int hashCode() {
        int result = businessName != null ? businessName.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    //automatically generated getters and setters
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getBusinessName() {
        return businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStreet(){
        return street;
    }
    public  void setStreet(String street){
        this.street = street;
    }
}
