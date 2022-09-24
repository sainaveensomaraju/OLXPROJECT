package com.zensar.olx.advertise.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zensar.olx.advertise.bean.Advertisement;
import com.zensar.olx.advertise.db.AdvertisementDAO;

@Service
public class AdvertisementService {
	@Autowired
	AdvertisementDAO dao;
	
	
	public Advertisement addAdvertisement(Advertisement advertisement) {
		return dao.save(advertisement);
	}
	
	public Advertisement updateAdvertisement(Advertisement advertisement) {
		return this.dao.save(advertisement);
	}
	public List<Advertisement> getAllAdvertisement(){
		return this.dao.findAll();
	}
	
	public boolean deleteAdvertisement(Advertisement advertisement) {
		boolean result=false;
		try {
			this.dao.delete(advertisement);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	public Advertisement getAdvertisementById(int id) {
		Optional<Advertisement> optional=this.dao.findById(id);
		if(optional.isPresent())
			return optional.get();
		return null;
	}
}
