package io.github.Kabooman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Jugador jugador;
    private FitViewport viewport;
    private BombaManager bombaManager;
    private Mapa mapa;
    private OrthographicCamera camara;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camara = new OrthographicCamera();
        viewport = new FitViewport(15, 13, camara); // tamaño del mundo lógico
        camara.position.set(8 / 2f, 5 / 2f, 0);
        camara.update();

        mapa = new Mapa("nivel1.tmx");
        jugador = new Jugador("marroqui.png", 3, 2);
        bombaManager = new BombaManager();
        jugador.setBombaManager(bombaManager);
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camara.update();
        mapa.renderizar(camara);

        jugador.actualizar();
        bombaManager.actualizar(Gdx.graphics.getDeltaTime());
        limitarJugador();

        batch.setProjectionMatrix(camara.combined);
        batch.begin();
        jugador.render(batch);
        bombaManager.render(batch);
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
        jugador.dispose();
        bombaManager.dispose();
        mapa.dispose();
    }
}

