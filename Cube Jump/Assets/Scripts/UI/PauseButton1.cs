using UnityEngine;

public class PauseButton1 : MonoBehaviour
{
    public GameObject pauseCanvas;
    public GameObject gameCanvas;
    public GameObject gameMusic;

    public void PauseButton()
    {
        pauseCanvas.SetActive(true);
        gameCanvas.SetActive(false);
        Time.timeScale = 0f;
        gameMusic.GetComponent<AudioSource>().Pause();
    }
}
