package org.oobootcamp.warmup;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraduateParkingBoyTest {

    // AC1：当有停车场有空车位时，银卡VIP用户来停车，小弟按序停车成功，用户获得停车票
    // - Example
    //    -  Given A/B/C三个停车场有车位, When 银卡用户张三来停车, Then 在A停车场停车成功并获得A停车场的停车票
    @Test
    void should_return_ticket_of_A_when_parking_by_silver_member_given_A_B_C_all_have_available_lots() {
        // given

        // when

        // then

    }

    // AC2：当所有停车场无空车位时，银卡VIP用户来停车，小弟提示：“停车场已满”
    // - Example
    //    -  Given A/B/C三个停车场都无车位, When 银卡VIP用户张三来停车, Then 无法停车并获得提示“暂无车位”
    @Test
    void should_throw_parking_lot_is_full_exception_when_parking_by_silver_member_given_A_B_C_all_have_no_available_lots() {
        // given

        // when

        // then

    }

    // ### AC3：当非银卡VIP用户来停车，小弟提示：“请先充值办理银卡VIP会员”
    // - Example
    //    -  Given 张三是普通用户, When 张三来停车, Then 无法停车并获得提示“请先充值办理银卡VIP会员”
    @Test
    void should_throw_none_qualification_exception_when_parking_given_a_un_silver_member() {
        // given

        // when

        // then

    }

    // AC4：当用户拿有效停车票来取车，小弟成功取回票中对应停车场的对应车辆
    // - Example
    //    -  Given 张三手拿停车场C的有效停车票, When 张三来取车, Then 小弟成功取回停在停车场C的车
    @Test
    void should_return_car_when_pickup_given_a_valid_ticket() {
        // given

        // when

        // then

    }

    // AC5：当用户拿无效停车票来取车，小弟提示：“此票无效，请检查”
    // - Example
    //    -  Given 张三手拿已使用过的停车票, When 张三来取车, Then 小弟提示：“此票无效，请检查”
    @Test
    void should_throw_invalid_ticket_exception_when_pickup_given_a_used_ticket() {
        // given

        // when

        // then

    }
}