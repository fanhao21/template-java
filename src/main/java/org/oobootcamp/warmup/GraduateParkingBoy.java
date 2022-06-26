package org.oobootcamp.warmup;

import java.util.List;

public class GraduateParkingBoy extends ParkingDelegator {

    public GraduateParkingBoy(List<ParkingManagement> parkingManagements) {
        super(parkingManagements);
    }

    @Override
    protected List<ParkingManagement> orderParkingManagements() {
        return parkingManagements;
    }
}
