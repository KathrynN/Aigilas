package lidgren.wrapper; import com.badlogic.gdx.graphics.Texture;  import com.badlogic.gdx.graphics.Color;import java.util.List;public class NetServer {	public int ConnectionsCount;	public List<NetConnection> Connections;	public NetServer(NetPeerConfiguration _config) {		// TODO Auto-generated constructor stub	}	public void Start() {		// TODO Auto-generated method stub	}	public NetIncomingMessage ReadMessage() {		// TODO Auto-generated method stub		return null;	}	public void Recycle(NetIncomingMessage _message) {		// TODO Auto-generated method stub	}	public NetOutgoingMessage CreateMessage(int bytecount) {		// TODO Auto-generated method stub		return null;	}	public void SendMessage(NetOutgoingMessage _reply, NetConnection target,			NetDeliveryMethod reliableordered, int channel) {		// TODO Auto-generated method stub	}	public void SendMessage(NetOutgoingMessage _announcement,			List<NetConnection> connections, NetDeliveryMethod reliableordered,			int channel) {		// TODO Auto-generated method stub	}	public void Shutdown(String string) {		// TODO Auto-generated method stub	}}