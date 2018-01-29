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
  public double grossProfitRatio(double grossProfit, double netSales) {
    return grossProfit/netSales*100;
  }

  @Override
  public double netProfitRatio(double netProfitAfterTax, double netSales) {
      return netProfitAfterTax/netSales*100;
  }

  @Override
  public double operatingProfitRatio(double operatingProfit, double netSales) {
      return operatingProfit/netSales*100;
  }

  @Override
  public double earningsPerShareRatio(double netProfitAfterTax, double numberOfEquityShares) {
    return netProfitAfterTax/numberOfEquityShares;
  }

  @Override
  public double returnOnEquityRatio(double netProfitAfterInterestAndTax, double shareholdersFunds) {
    return netProfitAfterInterestAndTax/shareholdersFunds*100;
  }

  @Override
  public double dividendPayoutRatio(double equityDividend, double netProfitAfterTax) {
    return equityDividend/netProfitAfterTax*100;
  }
  @Override
  public double stockTurnoverRatio(double costOfSales, double averageInventory) {
      return costOfSales/averageInventory;
  }
  @Override
    public double fixedAssetTurnoverRatio (double netSales, double netPropertyPlantAndEquipment){
      return netSales/netPropertyPlantAndEquipment;
  }
  @Override
    public double workingCapitalTurnoverRatio (double netSales, double capitalEmployed){
        return netSales/capitalEmployed;
  }
  @Override
    public double debtEquityRatio (double totalLiabilities, double totalEquity){
        return totalLiabilities/totalEquity;
  }
}

