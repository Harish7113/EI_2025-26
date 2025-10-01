import java.util.HashMap;
import java.util.Map;

public class PlayerRegistry {
    private Map<String, Player> playerMap = new HashMap<>();

    public void addPrototype(String key, Player player) {
        playerMap.put(key, player);
    }

    public Player getPrototype(String key) {
        Player prototype = playerMap.get(key);
        if (prototype != null) {
            return prototype.clone();
        }
        return null;
    }
}
