package io.github.Kabooman;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BombaManager {
    private List<Bomba> bombas;
    private List<Explosion> explosiones;
    private Texture texturaBomba;
    private Texture texturaExplosion;

    public BombaManager() {
        bombas = new ArrayList<>();
        explosiones = new ArrayList<>();
        texturaBomba = new Texture("bomba.png");
        texturaExplosion = new Texture("explosion.png");
    }

    public void colocarBomba(float x, float y) {
        bombas.add(new Bomba(x, y, texturaBomba, texturaExplosion, this));    }

    public void agregarExplosion(float x, float y) {
        explosiones.add(new Explosion(texturaExplosion, x, y));
    }

    public void actualizar(float delta) {
        Iterator<Bomba> it = bombas.iterator();
        while (it.hasNext()) {
            Bomba bomba = it.next();
            bomba.actualizar(delta);
            if (bomba.haExplotado()) {
                it.remove();
            }
        }
        Iterator<Explosion> itE = explosiones.iterator();
        while (itE.hasNext()) {
            Explosion e = itE.next();
            e.actualizar(delta);
            if (e.haTerminado()) {
                itE.remove();
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Bomba b : bombas) {
            b.render(batch);
        }

        for (Explosion e : explosiones) {
            e.render(batch);
        }
    }

    public void dispose() {
        texturaBomba.dispose();
        texturaExplosion.dispose();
    }
}

