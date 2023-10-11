package nam.nguyen.store.service;

import nam.nguyen.store.model.CartItem;
import nam.nguyen.store.model.DiningTable;
import nam.nguyen.store.model.Product;
import nam.nguyen.store.repository.DiningTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {
    @Autowired
    private DiningTableRepository diningTableRepository;
    public void setTable(Integer quantity){
        List<DiningTable> diningTables = diningTableRepository.findAll();
        Integer size= diningTables.size();

        if(size<quantity){
            for (int i = size+1; i <= quantity; i++) {
                DiningTable table = new DiningTable();
                table.setId(i);
                table.setStatus(1);
                diningTableRepository.save(table);
            }
        }if(size>quantity){
            for (int i =quantity +1 ; i<= size ; i++){
                diningTableRepository.delete(diningTables.get(i-1));
            }
        }

    }
    public List<DiningTable> viewTable(){
        return diningTableRepository.findAll();
    }
    public DiningTable getTable(Integer id){
        DiningTable diningTable = diningTableRepository.getById(id);
        return diningTable ;
    }


}
