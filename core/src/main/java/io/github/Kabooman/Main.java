package io.github.Kabooman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private Jugador jugador;
    private FitViewport viewport;
    private BombaManager bombaManager; // NUEVO

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("mapa.jpg"); // Fondo
        jugador = new Jugador("marroqui.png", 3, 2); // Posición dentro del mundo
        bombaManager = new BombaManager(); // NUEVO
        jugador.setBombaManager(bombaManager); // NUEVO
        viewport = new FitViewport(8, 5); // Mundo lógico 8x5
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        float delta = Gdx.graphics.getDeltaTime();
        jugador.actualizar();
        bombaManager.actualizar(delta); // NUEVO
        limitarJugador();

        batch.begin();
        batch.draw(image, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight()); // Fondo
        jugador.render(batch); // Jugador encima
        bombaManager.render(batch); // NUEVO
        batch.end();
    }

    private void limitarJugador() {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float jugadorWidth = jugador.getWidth();
        float jugadorHeight = jugador.getHeight();

        jugador.setX(MathUtils.clamp(jugador.getX(), 0, worldWidth - jugadorWidth));
        jugador.setY(MathUtils.clamp(jugador.getY(), 0, worldHeight - jugadorHeight));
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        jugador.dispose();
        bombaManager.dispose(); // NUEVO
    }
}
