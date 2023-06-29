using UnityEngine;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

public static class SaveData 
{
    public static void SaveGameData()
    {
        BinaryFormatter formatter = new BinaryFormatter();
        string path = Application.persistentDataPath + "/data.cj";
        FileStream stream = new FileStream(path, FileMode.Create);

        formatter.Serialize(stream, new GameData(MyVariables.instance));
        stream.Close();
    }

    public static GameData LoadData()
    {
        string path = Application.persistentDataPath + "/data.cj";

        if(File.Exists(path))
        {
            BinaryFormatter formatter = new BinaryFormatter();
            FileStream stream = new FileStream(path, FileMode.Open);

            GameData data = formatter.Deserialize(stream) as GameData;
            stream.Close();

            return data;
        }
        
        return null;
    }
}
