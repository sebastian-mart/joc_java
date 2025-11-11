package PaooGame.Camera;

public class Camera {
    private float xOffset;
    private int screenWidth;
    private int mapWidthPixels;

    public Camera(int screenWidth, int mapWidthPixels) {
        this.screenWidth = screenWidth;
        this.mapWidthPixels = mapWidthPixels;
        xOffset = 0;
    }

    public void update(float heroX) {
        xOffset = heroX - screenWidth / 2.0f;

        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset + screenWidth > mapWidthPixels) {
            xOffset = mapWidthPixels - screenWidth;
        }
    }

    public float getXOffset() {
        return xOffset;
    }
}
