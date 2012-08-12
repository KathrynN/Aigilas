package com.aigilas;
    public class EntryPoint
    {
        public static void main(String[] args)
        {
            Thread server = new Thread(new ServerThread());
            server.run();
            try {
            	game.Update();              
            Server.Get().Close();
            Client.Get().Close();
            Console.WriteLine("Finished being a client");
        }
    }