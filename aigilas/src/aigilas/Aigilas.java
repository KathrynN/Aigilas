package aigilas;

import aigilas.hud.HudRenderer;
import aigilas.net.Client;
import aigilas.net.LocalClient;
import aigilas.states.MainMenuState;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import sps.audio.MusicPlayer;
import sps.bridge.Bridge;
import sps.bridge.Commands;
import sps.bridge.SpriteTypes;
import sps.bridge.Spx;
import sps.core.DevConsole;
import sps.core.Logger;
import sps.graphics.Renderer;
import sps.graphics.SpriteSheetManager;
import sps.io.Input;
import sps.particles.ParticleEngine;
import sps.states.StateManager;
import sps.text.TextPool;

public class Aigilas implements ApplicationListener {
    private boolean IsRunning = true;

    private void setIsRunning(boolean isRunning) {
        IsRunning = isRunning;
    }

    @Override
    public void create() {
        Bridge.get();
        Spx.setup();
        Client.reset(new LocalClient());
        Input.setup(Client.get());
        SpriteSheetManager.setup(SpriteTypes.getDefs());
        StateManager.loadState(new MainMenuState());
        ParticleEngine.reset();
        MusicPlayer.get(new DefaultMusicPlayer());
        StateManager.loadContent();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        try {
            //$$$ Logger.devConsole("" + Gdx.graphics.getFramesPerSecond() + ": " + Gdx.graphics.getDeltaTime());

            // Update
            Input.update();
            if (Input.isActive(Commands.get(Common.Commands.ToggleDevConsole), Client.get().getFirstPlayerIndex())) {
                DevConsole.get().toggle();
            }
            if (Input.isActive(Commands.get(Common.Commands.Back), Client.get().getFirstPlayerIndex())) {
                Gdx.app.exit();
            }
            if (Input.isActive(Commands.get(Common.Commands.Music), Client.get().getFirstPlayerIndex())) {
                MusicPlayer.get().toggle();
            }
            if(Input.isActive(Commands.get(Common.Commands.ToggleFullScreen),Client.get().getFirstPlayerIndex())){
                Renderer.get().toggleFullScreen();
            }
            if (Client.get().nextTurn()) {
                ParticleEngine.update();
                StateManager.update();
                Client.get().prepareForNextTurn();
            }
            else {
                Client.get().heartBeat();
            }
            if (!IsRunning) {
                System.exit(0);
            }

            TextPool.get().update();

            // Render
            Renderer.get().begin();
            StateManager.draw();
            ParticleEngine.draw();
            HudRenderer.get().draw();
            TextPool.get().draw();
            DevConsole.get().draw();
            Renderer.get().end();
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}
