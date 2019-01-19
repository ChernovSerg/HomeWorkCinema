package Cinema;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Shedule {

    private Set<Seance> seances;

    public Shedule() {
        this.seances = new TreeSet<Seance>();
    }

    public boolean addSeance(Seance s) {
        Seance previous = (Seance) ((TreeSet) seances).floor(s);
        Seance following = (Seance) ((TreeSet) seances).ceiling(s);
        if (previous != null) {
            if (previous.getEndTime().getDurationOfMin() >= s.getStartTime().getDurationOfMin()) {
                return false;
            }
        }
        if (following != null) {
            if (s.getEndTime().getDurationOfMin() >= following.getStartTime().getDurationOfMin()) {
                return false;
            }
        }
        return seances.add(s);
    }

    public boolean removeSeance(Seance s) {
        //использую его, т.к. обычный remove использует compareTo и может удалить сеанс по совспадению времени невзирая на фильм
        return seances.removeIf(seance -> seance.equals(s));
    }

    public boolean removeSeanceOfMovie (Movie movie) {
        return seances.removeIf(seance -> seance.getMovie().equals(movie));
    }

    public boolean isEmpty() {
        return seances.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("Shedule{\n");
        for (Seance s : seances) {
            sb.append("\t" + (++i) + ": " + s + "\n");
        }
        sb.append("}");
        return sb.toString();
    }

    //==========================================================
    public static void main(String[] args) throws Exception {
        Movie movie1 = new Movie("Фантастические твари", new Time(1, 50));
        Seance seance1 = new Seance(movie1, new Time(20, 30));
        System.out.println(seance1);

        Movie movie2 = new Movie("Лара Крофт", new Time(90));
        Seance seance2 = new Seance(movie2, new Time(16, 0));
        System.out.println(seance2);

        Movie movie3 = new Movie("Аквамен", new Time(100));
        Seance seance3 = new Seance(movie3, new Time(18, 30));
        System.out.println(seance3);

        Shedule shedule = new Shedule();
        System.out.println("shedule.isEmpty is " + shedule.isEmpty());
        System.out.println("Added seance is " + shedule.addSeance(seance1));
        System.out.println("shedule.isEmpty is " + shedule.isEmpty());
        System.out.println("Added seance is " + shedule.addSeance(seance2));
        System.out.println("Added seance is " + shedule.addSeance(seance3));
        System.out.println(shedule.toString());

        Movie movie4 = new Movie("Боги Олимпа", new Time(110));
        Seance seance4 = new Seance(movie3, new Time(13, 0));
        System.out.println(seance4);
//        Seance e = (Seance) ((TreeSet) shedule.seances).floor(seance4);
        System.out.println("Added seance is " + shedule.addSeance(seance4));
        System.out.println(shedule);

        System.out.println("Delete seances of movie is " + shedule.removeSeanceOfMovie(movie3));
        System.out.println("Delete seance is " + shedule.removeSeance(seance3));
        System.out.println(shedule );

    }

}
