using UnityEngine;

public class Sprites : MonoBehaviour
{

    public Sprite[] sprites;
    public SpriteRenderer spriteComponent;
    public int selected;

    public void Start()
    {
        if (gameObject.name == "Background" || gameObject.name == "Background 0" || gameObject.name == "Background 1" || gameObject.name == "Background 2")
        {
            selected = MyVariables.instance.selectedBackground;
        }
        else if (gameObject.tag == "Ground")
        {
            selected = MyVariables.instance.selectedGround;
        }
        else if (gameObject.name == "CubeSprite")
        {
            selected = MyVariables.instance.selectedPlayer;
        }

        spriteComponent = GetComponent<SpriteRenderer>();
        spriteComponent.sprite = sprites[selected];
    }

}
