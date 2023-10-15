package nam.nguyen.store.service;

import nam.nguyen.store.model.Cart;
import nam.nguyen.store.model.CartItem;
import nam.nguyen.store.model.Product;
import nam.nguyen.store.repository.CartItemRepository;
import nam.nguyen.store.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemService cartItemService;
    public Cart newCart() {
        Cart newCart = new Cart();
        newCart = cartRepository.save(newCart);
        return newCart;
    }
    public List<CartItem> getAllCartItem(Integer id){
        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart = cartOptional.get();
        List<CartItem > cartItems = cart.getCartItems();
        return cartItems;
    }
    public void addProduct(Product product ,Integer id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart = cartOptional.get();
        List<CartItem> cartItems = cart.getCartItems();
        Boolean temp = false;
        for ( CartItem c: cartItems) {
            if (c.getProduct().getId().equals(product.getId())){
                c.setQuantity(c.getQuantity()+1);
                temp= true;
                break;
            }

        }
        if (!temp){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cartItems.add(cartItem);
        }
        cart.setCartItems(cartItems);
        cartRepository.save(cart);

    }
    public void removeProduct(Product product, Integer id) {
      List<CartItem> cartItems= cartItemService.getAll(id);
        for (CartItem c : cartItems) {
            if(c.getProduct().equals(product)){
                cartItemService.removeCartItem(c);
            }
        }
    }
    public void updateProduct(Product product, Integer id, Integer quantity) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart = cartOptional.get();
        List<CartItem> cartItems = cart.getCartItems();
        for ( CartItem c: cartItems) {
            if (c.getProduct().getId().equals(product.getId())){
                c.setQuantity(quantity);
                break;
            }

        }
        cart.setCartItems(cartItems);
        cartRepository.save(cart);
    }
    public void removeAllProduct(Integer id) {
        cartItemService.removeAllCartItem(id);
    }
    public long total( Integer id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        List<CartItem > cartItems = cartOptional.get().getCartItems();
        long total = 0;
        for ( CartItem c: cartItems) {
           total+=c.getProduct().getPrice()*c.getQuantity();

        }
        return total;
    }

}
