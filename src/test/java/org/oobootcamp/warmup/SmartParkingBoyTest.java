package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SmartParkingBoyTest {
    @Test
    void should_return_ticket_given_one_lot_vacancy_when_park() {
        // given
        Car myCar = new Car();
        ParkingLot parkingLotA = new ParkingLot(1);
        Parking smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA));

        // when
        Ticket ticket = smartParkingBoy.park(myCar);

        // then
        assertThat(parkingLotA.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_return_ticket_given_two_lot_has_one_vacancy_when_park() {
        // given
        Car myCar = new Car();
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(1);
        Parking smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));

        // when
        Ticket ticket = smartParkingBoy.park(myCar);

        // then
        assertThat(parkingLotA.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_return_ticket_given_one_lot_has_one_vacancy_and_other_has_two_when_park() {
        // given
        Car myCar = new Car();
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        Parking smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB));
        // when
        Ticket ticket = smartParkingBoy.park(myCar);

        // then
        assertThat(parkingLotB.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_return_ticket_given_one_lot_has_one_vacancy_and_other_two_have_same_available_lot_when_park() {
        // given
        Car myCar = new Car();
        ParkingLot parkingLotA = new ParkingLot(1);
        ParkingLot parkingLotB = new ParkingLot(2);
        ParkingLot parkingLotC = new ParkingLot(2);
        Parking smartParkingBoy = new SmartParkingBoy(List.of(parkingLotA, parkingLotB, parkingLotC));
        // when
        Ticket ticket = smartParkingBoy.park(myCar);

        // then
        assertThat(parkingLotB.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_throw_parking_lot_is_full_exception_when_park_given_A_B_is_full() {
        // given
        Car myCar = new Car();
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(1);
        Parking boy = new SmartParkingBoy(List.of(parkA, parkB));

        parkA.park(new Car());
        parkB.park(new Car());

        // when & then
        assertThatThrownBy(() -> boy.park(myCar))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("停车场已满");
    }

    @Test
    void should_return_car_when_pick_up_given_A_B_parking_lot_and_the_car_is_parked_at_B() {
        // given
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(2);
        Car myCar = new Car();
        Parking boy = new SmartParkingBoy(List.of(parkA, parkB));
        Ticket ticket = boy.park(myCar);

        // when
        Car actual = boy.pickup(ticket);

        // then
        assertThat(actual).isEqualTo(myCar);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_pickup_given_a_used_ticket() {
        // given
        ParkingLot parkA = new ParkingLot(1);
        Car myCar = new Car();
        Ticket ticket = parkA.park(myCar);
        parkA.pickup(ticket);
        Parking boy = new SmartParkingBoy(List.of(parkA));

        // when & then
        assertThatThrownBy(() -> boy.pickup(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");
    }
}