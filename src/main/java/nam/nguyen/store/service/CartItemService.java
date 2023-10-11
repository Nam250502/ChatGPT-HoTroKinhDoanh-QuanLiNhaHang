package nam.nguyen.store.service;

import nam.nguyen.store.model.CartItem;
import nam.nguyen.store.model.DiningTable;
import nam.nguyen.store.model.Product;
import nam.nguyen.store.repository.CartItemRepository;
import nam.nguyen.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private DiningTableService diningTableService;


    public List<CartItem> getAllCartItem(Integer id) {
        return cartItemRepository.findCartsByTableId(id);
    }


    public void addProduct(Product product, Integer idtable) {
        DiningTable diningTable = diningTableService.getTable(idtable);
        if (diningTable == null) {

            return;
        }

        List<CartItem> cartItems = cartItemRepository.findCartsByTableId(idtable);
        boolean productFound = false;

        for (CartItem c : cartItems) {
            if (c.getProduct().getId().equals(product.getId())) {
                c.setQuantity(c.getQuantity() + 1);
                cartItemRepository.save(c);
                productFound = true;
                break;
            }
        }

        if (!productFound) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setDiningTable(diningTable);
            cartItemRepository.save(cartItem);
        }
    }

    public void removeProduct(Product product, Integer idtable) {
        List<CartItem> cartItems = cartItemRepository.findCartsByTableId(idtable);
        for (CartItem c : cartItems) {
            if (c.getProduct().getId().equals(product.getId())) {
               cartItemRepository.delete(c);
                break;
            }
        }
    }
    public void updateProduct(Product product, Integer idtable , Integer quantity) {
        List<CartItem> cartItems = cartItemRepository.findCartsByTableId(idtable);
        for (CartItem c : cartItems) {
            if (c.getProduct().getId().equals(product.getId())) {
                c.setQuantity(quantity);
                cartItemRepository.save(c);
                break;
            }
        }
    }
    public void removeAllProduct( Integer idtable ) {
        List<CartItem> cartItems = cartItemRepository.findCartsByTableId(idtable);
        cartItemRepository.deleteAll(cartItems);
    }
    public long total( Integer idtable ) {
        List<CartItem> cartItems = cartItemRepository.findCartsByTableId(idtable);
        long total=0;
        for (CartItem c : cartItems) {
           total=c.getQuantity()*c.getProduct().getPrice();
        }
        return total;
    }

}
