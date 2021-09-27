package com.greatlearning.stockers.main;

import java.util.Scanner;

import com.greatlearning.stockers.models.StockCompany;
import com.greatlearning.stockers.services.StockService;

public class Driver {
	Scanner sc = new Scanner(System.in);

	StockCompany[] inputStockPrice(int len) {

		StockCompany[] stockPrices = new StockCompany[len];
		for (int i = 0; i <= len - 1; i++) {
			StockCompany stockCompany = new StockCompany();
			System.out.println("Enter current stock price of the company " + (i + 1));
			stockCompany.setSharePrice(sc.nextDouble());
			System.out.println("Whether company's stock price rose today compare to yesterday?");
			stockCompany.setShareValue(sc.nextBoolean());
			stockPrices[i] = stockCompany;
		}
		return stockPrices;
	}

	int inputCompanies() {
		System.out.println("Enter the no. of companies");
		int no_of_companies = sc.nextInt();
		return no_of_companies;
	}

	public static void main(String[] args) {
		StockService stockService = new StockService();
		Driver obj = new Driver();

		Scanner sc = new Scanner(System.in);

		int len = obj.inputCompanies();
		StockCompany[] stockPrices = obj.inputStockPrice(len);

		double key;

		int option;
		do {
			System.out.println("-----------------------------------------------");
			System.out.println("Enter the operation that you want to perform");
			System.out.println("1. Display the companies stock prices in ascending order");
			System.out.println("2. Display the companies stock prices in descending order");
			System.out.println("3. Display the total no of companies for which stock prices rose today");
			System.out.println("4. Display the total no of companies for which stock prices declined today");
			System.out.println("5. Search a specific stock price");
			System.out.println("0. press 0 to exit");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Exited Successfully");
				System.exit(0);
				break;
			case 1:
				// Display the companies stock prices in ascending order
				stockService.displayStockAscendingOrder(stockPrices);
				break;
			case 2:
				// Display the companies stock prices in descending order
				stockService.displayStockDescendingOrder(stockPrices);
				break;
			case 3:
				// Display the total no of companies for which stock prices rose today
				stockService.displayTotalCompanyOnRise(stockPrices);
				break;
			case 4:
				// Display the total no of companies for which stock prices declined today
				stockService.displayTotalCompaniesOnDecline(stockPrices);
				break;
			case 5:
				// Search a specific stock price
				System.out.println("enter the key value");
				key = sc.nextDouble();
				stockService.searchStockPrice(stockPrices, key);
				break;
			default:
				System.out.println("Invalid input");
			}
		} while (option >= 0 && option <= 5);
		sc.close();
	}

}
