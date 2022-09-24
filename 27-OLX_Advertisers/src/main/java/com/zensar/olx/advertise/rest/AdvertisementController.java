package com.zensar.olx.advertise.rest;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zensar.olx.advertise.bean.Advertisement;
import com.zensar.olx.advertise.bean.AdvertisementPostRequest;
import com.zensar.olx.advertise.bean.AdvertisementPostResponse;
import com.zensar.olx.advertise.bean.AdvertisementStatus;
import com.zensar.olx.advertise.bean.Category;
import com.zensar.olx.advertise.bean.OlxUser;
import com.zensar.olx.advertise.service.AdvertisementService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AdvertisementController {

	@Autowired
	AdvertisementService service;

// post advertisement
	// skdfihklaufjug
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/advertise/{un}")
	public AdvertisementPostResponse add(@RequestBody AdvertisementPostRequest request,
			@PathVariable("un") String userName) {

		Advertisement advt = new Advertisement();
		advt.setTitle(request.getTitle());
		advt.setPrice(request.getPrice());
		advt.setDescription(request.getDescription());

		int categoryId = request.getCategoryId();

		RestTemplate restTemplate = new RestTemplate();
		Category category;
		String url = "http://localhost:9052/advertise/getCategoryById/" + categoryId;
		category = restTemplate.getForObject(url, Category.class);
		advt.setCategory(category);
		System.out.println(category.getName());

		url = "http://localhost:9051/findUserByName/" + userName;
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		advt.setOlxUser(olxUser);
		url="http://localhost:9052/advertise/status/"+request.getStatusId();
	//	AdvertisementStatus advertisementStatus = new AdvertisementStatus(1, "OPEN");
		AdvertisementStatus advertisementStatus=restTemplate.getForObject(url,AdvertisementStatus.class);
		advt.setAdvertisementStatus(advertisementStatus);

		Advertisement advertisement = this.service.addAdvertisement(advt);// entity saved

		AdvertisementPostResponse response = new AdvertisementPostResponse();
		response.setId(advertisement.getId());
		response.setTitle(advertisement.getTitle());
		response.setPrice(advertisement.getPrice());
		response.setCategory(advertisement.getCategory().getName());
		response.setDescription(advertisement.getDescription());
		response.setUserName(advertisement.getOlxUser().getUserName());
		response.setCreateDate(advertisement.getCreateDate());
		response.setModofiedDate(advertisement.getModifiedDate());
		response.setStatus(advertisement.getAdvertisementStatus().getStatus());

		return response;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	// update advertisement or edit posted advertisement
	@PutMapping("/advertise/{aid}/{username}")
	public AdvertisementPostResponse f2(@RequestBody AdvertisementPostRequest request, @PathVariable("aid") int id,
			@PathVariable("username") String userName) {

		Advertisement advt = new Advertisement(id);
		advt.setTitle(request.getTitle());
		advt.setPrice(request.getPrice());
		advt.setDescription(request.getDescription());

		RestTemplate restTemplate = new RestTemplate();
		Category category;
		String url = "http://localhost:9052/advertise/getCategoryById/" + request.getCategoryId();
		category = restTemplate.getForObject(url, Category.class);
		advt.setCategory(category);
		System.out.println(advt.getCategory());
		url = "http://localhost:9051/findUserByName/" + userName;
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		advt.setOlxUser(olxUser);
		System.out.println(advt.getOlxUser());
		url = "http://localhost:9052/advertise/status/" + request.getCategoryId();
		AdvertisementStatus advertisementStatus;
		advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
		advt.setAdvertisementStatus(advertisementStatus);
		System.out.println(advt.getAdvertisementStatus());
		Advertisement advertisement = this.service.updateAdvertisement(advt);

		AdvertisementPostResponse response = new AdvertisementPostResponse();
		response.setId(advertisement.getId());
		response.setTitle(advertisement.getTitle());
		response.setDescription(advertisement.getDescription());
		response.setPrice(advertisement.getPrice());
		response.setUserName(advt.getOlxUser().getUserName());
		response.setCategory(advt.getCategory().getName());
		response.setCreateDate(advertisement.getCreateDate());
		response.setModofiedDate(advertisement.getModifiedDate());
		response.setStatus(advt.getAdvertisementStatus().getStatus());

		return response;

	}

	// delete advertisement by id
	@DeleteMapping("/user/advertise/{id}")
	public boolean deleteAdvertiseById(@PathVariable(name = "id") int id) {
		Advertisement advertisement = this.service.getAdvertisementById(id);
		return this.service.deleteAdvertisement(advertisement);

	}

//dfxgdfdzf
	// get advertisement by id
	@GetMapping("/user/advertise/advertisementId/{id}")
	public AdvertisementPostResponse getAdvertisementById(@PathVariable(name = "id") int id) {
		RestTemplate restTemplate = new RestTemplate();

		Advertisement advertisement = this.service.getAdvertisementById(id);
		String url;
		Category category;
		url = "http://localhost:9052/advertise/getCategoryById/" + advertisement.getCategory().getId();
		category = restTemplate.getForObject(url, Category.class);
		advertisement.setCategory(category);
		System.out.println("category------------" + advertisement);

		url = "http://localhost:9052/advertisement/status/" + advertisement.getAdvertisementStatus().getId();
		AdvertisementStatus advertisementStatus;
		advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
		advertisement.setAdvertisementStatus(advertisementStatus);

		url = "http://localhost:9051/findUserById/" + advertisement.getOlxUser().getUserId();
		OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
		advertisement.setOlxUser(olxUser);

		AdvertisementPostResponse response = new AdvertisementPostResponse();
		response.setId(advertisement.getId());
		response.setTitle(advertisement.getTitle());
		response.setDescription(advertisement.getDescription());
		response.setPrice(advertisement.getPrice());
		response.setUserName(advertisement.getOlxUser().getUserName());
		response.setCategory(advertisement.getCategory().getName());
		response.setCreateDate(advertisement.getCreateDate());
		response.setModofiedDate(advertisement.getModifiedDate());
		response.setStatus(advertisement.getAdvertisementStatus().getStatus());

		return response;

	}

//get all advertisemets 
	@GetMapping("/advertise/getAllAdvertises")
	public List<Advertisement> getAllAdvertisements() {
		
		List<Advertisement>allAdvertisement=new ArrayList<Advertisement>();
		
		 allAdvertisement=service.getAllAdvertisement();
		 
		 RestTemplate retRestTemplate = new RestTemplate();
		 for (Advertisement advertisement : allAdvertisement) {
			 String url = "http://localhost:9051/findUserById/" + advertisement.getOlxUser().getUserId();
				OlxUser olxUser = retRestTemplate.getForObject(url, OlxUser.class);
				advertisement.setOlxUser(olxUser);
				url = "http://localhost:9052/advertise/getCategoryById/" + advertisement.getCategory().getId();
				Category category = retRestTemplate.getForObject(url, Category.class);
				advertisement.setCategory(category);
				System.out.println("Catedory---------" + advertisement.toString());

				url = "http://localhost:9052/advertisement/status/" + advertisement.getAdvertisementStatus().getId();
				AdvertisementStatus advertisementStatus = retRestTemplate.getForObject(url, AdvertisementStatus.class);
				advertisement.setAdvertisementStatus(advertisementStatus);
				System.out.println("advertisementStatus---------" + advertisement.toString());
				
			 
		}
		 
		 
		 return allAdvertisement;
	}

//get all advertisements posted by particular user
	@GetMapping("/user/advertise/{userName}")
	public List<AdvertisementPostResponse> getAllAdvertisementsByUserName(
			@PathVariable(name = "userName") String userName) {

		List<Advertisement> advPosts = this.service.getAllAdvertisement();
		RestTemplate retRestTemplate = new RestTemplate();
		String url = "http://localhost:9051/findUserByName/" + userName;
		OlxUser olxUser = retRestTemplate.getForObject(url, OlxUser.class);

		List<Advertisement> filterList = new ArrayList<Advertisement>();

		for (Advertisement advertisement : advPosts) {
			url = "http://localhost:9052/advertise/getCategoryById/" + advertisement.getCategory().getId();
			Category category = retRestTemplate.getForObject(url, Category.class);
			advertisement.setCategory(category);
			System.out.println("Catedory---------" + advertisement.toString());

			url = "http://localhost:9052/advertisement/status/" + advertisement.getAdvertisementStatus().getId();
			AdvertisementStatus advertisementStatus = retRestTemplate.getForObject(url, AdvertisementStatus.class);
			advertisement.setAdvertisementStatus(advertisementStatus);
			System.out.println("advertisementStatus---------" + advertisement.toString());
			//url = "http://localhost:9051/findUserByName/" + userName;
		 	//OlxUser olxUser1 = retRestTemplate.getForObject(url, OlxUser.class);
			//advertisement.setOlxUser(olxUser1);

			if (olxUser.getUserId() == advertisement.getOlxUser().getUserId()) {
				advertisement.setOlxUser(olxUser);
				filterList.add(advertisement);

			}

		}

		System.out.println("List-------------------" + filterList.toString());
		List<AdvertisementPostResponse> responseList = new ArrayList<AdvertisementPostResponse>();
		for (Advertisement advertisementPost : filterList) {
			AdvertisementPostResponse postResponse = new AdvertisementPostResponse();
			postResponse.setId(advertisementPost.getId());
			postResponse.setTitle(advertisementPost.getTitle());
			postResponse.setDescription(advertisementPost.getDescription());
			postResponse.setPrice(advertisementPost.getPrice());
			postResponse.setCategory(advertisementPost.getCategory().getName());
			postResponse.setCreateDate(advertisementPost.getCreateDate());
			postResponse.setModofiedDate(advertisementPost.getModifiedDate());
			postResponse.setUserName(advertisementPost.getOlxUser().getUserName());
			postResponse.setStatus(advertisementPost.getAdvertisementStatus().getStatus());
			responseList.add(postResponse);
		}
		return responseList;
	}

	@GetMapping("/advertise/search/filterCriteria/{input}")
	public List<Advertisement> filterCriteria(@PathVariable(name = "input") String key) {

		String url;
		RestTemplate restTemplate = new RestTemplate();
		String[] input = key.split("=");

		List<Advertisement> filterList = new ArrayList<Advertisement>();

		List<Advertisement> allAdvertisement = service.getAllAdvertisement();
		for (Advertisement advertisement : allAdvertisement) {

			url = "http://localhost:9052/advertise/getCategoryById/" + advertisement.getCategory().getId();
			Category category = restTemplate.getForObject(url, Category.class);
			advertisement.setCategory(category);
			System.out.println("Catedory---------" + advertisement.toString());

			url = "http://localhost:9052/advertise/status/" + advertisement.getAdvertisementStatus().getId();
			AdvertisementStatus advertisementStatus = restTemplate.getForObject(url, AdvertisementStatus.class);
			advertisement.setAdvertisementStatus(advertisementStatus);

			System.out.println("advertisementStatus---------" + advertisement.toString());
			url = "http://localhost:9051/findUserById/" + advertisement.getOlxUser().getUserId();
			OlxUser olxUser = restTemplate.getForObject(url, OlxUser.class);
			advertisement.setOlxUser(olxUser);

			if (input[0].equalsIgnoreCase("title")) {
				if (advertisement.getTitle().contains(input[1]))
					filterList.add(advertisement);
			} else if (input[0].equalsIgnoreCase("description")) {
				if (advertisement.getDescription().contains(input[1]))
					filterList.add(advertisement);
			} else if (input[0].equalsIgnoreCase("price")) {
				if (advertisement.getPrice() == Double.parseDouble(input[1]))
					filterList.add(advertisement);
			} else if (input[0].equalsIgnoreCase("modifiedDate")) {
				LocalDate date = LocalDate.parse(input[1]);
				if (advertisement.getModifiedDate().equals(input[1])) {
					filterList.add(advertisement);
				}
			} else if (input[0].equalsIgnoreCase("createDate")) {

				LocalDate date = LocalDate.parse(input[1]);
				if (advertisement.getModifiedDate().equals(date)) {
					filterList.add(advertisement);
				}
			} else if (input[0].equalsIgnoreCase("category")) {
				if (advertisement.getCategory().getName().contains(input[1]))
					filterList.add(advertisement);
			} else if (input[0].equalsIgnoreCase("status")) {
				if (advertisement.getAdvertisementStatus().getStatus().contains(input[1])) {
					filterList.add(advertisement);
				}
			} else {
				System.out.println("No Matches Found");
			}

		}

		return filterList;

	}

}
