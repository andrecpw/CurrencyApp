package space.harbour.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {
    @Autowired
    private CurrencyPairRepository currencyPairRepositoryImpl;

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        CurrencyPair currencyPair = currencyPairRepositoryImpl.findByPair(fromCurrency, toCurrency);
        if (currencyPair == null) {
            throw new RuntimeException("Invalid currency pair.");
        }
        return amount * currencyPair.getExchangeRate();
    }
}
