package com.nijikawa.demo.entity;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("public.rides")
public class Rides {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dropoff_datetime;
    private Long dropoff_latitude;
    private Long dropoff_longitude;
    private int extra;
    private int fare_amount;
    private int improvement_surcharge;
    private int mta_tax;
    private int passenger_count;
    private int payment_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pickup_datetime;
    private Long pickup_latitude;
    private Long pickup_longitude;
    private int rate_code;
    private int sort;
    private int tip_amount;
    private int tolls_amount;
    private int total_amount;
    private int trip_distance;
    private String vendor_id;
}
