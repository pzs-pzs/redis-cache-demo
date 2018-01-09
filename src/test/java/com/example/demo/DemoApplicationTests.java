package com.example.demo;

import com.example.demo.domain.City;
import com.example.demo.service.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityService.class);
	@Test
	public void contextLoads() {
	}

	@Autowired
    private CityService service;
	@Test
	public void testRedis() {
        City city = getChengdu();
        service.saveCity(city);
        City cityInfo = service.getCityByName("成都");
        LOGGER.info(cityInfo.toString());
	}

	@Test
    public void testRdisCache() {
	    City city = getChengdu();
	    service.saveCity(city);
	    City cityInfo = service.getCityByName("成都");
	    //从数据库中查询，并写入redis-cache
        System.out.println("第一次查询："+ cityInfo.toString());

        //从redis-cache中读取
	    cityInfo = service.getCityByName("成都");
        System.out.println("第二次查询："+cityInfo.toString());

        cityInfo = getXian();
        service.saveCity(cityInfo);
	    service.updateCityDescription("西安","兵马俑");
//	    cityInfo = service.getCityByName("西安");
//        System.out.println("更新描述后的查询："+cityInfo.toString());

//        service.deleteCityByName("成都");
//        service.deleteCityByName("西安");
    }

    private City getChengdu(){
        return new City(1L, 10L, "成都", "天府之都");
    }

    private City getXian(){
        return new City(2L, 20L, "西安", "十三朝古都");
    }

}
