package io.github.Kabooman;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Mapa {
    private TiledMap mapa;
    private OrthogonalTiledMapRenderer renderer;

    public Mapa(String archivo) {
        mapa = new TmxMapLoader().load(archivo);

        // Cada tile es de 64x64 píxeles, así que usamos esta escala
        float escala = 1f / 64f;
        renderer = new OrthogonalTiledMapRenderer(mapa, escala);
    }

    public void renderizar(OrthographicCamera camara) {
        renderer.setView(camara);
        renderer.render();
    }

    public void dispose() {
        mapa.dispose();
    }
}


