package JavaCMain;

public interface JavaCMain_Interface {
    public long convertToDecimalIP(String ip);
    public String resolveHostname(String ip);
    public int fetchASNInfo(String ip);
    public String getISPDetails(String ip);
    public String identifyServiceType(String ip);
    public String getCountryByIP(String ip);
    public String getStateByIP(String ip);
    public String getCityByIP(String ip);
    public double getLatitudeByIP(String ip);
    public double getLongitudeByIP(String ip);
	String getIPDetails(String ip);
}
