package IC.SmartPark.ParkingSpot;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot,Integer> {
    public ParkingSpot findFirstByEmptyParkingSpotTrueAndBookedParkingSpotFalse();
}
