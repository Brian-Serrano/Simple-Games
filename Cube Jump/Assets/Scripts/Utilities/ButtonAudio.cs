using UnityEngine;
using UnityEngine.UI;

public class ButtonAudio : MonoBehaviour
{
    public void Start()
    {
        GetComponent<Button>().onClick.AddListener(MyVariables.instance.PlayAudio);
    }
}
