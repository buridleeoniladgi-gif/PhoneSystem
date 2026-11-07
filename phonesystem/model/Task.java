package model;

import java.io.Serializable;

/** Represents one phone record in the Phone System. */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private String snum;
    private String brand;
    private String unit;
    private String price;
    private String specs;

    public String getSnum() { return snum; }
    public void setSnum(String snum) { this.snum = snum; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getSpecs() { return specs; }
    public void setSpecs(String specs) { this.specs = specs; }

    @Override
    public String toString() {
        return "Task{" + "snum=" + snum + ", brand=" + brand
                + ", unit=" + unit + ", price=" + price + ", specs=" + specs + '}';
    }
}
