package Objekts;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;


public class SnakeCK {
	ArrayList<Point2D> body;
	int speed=1; //multiplier for speed
	int direction=1;//1=left, 2=right, 3=up, 4=down
	BufferedImage tiles[]=new BufferedImage[16];
		
	public SnakeCK(Point2D p,int dir) {
		this.body.add(p);
		this.direction=dir;
		this.initializeTiles();
	}
	
	private void initializeTiles() {
		BufferedImage  image;
		InputStream is1 = getClass().getResourceAsStream("/res/snake-graphics32.png");
        try {
        	  image = ImageIO.read(is1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int columns=5;
        int rows=4;
        int subsize=32;
        int current_img = 0;
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                // Creating sub image
                tiles[current_img] = new BufferedImage(subsize, subsize, image.getType());
                Graphics2D img_creator = tiles[current_img].createGraphics();

                // coordinates of source image
                int src_first_x = subsize * j;
                int src_first_y = subsize * i;

                // coordinates of sub-image
                int dst_corner_x = subsize * j + subsize;
                int dst_corner_y = subsize * i + subsize;
                
                img_creator.drawImage(image, 0, 0, subsize, subsize, src_first_x, src_first_y, dst_corner_x, dst_corner_y, null);
                current_img++;
            }
        }
	}

	public void draw(Graphics g) {
		for (int i = 0; i < 20; i++) {
			g.drawImage(tiles[i], 32*i,0,null);
		}
	}
	
	public void setSpeed(int i) {
		this.speed=i;
	}
}
