package xna.wrapper;import spx.core.GameManager;import com.badlogic.gdx.graphics.Texture;public class AssetManager {	private static final String assetPath = "assets";	private static AssetManager instance;	private static final String __spriteSheetName = "GameplaySheet.png";	public static AssetManager Get() {		if (instance == null) {			instance = new AssetManager();		}		return instance;	}	private SpriteSheet gameplaySheet;	private static String Graphic(String fileName) {		return assetPath + "/graphics/" + fileName;	}	private AssetManager() {		gameplaySheet = new SpriteSheet(Graphic("GameplaySheet.png"), 32, 32, 1);	}	public Texture GetImage(String fileName) {		return new Texture(Graphic(fileName));	}	public SpriteSheet GetSpriteSheet() {		return new SpriteSheet(GetImage(__spriteSheetName), GameManager.SpriteWidth, GameManager.SpriteHeight, 1);	}}