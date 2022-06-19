package org.oobootcamp.warmup;

import java.util.List;

public record GraduateParkingBoy(List<Customer> members, List<ParkingLot> parkingLots) {

    public Ticket park(Customer customer, Car car) {
        checkMembers(customer);
        for (ParkingLot parkingLot : parkingLots) {
            try {
                return parkingLot.park(car);
            } catch (RuntimeException ignored) {
            }
        }
        throw new RuntimeException("停车场已满");
    }

    private void checkMembers(Customer customer) {
        boolean isMember = members.stream().anyMatch(it -> it.equals(customer));
        if (!isMember) {
            throw new RuntimeException("请先充值办理银卡VIP会员");
        }
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
