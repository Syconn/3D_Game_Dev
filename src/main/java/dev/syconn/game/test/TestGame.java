package dev.syconn.game.test;

import dev.syconn.game.Launcher;
import dev.syconn.game.core.Loader;
import dev.syconn.game.core.RenderHandler;
import dev.syconn.game.core.Window;
import dev.syconn.game.core.entity.Model;
import dev.syconn.game.util.ILogic;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class TestGame implements ILogic {

    private int direction = 0;
    private float color = 0.0f;

    private final RenderHandler renderer;
    private final Loader loader;
    private final Window window;

    private Model model;

    public TestGame() {
        this.renderer = new RenderHandler();
        this.window = Launcher.getWindow();
        this.loader = new Loader();
    }

    public void init() throws Exception {
        renderer.init();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        model = loader.loadModel(vertices, indices);
    }

    public void input() {
        if (window.isKeyPressed(GLFW.GLFW_KEY_UP))
            direction = 1;
        else if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN))
            direction = -1;
        else
            direction = 0;
    }

    public void update() {
        color += direction * 0.01f;
        if (color > 1)
            color = 1.0f;
        else if (color <= 0)
            color = 0.0f;
    }

    public void render() {
        if (window.isResize()) {
            GL11.glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResize(true);
        }

        window.setClearColor(color, color, color, 0.0f);
        renderer.render(model);
    }

    public void cleanup() {
        renderer.cleanup();
        loader.cleanup();
    }
}
