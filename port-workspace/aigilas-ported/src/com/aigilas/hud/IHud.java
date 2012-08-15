package com.aigilas.hud;
    public class IHud
    {
        protected boolean _isVisible = false;
        protected ICreature _parent;
        protected static Texture2D _menuBase;
        protected TextHandler _textHandler = new TextHandler();
        protected List<Vector2> playerHudPositions = new ArrayList<Vector2>();
        protected Vector2 _dimensions;

        protected IHud(ICreature owner,int width,int height)
        {
            _parent = owner;
            if (_menuBase == null)
            {
                _menuBase = XnaManager.GetMenuBaseAsset();
            }
            _dimensions = new Vector2(width, height);
            playerHudPositions.add(new Vector2(0, 0));
            playerHudPositions.add(new Vector2(XnaManager.WindowWidth-_dimensions.X, 0));
            playerHudPositions.add(new Vector2(0,XnaManager.WindowHeight-_dimensions.Y));
            playerHudPositions.add(new Vector2(XnaManager.WindowWidth-_dimensions.X,XnaManager.WindowHeight-_dimensions.Y));
        }

        public void Toggle()
        {
            _isVisible = !_isVisible;
        }

        public boolean IsVisible()
        {
            return _isVisible;
        }

        public void LoadContent()
        {
            _menuBase = XnaManager.GetMenuBaseAsset();
        }

        protected Vector2 GetHudOrigin()
        {
            return playerHudPositions.get(_parent.GetPlayerIndex());
        }
    }