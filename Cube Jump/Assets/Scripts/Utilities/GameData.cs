[System.Serializable]
public class GameData 
{
    public int highScore;
    public int selectedBackground;
    public int selectedGround;
    public int selectedPlayer;

    public GameData(MyVariables data)
    {
        highScore = data.highScore;
        selectedBackground = data.selectedBackground;
        selectedGround = data.selectedGround;
        selectedPlayer = data.selectedPlayer;
    }
}
