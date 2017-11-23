package com.js.service;

public interface RatioCalculateService {


  /**
   * If Ratio = Liquidity Ratio && Sub Ratio = Current Ratio
   * %Calculate Current Ratio = Current Assets/Current Liabilities
   * Input = Balance Sheet
   * @param currentAsset
   * @param currentLiabilities
   * @return
   */
  double currentRatio(double currentAsset, double currentLiabilities);
}
