using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayButton1 : MonoBehaviour
{
    public void PlayButton()
    {
        SceneManager.LoadScene("Game");
    }
}
