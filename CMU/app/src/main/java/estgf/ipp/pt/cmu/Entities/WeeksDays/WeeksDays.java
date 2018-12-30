package estgf.ipp.pt.cmu.Entities.WeeksDays;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.arch.persistence.room.TypeConverters;
import android.content.Intent;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import estgf.ipp.pt.cmu.Entities.Meal.Meal;
import estgf.ipp.pt.cmu.Utilities.Converter;

@Entity
public class WeeksDays{
    @PrimaryKey(autoGenerate = true)
    public int id;
    @Ignore
    private Calendar date;
    private int caloriesConsumed; //
    private int caloriesEaten;
    private String dayOfWeek;
    private String dateFormat;

    public WeeksDays(){

    }

    public WeeksDays(Calendar date) {
        this.date = date;
        initializeDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
        initializeCalendar();
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


    public int getCaloriesConsumed() {
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(int caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public int getCaloriesEaten() {
        return caloriesEaten;
    }

    public void setCaloriesEaten(int caloriesEaten) {
        this.caloriesEaten = caloriesEaten;
    }



    private void initializeDate(){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat= format.format(date.getTime());
        switch(date.get(Calendar.DAY_OF_WEEK)){
            case 1:
                dayOfWeek="Sunday";
                break;
            case 2:
                dayOfWeek="Monday";
                break;
            case 3:
                dayOfWeek="Tuesday";
                break;
            case 4:
                dayOfWeek="Wednesday";
                break;
            case 5:
                dayOfWeek="Thursday";
                break;
            case 6:
                dayOfWeek="Friday";
                break;
            case 7:
                dayOfWeek="Saturday";
                break;
        }
    }

    private void initializeCalendar(){
        String array[] = this.dateFormat.split("/");
        Calendar save = Calendar.getInstance();
        save.set(Calendar.DAY_OF_MONTH,Integer.parseInt(array[0]));
        save.set(Calendar.MONTH,Integer.parseInt(array[1]));
        save.set(Calendar.YEAR,Integer.parseInt(array[2]));
        this.date=save;
    }



}
