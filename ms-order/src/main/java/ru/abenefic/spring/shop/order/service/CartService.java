package ru.abenefic.spring.shop.order.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.abenefic.spring.shop.core.clients.ProductClient;
import ru.abenefic.spring.shop.core.model.dtos.CartDto;
import ru.abenefic.spring.shop.core.model.dtos.ProductDto;
import ru.abenefic.spring.shop.order.model.Cart;
import ru.abenefic.spring.shop.order.model.CartItem;
import ru.abenefic.spring.shop.order.repository.CartRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ProductClient productClient;
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public CartDto findById(UUID uuid) {
        Optional<Cart> cart = cartRepository.findById(uuid);
        return modelMapper.map(cart.orElseGet(Cart::new), CartDto.class);
    }

    @Transactional
    public void addToCart(UUID cartUuid, Long productId) {
        CartDto cartDto = findById(cartUuid);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        ProductDto product = productClient.getById(productId);
        cart.add(new CartItem(product));
    }

    @Transactional
    public void clearCart(UUID cartUuid) {
        CartDto cartDto = findById(cartUuid);
        Cart cart = modelMapper.map(cartDto, Cart.class);
        cart.clear();
    }

    public Optional<Cart> findByUserId(Long id) {
        return cartRepository.findByUserId(id);
    }

    @Transactional
    public UUID getCartForUser(Long userId, UUID cartUuid) {
        // сперва не понял, зачем это. Потом понял -
        // так мы не теряем корзину, если пользователь разлогинился
        // или зашёл с другого устройства и начал наполнять корзину, потом залогинился
        if (userId != null && cartUuid != null) {
            CartDto cartDto = findById(cartUuid);
            Cart cart = modelMapper.map(cartDto, Cart.class);
            Optional<Cart> oldCart = findByUserId(userId);
            if (oldCart.isPresent()) {
                cart.merge(oldCart.get());
                cartRepository.delete(oldCart.get());
            }
            cart.setUserId(userId);
            return cartUuid;
        }
        if (userId == null) {
            Cart cart = save(new Cart());
            return cart.getId();
        }
        Optional<Cart> cart = findByUserId(userId);
        if (cart.isPresent()) {
            return cart.get().getId();
        }
        Cart newCart = new Cart();
        newCart.setUserId(userId);
        save(newCart);
        return newCart.getId();
    }
}
