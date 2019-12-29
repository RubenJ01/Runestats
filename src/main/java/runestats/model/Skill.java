package runestats.model;

public class Skill {

    private String name;
    private int rank;
    private int level;
    private long xp;

    public Skill(String name, int level, long xp, int rank) {
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                ", level=" + level +
                ", xp=" + xp +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getXp() {
        return xp;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }
}
