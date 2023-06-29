using UnityEngine;
using UnityEngine.UI;

public class Canvas1 : MonoBehaviour
{
    public Text text;

    void Update()
    {
        text.text = "Score: " + (int)(MyVariables.instance.score);
    }
}
