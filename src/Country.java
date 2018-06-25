public class Country {
    private String countryName;
    private String regionName;
    private String linkName;

    public Country(String c, String r, String u) {
        this.countryName = c;
        this.regionName = r;
        this.linkName = u;
    }

    public String getCountryName() {
        return this.countryName;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public String getLinkName() {
        return this.linkName;
    }
}