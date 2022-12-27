package springproject.services.impl;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springproject.models.Good;
import springproject.repositories.impl.GoodRepository;
import springproject.services.GoodService;

import java.util.Collection;
import java.util.Optional;

@NoArgsConstructor
@Service
public class GoodServiceImpl implements GoodService {

    private GoodRepository goodRepository;

    public GoodRepository getGoodRepository() {
        return goodRepository;
    }

    @Autowired
    public void setGoodRepository(GoodRepository goodRepository){
        this.goodRepository = goodRepository;
    }

    public Collection<Good> getAllGoods(){
        return goodRepository.getAll();
    }

    public Optional<Good> getGoodById(int id){
        return goodRepository.getById(id);
    }

    public void createGood(Good good){
        goodRepository.create(good);
    }

    public void updateGood(Good person){goodRepository.update(person);}

    public void deleteGood(int id){
        goodRepository.delete(id);
    }

}

