package io.github.Kabooman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
    private TextureRegion[] frames;
    private float x, y;
    private float duracion = 0.6f; // tiempo visible
    private float tiempoActual = 0f;
    private boolean terminada = false;

    public Explosion(Texture explosionTexture, float x, float y) {
        this.x = x;
        this.y = y;

        frames = new TextureRegion[5]; // centro + 4 direcciones
        for (int i = 0; i < 5; i++) {
            frames[i] = new TextureRegion(explosionTexture, i * 32, 0, 32, 32);
        }
    }

    public void actualizar(float delta) {
        tiempoActual += delta;
        if (tiempoActual >= duracion) {
            terminada = true;
        }
    }

    public void render(SpriteBatch batch) {
        float size = 0.5f;

        // Centro
        batch.draw(frames[0], x, y, size, size);

        // Arriba
        batch.draw(frames[1], x, y + size, size, size);

        // Abajo
        batch.draw(frames[2], x, y - size, size, size);

        // Izquierda
        batch.draw(frames[3], x - size, y, size, size);

        // Derecha
        batch.draw(frames[4], x + size, y, size, size);
    }

    public boolean haTerminado() {
        return terminada;
    }
}
