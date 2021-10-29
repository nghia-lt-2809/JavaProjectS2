package Models;

public class Service {
    private String id_Service;
    private String name_Service;
    private String Staff_Name_Perform;
    private double Price_Service;

    @Override
    public String toString() {
        return "Service{" +
                "id_Service='" + id_Service + '\'' +
                ", name_Service='" + name_Service + '\'' +
                ", Staff_Name_Perform='" + Staff_Name_Perform + '\'' +
                ", Price_Service=" + Price_Service +
                '}';
    }

    public Service(String id_Service, String staff_Name_Perform, double price_Service, String name_Service) {
        this.id_Service = id_Service;
        Staff_Name_Perform = staff_Name_Perform;
        Price_Service = price_Service;
        this.name_Service = name_Service;
    }

    public String getName_Service() {
        return name_Service;
    }

    public void setName_Service(String name_Service) {
        this.name_Service = name_Service;
    }
    public String getId_Service() {
        return id_Service;
    }

    public void setId_Service(String id_Service) {
        this.id_Service = id_Service;
    }

    public String getStaff_Name_Perform() {
        return Staff_Name_Perform;
    }

    public void setStaff_Name_Perform(String staff_Name_Perform) {
        Staff_Name_Perform = staff_Name_Perform;
    }

    public double getPrice_Service() {
        return Price_Service;
    }

    public void setPrice_Service(double price_Service) {
        Price_Service = price_Service;
    }
}