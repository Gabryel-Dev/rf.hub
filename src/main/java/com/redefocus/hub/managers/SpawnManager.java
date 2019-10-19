package com.redefocus.hub.managers;

        import com.redefocus.hub.FocusHub;
        import com.redefocus.hub.util.LocationSerialize;
        import org.bukkit.Location;

public class SpawnManager {
    private static Location spawn;

    public SpawnManager() {
        String serializedLocation = FocusHub.getInstance().getConfig().getString("settings.spawn");

        SpawnManager.spawn = LocationSerialize.toLocation(serializedLocation);
    }

    public static Location getSpawn() {
        return SpawnManager.spawn;
    }

    public static void setSpawn(Location spawn) {
        SpawnManager.spawn = spawn;
    }
}
