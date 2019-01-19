package Cinema;

public class Movie implements Cloneable {
    private String title;
    private Time duration;

    public Movie(String title, Time duration) {
        this.title = title;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title = '" + title + '\'' +
                ", duration = " + duration +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movie m = (Movie) obj;
        return (this.title == m.title &&
                this.duration.getDurationOfMin() == ((Movie) obj).duration.getDurationOfMin()
               );
    }

    @Override
    protected Movie clone() throws CloneNotSupportedException {
        Movie cloneMovie = (Movie)super.clone();
        cloneMovie.duration = this.duration.clone();
        return cloneMovie;
    }

    public int getDuration() {
        return this.duration.getDurationOfMin();
    }

    //для тестирования
    public static void main(String[] args) throws Exception, CloneNotSupportedException {
        Movie m1 = new Movie("Ёлки", new Time(1,50));
        System.out.println("m1: " + m1);
        Movie m2 = m1.clone();
        m1.title = "asd";
        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);

        Movie m3 = new Movie("Ёлки", new Time(1,50));
        boolean eq = m2.equals(m3);
        System.out.println("m3 is " + eq + " m2");
    }
}
