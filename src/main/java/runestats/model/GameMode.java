package runestats.model;

public enum GameMode {
    NORMAL("/index_lite.ws?player="), IRONMAN("_ironman/index_lite.ws?player="),
    ULTIMATE_IRONMAN("_ultimate/index_lite.ws?player="), HARDCORE_IRONMAN("_hardcore/index_lite.ws?player="),
    DEADMAN_MODE("_deadman/index_lite.ws?player="), LEAGUES("_seasonal/index_lite.ws?player="),
    TOURNAMENT("_tournament/index_lite.ws?player=");

    private String url;

    GameMode(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
