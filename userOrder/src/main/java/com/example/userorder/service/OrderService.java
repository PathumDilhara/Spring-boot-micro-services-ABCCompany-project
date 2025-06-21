package com.example.userorder.service;

import com.example.inventory.dto.InventoryDTO;
import com.example.product.dto.ProductDTO;
import com.example.userorder.common.ErrorOrderResponse;
import com.example.userorder.common.OrderResponse;
import com.example.userorder.common.SuccessOrderResponse;
import com.example.userorder.dto.UserOrderDTO;
import com.example.userorder.entity.UserOrder;
import com.example.userorder.repo.OrderRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    // Web client config
    private final WebClient inventoryWebClient;
    private final WebClient productWebClient;

    // Constructor for web client
    public OrderService(WebClient inventoryWebClient, WebClient productWebClient, OrderRepo orderRepo, ModelMapper modelMapper) {
        this.inventoryWebClient = inventoryWebClient;
        this.productWebClient = productWebClient;
        this.orderRepo = orderRepo;
        this.modelMapper = modelMapper;
    }

    public List<UserOrderDTO> getAllOrders(){
        List<UserOrder> orders = orderRepo.findAll();
        return modelMapper.map(orders, new TypeToken<List<UserOrderDTO>>(){}.getType());
    }

    public OrderResponse saveOrderDTO(UserOrderDTO userOrderDTO){

        Integer itemId = userOrderDTO.getItemId();

        try {
          InventoryDTO inventoryDTOResponse =   inventoryWebClient.get().uri(uriBuilder -> uriBuilder.path("/inventory/getItemByItemId/{itemId}").build(itemId))
                    .retrieve()
                    .bodyToMono(InventoryDTO.class)
                    .block();


          assert inventoryDTOResponse != null;

//            System.out.println(inventoryDTO);

            Integer productId = userOrderDTO.getItemId(); // itemId = productId in this scenario

            ProductDTO productDTOResponse =   productWebClient.get().uri(uriBuilder -> uriBuilder.path("/product/getProductsByProductId/{productId}").build(productId))
                    .retrieve()
                    .bodyToMono(ProductDTO.class)
                    .block();

            assert productDTOResponse != null;

            if (inventoryDTOResponse.getQuantity()  > 0) {
                if(productDTOResponse.getForSale()==1){
                    orderRepo.save(modelMapper.map(userOrderDTO, UserOrder.class));
                }else {
                    return new ErrorOrderResponse("Product not for sale");
                }
                return new SuccessOrderResponse(userOrderDTO);
            } else{
                return new ErrorOrderResponse("Item not available 0 )");
            }
        } catch (WebClientResponseException err){
            if(err.getStatusCode().is5xxServerError()){
                return new ErrorOrderResponse("Item not found");
            }
            return  null;
        }
    }

    public UserOrderDTO updateOrder(UserOrderDTO userOrderDTO){
        orderRepo.save(modelMapper.map(userOrderDTO, UserOrder.class));
        return userOrderDTO;
    }

    public boolean deleteOrder(UserOrderDTO userOrderDTO){
        orderRepo.delete(modelMapper.map(userOrderDTO, UserOrder.class));
        return true;
    }
}
