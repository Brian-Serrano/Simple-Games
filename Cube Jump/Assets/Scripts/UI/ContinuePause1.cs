using UnityEngine;

public class ContinuePause1 : MonoBehaviour
{
    public GameObject pauseCanvas;
    public GameObject gameCanvas;
    public GameObject gameMusic;

    public void ContinuePause()
    {
        pauseCanvas.SetActive(false);
        gameCanvas.SetActive(true);
        Time.timeScale = 1f;
        gameMusic.GetComponent<AudioSource>().Play();
    }
}
