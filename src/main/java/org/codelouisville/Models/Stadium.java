package org.codelouisville.Models;

//Enum to get city and state names for each team
public enum Stadium {
    ATLANTA_FALCONS("Mercedes-Benz Stadium", true ),
    ARIZONA_CARDINALS("University of Phoenix Stadium", true),
    BALTIMORE_RAVENS("M&T Bank Stadium", false),
    BUFFALO_BILLS("New Era Field", false),
    CAROLINA_PANTHERS("Bank of America Stadium", false),
    CHICAGO_BEARS("Soldier Field", false),
    CINCINNATI_BENGALS("Paul Brown Stadium", false),
    CLEVELAND_BROWNS("FirstEnergy Stadium", false),
    DALLAS_COWBOYS("AT&T Stadium", true),
    DETROIT_LIONS("Ford Field", true),
    DENVER_BRONCOS("Mile High Stadium", false),
    GREEN_BAY_PACKERS("Lambeau Field", false),
    HOUSTON_TEXANS("NRG Stadium", true),
    INDIANAPOLIS_COLTS ("Lucas Oil Stadium", true),
    JACKSONVILLE_JAGUARS("TIAA Bank Field", false),
    KANSAS_CITY_CHIEFS("Arrowhead Stadium", false),
    LOS_ANGELES_CHARGERS ("Dignity Health Sports Park", false),
    LOS_ANGELES_RAMS ("LA Memorial Coliseum", false),
    MIAMI_DOLPHINS("Hard Rock Stadium", false),
    MINNESOTA_VIKINGS("U.S. Bank Stadium", true),
    NEW_ENGLAND_PATRIOTS("Gillette Stadium", false),
    NEW_ORLEANS_SAINTS("Mercedes-Benz Superdome", true),
    NEW_YORK_GIANTS("MetLife Stadium", false),
    NEW_YORK_JETS("MetLife Stadium", false),
    OAKLAND_RAIDERS("Oakland-Alameda County Coliseum", false),
    PITTSBURGH_STEELERS("Heinz Field", false),
    PHILADELPHIA_EAGLES("Lincoln Financial Field", false),
    SAN_FRANCISCO_49ERS("Levi's Stadium", false),
    SEATTLE_SEAHAWKS("CenturyLink Field", false),
    TAMPA_BAY_BUCCANEERS("Raymond James Stadium", false),
    TENNESSEE_TITANS("Nissan Stadium", false),
    WASHINGTON_REDSKINS("FedExField", false);

    private String Stadium;
    private Boolean isDome;

    Stadium(String stadium, Boolean isDome) {
        Stadium = stadium;
        this.isDome = isDome;
    }

    public String getStadium() {
        return Stadium;
    }

    public Boolean getIsDome() {
        return isDome;
    }

}
