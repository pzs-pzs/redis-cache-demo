package com.example.demo.service;

import com.example.demo.domain.City;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class CityService {
    // 模拟数据库存储
    private Map<String, City> cityMap = new HashMap<String, City>();

    public void saveCity(City city){
        // 模拟数据库插入操作
        cityMap.put(city.getCityName(), city);
    }

    @Cacheable(value = "baseCityInfo" ,key = "#cityName")
    public City getCityByName(String cityName){
        // 模拟数据库查询并返回
        // 设置睡眠，可以看出是使用的缓存数据
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        City city = cityMap.get(cityName);
        return city;
    }

    @CachePut(value = "baseCityInfo",key = "#cityName")
    public void updateCityDescription(String cityName, String description){
        City city = cityMap.get(cityName);
        city.setDescription(description);
        // 模拟更新数据库
        cityMap.put(cityName, city);
    }
    @CacheEvict(value = "baseCityInfo",key = "#cityName")
    public void deleteCityByName(String cityName) {
//      模拟删除数据
        cityMap.remove(cityName);
    }
}
