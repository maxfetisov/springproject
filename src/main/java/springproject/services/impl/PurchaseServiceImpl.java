package springproject.services.impl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springproject.models.Purchase;
import springproject.repositories.impl.PurchaseRepository;
import springproject.services.PurchaseService;

import java.util.Collection;
import java.util.Optional;

@NoArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {

    private PurchaseRepository purchaseRepository;

    public PurchaseRepository getPurchaseRepository() {
        return purchaseRepository;
    }

    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public Collection<Purchase> getAllPurchases() {
        return purchaseRepository.getAll();
    }

    @Override
    public Optional<Purchase> getPurchaseByNumber(int number) {
        return purchaseRepository.getById(number);
    }

    @Override
    public void createPurchase(Purchase purchase) {
        purchaseRepository.create(purchase);
    }

    @Override
    public void updatePurchase(Purchase purchase) {
        purchaseRepository.update(purchase);
    }

    @Override
    public void deletePurchase(int number) {
        purchaseRepository.delete(number);
    }
}
