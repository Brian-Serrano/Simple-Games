using UnityEngine;
using UnityEngine.UI;

public class Canvas2 : MonoBehaviour
{
    public Text text;

    void Start()
    {
        text.text = "Highscore: " + MyVariables.instance.highScore;
    }
}
