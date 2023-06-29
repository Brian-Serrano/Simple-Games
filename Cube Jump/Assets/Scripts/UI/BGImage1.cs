using UnityEngine;
using UnityEngine.UI;

public class BGImage1 : MonoBehaviour
{
    public Sprite[] images;
    public Image imageComponent;
    public int selected;

    void Start()
    {
        if (gameObject.name == "BackgroundImage")
        {
            selected = MyVariables.instance.selectedBackground;
        }
        else if (gameObject.name == "GroundImage")
        {
            selected = MyVariables.instance.selectedGround;
        }
        else if (gameObject.name == "PlayerImage")
        {
            selected = MyVariables.instance.selectedPlayer;
        }

        imageComponent = GetComponent<Image>();
        imageComponent.sprite = images[selected];
    }

    public void Prev()
    {
        selected++;
        if(selected >= images.Length)
        {
            selected = 0;
        }
        imageComponent.sprite = images[selected];
    }

    public void Next()
    {
        selected--;
        if(selected < 0)
        {
            selected = images.Length - 1;
        }
        imageComponent.sprite = images[selected];
    }
}
