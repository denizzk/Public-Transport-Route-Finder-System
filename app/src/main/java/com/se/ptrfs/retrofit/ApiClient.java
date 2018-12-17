package com.se.ptrfs.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static String Base_Url = "http://api-ext.trafi.com/";

    public static Retrofit getClient() {
        if (retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Base_Url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            return retrofit;
        }
        return retrofit;
    }

//    static class GenericOrCustomGsonConverterFactory extends Converter.Factory {
//        Gson gsonRestaurant =
//                new GsonBuilder()
//                        .registerTypeAdapter(Restaurant.class, new RestDeserializer<>
//                                (Restaurant.class, "restaurant"))
//                        .create();
//        Gson gsonReview =
//                new GsonBuilder()
//                        .registerTypeAdapter(Review.class, new RestDeserializer<>
//                                (Review.class, "review"))
//                        .create();
//
//        final Converter.Factory generic = GsonConverterFactory.create();
//        final Converter.Factory restaurant = GsonConverterFactory.create(gsonRestaurant);
//        final Converter.Factory review = GsonConverterFactory.create(gsonReview);
//
//        @Override
//        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[]
//                annotations, Retrofit retrofit) {
//            for (Annotation annotation : annotations) {
//                if (annotation.annotationType() == GenericGson.class) {
//                    return generic.responseBodyConverter(type, annotations, retrofit);
//                }
//                if (annotation.annotationType() == RestaurantGson.class) {
//                    return restaurant.responseBodyConverter(type, annotations, retrofit);
//                }
//                if (annotation.annotationType() == ReviewGson.class) {
//                    return review.responseBodyConverter(type, annotations, retrofit);
//                }
//            }
//            return null;
//        }
//    }
}
