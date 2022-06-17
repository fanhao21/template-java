package org.oobootcamp.warmup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

class ParkingLotUnitTest {
    // Retrieving
    // AC : 用户拿着有效票去停车场取车，
    // Given a valid ticket when pick up a car then getting a car
    @Test
    void should_get_the_car_when_pick_up_given_a_valid_ticket() {
        // given
        Car parkedCar = new Car();
        Integer capacity = 3;
        ParkingLot parkingLot = new ParkingLot(capacity);
        Ticket ticket = parkingLot.park(parkedCar);

        // when
        Car pickedUpCar = parkingLot.pickUp(ticket);

        // then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    // AC 2:
    // Given a used ticket when pick then return invalid ticket message
    @Test
    void should_throw_invalid_ticket_exception_when_pick_up_given_an_ticket_used() {
        // given
        Integer capacity = 10;
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(capacity);
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickUp(ticket);

        // when & then
        assertThatThrownBy(() -> parkingLot.pickUp(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");

    }

    // AC 3:
    // Given an invalid ticket when retrieve then getting no car
    @Test
    void should_get_no_car_when_retrieve_given_an_invalid_ticket() {
        // given
        Integer capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);

        // when & then
        Ticket ticket = new Ticket();
        assertThatThrownBy(() -> parkingLot.pickUp(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");
    }

    // Parking
    // AC： 当停车场有空位时，用户去停车，能停车成功并且取到停车票
    // eg - Given: 停车场有1个空位， When:用户去停车  Then:取到停车票
    // Given a car and enough lots when parking then getting a ticket
    @Test
    void should_get_a_parking_ticket_when_parking_given_a_car_and_available_lots() {
        // given
        Integer capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        Car car = new Car();

        // when
        Ticket ticket = parkingLot.park(car);

        // then
        assertThat(ticket).isNotNull();
    }

    // AC 2:
    // AC : 停车场没有空位，用户去停车，得到停车场已满提示
    // eg - Given: 没有空闲位置停车场  When:用户去停车  Then: 得到停车场已满提示
    // Given a car and no empty lots when parking then cannot park
    @Test
    void should_throw_parking_lot_is_full_exception_when_park_given_a_car_and_no_available_lots() {
        // given
        Integer capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.park(new Car());
        Car car = new Car();

        // when & then
        assertThatThrownBy(() -> parkingLot.park(car))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("停车场已满");
    }

}