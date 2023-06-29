using UnityEngine;
using UnityEngine.SceneManagement;

public class ConfirmButton1 : MonoBehaviour
{
    public GameObject background;
    public GameObject ground;
    public GameObject player;

    public void ConfirmButton()
    {
        MyVariables.instance.selectedBackground = background.GetComponent<BGImage1>().selected;
        MyVariables.instance.selectedGround = ground.GetComponent<BGImage1>().selected;
        MyVariables.instance.selectedPlayer = player.GetComponent<BGImage1>().selected;
        SaveData.SaveGameData();
        SceneManager.LoadScene("Menu");
    }
}
