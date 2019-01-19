package Cinema;

import java.util.Map;
import java.util.TreeMap;

public class Cinema {
    private Map<Days, Shedule> sheduleMap;
    private final Time open;
    private final Time close;

    public Cinema(Time open, Time close) {
        sheduleMap = new TreeMap<Days, Shedule>();
        this.open = open;
        this.close = close;
    }

    public boolean addMovie(Days day, Movie movie, Time... times) throws Exception {
        Shedule shedule = new Shedule();
        for (Time t : times) {
            Seance seance = new Seance(movie, t);
            addSeance(day, shedule, seance);
        }
        if (shedule.isEmpty()) {
            return false;
        } else {
            sheduleMap.put(day, shedule);
            return true;
        }
    }

    private void addSeance(Days day, Shedule shedule, Seance seance) {
        if (seance.getStartTime().getDurationOfMin() >= open.getDurationOfMin() &&
                seance.getEndTime().getDurationOfMin() <= close.getDurationOfMin()) {
            shedule.addSeance(seance);
        }
    }

    public void addSeance(Seance seance, Days day)  {
        Shedule shedule = sheduleMap.get(day);
        if (shedule != null) {
            addSeance(day, shedule, seance);
        } else {
            shedule = new Shedule();
            addSeance(day, shedule, seance);
            if (!shedule.isEmpty()) {
                sheduleMap.put(day, shedule);
            }
        }
    }

    public void removeMovie(Movie movie) {
        for (Map.Entry<Days, Shedule> sheduleEntry : sheduleMap.entrySet()) {
            Shedule shedule = sheduleEntry.getValue();
            shedule.removeSeanceOfMovie(movie);
        }
    }

    public void removeSeance(Seance seance, Days day) {
        Shedule shedule = sheduleMap.get(day);
        if (shedule != null) {
            shedule.removeSeance(seance);
        }
    }

    //=======================================================================================
    public static void main(String[] args) throws Exception {
        Movie movie1 = new Movie("Фантастические твари", new Time(1, 50));
        Movie movie2 = new Movie("Лара Крофт", new Time(100));
        Cinema cinema = new Cinema(new Time(8, 0), new Time(22, 0));
        cinema.addMovie(Days.FRIDAY, movie1, new Time(10, 0), new Time(16, 0));
        Seance seance3 = new Seance(movie1, new Time(18, 0));
        cinema.addSeance(seance3, Days.FRIDAY);
        Seance seance4 = new Seance(movie2, new Time(13, 10));
        cinema.addSeance(seance4, Days.FRIDAY);
        Seance seance5 = new Seance(movie2, new Time(20, 0));
        cinema.addSeance(seance5, Days.FRIDAY);
        Seance seance6 = new Seance(movie1, new Time(20, 0));
        cinema.addSeance(seance6, Days.SATURDAY);
        cinema.removeSeance(seance4,Days.FRIDAY);
        cinema.removeMovie(movie1);
        System.out.println("master");
        System.out.println("master11");
    }

}
