package com.nijikawa.demo.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nijikawa.demo.entity.Rides;
import com.nijikawa.demo.mapper.RidesMapper;
import com.nijikawa.demo.service.RidesService;
import org.springframework.stereotype.Service;

@Service
public class RidesServiceImpl extends ServiceImpl<RidesMapper, Rides> implements RidesService {
}
