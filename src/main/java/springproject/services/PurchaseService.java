package springproject.services;

import springproject.models.Person;
import springproject.models.Purchase;

import java.util.Collection;
import java.util.Optional;

public interface PurchaseService {
    Collection<Purchase> getAllPurchases();

    Optional<Purchase> getPurchaseByNumber(int number);

    void createPurchase(Purchase person);
    void updatePurchase(Purchase person);
    void deletePurchase(int number);
}
