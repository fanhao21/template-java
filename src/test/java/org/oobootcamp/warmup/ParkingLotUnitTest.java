package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParkingLotUnitTest {
    // AC1： 当停车场有空位时，用户去停车，能停车成功并且取到停车票
    // eg - Given: 停车场有1个空位， When:用户去停车  Then:取到停车票
    @Test
    void should_get_a_parking_ticket_when_parking_given_a_car_and_available_lots() {
        // given
        Integer capacity = 1;
        ParkingManagement parkingLot = new ParkingLot(capacity);
        Car car = new Car();

        // when
        Ticket ticket = parkingLot.park(car);

        // then
        assertThat(ticket).isNotNull();
    }

    // AC2: 停车场没有空位，用户去停车，得到停车场已满提示
    // eg - Given: 停车场没有空闲车位  When:用户去停车  Then: 提示：“停车场已满”
    @Test
    void should_throw_parking_lot_is_full_exception_when_park_given_a_car_and_no_available_lots() {
        // given
        Integer capacity = 1;
        ParkingManagement parkingLot = new ParkingLot(capacity);
        parkingLot.park(new Car());
        Car car = new Car();

        // when & then
        assertThatThrownBy(() -> parkingLot.park(car))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("停车场已满");
    }

    // AC3: 用户拿着有效票去停车场取车，取回票对应的车辆
    // eg: - Given: 用户拿有效票  When:用户去取车  Then: 取回票对应的车
    @Test
    void should_get_the_car_when_pick_up_given_a_valid_ticket() {
        // given
        Car parkedCar = new Car();
        Integer capacity = 3;
        ParkingManagement parkingLot = new ParkingLot(capacity);
        Ticket ticket = parkingLot.park(parkedCar);

        // when
        Car pickedUpCar = parkingLot.pickup(ticket);

        // then
        assertThat(pickedUpCar).isEqualTo(parkedCar);
    }

    // AC4: 用户拿已使用的票去停车场取车，得到提示："此票无效，请检查"
    // eg1: - Given: 用户拿已使用的票  When:用户去取车  Then: 用户得到提示："此票无效，请检查"
    @Test
    void should_throw_invalid_ticket_exception_when_pick_up_given_an_ticket_used() {
        // given
        Integer capacity = 10;
        Car car = new Car();
        ParkingManagement parkingLot = new ParkingLot(capacity);
        Ticket ticket = parkingLot.park(car);
        parkingLot.pickup(ticket);

        // when & then
        assertThatThrownBy(() -> parkingLot.pickup(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");
    }

    // AC4: 用户拿已使用的票去停车场取车，得到提示："此票无效，请检查"
    // eg2: - Given: 用户拿非法的票  When:用户去取车  Then: 用户得到提示："此票无效，请检查"
    @Test
    void should_get_no_car_when_retrieve_given_an_invalid_ticket() {
        // given
        Integer capacity = 10;
        ParkingManagement parkingLot = new ParkingLot(capacity);

        // when & then
        Ticket ticket = new Ticket();
        assertThatThrownBy(() -> parkingLot.pickup(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");
    }
}