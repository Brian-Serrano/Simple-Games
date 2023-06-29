using UnityEngine;

public class Cube2 : MonoBehaviour
{
    public float speed = 5.0f;
    public GameObject obstacle;
    public GameObject block;
    public GameObject grounds;
    public GameObject ground;
    public GameObject[] background;
    private float obstacleCreation = 0;
    private Vector3 obstacleSize;
    private Vector3 groundSize;

    void Start()
    {
        obstacleSize = block.GetComponent<Renderer>().bounds.size;
        groundSize = ground.GetComponent<Renderer>().bounds.size;
        Instantiate(obstacle, new Vector3(((obstacleSize.x * 36) / 2) + (MyVariables.instance.obstacleCount * (obstacleSize.x * 36)), ground.transform.position.y + (obstacleSize.y * 6) + (groundSize.y / 2), 0), Quaternion.identity);
    }

    void Update()
    {
        transform.position += Vector3.right * speed * Time.deltaTime;
        obstacleCreation += 1 * speed * Time.deltaTime;
        MyVariables.instance.score += 1 * speed * Time.deltaTime;
        for(int i = 0; i < background.Length; i++)
        {
            background[i].transform.position += (Vector3.right * speed * Time.deltaTime) / 2;
        }

        if (obstacleCreation >= obstacleSize.x * 36)
        {
            Instantiate(obstacle, new Vector3(((obstacleSize.x * 36) / 2) + (MyVariables.instance.obstacleCount * (obstacleSize.x * 36)), ground.transform.position.y + (obstacleSize.y * 6) + (groundSize.y / 2), 0), Quaternion.identity);
            obstacleCreation = 0;
        }
    }
}
