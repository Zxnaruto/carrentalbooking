package com.zhaixin.carrentalbooking.service.impl;

import com.zhaixin.carrentalbooking.config.RedisLock;
import com.zhaixin.carrentalbooking.constant.CommonResult;
import com.zhaixin.carrentalbooking.dao.CarInfoDao;
import com.zhaixin.carrentalbooking.dto.CarInfoPageDTO;
import com.zhaixin.carrentalbooking.entity.CarInfo;
import com.zhaixin.carrentalbooking.entity.builder.CarInfoBuilder;
import com.zhaixin.carrentalbooking.entity.builder.CommonResultBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class CarInfoServiceImplTest {


    @InjectMocks
    private CarInfoServiceImpl carInfoService;

    @Mock
    private CarInfoDao carInfoDao;

    @Mock
    private RedisLock redisLock;



    @BeforeEach
    public void setUp() {
        initMocks(this);
        //this.carInfoDao = Mockito.mock(CarInfoDao.class);
        //this.redisLock = Mockito.mock(RedisLock.class);
        //this.carInfoService = Mockito.mock(CarInfoServiceImpl.class);
       // this.carInfoService = new CarInfoServiceImpl(carInfoDao, redisLock);
    }

    @Test
    void showCarInfoReturnSuccessCarInfo() {
        CarInfo carInfo = new CarInfoBuilder().build();
        List<CarInfo> carInfos = new ArrayList<>();
        carInfos.add(carInfo);
        when(carInfoDao.getCarInfoByPage(Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(carInfos);
        CarInfoPageDTO carInfoPageDTO = new CarInfoPageDTO();
        carInfoPageDTO.setCarInfos(carInfos);
        carInfoPageDTO.setTotal(carInfos.size());
        CommonResult<CarInfoPageDTO> commonResult = new CommonResultBuilder<CarInfoPageDTO>().withData(carInfoPageDTO).builder();
        when(carInfoService.showCarInfo(Mockito.any(Integer.class),Mockito.any(Integer.class),null).getData().getCarInfos())
                .thenReturn(commonResult.getData().getCarInfos());

        assertThat(carInfoDao.getCarInfoByPage(Mockito.any(Integer.class), Mockito.any(Integer.class)), is(carInfos));
        assertThat(carInfoService.showCarInfo(Mockito.any(Integer.class),Mockito.any(Integer.class),null).getData().getCarInfos(), is(carInfos));
    }

    @Test
    void showCarInfoReturnSuccessResult() {
        CarInfo carInfo = new CarInfoBuilder().build();
        List<CarInfo> carInfos = new ArrayList<>();
        carInfos.add(carInfo);
        when(carInfoDao.getCarInfoByPageAndType(Mockito.any(Integer.class), Mockito.any(Integer.class), Mockito.any(Integer.class))).thenReturn(carInfos);
        CarInfoPageDTO carInfoPageDTO = new CarInfoPageDTO();
        carInfoPageDTO.setCarInfos(carInfos);
        carInfoPageDTO.setTotal(carInfos.size());
        CommonResult<CarInfoPageDTO> commonResult = new CommonResultBuilder<CarInfoPageDTO>().withData(carInfoPageDTO).builder();
        when(carInfoService.showCarInfo(Mockito.any(Integer.class),Mockito.any(Integer.class),Mockito.any(Integer.class)).getData().getCarInfos())
                .thenReturn(commonResult.getData().getCarInfos());

        assertThat(carInfoService.showCarInfo(Mockito.any(Integer.class),Mockito.any(Integer.class),Mockito.any(Integer.class)).getData().getCarInfos(), is(carInfos));
    }



//    @Test
//    void addCarInfo() {
//    }
//
//    @Test
//    void upAndDownCarInfo() {
//    }
//
//    @Test
//    void updateCarInfo() {
//    }
//
//    @Test
//    void carStockAdd() {
//    }
//
//    @Test
//    void carStockSub() {
//    }
}