package runestats.model;

public enum GameMode {
    NORMAL("/index_lite.ws?player=", "Normal"), IRONMAN("_ironman/index_lite.ws?player=", "Ironman"),
    ULTIMATE_IRONMAN("_ultimate/index_lite.ws?player=", "Ultimate Ironman"), HARDCORE_IRONMAN("_hardcore/index_lite.ws?player=", "Hardcore Ironman"),
    DEADMAN_MODE("_deadman/index_lite.ws?player=", "Deadman Mode"), LEAGUES("_seasonal/index_lite.ws?player=", "Leagues"),
    TOURNAMENT("_tournament/index_lite.ws?player=", "Tournament");

    private String url;
    private String name;

    GameMode(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return name;
    }
}
