package com.zhaixin.carrentalbooking.cotroller;

import com.zhaixin.carrentalbooking.config.MyUserDetailsService;
import com.zhaixin.carrentalbooking.config.WithMockCustomUser;
import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.entity.CarInfo;
import com.zhaixin.carrentalbooking.entity.builder.CarInfoBuilder;
import com.zhaixin.carrentalbooking.entity.builder.CommonResultBuilder;
import com.zhaixin.carrentalbooking.service.CarInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CarInfoController.class)
class CarInfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarInfoService carInfoService;
    @MockBean
    private MyUserDetailsService myUserDetailsService;



    @Test
    @WithMockCustomUser
    public void shouldReturnString() throws Exception {
        CarInfo carInfo = new CarInfoBuilder().build();
        List<CarInfo> carInfos = new ArrayList<>();
        carInfos.add(carInfo);
        CarInfoPageDTO carInfoPageDTO = new CarInfoPageDTO();
        carInfoPageDTO.setCarInfos(carInfos);
        carInfoPageDTO.setTotal(carInfos.size());
        CommonResult<CarInfoPageDTO> commonResult = new CommonResultBuilder<CarInfoPageDTO>().withData(carInfoPageDTO).builder();
        when(carInfoService.showCarInfo(Mockito.anyInt(),Mockito.anyInt(), Mockito.isNull())).thenReturn(commonResult);

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("carInfos", commonResult.getData().getCarInfos()));
    }

}