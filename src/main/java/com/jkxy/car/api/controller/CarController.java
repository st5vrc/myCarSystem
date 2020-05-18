package com.jkxy.car.api.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jkxy.car.api.dao.InventoryDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.pojo.Inventory;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.service.InventoryService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;
    @Resource
    InventoryDao inventoryDao;
    @Resource
    InventoryService inventoryService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    @GetMapping("findAllInventory")
    public JSONResult findAllInventory() {
        List<Inventory> inventories = inventoryDao.findAll();
        return JSONResult.ok(inventories);
    }

    @PostMapping("buyCar")
    public String buyCar(int carId) {
        return inventoryService.buyCar(carId);

    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName")
    public JSONResult findByCarName(@RequestParam String carName,@RequestParam int pageNum,@RequestParam int pageSize) {
        carName="%"+carName.toLowerCase()+"%";
        PageHelper.startPage(pageNum, pageSize);
        List<Car> cars = carService.findByCarName(carName);
        PageInfo info = new PageInfo(cars);
        return JSONResult.ok(info);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }
}
