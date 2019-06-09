package com.photoalbum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of HttpService. Handles interaction to the photo service site.
 * 
 * @author Lance Staley
 *
 */
public class HttpServiceImpl implements HttpService {

	private final String url = "https://jsonplaceholder.typicode.com/photos?albumId=";
	private RestTemplate restTemplate;

	@Override
	public List<Photo> getPhotoAlbum(String albumId) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));
		messageConverters.add(converter);

		restTemplate.setMessageConverters(messageConverters);

		Photo[] photos = restTemplate.getForObject(url + albumId, Photo[].class);
		if (photos != null) {
			return Arrays.asList(photos);
		}
		return null;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

}
