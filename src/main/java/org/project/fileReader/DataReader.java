package org.project.fileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.project.algorithm.PickersScheduler;
import org.project.orders.*;
import org.project.picker.Picker;
import org.project.store.Store;
import org.project.store.StoreDto;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class DataReader {
    public OrderDto[] readOrderValuesFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderDto[] orders = objectMapper.readValue(new FileReader(filePath), OrderDto[].class);
        return orders;
    }

    public StoreDto readStoreValuesFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StoreDto storeDto = objectMapper.readValue(new FileReader(filePath), StoreDto.class);
        return storeDto;
    }

    public List<Order> transferOrderDtoArrayToOrderList(OrderDto[] orderDtos) {
        List<Order> orders = Arrays.stream(orderDtos)
                .map(orderDto -> new Order(
                        orderDto.getOrderId(),
                        Double.parseDouble(orderDto.getOrderValue()),
                        Duration.parse(orderDto.getPickingTime()),
                        LocalTime.parse(orderDto.getCompleteBy())
                ))
                .toList();
        return orders;
    }

    public Store transferStoreDtoToStore(StoreDto storeDto) {
        Store store = new Store();
        store.setPickingStartTime(LocalTime.parse(storeDto.getPickingStartTime()));
        store.setPickingEndTime(LocalTime.parse(storeDto.getPickingEndTime()));
        String[] pickersArray = storeDto.getPickers();
        List<Picker> pickersList = Arrays.stream(pickersArray)
                .map(Picker::new)
                .toList();
        store.setPickers(pickersList);

        return store;
    }
}