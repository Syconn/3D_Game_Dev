package dev.syconn.game.core;

import dev.syconn.game.Launcher;
import dev.syconn.game.core.entity.Model;
import dev.syconn.game.util.Utils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class RenderHandler {

    private final Window window;
    private Shaders shader;

    public RenderHandler() {
        window = Launcher.getWindow();
    }

    public void init() throws Exception {
        shader = new Shaders();
        shader.createVertexShader(Utils.loadResource("/shaders/vertex.vsh"));
        shader.createFragmentShader(Utils.loadResource("/shaders/fragment.fsh"));
        shader.link();
    }

    public void render(Model model) {
        clear();
        shader.bind();
        GL30.glBindVertexArray(model.getId());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.getVertexCount());
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.unbind();
    }

    public void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void cleanup() {
        shader.cleanup();
    }
}
