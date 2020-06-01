package top.stores.ecommerceapp.model;

import com.google.gson.annotations.SerializedName;

public class Employee {
    @SerializedName("firstname")
    private String mfirstName;
    @SerializedName("age")
    private int mage;
    @SerializedName("lastname")
    private String mlatName;

    public Employee(String firstName, int age, String latName) {
        this.mfirstName = firstName;
        this.mage = age;
        this.mlatName = latName;
    }
}
