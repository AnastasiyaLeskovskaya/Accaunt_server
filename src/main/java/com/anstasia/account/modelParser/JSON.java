//package com.anstasia.account.modelParser;
//
//
//import com.anstasia.account.model.Account;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import model.taxiStation.StationHolder;
//import model.taxiStation.TaxiStation;
//
//import java.io.File;
//import java.io.IOException;
//
///**
// * Created by Eugene on 17.11.2017.
// */
//
//public class JSON {
//
//    public static void writeModelToJSON(){
//        ObjectMapper mapper = new ObjectMapper();
//        //Object to JSON in file
//        mapper.writeValue(new File("c:\\file.json"), obj);
//
////Object to JSON in String
//        String jsonInString = mapper.writeValueAsString(obj);
//
//        System.out.println("Customers json created!");
//    }
//
////    @Override
////    public void call(final Object... args) {
////        ObjectMapper mapper = new ObjectMapper();
////        //RtmMessageJks msg = null;
////        try {
////            msg = mapper.readValue(args[0].toString(), RtmMessageJks.class);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////        String type = msg.getRtmType();
//
//    public static TaxiStation readListCarsFromJSON() throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.readValue(new File(LIST_CAR_FILE), TaxiStation.class);
//    }
//}
//
