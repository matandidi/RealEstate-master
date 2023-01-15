import java.util.Arrays;
public class City {

    private String cityName;
    private String district;

    private String [] streetNameArr;

    public City (String cityName, String district , String [] streetName){
        this.cityName = cityName;
        this.district = district;
        this.streetNameArr = streetName ;
    }


    public String getCityName (){
        return this.cityName;
    }


    public String[] getStreetNameArr() {
        return streetNameArr;
    }

    protected static Boolean chosenStreet (City city, String typedStreet){
        boolean existsStreet = false;
        for (int i = 0; i < city.streetNameArr.length; i++) {
            if (city.streetNameArr [i].equals(typedStreet)){
                existsStreet = true;
                break;
            }
        }
        return existsStreet;
    }

    public String toString() {
        return "City Name: " + this.cityName + "\n" +
                "Street Name: " + Arrays.toString(this.streetNameArr) + "\n";
    }
}