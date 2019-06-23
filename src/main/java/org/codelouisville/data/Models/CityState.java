package org.codelouisville.data.Models;

//Enum to get city and state names for each team
public enum CityState {
    ATLANTA_FALCONS("Atlanta", "GA", true ),
    ARIZONA_CARDINALS("Glendale", "AZ", true),
    BALTIMORE_RAVENS("Baltimore", "MD", false),
    BUFFALO_BILLS("Orchard Park", "NY", false),
    CAROLINA_PANTHERS("Charlotte", "NC", false),
    CHICAGO_BEARS("Chicago", "IL", false),
    CINCINNATI_BENGALS("Cincinnati", "OH", false),
    CLEVELAND_BROWNS("Cleveland", "OH", false),
    DALLAS_COWBOYS("Arlington", "TX", true),
    DETROIT_LIONS("Detroit", "MI", true),
    DENVER_BRONCOS("Denver", "CO", false),
    GREEN_BAY_PACKERS("Green Bay", "WI", false),
    HOUSTON_TEXANS("Houston", "TX", true),
    INDIANAPOLIS_COLTS ("Indianapolis", "IN", true),
    JACKSONVILLE_JAGUARS("Jacksonville", "FL", false),
    KANSAS_CITY_CHIEFS("Kansas City", "MO", false),
    LOS_ANGELES_CHARGERS ("Carson", "CA", false),
    LOS_ANGELES_RAMS ("Los Angeles", "CA", false),
    MIAMI_DOLPHINS("Miami", "FL", false),
    MINNESOTA_VIKINGS("Minneapolis", "MN", true),
    NEW_ENGLAND_PATRIOTS("Foxboro", "MA", false),
    NEW_ORLEANS_SAINTS("New Orelans", "LA", true),
    NEW_YORK_GIANTS("East Rutherford", "NJ", false),
    NEW_YORK_JETS("East Rutherford", "NJ", false),
    OAKLAND_RAIDERS("Oakland", "CA", false),
    PITTSBURGH_STEELERS("Pittsburgh", "PA", false),
    PHILADELPHIA_EAGLES("Philadelphia", "PA", false),
    SAN_FRANCISCO_49ERS("Santa Clara", "CA", false),
    SEATTLE_SEAHAWKS("Seattle", "WA", false),
    TAMPA_BAY_BUCCANEERS("Tampa", "FL", false),
    TENNESSEE_TITANS("Nashville", "TN", false),
    WASHINGTON_REDSKINS("Landover", "MD", false);

    private String city;
    private String state;
    private Boolean isDome;

    CityState(String city, String state,Boolean isDome){
        this.city = city;
        this.state = state;
        this.isDome = isDome;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
    public Boolean getIsDome() {
        return isDome;
    }

}
