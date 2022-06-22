package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GraduateParkingBoyTest {

    // AC : 至少有一个停车场有空闲停车位，车停到第一个有空位的停车场，用户获得停车票
    // - Given 只有A停车场有空闲停车位，When 停车，Then 停车到A停车场，并且获取的停车票
    @Test
    void should_return_ticket_when_park_given_only_A_parking_lot_and_A_has_vacancy() {
        // given
        Car car = new Car();
        ParkingLot parkingLotA = new ParkingLot(1);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(List.of(parkingLotA));

        // when
        Ticket ticket = parkingBoy.park(car);

        // then
        assertThat(ticket).isNotNull();
    }

    // -  Given A/B均有一个车位, When 停车, Then 在A停车场停车成功并获得停车票
    @Test
    void should_return_ticket_when_park_given_A_B_both_have_one_vacancy() {
        // given
        Car car = new Car();
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(1);
        GraduateParkingBoy boy = new GraduateParkingBoy(List.of(parkA, parkB));

        // when
        Ticket ticket = boy.park(car);

        // then
        assertThat(ticket).isNotNull();
    }
    // - Given A停车场已满，停车场B有一个空闲车位，When 停车, Then 停车到B停车场，并且获得停车票
    @Test
    void should_return_ticket_when_park_given_A_is_full_and_B_has_one_vacancy() {
        // given
        Car car = new Car();
        Car anotherCar = new Car();
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(1);
        GraduateParkingBoy boy = new GraduateParkingBoy(List.of(parkA, parkB));

        // when
        boy.park(anotherCar);
        Ticket ticket = boy.park(car);

        // then
        assertThat(ticket).isNotNull();
    }

    // AC 当所有停车场无空车位时, 提示：“停车场已满”
    // - Example
    //    -  Given A/B 2个停车场都无车位, When 停车, Then 无法停车并获得提示“暂无车位”
    @Test
    void should_throw_parking_lot_is_full_exception_when_park_given_A_B_is_full() {
        // given
        Car myCar = new Car();
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(1);
        GraduateParkingBoy boy = new GraduateParkingBoy(List.of(parkA, parkB));

        parkA.park(new Car());
        parkB.park(new Car());

        // when & then
        assertThatThrownBy(() -> boy.park(myCar))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("停车场已满");
    }

    // AC：当用户拿有效停车票来取车，小弟成功取回票中对应停车场的对应车辆
    // Given 有两个停车场A和B，用户的车在停车场B，有对应停车票 When 取车, Then 小弟成功取回停在停车场的车
    @Test
    void should_return_car_when_pick_up_given_A_B_parking_lot_and_the_car_is_parked_at_B() {
        // given
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(1);
        Car myCar = new Car();
        GraduateParkingBoy boy = new GraduateParkingBoy(List.of(parkA, parkB));
        boy.park(new Car());
        Ticket ticket = boy.park(myCar);

        // when
        Car actual = boy.pickup(ticket);

        // then
        assertThat(actual).isEqualTo(myCar);
    }
    //  Given 有两个停车场A和B，用户的车在停车场A，有对应停车票 When 取车, Then 小弟成功取回停在停车场的车
    @Test
    void should_return_car_when_pick_up_given_A_B_parking_lot_and_the_car_is_parked_at_A() {
        // given
        ParkingLot parkA = new ParkingLot(1);
        ParkingLot parkB = new ParkingLot(1);
        Car myCar = new Car();
        GraduateParkingBoy boy = new GraduateParkingBoy(List.of(parkA, parkB));
        Ticket ticket = boy.park(myCar);
        boy.park(new Car());

        // when
        Car actual = boy.pickup(ticket);

        // then
        assertThat(actual).isEqualTo(myCar);
    }


    // AC：使用过的停车票取票，提示：“此票无效，请检查”
    // Given用户停车到停车场，并且拿对应的票取到车，再用相同的票, When 取车, Then 提示：“此票无效，请检查”
    @Test
    void should_throw_invalid_ticket_exception_when_pickup_given_a_used_ticket() {
        // given
        ParkingLot parkA = new ParkingLot(1);
        Car myCar = new Car();
        Ticket ticket = parkA.park(myCar);
        parkA.pickUp(ticket);
        GraduateParkingBoy boy = new GraduateParkingBoy(List.of(parkA));

        // when & then
        assertThatThrownBy(() -> boy.pickup(ticket))
                .isInstanceOf(RuntimeException.class)
                .extracting("message")
                .isEqualTo("此票无效，请检查");
    }
}