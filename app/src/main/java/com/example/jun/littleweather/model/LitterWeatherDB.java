package com.example.jun.littleweather.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jun.littleweather.db.LittleWeatherOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 16/4/29.
 */
public class LitterWeatherDB {

    public static final String DB_NAME = "little_weather";

    private static final int VERSION = 1;

    private static LitterWeatherDB litterWeatherDB;

    private SQLiteDatabase db;


    private LitterWeatherDB(Context context) {
        LittleWeatherOpenHelper dbHelper = new LittleWeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static LitterWeatherDB getInstance(Context context) {
        if (litterWeatherDB == null) {
            litterWeatherDB = new LitterWeatherDB(context);
        }
        return litterWeatherDB;
    }


    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }

    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<Province>();
        Cursor cursor = db.query("Province", null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                list.add(province);
            } while (cursor.moveToNext());

        }
        return list;
    }



    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City",null, values);
        }
    }


    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<City>();
        Cursor cursor = db.query("City", null, "province_id=?", new String[]{String.valueOf(provinceId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));

                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());

        }
        return list;
    }



    public void saveCountry(County county){
        if(county != null){
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County",null, values);
        }
    }


    public List<County> loadCountries(int cityId) {
        List<County> list = new ArrayList<County>();
        Cursor cursor = db.query("County", null, "city_id=?", new String[]{String.valueOf(cityId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                County country = new County();
                country.setId(cursor.getInt(cursor.getColumnIndex("id")));
                country.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                country.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));

                country.setCityId(cityId);
                list.add(country);
            } while (cursor.moveToNext());

        }
        return list;
    }
}




















