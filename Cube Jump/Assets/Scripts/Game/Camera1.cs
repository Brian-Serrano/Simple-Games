using UnityEngine;

public class Camera1 : MonoBehaviour
{
    public Transform target;

    void LateUpdate()
    {
        transform.position = target.transform.position + new Vector3(5, 1, -5);
    }
}
