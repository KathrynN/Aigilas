package aigilas.net;

import aigilas.Config;
import sps.bridge.Command;
import sps.bridge.Commands;
import sps.core.DevConsole;
import sps.core.Logger;
import sps.core.RNG;
import sps.core.Settings;

import java.net.Socket;
import java.util.HashMap;

public class LanClient implements IClient {
    // Client <-> Server
    private Message _message;
    private MessageHandler _comm;
    private int _heartBeat = 30;

    // Client <-> Game
    private Integer _initialPlayerIndex;
    private boolean _isGameStarting;
    private boolean _dungeonHasLoaded = false;
    private boolean _isConnected;
    private final HashMap<Integer, HashMap<Command, Boolean>> _playerStatus = new HashMap<Integer, HashMap<Command, Boolean>>();

    public LanClient() {
        for (int ii = 0; ii < Message.PlayerMax; ii++) {
            _playerStatus.put(ii, new HashMap<Command, Boolean>());
            for (Command command : Commands.values()) {
                _playerStatus.get(ii).put(command, false);
            }
        }
        try {
            Socket server = new Socket(Config.get().serverIp, Config.get().port);
            _comm = new MessageHandler(server);
            _comm.owner = "CLIENT";
            sendMessage(Message.createInit(0, 0));
            awaitReply(MessageTypes.Connect);
            handleResponse(_message);
        }
        catch (Exception e) {
            Logger.exception(e);
        }
    }

    // Client <-> Game communication
    public boolean isGameStarting() {
        return _isGameStarting;
    }

    public boolean isConnected() {
        return _isConnected;
    }

    public void heartBeat() {
        if (!_dungeonHasLoaded) {
            _heartBeat--;
            if (_heartBeat <= 0) {
                sendMessage(Message.createHeartBeat());
                _heartBeat = 15;
            }
        }
    }

    public void dungeonHasLoaded() {
        _dungeonHasLoaded = true;
    }

    public boolean nextTurn() {
        update();
        if (_message != null) {
            if (_message.MessageType == MessageTypes.Sync_State) {
                RNG.seed(_message.RngSeed);
                _heartBeat = 15;
            }
            return _message.MessageType == MessageTypes.Sync_State;
        }
        return false;
    }

    private void initPlayer(int playerIndex, Command command) {
        if (!_playerStatus.containsKey(playerIndex)) {
            _playerStatus.put(playerIndex, new HashMap<Command, Boolean>());
        }
        if (!_playerStatus.get(playerIndex).containsKey(command)) {
            _playerStatus.get(playerIndex).put(command, false);
        }
    }

    public int getFirstPlayerIndex() {
        return _initialPlayerIndex;
    }

    public void prepareForNextTurn() {
        sendMessage(Message.createReadyForNextTurn());
    }

    // Client <-> Server communication
    public boolean isActive(Command command, int playerIndex) {
        if (_playerStatus.containsKey(playerIndex) && _playerStatus.get(playerIndex).containsKey(command)) {
            return _playerStatus.get(playerIndex).get(command);
        }
        return false;
    }

    public void setState(Command command, int playerIndex, boolean isActive) {
        initPlayer(playerIndex, command);
        if (_playerStatus.get(playerIndex).get(command) != isActive) {
            sendMessage(Message.createMovement(command, playerIndex, isActive));
        }
    }

    int _playerCount = 0;

    public int getPlayerCount() {
        if (_playerCount == 0) {
            sendMessage(Message.createPlayerCount(0));
            awaitReply(MessageTypes.Player_Count);
            _playerCount = _message.PlayerCount;
        }
        return _playerCount;
    }

    public void startGame() {
        sendMessage(Message.create(MessageTypes.Start_Game));
    }

    private void sendMessage(Message contents) {
        contents.PlayerIndex = _initialPlayerIndex;
        contents.LocalPort = _comm.getLocalPort();
        _comm.sendOutboundMessage(contents);
    }

    // If the server doesn't reply at some point with the messageType you expect
    // Then the client will hang in an infinite loop.
    private void awaitReply(MessageTypes messageType) {
        while (true) {
            _message = _comm.readInboundMessage();
            if (_message != null) {
                if (_message.MessageType == messageType) {
                    return;
                }
                else {
                    handleResponse(_message);
                }
            }
        }
    }

    public void update() {
        _message = _comm.readInboundMessage();
        if (_message != null) {
            handleResponse(_message);
        }
    }

    private void handleResponse(Message contents) {
        switch (contents.MessageType) {
            case Connect:
                RNG.seed(contents.RngSeed);
                _initialPlayerIndex = (int) contents.PlayerCount;
                _isConnected = true;
                break;
            case Start_Game:
                _isGameStarting = true;
                break;
            case Sync_State:
                contents.readPlayerState(_playerStatus);
                break;
            default:
                break;
        }
    }

    public void close() {
        Logger.info("Shutting down a client.");
    }
}