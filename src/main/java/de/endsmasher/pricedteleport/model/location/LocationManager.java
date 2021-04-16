package de.endsmasher.pricedteleport.model.location;

import de.endsmasher.pricedteleport.backend.LocationBackend;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LocationManager {

    private ArrayList<NavigatorLocations> locations = new ArrayList<>();
    private final LocationBackend locationBackend;

    public void add(NavigatorLocations location) {
        if (locations.contains(location)) return;
        locations.add(location);
        locationBackend.save(location);
    }

    public void setArrayList(List<NavigatorLocations> locationList) {
        locations.clear();
        locations.addAll(locationList);
    }

    public NavigatorLocations get(String id) {
        for (NavigatorLocations locationList: locations) {
            if (locationList.getId().equals(id)) {
                return locationList;
            }
        }
        return null;
    }

    public NavigatorLocations get(String name, boolean ignored) {
        for (NavigatorLocations locationList: locations) {
            if (locationList.getName().equals(name)) {
                return locationList;
            }
        }
        return null;
    }

    public List<String> getNameList() {
        List<String> names = new ArrayList<>();
        locations.forEach(navigatorLocations -> names.add(navigatorLocations.getName()));
        return names;
    }

    public void save(NavigatorLocations navigatorLocations) {
        locations.remove(navigatorLocations);
        locations.add(navigatorLocations);
        locationBackend.save(navigatorLocations);
    }

    public void remove(String name, boolean ignored) {
        locationBackend.remove(get(name, true).getId());
        locations.removeIf(locationList -> locationList.getName().equals(name));
    }

    public void remove(String id) {
        locations.removeIf(locationList -> locationList.getId().equals(id));
        locationBackend.remove(id);
    }
}
