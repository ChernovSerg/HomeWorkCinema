package Cinema;

public final class Time implements Cloneable {
    private final int hour;
    private final int min;
    private final int durationOfMin;

    public Time(int hour, int min) throws Exception {
        if (hour >= 0 && hour <= 23 && min >= 0 && min <= 59) {
            this.hour = hour;
            this.min = min;
            this.durationOfMin = hour * 60 + min;
        } else
            throw new ExceptionTime("Invalid hours or minutes!");
    }

    public Time(int min) throws Exception {
        if (min >= 0 && min < 1440) {
            this.durationOfMin = min;
            this.hour = min / 60;
            this.min = min % 60;
        } else
            throw new ExceptionTime("Invalid hours or minutes!");
    }

    @Override
    protected Time clone() throws CloneNotSupportedException {
        return (Time)super.clone();
    }

    @Override
    public String toString() {
        return hour + "hh:" +
                min + "mm";
//                min + "mm, OR " +
//                durationOfMin + " min";
    }

    public int getDurationOfMin() {
        return this.durationOfMin;
    }


    //Для тестирования
    public static void main(String[] args) throws Exception {
        try {
            Time t = new Time(23, 30);
            System.out.println(t);
//            Time t2 = new Time(23, 60); //to be exception
//            System.out.println(t2);

            Time t3 = new Time(1439);
            System.out.println(t3);
        } catch (ExceptionTime e) {
            System.out.println(e);
        }
    }
}

class ExceptionTime extends Exception {
    public ExceptionTime(String message) {
        super(message);
    }
}