package com.photoalbum;

import java.util.List;

import org.springframework.web.client.RestTemplate;

/**
 * Interface defining methods to interact with photo service site.
 * 
 * @author Lance Staley
 *
 */
public interface HttpService {

	/**
	 * Returns the List of Photos for the given albumId.
	 * 
	 * @param albumId
	 * @return List<Photo>, the photo album for the given albumId.
	 */
	List<Photo> getPhotoAlbum(String albumId);

	void setRestTemplate(RestTemplate restTemplate);
}
