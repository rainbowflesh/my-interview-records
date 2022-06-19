package com.nijikawa.liteblog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nijikawa.liteblog.entity.Search;
import com.nijikawa.liteblog.mapper.SearchMapper;
import com.nijikawa.liteblog.service.SearchService;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search> implements SearchService {
}
