using UnityEngine;

public class DestroyObstacle : MonoBehaviour
{
    private Camera mainCamera;

    private void Start()
    {
        mainCamera = Camera.main;
    }

    private void Update()
    {
        Vector3 objectViewportPosition = mainCamera.WorldToViewportPoint(transform.position);

        if (objectViewportPosition.x < -0.2f)
        {
            Destroy(gameObject);
        }
    }
}
