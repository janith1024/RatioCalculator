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
     * Calculate Gross Profit Ratio = Gross Profit/Net Sales*100
     * Input = Income Statement
     *
     * @param grossProfit
     * @param netSales
     * @return
     */
    double grossProfitRatio(double grossProfit, double netSales);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Net Profit Ratio
     * Calculate Net Profit Ratio = Net Profit after Tax/Net Sales*100
     * Input = Income Statement
     *
     * @param netProfitAfterTax
     * @param netSales
     * @return
     */
    double netProfitRatio(double netProfitAfterTax, double netSales);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Operating Profit Ratio
     * Calculate Operating Profit Ratio = Operating Profit/Net Sales*100
     * Input = Income Statement
     *
     * @param operatingProfit
     * @param netSales
     * @return
     */
    double operatingProfitRatio(double operatingProfit, double netSales);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Earnings Per Share Ratio
     * Calculate Earnings Per Share Ratio = Net Profit After Tax/Number of Equity Shares
     * Input = Income Statement
     *
     * @param netProfitAfterTax
     * @param numberOfEquityShares
     * @return
     */

    double earningsPerShareRatio(double netProfitAfterTax, double numberOfEquityShares);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Return on Equity Ratio
     * Calculate Return on Equity Ratio = Net Profit After Interest And Tax / Shareholder's Funds*100
     * Input = Income Statement & Balance Sheet
     *
     * @param netProfitAfterInterestAndTax
     * @param shareholdersFunds
     * @return
     */

    double returnOnEquityRatio(double netProfitAfterInterestAndTax, double shareholdersFunds);

    /**
     * If Ratio = Profitability Ratio && Sub Ratio = Dividend Payout Ratio
     * Calculate Dividend Payout Ratio = Equity Dividend / Net Profit After Tax*100
     * Input = Income Statement
     *
     * @param equityDividend
     * @param netProfitAfterTax
     * @return
     */

    double dividendPayoutRatio(double equityDividend, double netProfitAfterTax);

    /**
     * If Ratio = Turnover Ratio && Sub Ratio = Stock Turnover Ratio
     * Calculate Stock Turnover Ratio = Cost Of Sales / Average Inventory
     * Input = Income Statement & Balance Sheet
     * @param costOfSales
     * @param averageInventory
     * @return
     */
    double stockTurnoverRatio (double costOfSales, double averageInventory);

    /**
     * If Ratio = Turnover Ratio && Sub Ratio = Fixed Asset Turnover Ratio
     * Calculate Fixed Asset Turnover Ratio = Net Sales / Net Property Plant And Equipment
     * Input = Income Statement & Balance Sheet
     * @param netSales
     * @param netPropertyPlantAndEquipment
     * @return
     */
    double fixedAssetTurnoverRatio (double netSales, double netPropertyPlantAndEquipment);

    /**
     * If Ratio = Turnover Ratio && Sub Ratio = Working Capital Turnover Ratio
     * Calculate Working Capital Turnover Ratio = Net Sales / Capital Employed
     * Input = Income Statement & Balance Sheet
     * @param netSales
     * @param capitalEmployed
     * @return
     */

    double workingCapitalTurnoverRatio (double netSales, double capitalEmployed);

    /**
     * If Ratio = Solvency Ratio && Sub Ratio = Debt Equity Ratio
     * Calculate Debt Equity Ratio = Total Liabilities / Total Equity
     * Input = Balance Sheet
     * @param totalLiabilities
     * @param totalEquity
     * @return
     */

    double debtEquityRatio (double totalLiabilities, double totalEquity);
}