package filipe.app.appip;

public class Localizacao {

    private String country_code;
    private String country_name;

    public Localizacao(String code, String name){
        this.country_code = code;
        this.country_name = name;
    }

    public String getCountryCode(){ return this.country_code;}
    public String getCountryName(){ return this.country_name;}
}
