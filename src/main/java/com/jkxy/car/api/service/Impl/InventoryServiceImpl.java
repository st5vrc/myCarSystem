package com.jkxy.car.api.service.Impl;

import com.jkxy.car.api.dao.InventoryDao;

import com.jkxy.car.api.pojo.Inventory;
import com.jkxy.car.api.service.InventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class InventoryServiceImpl implements InventoryService {
    @Resource
    InventoryDao inventoryDao;

    @Override
    public String buyCar(int carId) {
        String result;
        List<Inventory> list = inventoryDao.findByCarId(carId);
        if (list.size() > 0) {
            if (list.get(0).getCount() > 0) {
                Inventory inventory = new Inventory();
                inventory.setCount(list.get(0).getCount() - 1);
                inventory.setId(list.get(0).getId());
                inventory.setCarId(list.get(0).getCarId());
                inventory.setLastUpdate(list.get(0).getLastUpdate());
                if (inventoryDao.updateById(inventory) > 0) {
                    result = "购车成功！";

                } else {

                    result = "购车成功！";
                }

            } else {
                result = "此车已售完！";
            }
        } else {
            result = "查不到此车！";
        }
        return result;
    }


}
