package imageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * The ImageLoaderabstract class is responsible for loading images.
 * <p>
 * This class takes a filename as input and loads the corresponding image.
 * </p>
 *
 * @author
 */

public class ImageLoaderabstract {


    private BufferedImage loadedImage;
    private String filename;

    /**
     * This constructor initializes a new instance of the ImageLoaderabstract class.
     *
     * @param filename This is the name of the file to be loaded.
     */

    public ImageLoaderabstract(String filename) {
        this.filename = filename;
        loadImage();
    }

    /**
     * This method is used to load an image.
     */

    private void loadImage() {
        InputStream is1 = getClass().getResourceAsStream(filename);
        try {
            loadedImage = ImageIO.read(is1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is used to get the loaded image.
     *
     * @return BufferedImage This returns the loaded image.
     */

    public BufferedImage getLoadedImage() {
        return loadedImage;
    }

}
