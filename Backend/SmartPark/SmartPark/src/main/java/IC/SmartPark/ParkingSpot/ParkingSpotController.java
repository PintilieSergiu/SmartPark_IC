package IC.SmartPark.ParkingSpot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingSpotController {
    @Autowired
    public final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @GetMapping("/getAvailableParkingSpot")
    public String getAvailableParkingSpot() {
        return parkingSpotService.getAvalableParkingSpot().getName();
    }
}
