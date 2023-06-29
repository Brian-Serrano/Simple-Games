using UnityEngine;
using UnityEngine.SceneManagement;

public class RestartPause1 : MonoBehaviour
{
    public void RestartPause()
    {
        MyVariables.instance.score = 0;
        MyVariables.instance.obstacleCount = 0;
        SceneManager.LoadScene("Game");
        Time.timeScale = 1f;
    }
}