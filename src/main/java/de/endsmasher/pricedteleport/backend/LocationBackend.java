package de.endsmasher.pricedteleport.backend;

import de.endsmasher.pricedteleport.model.location.NavigatorLocations;

import java.util.List;

public interface LocationBackend {

    void save(NavigatorLocations navigatorLocations);
    List<NavigatorLocations> load();
    void remove(String name);
}
