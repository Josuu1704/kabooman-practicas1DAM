package io.github.Kabooman;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Jugador {
    private Texture textura;
    private float x, y;
    private float velocidad = 1f; // Unidades por segundo
    private float ancho = 0.5f, alto = 0.5f; // 1 unidad lógica (no píxeles)
    private boolean espacioPresionado = false;
    private BombaManager bombaManager;


    public void setBombaManager(BombaManager bombaManager){
        this.bombaManager = bombaManager;
    }

    public Jugador(String rutaImagen, float x, float y) {
        this.textura = new Texture(rutaImagen);
        this.x = x;
        this.y = y;
    }

    public void actualizar() {
        float delta = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))  x -= velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.UP))    y += velocidad * delta;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN))  y -= velocidad * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (!espacioPresionado && bombaManager != null) {
                bombaManager.colocarBomba(x + ancho / 2, y + alto / 2);
                espacioPresionado = true;
            }
        } else {
            espacioPresionado = false;
        }


    }

    public void render(SpriteBatch batch) {

        batch.draw(textura, x, y, ancho, alto);
    }

    public void dispose() {

        textura.dispose();
    }

    // Getters y setters
    public float getX() {

        return x;
    }
    public float getY() {

        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getWidth() {

        return ancho;
    }
    public float getHeight() {

        return alto;
    }
}
