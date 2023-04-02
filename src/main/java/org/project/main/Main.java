package org.project.main;

import org.project.algorithm.PickersScheduler;
import org.project.fileReader.DataReader;
import org.project.orders.OrderDto;
import org.project.store.StoreDto;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataReader dataReader = new DataReader();
        if(args.length!=2){
            System.out.println("wrong files' paths values");
        }

        String path1 = args[0];
        String path2 = args[1];

        OrderDto[] o = dataReader.readOrderValuesFromFile(path1);
        StoreDto s = dataReader.readStoreValuesFromFile(path2);

        PickersScheduler pickersSheduler = new PickersScheduler(dataReader.transferOrderDtoArrayToOrderList(o), dataReader.transferStoreDtoToStore(s));
        pickersSheduler.makeScheduleForPickers();
    }
}
