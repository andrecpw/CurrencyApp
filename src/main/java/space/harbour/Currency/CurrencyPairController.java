package space.harbour.Currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/currency-pairs")
public class CurrencyPairController {
    @Autowired
    private CurrencyPairRepository currencyPairRepository;
    @Autowired
    private CurrencyConversionService currencyConversionService;

    @GetMapping
    public List<CurrencyPair> getAllCurrencyPairs() {
        return currencyPairRepository.findAll();
    }

    @PostMapping
    public CurrencyPair addCurrencyPair(@RequestBody CurrencyPair currencyPair) {
        return currencyPairRepository.save(currencyPair);
    }

    @PutMapping("/{id}")
    public CurrencyPair updateCurrencyPair(@PathVariable Long id, @RequestBody CurrencyPair currencyPair) {
        CurrencyPair existingCurrencyPair = currencyPairRepository.getReferenceById(id);
        if (existingCurrencyPair == null) {
            throw new RuntimeException("Currency pair not found.");
        }

        existingCurrencyPair.setFromCurrency(currencyPair.getFromCurrency());
        existingCurrencyPair.setToCurrency(currencyPair.getToCurrency());
        existingCurrencyPair.setExchangeRate(currencyPair.getExchangeRate());
        return currencyPairRepository.save(existingCurrencyPair);
    }

    @PostMapping("/convert")
    public double convertCurrency(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam double amount) {
        return currencyConversionService.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
