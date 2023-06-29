using UnityEngine;

public class Ground1 : MonoBehaviour
{
    private float objectWidth;

    private Camera mainCamera;

    private void Start()
    {
        objectWidth = GetComponent<Renderer>().bounds.size.x;

        mainCamera = Camera.main;
    }

    private void Update()
    {
        Vector3 objectViewportPosition = mainCamera.WorldToViewportPoint(transform.position);

        float xPosition = objectWidth * 7;

        if (objectViewportPosition.x < -0.2f)
        {
            transform.position = new Vector3(transform.position.x + xPosition, transform.position.y, transform.position.z);
        }
    }
}
