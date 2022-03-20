package com.zhaixin.carrentalbooking.entity.builder;

import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.entity.CarInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarInfoPageDTOBuilder {
    List<CarInfo> carInfos = Collections.singletonList(new CarInfoBuilder().build());
    Integer total = 1;

    public CarInfoPageDTOBuilder withCarInfos(List<CarInfo> carInfos) {
        this.carInfos = carInfos;
        return this;
    }

    public CarInfoPageDTOBuilder withTotal(Integer total) {
        this.total = total;
        return this;
    }

    public CarInfoPageDTO build() {
        return  new CarInfoPageDTO(carInfos, total);
    }
}
