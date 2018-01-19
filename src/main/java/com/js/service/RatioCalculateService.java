package com.js.service;

public interface RatioCalculateService {


    /**
     * If Ratio = Liquidity Ratio && Sub Ratio = Current Ratio
     * Calculate Current Ratio = Current Assets/Current Liabilities
     * Input = Balance Sheet
     *
     * @param currentAsset
     * @param currentLiabilities
     * @return
     */
    double currentRatio(double currentAsset, double currentLiabilities);


    /**
     * If Ratio = Liquidity Ratio && Sub Ratio = Quick Ratio
     * Calculate Quick Ratio = Current Assets-Prepaid expenses/Current Liabilities
     * Input = Balance Sheet
     *
     * @param currentAsset
     * @param prepaidExpenses
     * @param currentLiabilities
     * @return
     */

    double quickRatio(double currentAsset, double prepaidExpenses, double currentLiabilities);

    /**
     * If Ratio = Liquidity Ratio && Sub Ratio = Absolute Liquidity Ratio
     * Calculate Absolute Liquidity Ratio = Absolute Liquid Assets/Current Liabilities
     * Input = Balance Sheet
     *
     * @param absoluteLiquidAssets
     * @param currentLiabilities
     * @return
     */
    double absoluteLiquidRatio(double absoluteLiquidAssets, double currentLiabilities);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Gross Profit Ratio
     * Calculate Gross Profit Ratio = Gross Profit/Revenue*100
     * Input = Income Statement
     *
     * @param grossProfit
     * @param revenue
     * @return
     */
    double grossProfitRatio(double grossProfit, double revenue);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Net Profit Ratio
     * Calculate Net Profit Ratio = Net Profit after Tax/Revenue*100
     * Input = Income Statement
     *
     * @param netProfitAfterTax
     * @param revenue
     * @return
     */
    double netProfitRatio(double netProfitAfterTax, double revenue);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Operating Profit Ratio
     * Calculate Net Profit Ratio = Operating Profit/Revenue*100
     * Input = Income Statement
     *
     * @param operatingProfit
     * @param revenue
     * @return
     */
    double operatingProfitRatio(double operatingProfit, double revenue);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Earning Per Share Ratio
     * Calculate Net Profit Ratio = Profit After Tax/# of Shares
     * Input = Income Statement
     *
     * @param profitAfterTax
     * @param numberOfShares
     * @return
     */

    double earningPerShareRatio(double profitAfterTax, double numberOfShares);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Return on Equity Ratio
     * Calculate Net Profit Ratio = Net Profit After Tax / Shareholder's Funds*100
     * Input = Income Statement & Balance Sheet
     *
     * @param netProfitAfterTax
     * @param shareholdersFunds
     * @return
     */

    double returnOnEquityRatio(double netProfitAfterTax, double shareholdersFunds);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Dividend Payout Ratio
     * Calculate Net Profit Ratio = Equity Dividend / Net Profit After Tax*100
     * Input = Income Statement & Balance Sheet
     *
     * @param equityDividend
     * @param netProfitAfterTax
     * @return
     */

    double dividendPayoutRatio(double equityDividend, double netProfitAfterTax);


}