package springproject.services;

import springproject.models.Good;

import java.util.Collection;
import java.util.Optional;

public interface GoodService {
    Collection<Good> getAllGoods();

    Optional<Good> getGoodById(int id);

    void createGood(Good good);

    void updateGood(Good person);

    void deleteGood(int id);
}
