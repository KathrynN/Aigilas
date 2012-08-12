package com.spx.core;
    public class Settings
    {
        private static final String __configPath = "settings.cfg";

        private static Settings __instance;
        public static Settings Get()
        {
            if (__instance == null)
            {
                __instance = new Settings();
            }
            return __instance;
        }

        private HashMap<String, String> _values = new HashMap<String, String>();
        private int _port;
        private String _serverIp;
        private boolean _clientVerbose;
        private boolean _serverVerbose;
        private boolean _messageContentsVerbose;
        private Settings()
        {
        }

        private boolean IsTrue(String value)
        {
            return value.equalsIgnoreCase("true");
        }

        public String Get(String key)
        {
            return _values.get(key);
        }

        public String GetIp()
        {
            return _serverIp;
        }

        public int GetPort()
        {
            return _port;
        }

        public boolean GetServerVerbose()
        {
            return _serverVerbose;
        }

        public boolean GetClientVerbose()
        {
            return _clientVerbose;
        }

        public boolean GetMessageContentsVerbose()
        {
            return _messageContentsVerbose;
        }
    }