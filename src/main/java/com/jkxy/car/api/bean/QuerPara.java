package com.jkxy.car.api.bean;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class QuerPara {
    int pageNum;
    int pageSize;
    String carName;
}
