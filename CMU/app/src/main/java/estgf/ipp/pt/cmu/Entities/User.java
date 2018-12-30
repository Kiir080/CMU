package estgf.ipp.pt.cmu.Entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    private String username;
    private int age;
    private String gender;
    private int maxCalories;
    private String activity;
    private Float height;
    private int weight;

    public User(){
    }

    public User(String username, int age, String gender, int maxCalories, String activity, Float height, int weight) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.maxCalories = maxCalories;
        this.activity = activity;
        this.height = height;
        this.weight = weight;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(int maxCalories) {
        this.maxCalories = maxCalories;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
