package imageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public abstract class ImageLoaderabstract {


    private BufferedImage loadedImage;
    private String filename;

    protected ImageLoaderabstract(String filename) {
        this.filename = filename;
        loadImage();
    }

    private void loadImage() {
        InputStream is1 = getClass().getResourceAsStream(filename);
        try {
            loadedImage = ImageIO.read(is1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    abstract void Imagemaker();

    public BufferedImage getLoadedImage() {
        return loadedImage;
    }

}
