package com.js.service.impl;

import com.js.service.RatioCalculateService;
import org.springframework.stereotype.Service;

@Service
public class RatioCalculateServiceImpl implements RatioCalculateService {

  @Override
  public int add(int a, int b) {
    return a+b;
  }
}
