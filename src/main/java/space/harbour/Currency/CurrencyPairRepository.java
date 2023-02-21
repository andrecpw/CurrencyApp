package space.harbour.Currency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyPairRepository extends JpaRepository<CurrencyPair, Long> {
    CurrencyPair findByPair(String fromCurrency, String toCurrency);
}
