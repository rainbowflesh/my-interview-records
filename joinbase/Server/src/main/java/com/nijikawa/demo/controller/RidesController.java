package com.nijikawa.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nijikawa.demo.entity.Function;
import com.nijikawa.demo.entity.Rides;
import com.nijikawa.demo.service.RidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class RidesController {
    @Autowired
    private RidesService ridesService;
    @PostMapping("/v1/limit")
    public List<Rides> list(@RequestBody Function function) {
        int limitSize = function.getSize();
        System.out.println("request content " + limitSize);
//        return null;
        return ridesService.list(new QueryWrapper<Rides>().last("limit " + limitSize));
    }
    @GetMapping("/v1/all")
    public List<Rides> listAll() {
        return ridesService.list();
    }@PostMapping("/v1/sum")
    public List<Rides> sum(@RequestBody Function function) {
        return ridesService.list();
    }
}
