import java.util.*;

/**
 * ============================================================
 * CLASS - AddOnService
 * ============================================================
 */
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

/**
 * ============================================================
 * CLASS - AddOnServiceManager
 * ============================================================
 */
class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) return 0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getCost();
        }

        return total;
    }
}

/**
 * ============================================================
 * MAIN CLASS
 * ============================================================
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        // Example reservation ID (from previous use case)
        String reservationId = "Single-1";

        // Create manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Add services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Spa", 1000));

        // Display result
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " +
                manager.calculateTotalServiceCost(reservationId));
    }
}