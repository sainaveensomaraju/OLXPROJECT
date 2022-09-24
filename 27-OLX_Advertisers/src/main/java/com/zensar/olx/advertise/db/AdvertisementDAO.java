package com.zensar.olx.advertise.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zensar.olx.advertise.bean.Advertisement;
@Repository
public interface AdvertisementDAO extends JpaRepository<Advertisement, Integer>{

}
