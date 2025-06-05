package io.github.Kabooman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bomba {
    private float x, y;
    private float tiempoRestante = 2f; // segundos hasta explotar
    private boolean explotada = false;
    private Texture textura;
    private Texture texturaExplosion;
    private BombaManager bombaManager;

    public Bomba(float x, float y, Texture textura, Texture texturaExplosion, BombaManager manager) {
        this.x = x;
        this.y = y;
        this.textura = textura;
        this.texturaExplosion = texturaExplosion;
        this.bombaManager = manager;
    }

    public void actualizar(float delta) {
        if (explotada) return;

        tiempoRestante -= delta;
        if (tiempoRestante <= 0) {
            explotar();
        }
    }

    private void explotar() {
        explotada = true;
        bombaManager.agregarExplosion(x, y); // centro
    }

    public void render(SpriteBatch batch) {
        if (!explotada) {
            batch.draw(textura, x, y, 0.5f, 0.5f);
        }
    }

    public boolean haExplotado() {
        return explotada;
    }
}
