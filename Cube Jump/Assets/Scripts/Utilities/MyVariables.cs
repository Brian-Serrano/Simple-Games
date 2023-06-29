
using UnityEngine;

public class MyVariables : MonoBehaviour
{
    [HideInInspector] public static MyVariables instance;

    // Saveable Data
    [HideInInspector] public int highScore;
    [HideInInspector] public int selectedBackground;
    [HideInInspector] public int selectedGround;
    [HideInInspector] public int selectedPlayer;

    [HideInInspector] public int obstacleCount = 0;
    [HideInInspector] public float score = 0;

    private GameData data;

    public void PlayAudio()
    {
        GetComponent<AudioSource>().Play(); ;
    }

    private void Awake()
    {
        if (instance == null)
        {
            instance = this;
        }
        else
        {
            Destroy(gameObject);
        }

        DontDestroyOnLoad(gameObject);

        data = SaveData.LoadData();

        if(data != null)
        {
            highScore = data.highScore;
            selectedBackground = data.selectedBackground;
            selectedGround = data.selectedGround;
            selectedPlayer = data.selectedPlayer;
        }
        else
        {
            highScore = 0;
            selectedBackground = 0;
            selectedGround = 0;
            selectedPlayer = 0;
        }
    }
}
