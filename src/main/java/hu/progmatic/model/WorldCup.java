package hu.progmatic.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.TreeSet;

public class WorldCup {
    final static String SEPARATOR = ";";
    final private int year;
    private String host;
    private String confederation;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private final Set<Match> matches = new TreeSet<>();

    public WorldCup(int year, String host, String confederation, LocalDate dateFrom, LocalDate dateTo) {
        this.year = year;
        this.host = host;
        this.confederation = confederation;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public WorldCup(String line){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String[] parameters = line.split(SEPARATOR);
        this.year = Integer.parseInt(parameters[0]);
        this.host = parameters[1];
        this.confederation = parameters[2];
        this.dateFrom = LocalDate.parse(parameters[3],formatter);
        this.dateTo = LocalDate.parse(parameters[4],formatter);
    }

    public int getYear() {
        return year;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getConfederation() {
        return confederation;
    }

    public void setConfederation(String confederation) {
        this.confederation = confederation;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "WorldCup{" +
                "year=" + year +
                ", host='" + host + '\'' +
                ", confederation='" + confederation + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", matches=" + matches +
                '}';
    }
}
