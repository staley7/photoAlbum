package com.photoalbum;

import java.util.List;
import java.util.Scanner;

import org.springframework.web.client.RestTemplate;

/**
 * Main class to run photoAlbum program
 * 
 * @author Lance Staley
 *
 */
public class App {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpService service = new HttpServiceImpl();
		service.setRestTemplate(restTemplate);
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			System.out.println("Please enter a photo album id:");
			while (in.hasNext()) {
				if (in.hasNextInt()) {
					String next = in.next();
					handleInput(next, service);
				} else {
					String next = in.next();
					if (next.equals("exit")) {
						break;
					}
					System.out.println("Please enter a valid id:");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				in.close();
			}
		}

	}

	private static void handleInput(String next, HttpService service) {
		List<Photo> album = service.getPhotoAlbum(next);
		if (!album.isEmpty()) {
			System.out.println("> photo-album " + next);
			album.forEach(photo -> System.out.println(getPhotoString(photo)));
			System.out.println("Please enter the next photo album id:");
		}else {
			System.out.println("No matching photo albums with that id. Please enter a different photo album id:");
		}
	}

	/**
	 * Helper method to change photo into desired format
	 * 
	 * @param photo
	 * @return String, formatted string representation of a photo
	 */
	private static String getPhotoString(Photo photo) {
		StringBuilder builder = new StringBuilder("[");
		builder.append(photo.getId());
		builder.append("] ");
		builder.append(photo.getTitle());
		return builder.toString();
	}
}
