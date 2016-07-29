package car.web.forms;

import java.util.Collection;
import java.util.Date;

public class MainFrameForm
{
    private int yearOfIssue;
    private int showroomId;
    private Collection showrooms;
    private Collection cars;

    public void setYearOfIssue(int year) {
        this.yearOfIssue = year;
    }

    public int getYearOfIssue() {
        return yearOfIssue;
    }

    public void setShowroomId(int showroomId) {
        this.showroomId = showroomId;
    }

    public int getShowroomId() {
        return showroomId;
    }

    public void setShowrooms(Collection showrooms) {
        this.showrooms = showrooms;
    }

    public Collection getShowrooms() {
        return showrooms;
    }

    public void setCars(Collection cars) {
        this.cars = cars;
    }

    public Collection getCars() {
        return cars;
    }
 
    
}
