package com.photoalbum;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import org.springframework.web.client.RestTemplate;
import static org.mockito.Mockito.*;

import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HttpServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private HttpService httpService = new HttpServiceImpl();

	@Test
	public void testGetPhotoAlbum() {
		httpService.setRestTemplate(restTemplate);
		Mockito.when(restTemplate.getForObject(anyString(), eq(Photo[].class))).thenReturn(setupMockData());
		List<Photo> results = httpService.getPhotoAlbum("1");
		verify(restTemplate, atLeastOnce()).getForObject(anyString(), eq(Photo[].class));
		Assert.assertNotNull(results);
		Assert.assertEquals(true, !results.isEmpty());
		Assert.assertEquals(results.iterator().next().getAlbumId(), new Long(1));
	}

	@Test
	public void testGetPhotoAlbumNoResult() {
		httpService.setRestTemplate(restTemplate);
		Mockito.when(restTemplate.getForObject(anyString(), eq(Photo[].class))).thenReturn(new Photo[0]);
		List<Photo> results = httpService.getPhotoAlbum("1");
		verify(restTemplate, atLeastOnce()).getForObject(anyString(), eq(Photo[].class));
		Assert.assertNotNull(results);
		Assert.assertEquals(false, !results.isEmpty());
	}

	private Photo[] setupMockData() {
		Photo photo = new Photo();
		photo.setId(1L);
		photo.setAlbumId(1L);
		photo.setTitle("title1");

		Photo photo2 = new Photo();
		photo2.setId(2L);
		photo2.setAlbumId(1L);
		photo2.setTitle("title1");

		Photo[] photos = new Photo[2];
		photos[0] = photo;
		photos[1] = photo2;
		return photos;
	}
}
