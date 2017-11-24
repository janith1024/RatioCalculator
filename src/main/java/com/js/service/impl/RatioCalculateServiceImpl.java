package com.js.service.impl;

import com.js.service.RatioCalculateService;
import org.springframework.stereotype.Service;

@Service
public class RatioCalculateServiceImpl implements RatioCalculateService {

  @Override
  public double currentRatio(double currentAsset, double currentLiabilities) {
    return currentAsset/currentLiabilities;
  }

  @Override
  public double quickRatio(double currentAsset, double prepaidExpenses, double currentLiabilities) {
    return (currentAsset-prepaidExpenses)/currentLiabilities;
  }

  @Override
  public double absoluteLiquidRatio(double absoluteLiquidAssets, double currentLiabilities) {
    return absoluteLiquidAssets/currentLiabilities;
  }

  @Override
  public double grossProfitRatio(double grossProfit, double revenue) {
    return grossProfit/revenue*100;
  }

  @Override
  public double netProfitRatio(double netProfitAfterTax, double revenue) {
    return netProfitAfterTax/revenue*100;
  }

  @Override
  public double operatingProfitRatio(double operatingProfit, double revenue) {
    return operatingProfit/revenue*100;
  }

  @Override
  public double earningPerShareRatio(double profitAfterTax, double numberOfShares) {
    return profitAfterTax/numberOfShares;
  }

  @Override
  public double returnOnEquityRatio(double netProfitAfterTax, double equity) {
    return netProfitAfterTax/equity*100;
  }

  @Override
  public double dividendPayoutRatio(double equityDividend, double netProfitAfterTax) {
    return equityDividend/netProfitAfterTax*100;
  }
}
