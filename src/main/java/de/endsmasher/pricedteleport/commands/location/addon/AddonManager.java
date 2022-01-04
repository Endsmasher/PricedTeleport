package de.endsmasher.pricedteleport.commands.location.addon;

import java.util.HashMap;
import java.util.Map;

public class AddonManager {

    private Map<String, CommandBase> commandBaseList = new HashMap<>();

    public void add(String string, CommandBase commandBase) {
        commandBaseList.put(string, commandBase);
    }

    public CommandBase get(String string) {
        return commandBaseList.get(string);
    }
}
