package com.greatlearning.stockers.services;

import com.greatlearning.stockers.models.StockCompany;

public class StockService {

	public void displayStockAscendingOrder(StockCompany[] stockPriceArr) {
		String order = "ascending";
		sort(stockPriceArr, 0, stockPriceArr.length - 1, order);
		System.out.println("Stock prices in ascending order are :");
		printCompanies(stockPriceArr);
	}

	public void displayStockDescendingOrder(StockCompany[] stockPriceArr) {
		String order = "descending";
		sort(stockPriceArr, 0, stockPriceArr.length - 1, order);
		System.out.println("Stock prices in descending order are :");
		printCompanies(stockPriceArr);
	}

	public void displayTotalCompanyOnRise(StockCompany[] stockPriceArr) {
		        
        System.out.println("Total no of companies whose stock price rose today : "+getCount(stockPriceArr,true));
        
	}
	
	public int getCount(StockCompany[] stockPrices,boolean value) {
		int count = 0;
		for (StockCompany stockCompany : stockPrices) {
            if (stockCompany.isShareValue() == value)
                count++;
        }
		return count;
		
	}

	public void displayTotalCompaniesOnDecline(StockCompany[] stockPriceArr) {
	
		System.out.println("Total no of companies whose stock price declined today : "+getCount(stockPriceArr,false)); 
	}

	public void searchStockPrice(StockCompany[] stockPrices, double searchkey) {
		sort(stockPrices, 0, stockPrices.length - 1, "ascending");
		int l, r, mid;
		l = 0;
		r = stockPrices.length - 1;
		mid = l + (r - l) / 2;
		while (l <= r) {
			if (searchkey < stockPrices[mid].getSharePrice()) {
				// range is l to mid-1
				r = mid - 1;
			} else if (searchkey > stockPrices[mid].getSharePrice()) {
				// range is mid+1 to r
				l = mid + 1;
			} else {
				System.out.println("Stock of value " + searchkey + " is present");
				break;
			}
			mid = l + (r - l) / 2;
		}
		if (l > r) {
			System.out.println("Value not found");
		}

	}

	void merge(StockCompany[] arr, int l, int m, int r, String order) {

		int n1 = m - l + 1;
		int n2 = r - m;

		StockCompany L[] = new StockCompany[n1];
		StockCompany R[] = new StockCompany[n2];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		int i = 0, j = 0;

		int k = l;
		while (i < n1 && j < n2) {
			if (order.equals("ascending")) {
				if (L[i].getSharePrice() <= R[j].getSharePrice()) {
					arr[k] = L[i];
					i++;
				} else {
					arr[k] = R[j];
					j++;
				}
				k++;
			} else if (order.equals("descending")) {
				if (L[i].getSharePrice() >= R[j].getSharePrice()) {
					arr[k] = L[i];
					i++;
				} else {
					arr[k] = R[j];
					j++;
				}
				k++;
			}
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	void sort(StockCompany[] arr, int l, int r, String order) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(arr, l, m, order);
			sort(arr, m + 1, r, order);

			// Merge the sorted halves
			merge(arr, l, m, r, order);
		}
	}

	static void printCompanies(StockCompany[] stockPrices) {
		for (StockCompany company : stockPrices) {
			System.out.print(company.getSharePrice() + " ");
		}
		System.out.println();

	}

}
