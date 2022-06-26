package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkingManagerTest {

    @Test
    void should_return_ticket_given_manage_two_boys_when_park() {
        // given
        Parking boyA = new SmartParkingBoy(List.of(new ParkingLot(1)));
        Parking boyB = new SmartParkingBoy(List.of(new ParkingLot(1)));
        Parking manager = new ParkingManager(List.of(boyA, boyB));

        // when
        Car myCar = new Car();
        Ticket ticket = manager.park(myCar);

        // then
        assertThat(boyA.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_return_ticket_given_manage_two_lots_when_park() {
        // given
        Parking lotA = new ParkingLot(1);
        Parking lotB = new ParkingLot(1);
        Parking manager = new ParkingManager(List.of(lotA, lotB));

        // when
        Car myCar = new Car();
        Ticket ticket = manager.park(myCar);

        // then
        assertThat(lotA.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_return_ticket_given_manage_two_lots_and_two_boys_only_boys_have_vacancy_when_park() {
        // given
        Parking boyA = new SmartParkingBoy(List.of(new ParkingLot(1)));
        Parking boyB = new SmartParkingBoy(List.of(new ParkingLot(1)));
        Parking lotA = new ParkingLot(1);
        Parking lotB = new ParkingLot(1);
        Parking manager = new ParkingManager(List.of(boyA, boyB, lotA, lotB));
        lotA.park(new Car());
        lotB.park(new Car());

        // when
        Car myCar = new Car();
        Ticket ticket = manager.park(myCar);

        // then
        assertThat(boyA.pickup(ticket)).isEqualTo(myCar);
    }

    @Test
    void should_throw_parking_lot_is_full_exception_given_manage_two_lots_and_two_boys_all_have_no_vacancy_when_park() {
        // given
        Parking boyA = new SmartParkingBoy(List.of(new ParkingLot(1)));
        Parking boyB = new SmartParkingBoy(List.of(new ParkingLot(1)));
        Parking lotA = new ParkingLot(1);
        Parking lotB = new ParkingLot(1);
        Parking manager = new ParkingManager(List.of(boyA, boyB, lotA, lotB));
        lotA.park(new Car());
        lotB.park(new Car());
        boyA.park(new Car());
        boyB.park(new Car());

        // when
        assertThatThrownBy(() -> manager.park(new Car()))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("停车场已满");
    }

    @Test
    void should_return_car_when_pick_up_given_A_B_parking_lot_and_the_car_is_parked_at_B() {
        // given
        Parking parkA = new ParkingLot(1);
        Parking parkB = new ParkingLot(2);
        Car myCar = new Car();
        Parking manager = new ParkingManager(List.of(parkA, parkB));
        Ticket ticket = manager.park(myCar);

        // when
        Car actual = manager.pickup(ticket);

        // then
        assertThat(actual).isEqualTo(myCar);
    }

    @Test
    void should_throw_invalid_ticket_exception_when_pickup_given_a_used_ticket() {
        // given
        Parking parkA = new ParkingLot(1);
        Car myCar = new Car();
        Ticket ticket = parkA.park(myCar);
        parkA.pickup(ticket);
        Parking manager = new ParkingManager(List.of(parkA));

        // when & then
        assertThatThrownBy(() -> manager.pickup(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");
    }
}