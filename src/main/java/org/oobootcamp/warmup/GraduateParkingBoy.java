package org.oobootcamp.warmup;

import java.util.List;

public record GraduateParkingBoy(List<ParkingLot> parkingLots) {

    public Ticket park(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (RuntimeException ignored) {
            }
        }
        throw new RuntimeException("停车场已满");
    }


    public Car pickup(Ticket ticket) {
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.pickUp(ticket);
            } catch (RuntimeException ignored) {
            }
        }
        throw new RuntimeException("此票无效，请检查");
    }
}
