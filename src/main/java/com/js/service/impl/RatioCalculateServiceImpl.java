package com.js.service.impl;

import com.js.service.RatioCalculateService;
import org.springframework.stereotype.Service;

@Service
public class RatioCalculateServiceImpl implements RatioCalculateService {

  @Override
  public double currentRatio(double currentAsset, double currentLiabilities) {
    return currentAsset/currentLiabilities;
  }
}
