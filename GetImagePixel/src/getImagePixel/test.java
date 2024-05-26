package getImagePixel;

import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class test {
	public static void main(String[] args){
		File imgf = new File("res/source.png");

		BufferedImage img = new BufferedImage(20, 10, BufferedImage.TYPE_INT_ARGB);
		try {
			img = ImageIO.read(imgf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int width = img.getWidth();

		int height = img.getHeight();

		int[] pixels=new int[width*height];

		PixelGrabber grab = new PixelGrabber(img, 0, 0, width, height, pixels, 0,width);

		try {
			grab.grabPixels();
		} catch (InterruptedException e) {
		}
		int[][] picture=new int[width][height];

		for(int i=0;i<pixels.length;i++)
			picture[i%width][i/width]=pixels[i];
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				System.out.print((picture[j][i] + 1 <0 ? 1:0));
			}
			System.out.println();
		}
	}
}
