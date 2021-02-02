using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Net.Sockets;
using UnityEngine;

public class Otuken : MonoBehaviour
{
    private bool socketReady = false;
    private TcpClient socket;
    private NetworkStream stream;
    private StreamReader reader;
    private StreamWriter writer;

    public float x;
    public float y;
    public float z;

    private void Start()
    {
        if (socketReady)
            return;
        string host = "192.168.1.105";
        int port = 123;
        try
        {
            socket = new TcpClient(host, port);
            socketReady = true;
            stream = socket.GetStream();
            writer = new StreamWriter(stream);
            reader = new StreamReader(stream);
            writer.WriteLine("OTUKEN");
            writer.Flush();
            
        }
        catch(Exception e)
        {
            Debug.Log("Soket Hatası : " + e.Message);
        }
    }

    private void Update()
    {
        if (socketReady)
        {
            if (stream.DataAvailable)
            {
                string data = reader.ReadLine();
                if(data != null)
                {
                    string[] dataArray = data.Split('a');
                    OnIncomingData(dataArray[1], dataArray[2], dataArray[3]);
                }
            }
        }
               
    }

    private void OnIncomingData(string a, string b, string c)
    {
        Debug.Log(a+"--"+b+"--"+c);
        x = int.Parse(a);
        y = int.Parse(b);
        z = int.Parse(c);

        transform.localEulerAngles = new Vector3(-x, -y, -z);


    }

}
