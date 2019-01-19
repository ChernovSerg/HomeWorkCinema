package Cinema;

public class Seance implements Comparable<Seance> {
    private Movie movie;
    private Time startTime;
    private Time endTime;

    Seance(Movie movie, Time startTime) throws CloneNotSupportedException, Exception {
        this.movie = movie.clone();
        this.startTime = startTime;
        this.endTime = calcEndTime(startTime, movie.getDuration());
    }

    //вычисляем время окончания сеанса
    private static Time calcEndTime(Time startTime, int durationOfMin) throws Exception {
        int result = startTime.getDurationOfMin() + durationOfMin;
        if (result > 1440) {
            result -= 1440;
        }
        return new Time(result / 60, result % 60);
    }

    public Time getEndTime() {
        return endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Movie getMovie () { return movie; }

    public Seance containsMovie(Movie movie) {
        if (this.movie.equals(movie)) {
            return this;
        }
        else
            return null;
    }

    @Override
    public String toString() {
        return "Seance{ " + movie +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                " }";
    }

    @Override
    public int compareTo(Seance o) {
        return Integer.compare(startTime.getDurationOfMin(), o.startTime.getDurationOfMin());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Seance s = (Seance) obj;
        return (movie.equals(s.movie) && startTime.getDurationOfMin() == s.startTime.getDurationOfMin());
    }

    //==========================================================
    public static void main(String[] args) throws Exception {
        Movie movie1 = new Movie("Фантастические твари", new Time(1, 50));
        Seance seance1 = new Seance(movie1, new Time(23, 0));
        System.out.println(seance1);

        Movie movie2 = new Movie("Лара Крофт", new Time(140));
        Seance seance2 = new Seance(movie2, new Time(21, 0));
        System.out.println(seance2);
    }

}
