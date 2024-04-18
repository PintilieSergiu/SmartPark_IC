package IC.SmartPark.ParkingSpot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParkingSpotService {
    @Autowired
    private final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    public ParkingSpot getAvalableParkingSpot(){
        ParkingSpot p=parkingSpotRepository.findFirstByEmptyParkingSpotTrueAndBookedParkingSpotFalse();
        if(p!=null){
            return p;
        }
        else return null;
        //return null;
    }
}
